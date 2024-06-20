package pk_CloudLab;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SauceLab_Mobile_Browser {
	
	RemoteWebDriver driver;
	public WebDriverWait wait;

	@BeforeTest
	public void LaunchApp() throws MalformedURLException {
		FirefoxOptions browserOptions = new FirefoxOptions();
		browserOptions.setCapability("platformName", "Windows 11");
		browserOptions.setCapability("browserVersion", "127");
		Map<String, Object> sauceOptions = new HashMap<>();
		sauceOptions.put("build", "1.1");
		sauceOptions.put("name", "FirefoxMobileBrowser");
		sauceOptions.put("username", "oauth-bhawesh.hariom-e5b7c");
		sauceOptions.put("accessKey", "c0ed81f0-581c-4425-aaf5-38e903675d40");
		browserOptions.setCapability("sauce:options", sauceOptions);

		URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
		driver = new RemoteWebDriver(url, browserOptions);
	}

	@Test
	public void EriBank_SignOn() throws InterruptedException {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.findElement(By.linkText("Logout")).click();
	}

	@AfterTest
	public void CloseApp() {
		driver.quit();

	}


}
