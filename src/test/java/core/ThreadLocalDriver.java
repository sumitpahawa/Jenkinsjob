package core;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ThreadLocalDriver {


    private static ThreadLocal<AndroidDriver<AndroidElement>> tlDriver = new ThreadLocal<>();
    private static ThreadLocal<MobileDriver<IOSElement>> tlIOSDriver = new ThreadLocal<>();

    public synchronized static AndroidDriver<AndroidElement> getTLDriver() {
        return (AndroidDriver<AndroidElement>) tlDriver.get();
    }

    public synchronized static void setTLDriver(AndroidDriver<AndroidElement> driver) {
        tlDriver.set(driver);
    }

    public synchronized static IOSDriver<IOSElement> getTLIOSDriver() {
        return (IOSDriver<IOSElement>) tlIOSDriver.get();
    }

    public synchronized static void setTLIOSDriver(IOSDriver<IOSElement> driver) {
        tlIOSDriver.set(driver);
    }

}
