package ecommerce.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import ecommerce.pageobjects.Landingpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EndToEndtest {

	public WebDriver driver;
	
	String baseUrl = "https://www.saucedemo.com/";
	String productName = "Sauce Labs Bolt T-Shirt";
	
	
	public void setup() 
	{ 
		// Initialize WebDriver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl);
		Landingpage lp = new Landingpage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


	}

	// Positive Scenario
	
	public void testpositivelogin() throws InterruptedException {
		{
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.id("login-button")).click();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String LogoText = driver.findElement(By.className("app_logo")).getText();

		Assert.assertEquals(LogoText, "Swag Labs");
		System.out.println("Login Sccessful");

		// Add items to cart
		List<WebElement> products = driver.findElements(By.className("inventory_list"));
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.className("shopping_cart_link")).click();
		List<WebElement> cartitems = driver.findElements(By.className("inventory_item_name"));
		Boolean match=cartitems.stream().anyMatch(cartitem -> cartitem.getText().contains(productName));
		Assert.assertTrue(match);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("checkout"))));
		driver.findElement(By.id("checkout")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("first-name")).sendKeys("John");
		driver.findElement(By.id("last-name")).sendKeys("Doe");
		driver.findElement(By.id("postal-code")).sendKeys("12345");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("finish")).click();
		String confirmationMessage = driver.findElement(By.className("complete-header")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thank you for your order!"));
		System.out.println("Order placed successfully!");

		
		 //Logout 
		 driver.findElement(By.id("react-burger-menu-btn")).click();
		 driver.findElement(By.id("logout_sidebar_link")).click(); 
		 driver.close();
		 

		
	}

}
