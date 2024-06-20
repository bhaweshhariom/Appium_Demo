package pk_Appium;

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
import io.appium.java_client.android.AndroidElement;

@Test
public class TapAction_Example {

	public AndroidDriver<MobileElement> driver;
	public WebDriverWait wait;
	MobileActions action;
 
    @BeforeTest
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
       caps.setCapability("deviceName", "Nexus");
       //Set UDID: Unique Device Identifier
       caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
       caps.setCapability("platformName", "Android");
       caps.setCapability("platformVersion", "10.0");
       caps.setCapability("skipUnlock","true");
       caps.setCapability("appPackage", "com.android.settings");
       caps.setCapability("appActivity","com.android.settings.Settings");
       caps.setCapability("noReset","false");
       driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
       wait = new WebDriverWait(driver, 10);
    }
  
    public void TapByElement_Test () throws InterruptedException {
 
    	System.out.println("Welcome to Android Settings for Tap functionality");
    	Thread.sleep(6000);
    	//Click on Display
    	WebElement GetDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated
    			(By.xpath("//*[contains(@text,'Display')]")));
    	action = new MobileActions(driver);
    	action.tapByElement((AndroidElement) GetDisplay);
    	
    	WebElement GetWallPaper = wait.until(ExpectedConditions.visibilityOfElementLocated
    			(By.xpath("//*[contains(@text,'Wallpaper')]")));
    	action.tapByElement((AndroidElement) GetWallPaper);
    	Thread.sleep(6000);
    	String GetGallery = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Photos')]"))).getText();
    	System.out.println(GetGallery);
    	Assert.assertEquals("Photos", GetGallery);
    }

   @AfterTest
    public void Quit(){
    //	driver.resetApp();
        driver.quit();
    }
}
