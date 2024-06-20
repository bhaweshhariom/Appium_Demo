package pk_Appium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.time.Duration;

public class MobileActions {

	private AndroidDriver driver;
	 
    public MobileActions (AndroidDriver driver) {
        this.driver = driver;
    }
 
    //Tap to an element for 250 milliseconds
    public void tapByElement (AndroidElement androidElement) {
        new TouchAction(driver)
                .tap(tapOptions().withElement(element(androidElement)))
                .waitAction(waitOptions(Duration.ofMillis(250))).perform();
    }
}
