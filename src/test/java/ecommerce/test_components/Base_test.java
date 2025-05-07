package ecommerce.test_components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerce.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Base_test {

	
		
		public WebDriver driver;
		public Landingpage landingpage;
		
		
		@BeforeMethod
		public WebDriver Intializedriver() throws IOException 
		{ 
		
		//Set global properties	
		Properties prop = new Properties();
		//FileInputStream constructs an input stream for the file to pass into the properties object
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ecommerce\\resources\\Globalproperties.properties");
		prop.load(fis); //reads the properties file
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser"); //get the browser name from the properties file
		//prop.getProperty("browser"); //get the browser name from the properties file);
		
		
		// Initialize WebDriver
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")){
			options.addArguments("headless");
			}		
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen

		} else if (browserName.equalsIgnoreCase("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// Firefox
		} 
		else if (browserName.equalsIgnoreCase("edge"))
		{
			// Edge
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		return driver;
		
		}
		
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
		
		//read json to string
		String jsonContent = 	FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to HashMap- Jackson Datbind
		
		ObjectMapper mapper = new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
		}
		
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
			
			
		}
		
		@BeforeMethod(alwaysRun = true)
		public Landingpage launchapplication() throws IOException  
		{
			driver = Intializedriver();
			Landingpage landingpage = new Landingpage(driver);
			landingpage.gotoURL();
			return landingpage;
		}
		
		@AfterMethod(alwaysRun = true)
		public void teardown() 
		{
			driver.close();
		}
	}