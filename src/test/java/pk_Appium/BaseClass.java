package pk_Appium;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {

	static ExtentReports extent;// This class will help to create HTML Report
	ExtentTest logger; // This will help to log each Test method result
	
	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
              //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshot_Failure/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		//return destination;
		return destination;
	}
	
	public static String getScreenhotSuccess(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
              //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshot_Success/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		//return destination;
		return destination;
	}
	
	public static ExtentReports HTML_Report() {
		// ExtentReports(String filePath,Boolean replaceExisting)
		// filepath - path of the file, in .htm or .html format - path where your report
		// needs to generate.
		// replaceExisting - Setting to overwrite (TRUE) the existing file or append to
		// it
		// True (default): the file will be replaced with brand new markup, and all
		// existing data will be lost. Use this option to create a brand new report
		// False: existing data will remain, new tests will be appended to the existing
		// report. If the the supplied path does not exist, a new file will be created.
		System.out.println(System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html");
		extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html", true);
		// extent.addSystemInfo("Environment","Environment Name")
		extent.addSystemInfo("Host Name", "Welcome to Extent Report").addSystemInfo("User Name", "Dixit")
				.addSystemInfo("OS", System.getProperty("os.name")).addSystemInfo("Andriod", "7.0");
		return extent;
	}
	
}
