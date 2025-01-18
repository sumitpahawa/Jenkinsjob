package core.managers.devicemanager;

import core.managers.baseutils.ADB;
import core.managers.baseutils.DeviceInfo;

public class DeviceManager {
    public DeviceInfo deviceInfo;
    public ADB adb;

    public DeviceManager(String deviceUDID) {
        adb = new ADB(deviceUDID);
        deviceInfo = new DeviceInfo(adb);
    }
}
