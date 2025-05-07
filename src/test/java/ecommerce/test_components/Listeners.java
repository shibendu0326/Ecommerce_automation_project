package ecommerce.test_components;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ecommerce.resources.ExtentreporterNG;

public class Listeners extends Base_test implements ITestListener {
	
	
	ExtentReports extent= ExtentreporterNG.getreportobject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		
	    ITestListener.super.onTestStart(result);
	    test=extent.createTest(result.getMethod().getMethodName());
	    extentTest.set(test);//unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result)  {
		
		ITestListener.super.onTestFailure(result);
		extentTest.get().fail(result.getThrowable());
		
		//Get the driver from Base_test class
	
		try {
		    driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
		        .get(result.getInstance());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		
		//Screenshot code
		String Filepath = null;
		try {
			Filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
	
		ITestListener.super.onTestSkipped(result);
		test.log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		ITestListener.super.onFinish(context);
		extent.flush();
	}


}
