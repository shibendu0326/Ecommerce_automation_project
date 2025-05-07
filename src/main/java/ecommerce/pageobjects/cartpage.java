package ecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import abstractcomponents.Abstractcomponents;

public class cartpage extends Abstractcomponents {

	WebDriver driver;
	
	public cartpage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(className="inventory_item_name")
	List<WebElement> cartitems;
	

	
	public Boolean verifyproductinCart(String productName) 
	{
		
		Boolean match=cartitems.stream().anyMatch(cartitem -> cartitem.getText().contains(productName));
		return match;
	}
	
	public checkoutpage clickcheckout() 
	{
		waitforElementToAppear(By.id("checkout"));
		driver.findElement(By.id("checkout")).click();
		checkoutpage checkoutpage = new checkoutpage(driver);
		return checkoutpage;
	}

}
