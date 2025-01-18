//package core.uiactions;
//
//import com.google.common.collect.ImmutableMap;
//import org.openqa.selenium.android.AndroidDriver;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class AppiumShellScript {
//
///* Sample code how to use command method
//command(driver,"adb","devices")*/
//
//    public String command(Appi driver, String... arguments) {
//        Map<String, Object> args = new HashMap<>();
//        for (String arg : arguments) {
//            args.put("args", arg);
//        }
//        String output = (String) driver.executeScript("mobile: shell", args);
//        return output;
//    }
//
//    public void fingerPrintUnlock(App driver) {
//        Map<String, Object> sessiondetails = driver.getSessionDetails();
//        if (sessiondetails.get("platformName").equals("ios")) {
//            IOSDriver driver1;
//            driver1 = (IOSDriver) driver;
//            driver1.performTouchID(true);
//        } else if (sessiondetails.get("platformName").equals("android")) {
//            AndroidDriver driver1;
//            driver1 = (AndroidDriver) driver;
//            driver1.fingerPrint(1);
//        } else {
//            System.out.println("Platform not supported");
//        }
//    }
//
//    public void faceIdUnlock(AppiumDriver driver) {
//        Map<String, Object> sessiondetails = driver.getSessionDetails();
//        driver.executeScript("mobile:enrollBiometric", ImmutableMap.of("isEnabled", true));
//        //Perform passing faceid authentication
//        driver.executeScript("mobile:sendBiometricMatch", ImmutableMap.of("type", "faceId", "match", true));
//    }
//}
