package pk_CloudLab;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
public class EriBankApp_DataDrivenTesting extends pk_Appium.AppTestData{

	public AndroidDriver driver;
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

		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:app", "storage:filename=EriBank.apk");  // The filename of the mobile app
		caps.setCapability("appium:deviceName", "Samsung.*");
		caps.setCapability("appium:automationName", "UiAutomator2");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("appiumVersion", "latest");
		sauceOptions.setCapability("username", "oauth-bhawesh.hariom-e5b7c");
		sauceOptions.setCapability("accessKey", "c0ed81f0-581c-4425-aaf5-38e903675d40");
		sauceOptions.setCapability("build", "1.2");
		sauceOptions.setCapability("name", "Android Real device");
		caps.setCapability("sauce:options", sauceOptions);

		URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
		driver = new AndroidDriver(url, caps);

		wait = new WebDriverWait(driver, 10);

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
