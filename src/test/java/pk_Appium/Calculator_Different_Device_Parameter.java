package pk_Appium;

import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Calculator_Different_Device_Parameter {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@Parameters({ "deviceName_", "UDID_", "platformVersion_", "URL_" })
	@BeforeTest
	public void setup(String deviceName_, String UDID_, String platformVersion_, String URL_)
			throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", deviceName_);
		// Set UDID: Unique Device Identifier
		caps.setCapability("udid", UDID_); // DeviceId from "adb devices" command
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", platformVersion_);
		caps.setCapability("skipUnlock", "true");
		caps.setCapability("appPackage", "com.android.calculator2");
		// This package name of your app (you can get it from apk info app)
		caps.setCapability("appActivity", "com.android.calculator2.Calculator");
		caps.setCapability("noReset", "false");
		// driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		driver = new AndroidDriver<MobileElement>(new URL("http://" + URL_), caps);
		wait = new WebDriverWait(driver, 10);
	}

	@Test(priority = 2)
	public void Addition() throws Exception {
		System.out.println("Welcome to Addition");
		// locate the Text on the calculator by using By.name()
		WebElement two = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_2")));
		// WebElement
		// two=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_2']")));
		two.click();
		WebElement plus = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/op_add")));
		plus.click();
		WebElement four = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_4")));
		four.click();
		WebElement equalTo = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/eq")));
		equalTo.click();
		// locate the edit box of the calculator by using By.tagName()
		WebElement results = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/result")));
		// Check the calculated value on the edit box
		assert results.getText().equals("6")
				: "Actual value is : " + results.getText() + " did not match with expected value: 6";

	}

	@Test(priority = 1)
	public void Multiplication() throws Exception {
		System.out.println("Welcome to Multiplication");
		// locate the Text on the calculator by using By.name()
		WebElement two = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_2")));
		// WebElement
		// two=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Button[@resource-id='com.android.calculator2:id/digit_2']")));
		two.click();
		WebElement plus = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/op_mul")));
		plus.click();
		WebElement four = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_4")));
		four.click();
		WebElement equalTo = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/eq")));
		equalTo.click();
		// locate the edit box of the calculator by using By.tagName()
		WebElement results = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/result")));
		// Check the calculated value on the edit box
		assert results.getText().equals("8")
				: "Actual value is : " + results.getText() + " did not match with expected value: 8";

	}

	@AfterTest
	public void teardown() {
		// close the app
		driver.quit();
	}
}
