package ecommerce.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.Abstractcomponents;

public class Landingpage extends Abstractcomponents {
	
	
	WebDriver driver;
	
	public Landingpage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "user-name")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement passwordElement;
	
	@FindBy(id = "login-button")	
	WebElement loginbutton;
	
	@FindBy(xpath = "(//div[@class='error-message-container error'])[1]")
	WebElement errormessage;
	
	public productcatalogue LoginApplication(String email, String password) 
	{
		username.sendKeys(email);
		passwordElement.sendKeys(password);
		loginbutton.click();
		productcatalogue productcatalogue = new productcatalogue(driver);
		return productcatalogue;

	}
	
	public void gotoURL() 
	{
		driver.get("https://www.saucedemo.com/");
	}
	
	public String geterrormessage() 
	{
		waitforWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	
	
	

}
