package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecommerce.pageobjects.cartpage;

public class Abstractcomponents {
	
	
	
	WebDriver driver;
	public Abstractcomponents(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(className="shopping_cart_link")
	WebElement cart;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement menu;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logout;
	
	public cartpage gotocart() 
	{
      cart.click();
      cartpage cartpage = new cartpage(driver);
      return cartpage;
    }
	
	
	public void waitforElementToAppear(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

	}
	public void waitforWebElementToAppear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findby));

	}
	public void waitforWebElementToDisappear(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(findby));

	}

}
