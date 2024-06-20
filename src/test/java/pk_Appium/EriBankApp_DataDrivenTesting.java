package pk_Appium;

import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
public class EriBankApp_DataDrivenTesting extends AppTestData{

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@Test(priority = 1)
	public void EriBank_SignOn() {
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

	}

	@Test(dataProvider = "EriBank_MakePayment",priority = 2)
	public void EriBank_MakePayment(String ph, String name, String amt,String country) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, 'Make Payment')]")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/phoneTextField")))
				.sendKeys(ph);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/nameTextField")))
				.sendKeys(name);
//	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/amount"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/amount")))
				.sendKeys(amt);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/countryTextField")))
				.sendKeys(country);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/countryButton")))
				.click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView["+Country+"]"))).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, '" +country+"')]"))).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/sendPaymentButton")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1"))).click();

	}

	@BeforeTest
	public void LaunchApp() throws MalformedURLException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Nexus");
		caps.setCapability("udid", "emulator-5556");
		// caps.setCapability("udid", "07c261d7028a5114"); //DeviceId from "adb
		// devices" command
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("skipUnlock", "true");
		// Launch Device at run time
		// caps.setCapability("avd", "Nexus_6");
		caps.setCapability("appPackage", "com.experitest.ExperiBank");
		caps.setCapability("appActivity", "com.experitest.ExperiBank.LoginActivity");
		caps.setCapability("noReset", "false");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

		wait = new WebDriverWait(driver, 60);

	}

	@AfterTest
	public void CloseApp() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/logoutButton")))
				.click();

		String LoginButton = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/loginButton")))
				.getText();
		Assert.assertEquals("Login", LoginButton);
		driver.quit();

	}
}
