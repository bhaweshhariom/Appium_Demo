package pk_CloudLab;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SauceLab_EriBankApp_Android {

	public AndroidDriver driver;
	  public WebDriverWait wait;

		@BeforeTest
		public void setUp() throws MalformedURLException {
			MutableCapabilities caps = new MutableCapabilities();
			caps.setCapability("platformName", "Android");
			caps.setCapability("appium:app", "storage:filename=EriBank.apk");  // The filename of the mobile app
			caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
			caps.setCapability("appium:platformVersion", "7.0");
			caps.setCapability("appium:automationName", "UiAutomator2");
			MutableCapabilities sauceOptions = new MutableCapabilities();
			sauceOptions.setCapability("appiumVersion", "2.0.0");
			sauceOptions.setCapability("username", "oauth-bhawesh.hariom-e5b7c");
			sauceOptions.setCapability("accessKey", "c0ed81f0-581c-4425-aaf5-38e903675d40");
			sauceOptions.setCapability("build", "2.1");
			sauceOptions.setCapability("name", "Eri Bank Mobile App");
			sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
			caps.setCapability("sauce:options", sauceOptions);

			URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
			AndroidDriver driver = new AndroidDriver(url, caps);



			wait = new WebDriverWait(driver, 10);
		}

		@Test
		public void testCal() throws Exception {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/usernameTextField"))).sendKeys("company");

			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/passwordTextField"))).sendKeys("company");

			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/loginButton"))).click();
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/logoutButton"))).isDisplayed();
			
			WebElement results = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@text=\"Your balance is: 100.00$\"]")));

			WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.experitest.ExperiBank:id/logoutButton")));
			logout.isDisplayed();
			logout.click(); 

//			assert results.getText().equals("Your balance is: 100.00$")
//					: "Actual value is : " + results.getText() + " did not match with expected value: Your balance is: 100.00$";
		}

		@AfterTest
		public void teardown() {
			// close the app
			driver.quit();
		}
	
}
