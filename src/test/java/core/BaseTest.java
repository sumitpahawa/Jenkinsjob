package core;

import api.apps.speedway.SpeedwayApp;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class BaseTest {

    public WebDriverWait wait;
    protected SpeedwayApp speedwayApp;

    @BeforeTest
    @Parameters({"deviceName", "platformName"})
    public void setup(String deviceName, String platformName) throws IOException {
        System.out.println("TestNG Before");
        //JSONReader jsonReader = new JSONReader("deviceDetails.json");
        // Unlock the device if it is locked.
        //if (platformName.equalsIgnoreCase("Android")) {
            /* Remember to change your Appium URL (command executor) to 'https://eu-mobile-hub.bitbar.com/wd/hub' */
            /* Mobile native capabilities */
            /* Remember to change your Appium URL (command executor) to 'https://eu-mobile-hub.bitbar.com/wd/hub' */
            /* Mobile native capabilities */
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("deviceName", "Galaxy");
//            capabilities.setCapability("platformVersion", "13");
//            capabilities.setCapability("platformName", "android");
//            capabilities.setCapability("automationName", "UiAutomator2");
//            capabilities.setCapability("udid", "R3CN60BS3AV");
//            capabilities.setCapability("noReset", true);
//            //setting capability for application we want to test
//            capabilities.setCapability("appPackage", "com.mattel.hwcollector");
//            capabilities.setCapability("appActivity", ".MainActivity");
//            //Instantiating Android Driver and using Appium server host and
//            ThreadLocalDriver.setTLDriver(new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723"), capabilities));
//            wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 100);
//            speedwayApp= new SpeedwayApp(ThreadLocalDriver.getTLDriver());
//            System.out.println("TestNG Before");
            //JSONReader jsonReader = new JSONReader("deviceDetails.json");
            // Unlock the device if it is locked.
           if (platformName.equalsIgnoreCase("Android")) {
                /* Remember to change your Appium URL (command executor) to 'https://eu-mobile-hub.bitbar.com/wd/hub' */
                /* Mobile native capabilities */
                /* Remember to change your Appium URL (command executor) to 'https://eu-mobile-hub.bitbar.com/wd/hub' */
                /* Mobile native capabilities */
                MutableCapabilities capabilities = new MutableCapabilities();
                capabilities.setCapability("appium:platformName", "Android");
                capabilities.setCapability("appium:automationName", "uiautomator2");
                HashMap<String, String> bitbarOptions = new HashMap<>();
                bitbarOptions.put("app", "256548971");
                bitbarOptions.put("apiKey", "7lwbIp8hgFSGnDWE2fP0uFZwywMq6y8P");
                bitbarOptions.put("device", "Samsung Galaxy");
                bitbarOptions.put("findDevice", "true");
                bitbarOptions.put("appiumVersion", "2.1");
                bitbarOptions.put("noReset", "true");
                bitbarOptions.put("testTimeout", "50000");
                capabilities.setCapability("bitbar:options", bitbarOptions);
                capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
                String host = "us-west-desktop-hub.bitbar.com";
                ThreadLocalDriver.setTLDriver(new AndroidDriver<>(new URL("https://eu-mobile-hub.bitbar.com/wd/hub"), capabilities));
                wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 180);
                speedwayApp= new SpeedwayApp(ThreadLocalDriver.getTLDriver());
        }
    }
    @BeforeMethod
    public void LaunchApp() {
        if (ThreadLocalDriver.getTLDriver() != null) {
           // ThreadLocalDriver.getTLDriver().launchApp();
            ThreadLocalDriver.getTLDriver().executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.mattel.hwcollector/com.mattel.hwcollector.MainActivity"));

        }
        else if (ThreadLocalDriver.getTLIOSDriver() != null){
            HashMap<String, Object> args = new HashMap<>();
            args.put("bundleId", "");
            ThreadLocalDriver.getTLIOSDriver().executeScript("mobile: launchApp", args);
        }
    }


    @AfterMethod
    public void closeApp() {
        if (ThreadLocalDriver.getTLDriver() != null) {
           // ThreadLocalDriver.getTLDriver().closeApp();
            ThreadLocalDriver.getTLDriver().executeScript("mobile:terminateApp", ImmutableMap.of("appId", "com.mattel.hwcollector"));

        }
    }

    @AfterSuite
    public void teardown() {
        try {
            System.out.println("TearDowning");
            if (ThreadLocalDriver.getTLDriver() != null) {
                ThreadLocalDriver.getTLDriver().quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
