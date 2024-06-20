package pk_Appium;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import pk_Appium.BaseClass;

public class EriBank_ExtentReports_Example {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;
	ExtentReports extent;// This class will help to create HTML Report
	ExtentTest logger; // This will help to log each Test method result

	@Test(priority = 1)
	public void EriBank_SignOn() {
		logger = extent.startTest("Welcome to EriBank App -Login");

		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.id("android:id/button1"))).click();
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/usernameTextField")))
				.sendKeys("company");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/passwordTextField")))
				.sendKeys("company");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/loginButton")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/logoutButton")))
				.isDisplayed();
		logger.log(LogStatus.INFO, "Test Case (EriBank_SignOn) Executed");
	}

	@Test(priority = 2)
	public void EriBank_MakePayment() {
		logger = extent.startTest("EriBank-MakePayment Test Cases Started");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, 'Make Payment')]")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/phoneTextField")))
				.sendKeys("123456789");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/nameTextField")))
				.sendKeys("Dixit");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/amount"))).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/countryTextField")))
				.sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/countryButton")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/text1"))).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/sendPaymentButton")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/logoutButton")))
				.click();

		String LoginButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/loginButton1")))
				.getText();
		Assert.assertEquals("Login", LoginButton);
		logger.log(LogStatus.INFO, "Test Case (EriBank_MakePayment) Executed");
	}

	@BeforeClass
	public void LaunchApp() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Nexus");
		caps.setCapability("udid", "emulator-5554");
		// caps.setCapability("udid", "07c261d7028a5114"); //DeviceId from "adb devices"
		// command
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("skipUnlock", "true");
		// Launch Device at run time
		// caps.setCapability("avd","Galaxy_Nexus_7");
		caps.setCapability("appPackage", "com.experitest.ExperiBank");
		caps.setCapability("appActivity", "com.experitest.ExperiBank.LoginActivity");
		caps.setCapability("noReset", "false");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		wait = new WebDriverWait(driver, 20);

	}

	@BeforeTest
	public void startReport() {
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
		extent.addSystemInfo("Report Name", "Welcome to EriBank Project Test Report").addSystemInfo("User Name", "Dixit")
				.addSystemInfo("OS", System.getProperty("os.name")).addSystemInfo("Andriod", "7.0").addSystemInfo("Device Name", "Nexus");

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this mehtod in to the extent reports using
			// "logger.addScreenCapture" method.
			// String screenshotPath =
			// EriBank_App_Make_Payment_ExtentReport.getScreenhot(driver, result.getName());
			String screenshotPath = BaseClass.getScreenhot(driver, result.getName());
			// To add it in the extent report
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}

	@AfterTest
	public void CloseApp() {
		extent.close();
		driver.quit();

	}	
}
