package pk_CloudLab;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class EriBank_CaptureScreenshot_Example {
	
	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;

	@Test(priority=1)//Main Method
	public void Login() {
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.id("android:id/button1"))).click();
		
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/usernameTextField"))).sendKeys("company");

			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/passwordTextField"))).sendKeys("company");

			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/loginButton"))).click();
			
			
			wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/logoutButton"))).isDisplayed();
			
//			wait.until(ExpectedConditions.visibilityOfElementLocated
//					(By.id("com.experitest.ExperiBank:id/logoutButton"))).click();
	}
	
	@Test(priority=2)
	public void MakePayment() {
		

		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/makePaymentButton"))).click();

		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/phoneTextField"))).sendKeys("123456789");
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/nameTextField"))).sendKeys("Dixit");
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/amount"))).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/countryTextField"))).sendKeys("India");
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/CountryButton"))).click();
		  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/text1"))).click();
		  
		  //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView["+Country+"]"))).click();
		  
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, 'India')]"))).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/sendPaymentButton"))).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("android:id/button1"))).click();
		  wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/logoutButton"))).click();
			
		  String LoginButton=wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("com.experitest.ExperiBank:id/loginButton"))).getText();
		  Assert.assertEquals("Login", LoginButton);
		
	}
	
	
	@BeforeTest
	public void LaunchApp() throws MalformedURLException
	{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Nexus");
		caps.setCapability("udid", "emulator-5554");
		//caps.setCapability("udid", "07c261d7028a5114"); //DeviceId from "adb devices" command
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "10.0");
		caps.setCapability("skipUnlock","true");
		//Launch Device at run time
		//caps.setCapability("avd","Galaxy_Nexus_7");
		caps.setCapability("appPackage", "com.experitest.ExperiBank");
		caps.setCapability("appActivity","com.experitest.ExperiBank.LoginActivity");
		caps.setCapability("noReset","false");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);

		wait = new WebDriverWait(driver, 60);
	}
	
	  @AfterTest
	  public void CloseApp() {
		  driver.quit();
		  
	  }
	  
	  @AfterMethod
	  public void CaptureScreenshot(ITestResult result) throws Exception {
		  if (ITestResult.FAILURE==result.getStatus())
			{
			  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
		              //after execution, you could see a folder "FailedTestsScreenshots" under src folder
				String destination = System.getProperty("user.dir") + "/Screenshot_Failure/"+result.getName()+dateName+".png";
				File finalDestination = new File(destination);
				FileUtils.copyFile(source, finalDestination);
			}
		  else if(ITestResult.SUCCESS==result.getStatus())
				{
				  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					TakesScreenshot ts = (TakesScreenshot) driver;
					File source = ts.getScreenshotAs(OutputType.FILE);
			              //after execution, you could see a folder "FailedTestsScreenshots" under src folder
					String destination = System.getProperty("user.dir") + "/Screenshot_Success/"+result.getName()+dateName+".png";
					File finalDestination = new File(destination);
					FileUtils.copyFile(source, finalDestination);
				}
	  }

}
