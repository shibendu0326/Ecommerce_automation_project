package ecommerce.pageobjects;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.Abstractcomponents;

public class checkoutpage extends Abstractcomponents {
	
	WebDriver driver;

	public checkoutpage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="first-name")
	WebElement firstname;
	
	@FindBy(id="last-name")
	WebElement lastname;
	
	@FindBy(id="postal-code")
	WebElement postalcode;
	
	@FindBy(id="continue")
	WebElement continuebutton;
	
	@FindBy(id="finish")
	WebElement finishbutton;
	
	public void entercheckoutdetails(String firstname, String lastname, String postalcode) 
	{
		
		this.firstname.sendKeys(firstname);
		this.lastname.sendKeys(lastname);
		this.postalcode.sendKeys(postalcode);
		continuebutton.click();
		

	}
	
	public Confirmationpage submitorder() 
	{
		finishbutton.click();
		Confirmationpage confirmationpage = new Confirmationpage(driver);
		return confirmationpage;
	}
	
	
	

}
