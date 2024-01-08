package PageTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.remote.MobileCapabilityType;

public class RegistrationPageTest {
//
//    private AndroidDriver driver;
//
//    WebElement screenTitle;
//    WebElement signInButton;
//
//    @Before
//    public void setUp() throws Exception {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "pixel3a");
//        capabilities.setCapability("platformVersion", "10");
//        capabilities.setCapability("appPackage", "com.example.restohelper");
//        capabilities.setCapability("appActivity",
//                "com.example.restohelper.activities.RegistrationActivity");
//        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
//        capabilities.setCapability(
//                "app",
//                "app/src/main/res/restohelper.apk");
//
//        try {
//            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        } catch (MalformedURLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    // Проверка, находимся ли мы на экране "Регистрация"
//    @Test
//    public void checkTitleScreen() {
//        screenTitle = driver.findElementById("com.example.restohelper:id/sign_up_heading_text_view");
//        screenTitle.isDisplayed();
//    }
//
//    @Test
//    public void goToLoginPage() {
//        signInButton = driver.findElementById("com.example.restohelper:id/sign_up_sign_in_text_view");
//        signInButton.isDisplayed();
//        signInButton.click();
//    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
}
