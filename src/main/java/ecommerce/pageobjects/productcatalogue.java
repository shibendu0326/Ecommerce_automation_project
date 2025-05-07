package ecommerce.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.Abstractcomponents;

public class productcatalogue extends Abstractcomponents {
	
	
	WebDriver driver;
	
	public productcatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(className="inventory_list")
	List<WebElement> products;
	
	By productsBy = By.className("inventory_item_name");
	
	

	
	public  List<WebElement> getproductlist() 
	{
		waitforElementToAppear(productsBy);
        return products;
	}
	
	
	public void addproducttocart() throws InterruptedException 
	{
		Thread.sleep(2000);
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
	}
	
	
	
	

}
