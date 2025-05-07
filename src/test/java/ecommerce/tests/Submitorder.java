package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.pageobjects.Confirmationpage;
import ecommerce.pageobjects.Landingpage;
import ecommerce.pageobjects.cartpage;
import ecommerce.pageobjects.checkoutpage;
import ecommerce.pageobjects.productcatalogue;
import ecommerce.test_components.Base_test;

public class Submitorder extends Base_test {

	public WebDriver driver;

	

	// Positive Scenario
	@Test(dataProvider = "getData", groups = "Purchase")
	public void endtoend(HashMap<String,String> input) throws InterruptedException, IOException 
	{
		
			
			productcatalogue productcatalogue = landingpage.LoginApplication(input.get("username"), input.get("password"));
			List<WebElement> products = productcatalogue.getproductlist();
			productcatalogue.addproducttocart();
			cartpage cartpage = productcatalogue.gotocart();
			Boolean match = cartpage.verifyproductinCart(input.get("productname"));
			Assert.assertTrue(match);
			checkoutpage checkoutpage = cartpage.clickcheckout();
			checkoutpage.entercheckoutdetails("John", "Doe", "12345");
			Confirmationpage confirmationPage = checkoutpage.submitorder();
			String confirmationMessage = confirmationPage.getconfirmationmessage();
			Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thank you for your order!"));
	}

	@Test(dependsOnMethods = "endtoend",dataProvider = "getData")
	public void checkoutconfirmation(HashMap<String,String> input) throws IOException, InterruptedException 
	{
		
		productcatalogue productcatalogue = landingpage.LoginApplication(input.get("username"), input.get("password"));
		List<WebElement> products = productcatalogue.getproductlist();
		productcatalogue.addproducttocart();
		cartpage cartpage = productcatalogue.gotocart();
		Boolean match = cartpage.verifyproductinCart(input.get("productname"));
		Assert.assertTrue(match);
		checkoutpage checkoutpage = cartpage.clickcheckout();
		checkoutpage.entercheckoutdetails("John", "Doe", "12345");
		Confirmationpage confirmationPage = checkoutpage.submitorder();
		String confirmationMessage = confirmationPage.getconfirmationmessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thank you for your order!"));
		Confirmationpage confirmationpage = new Confirmationpage(driver);
		confirmationpage.getcheckoutconfirmation();
		Assert.assertTrue(confirmationpage.getcheckoutconfirmation().equalsIgnoreCase("Checkout: Complete!"));
		System.out.println(confirmationpage.getcheckoutconfirmation());
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\ecommerce\\data\\purchase_order.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
	}
}


