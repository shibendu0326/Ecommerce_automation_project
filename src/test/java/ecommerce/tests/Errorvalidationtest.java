package ecommerce.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ecommerce.pageobjects.Landingpage;
import ecommerce.pageobjects.cartpage;
import ecommerce.pageobjects.productcatalogue;
import ecommerce.test_components.Base_test;

public class Errorvalidationtest extends Base_test {

	
	// Negative Scenario

	@Test(groups={"Errorhandling"},retryAnalyzer=ecommerce.test_components.Retry.class)
	public void testnegativelogin() throws IOException {

		{
			
			landingpage.LoginApplication("dawd", "dwad");
			landingpage.geterrormessage();
			Assert.assertEquals("Epic sadface: Userndawame and password do not match any user in this service", landingpage.geterrormessage());
			System.out.println("Login UnSccessful");
			

		}

	}

	@Test()
	public void testnegativeproduct() throws InterruptedException, IOException {
		{
			
			productcatalogue productcatalogue =landingpage.LoginApplication("standard_user", "secret_sauce");
			List<WebElement> products = productcatalogue.getproductlist();
			productcatalogue.addproducttocart();
			cartpage cartpage =productcatalogue.gotocart();
			Boolean match = cartpage.verifyproductinCart("dwdad");
			Assert.assertFalse(match);	
			System.out.println("Product did not match");
		

			
			
		}
		
}
}
