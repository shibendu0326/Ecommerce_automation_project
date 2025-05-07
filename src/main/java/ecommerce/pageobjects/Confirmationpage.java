package ecommerce.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.Abstractcomponents;

public class Confirmationpage extends Abstractcomponents {

	WebDriver driver;
	public Confirmationpage(WebDriver driver) 
    {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	
	}
	
	@FindBy(className="complete-header")
	WebElement confirmationmessage;
	
	@FindBy(className="title")
	WebElement checkoutconfirmation;
	
	public String getconfirmationmessage() 
	{
		return confirmationmessage.getText();
	
	}
	
	public String getcheckoutconfirmation() 
	{
		return checkoutconfirmation.getText();

	}
}
