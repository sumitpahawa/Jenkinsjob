package core.managers.baseutils;

public class DeviceInfo {
    private static String OEM;
    private static String DEVICE_MODEL;
    private static String OS_VERSION;
    private static String DEVICE_BUILD_NUMBER;
    private static ADB adb;

    public DeviceInfo(ADB adb) {
        this.adb = adb;
    }

    public static String getOEM() {
        return adb.getDeviceManufacturer();
    }

    public static String getDeviceModel() {
        return adb.getDeviceModel();
    }

    public static String getOSVersion() {
        return adb.getAndroidVersionAsString();
    }

    public static String getDeviceBuildNumber() {
        return adb.getDeviceBuildNumber();
    }

    public static String getIMEI() {
        return adb.getIMEI();
    }


}
