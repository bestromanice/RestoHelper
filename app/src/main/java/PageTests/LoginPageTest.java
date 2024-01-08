package PageTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

//import io.appium.java_client.android.AndroidDriver;

public class LoginPageTest {

//    private AndroidDriver driver;
//
//    WebElement screenTitle;
//    WebElement registrationButton;
//    WebElement emailInput;
//    WebElement passInput;
//    WebElement signInButton;
//
//    public void setEmail(String email) {
//        emailInput.isDisplayed();
//        emailInput.click();
//        emailInput.sendKeys(email);
//    }
//
//    public void setPassword(String password) {
//        passInput.isDisplayed();
//        passInput.click();
//        passInput.sendKeys(password);
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("deviceName", "pixel3a");
//        capabilities.setCapability("platformVersion", "10");
//        capabilities.setCapability("automationName", "Appium");
//        capabilities.setCapability("appPackage", "com.example.restohelper");
//        capabilities.setCapability("appActivity",
//                "com.example.restohelper.activities.LoginActivity");
//        capabilities.setCapability(
//                "app",
//                "C:\\AndroidStudioProjects\\RestoHelper\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
//
//        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//    }
//
//    // Проверка, находимся ли мы на экране "Вход"
//    @Test
//    public void checkTitleScreen() {
//        screenTitle = driver.findElementById("com.example.restohelper:id/sign_in_heading_text_view");
//        screenTitle.isDisplayed();
//    }
//
//    // Проверка наличия на экране кнопки "Зарегистрироваться"
//    @Test
//    public void checkRegistrationButton() {
//        registrationButton = driver.findElementById("com.example.restohelper:id/sign_in_sign_up_text_view");
//        registrationButton.isDisplayed();
//    }
//
////    @Test
////    public MainPageTest validLoginTest() {
////        emailInput = driver.findElementById("com.example.restohelper:id/sign_in_email_edit_text");
////        setEmail("admin@example.com");
////
////        passInput = driver.findElementById("com.example.restohelper:id/sign_in_password_edit_text");
////        setPassword("admin1");
////
////        signInButton = driver.findElementById("com.example.restohelper:id/sign_in_button");
////        signInButton.isDisplayed();
////        signInButton.click();
////
////        return new MainPageTest();
////    }
//
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
}
