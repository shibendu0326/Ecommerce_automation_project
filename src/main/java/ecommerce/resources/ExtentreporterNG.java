package ecommerce.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreporterNG {

	
	public static ExtentReports getreportobject() 
	{
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Shibendu's Report");
		reporter.config().setDocumentTitle("Test Reselts");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shibendu");
		return extent;
		
	}

}
