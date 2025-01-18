package core.managers.baseutils;

import core.constants.Constants;
import core.managers.filemanager.JSONReader;
import core.managers.logmanager.MyLogger;
import core.managers.servermanager.ServerManager;
import core.reporting.ExcelReporting;

import io.cucumber.messages.Messages;
import org.testng.Assert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ADB {

    public ExcelReporting error_reporting;
    public JSONReader jsonReader = new JSONReader("deviceDetails.json");

    private String ID;
    private Utils utils = Utils.getInstance();


    public ADB(String deviceID) {
        ID = deviceID;
    }

    public static String runCommand(String command) {
        MyLogger.log.debug("Formatting ADB Command: " + command);

      /*  if(command.startsWith("adb")) command = command.replace("adb ", ServerManager.getAndroidHome()+"/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run ADB commands only!");
        MyLogger.log.debug("Formatted ADB Command: "+command);
       */
        String output = ServerManager.runCommand(command);
        //MyLogger.log.debug("Output of the ADB Command: " + output);
        if (output == null) return "";
        else return output.trim();
    }

    public static ArrayList<String> getConnectedDevices() {
        ArrayList<String> devices = new ArrayList<String>();
        String output = runCommand("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        return devices;
    }

    public static int getDevicesCount() {
        List<String> list = null;
        try {
            list = getConnectedDevices();
        } catch (NullPointerException nullPointerException) {
            MyLogger.log.info("Check Availability has not executed properly");
        }

        return list.size();
    }

    public static void main(String[] args) {
        String month = LocalDate.now().minusMonths(1).getMonth() + "";
        month = month.toLowerCase();
        String newMonth = month.substring(0, 1).toUpperCase() + month.substring(1);

        String date = 7 + " " + newMonth + " " + LocalDate.now().getYear();
        System.out.println(date);
//        ADB adb = new ADB("BNPARAMOREA205A0015");
//        adb.closeCMD();
//        adb.takeAndDownloadScreenshot("./", "screenCap.jpg");
//        adb.tapToAnElementWithBounds(adb.getBoundsID("Settings"));
        //adb.clearAppCache("com.android.chrome");
//        adb.clearAppCache("com.google.android.calendar");
//        adb.clearAppCache("com.google.android.apps.photos");
//        adb.openApp("com.vinsmart.android.dialer.att","com.android.vinsmart.ui.dialer.VSmartDialerActivity");
        // System.out.println(adb.getMenuOptions());
//        adb.clearAppCache("com.google.android.apps.youtube.music");
//        adb.openApp("com.google.android.apps.youtube.music","com.google.android.apps.youtube.music.activities.MusicActivity");
//        adb.playSong("file:///storage/emulated/0/Download/Test\\ Data/Audio/Bloch_Prayer.mp3",50000);
//        adb.emptyTrash();
//        adb.waitForElementID("Individual plan",10);

    }

    public String command(String command) {
        MyLogger.log.debug("Formatting ADB Command: " + command);

      /*  if(command.startsWith("adb")) command = command.replace("adb ", ServerManager.getAndroidHome()+"/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run ADB commands only!");
        MyLogger.log.debug("Formatted ADB Command: "+command);
       */
        String output = ServerManager.runCommand(command);
        //MyLogger.log.debug("Output of the ADB Command: " + output);
        if (output == null) return "";
        else return output.trim();
    }

    public String commandURL(String command) throws IOException {
        MyLogger.log.debug("Formatting ADB Command: " + command);
        String[] commands = new String[]{"curl", "-X", "GET", "http://checkip.amazonaws.com"};
        Process process = Runtime.getRuntime().exec(commands);
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(process.getInputStream()));
        String line;
        String response = "";
        while ((line = reader.readLine()) != null) {
            response.concat(line);
        }
        return response;
    }

    public void killServer() {
        command("adb kill-server");
    }

  /*  public void captureBugreport() {
        MyLogger.log.info("Capturing Bugreport for the device ID " + ID);
        String output = command("adb -s " + ID + " bugreport test-output\\");

        if (output.contains("device failed to take a zipped bugreport")) {
            command("adb -s " + ID + " bugreport test-output\\restartedBugreport");
        }
    }*/

    public void startServer() {
        command("adb start-server");
    }

    public void captureBugreport(String REPORT_FOLDER_NAME) {
        try {
            //Runtime.getRuntime().exec("cmd.exe /c mkdir -p " + ServerManager.USER_HOME + "\\test-output\\" + REPORT_FOLDER_NAME + "\\LOGS\\Bugreport\\");
            String pathName = ServerManager.USER_HOME + "\\test-output\\" + REPORT_FOLDER_NAME + "\\LOGS\\";
            String dirName = pathName.concat("Bugreport");
            createDirectory(pathName, dirName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        MyLogger.log.info("Capturing Bugreport for the device ID " + ID);
        String output = command("adb -s " + ID + " bugreport test-output\\" + REPORT_FOLDER_NAME + "\\LOGS\\Bugreport\\");

        if (output.contains("device failed to take a zipped bugreport")) {
            command("adb -s " + ID + " bugreport test-output\\" + REPORT_FOLDER_NAME + "\\LOGS\\Bugreport\\");
        }
    }

    public String waitForDevie() {
        MyLogger.log.info("Waiting for the Device " + ID);
        return command("adb -s " + ID + " wait-for-device");
    }

    public boolean waitForActivity(String activity, int seconds) {
        MyLogger.log.info("waiting for the activity : " + activity);
        boolean status = false;
        for (int i = 0; i < seconds / 2; i++) {
            if (isActivityRunning(activity)) {
                status = true;
                break;
            } else
                utils.miscUtils.sleep(2000);
        }
        return status;
    }

    public String getPackageList() {
        MyLogger.log.info("Getting Package list");
        return command("adb -s " + ID + " shell pm list package");
    }

    public boolean isActivityRunning(String activity) {
        MyLogger.log.info("Checking if activity is running: " + activity);
        boolean isRunning = false;
        String output = command("adb -s " + ID + " shell dumpsys activity activities");
        //System.out.println(output);
        if (output.contains("mResumedActivity:")) {
            String[] lines = output.split("\\r?\\n");
            for (String line : lines) {
                //   System.out.println(line);
                if (line.trim().contains("mResumedActivity") && line.contains(activity)) {
                    MyLogger.log.info("Activity :" + activity + "  is running");
                    isRunning = true;
                    break;
                }
            }
        }
        return isRunning;
    }

    /*public void pullBugReport() {
        captureBugreport();
        MyLogger.log.info("Pulling Bugreport from the device to local drive");
        command("adb pull /data/user_de/0/com.android.shell/files/bugreports");

    }*/

    public boolean waitUntilOneAppInstallation(String packageName) {
        MyLogger.log.info("Waiting for app to install");
        String output = "";
        for (int i = 0; i < 6; i++) {
            output = getPackageList();
            if (output.contains(packageName)) {
                MyLogger.log.info(packageName + " got installed");
                return true;
            } else {
                utils.miscUtils.sleep(10000);
            }
        }
        return false;
    }

    public void checkDeviceAvailability() {
        ArrayList<String> list = getConnectedDevices();
        if (list.size() < 2) {

        }
    }

    public String getForegroundActivity() {
        return command("adb -s " + ID + " shell dumpsys window windows | grep mCurrentFocus");
    }

    public String turnDataOn() {
        return command("adb -s " + ID + " shell svc data enable");
    }

    public String turnDataOff() {
        return command("adb -s " + ID + " shell svc data disable");
    }

    //This method only works when there is ongoing appium session
    public String turnWifiOn() {
        MyLogger.log.info("WiFi Radio is turning on");
        //  return command("adb -s " + ID + " shell svc wifi enable");
        return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \" shell svc wifi enable\"");

    }

    //This method only works when there is ongoing appium session
    public String turnWifiOff() {
        MyLogger.log.info("WiFi Radio is turning off");
        return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \" shell svc wifi disable\"");

        // return command("adb -s " + ID + " shell svc wifi disable");
    }

    public String deleteSDPictures() {
        String output = command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \" rm -rf /mnt/sdcard/Pictures/*.*\"");

        //String output = command("adb -s " + ID + " rm -rf /mnt/sdcard/Pictures/*.*");
        return output;
    }

    public String getAndroidVersionAsString() {
        String output = command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"getprop ro.build.version.release\"");
        // String output = command("adb -s " + ID + " shell getprop ro.build.version.release");
        if (output.length() == 3) output += ".0";
        return output;
    }

    public String getIMEI() {
        String output = command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \" service call iphonesubinfo 1 | toybox cut -d \"'\\\" -f2 | toybox grep -Eo '[0-9]' | toybox xargs | toybox sed 's/\\ //g'\"\"");

        // String output = command("adb -s " + ID + " shell \"service call iphonesubinfo 1 | toybox cut -d \\\"'\\\" -f2 | toybox grep -Eo '[0-9]' | toybox xargs | toybox sed 's/\\ //g'\"");
        return output;
    }

    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public ArrayList<String> getInstalledPackages() {
        ArrayList<String> packages = new ArrayList<String>();
        String[] output = command("adb -s " + ID + " shell pm list packages").split("\n");
        for (String packageID : output) packages.add(packageID.replace("package:", "").trim());
        return packages;
    }

    public void openAppsActivity(String packageID, String activityID) {
        command("adb -s " + ID + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    public void closeApp(String packageID) {
        MyLogger.log.info("Closing Application with Package ID :" + packageID);
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    public void launchApp(String packageID, String activityID) {
        MyLogger.log.info("Launching App with Package :" + packageID + " and Activity :" + activityID);
        command("adb -s " + ID + " shell am start -n " + packageID + "/" + activityID);
    }

    public void restartADB() {
        command("adb kill-server");
        utils.miscUtils.sleep(5000);
        command("adb start-server");


    }

    public void clearAppsData(String packageID) {
        command("adb -s " + ID + " shell pm clear " + packageID);
    }

    public void forceStopApp(String packageID) {
        command("adb -s " + ID + " shell am force-stop " + packageID);
    }

    public void installApp(String apkPath) {
        command("adb -s " + ID + " install " + apkPath);
    }

    public void uninstallApp(String packageID) {
        command("adb -s " + ID + " uninstall " + packageID);
    }

    public void clearLogBuffer() {
        command("adb -s " + ID + " shell -c");
    }

    public String clearLogcat() {
        return command("adb -s " + ID + " logcat -b all -c");
        //return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"logcat -b all -c\"");

        //System.out.println("Logcat cleared");
    }

    public void pushFile(String source, String target) {
        command("adb -s " + ID + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        command("adb -s " + ID + " pull " + source + " " + target);
    }

    public void deleteFile(String target) {
        command("adb -s " + ID + " shell rm " + target);
    }

    public void moveFile(String source, String target) {
        command("adb -s " + ID + " shell mv " + source + " " + target);
    }

    public void takeScreenshot(String target) {
        command("adb -s " + ID + " shell screencap " + target);
    }

    public void takeAndDownloadScreenshot(String destinationFolder, String name) {
        command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"screencap -p /sdcard/" + name + "\"");
        //  command("adb -s " + ID + " shell screencap -p /sdcard/" + name);
        //  command("adb -s " + ID + " pull /sdcard/" + name + " " + destinationFolder + "");
        command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"pull /sdcard/ " + name + "  " + destinationFolder + "\"");
        //  command("adb -s " + ID + " shell rm /sdcard/" + name);
        command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"rm /sdcard/ " + name + "\"");

    }

    public void takeAndDownloadScreenshot(String name) {
        command("curl https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/RF8N128LQYY/screenshot -o" + " " + name + "");
    }

    public void rebootDevice() {
        command("adb -s " + ID + " reboot");
    }

    public String getDeviceModel() {

        return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"getprop ro.product.model\"");
        // return command("adb -s " + ID + " shell getprop ro.product.model");
    }

    public String getDeviceManufacturer() {

        return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"getprop ro.product.manufacturer\"");
        // return command("adb -s " + ID + " shell getprop ro.product.manufacturer");
    }

    public String getDeviceBuildNumber() {
        return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"getprop ro.build.fingerprint\"");

        //return command("adb -s " + ID + " shell getprop ro.build.fingerprint");
    }

    public String getDeviceSerialNumber() {

        // return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/"+ID+"/shell -d \"getprop ro.serialno\"");

        return command("adb -s " + ID + " shell getprop ro.serialno");
    }

    public String getDeviceCarrier() {
        return command("adb -s " + ID + " shell getprop gsm.operator.alpha");
    }

    public ArrayList<String> getLogcatProcesses() {
        String[] output = command("adb -s " + ID + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList<String> processes = new ArrayList<String>();
        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    public int sendSMS(String receiverPhoneNumber, String message) {
        String command = "adb -s " + ID + " shell service call isms 7 i32 0 s16 \"com.vinsmart.rcs.messaging\" s16 \"" + receiverPhoneNumber + "\" s16 \"null\" s16 \"" + "'" + message + "'" + "\" s16 \"null\"";
        MyLogger.log.debug("Command is: " + command);
        String output = command(command);
        MyLogger.log.debug("input receiverPhoneNumber:" + receiverPhoneNumber + " and message:" + message);
        MyLogger.log.debug("output of the command: " + output);
        if (output.contains("Result: Parcel(00000000"))
            return 1;
        else return 0;
    }

    public int makeCall(String receiverPhoneNumber) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.CALL -d tel:" + receiverPhoneNumber;
        MyLogger.log.debug("Command is: " + command);
        String output = command(command);
        MyLogger.log.debug("input receiverPhoneNumber:" + receiverPhoneNumber);
        MyLogger.log.debug("output of the command: " + output);
        if (output.contains("Intent { act=android.intent.action.CALL dat=tel:"))
            return 1;
        else return 0;
    }

    public int makeCallFromDialer(String receiverPhoneNumber) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.DIAL -d tel:" + receiverPhoneNumber;
        MyLogger.log.debug("Command is: " + command);
        String output = command(command);
        MyLogger.log.debug("input receiverPhoneNumber:" + receiverPhoneNumber);
        MyLogger.log.debug("output of the command: " + output);
        if (output.contains("Intent { act=android.intent.action.DIAL dat=tel:"))
            return 1;
        else return 0;
    }

    public void callDuration(int durationSeconds) {
        while (true) {
            if (getCallStatus() == 2) {
                MyLogger.log.debug("Waiting for call duration of " + durationSeconds + "seconds");
                sleep(durationSeconds);
                MyLogger.log.debug("Call waited for duration of " + durationSeconds + "seconds");
                break;
            }
        }
    }

    public int endCall() {
        MyLogger.log.info("Terminating call on " + ID);
        System.out.println(ID);
        String command = "curl -X POST https://1edd3b95292d4b02bca59be475fc1790@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"input keyevent 6\"";
        //String command = "adb -s " + ID + " shell input keyevent 6";
        command(command);
        MyLogger.log.debug("Command is: " + command);
        MyLogger.log.info("Ended call on " + ID);
        return 1;

    }

    public boolean acceptCall() {
        MyLogger.log.info("Accepting call");
        System.out.println(ID);
        String command = "curl -X POST https://1edd3b95292d4b02bca59be475fc1790@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"input keyevent 5\"";
        // String command = "adb -s " + ID + " shell input keyevent 5";
        MyLogger.log.debug("Command is: " + command);
        command(command);
    /*if (getCallStatus() == 2) {
        MyLogger.log.info("Accepted call on " + ID);
        status = true;
    } else {
        waitForIncomingCall(5);
        command(command);
    }
    if (getCallStatus() != 2) {
        Assert.fail();
    }*/
        return true;
    }

    public void screenOn() {
        MyLogger.log.info("Checking turn on ");
        String command = "adb -s " + ID + " shell input keyevent 224";
        command(command);

    }

    public boolean checkWiFiCallStatus(int waitSeconds) {
        MyLogger.log.info("Waiting for WiFi Call settings to connect");
        for (int i = 0; i < waitSeconds / 5; i++) {
            String output = "";
            String command = getLogcat();
            output = command(command);
            if (output.contains("imsTransportType=2")) { //isVoWifiEnabled=true  //mIsVoWifiAvailable=true
                MyLogger.log.info("WiFi Call settings CONNECTED");
                return true;
            }
            MyLogger.log.info("Waiting for 5 seconds");
            sleep(5);
        }
        MyLogger.log.info("WiFi Call settings DISCONNECTED");
        return false;
    }

    public boolean waitForATTToConnect(int waitSeconds) {
        int count = 0;
        MyLogger.log.info("Waiting for WiFi Call settings to connect");
        for (int i = 0; i < waitSeconds; i++) {
//            String output = getLogcat();
            String output = getUIAutomatorDump();
            if (output.contains("AT&amp;T")) { //isVoWifiEnabled=true  //mIsVoWifiAvailable=true
                MyLogger.log.info("WiFi Call + is CONNECTED; ready to make WIFI Call");
                return true;
            } else {
                MyLogger.log.info("Waiting for 1 seconds");
                sleep(1);
                count++;
                if (count == 25) {
                    turnWifiOff();
                    sleep(4);
                    turnWifiOn();
                    waitSeconds = waitSeconds - 4;
                }
            }

        }
        MyLogger.log.info("WiFi Call is not ready after 90 seconds");
        return false;
    }

    public boolean waitForNetowrkConnected(int waitSeconds) {
        boolean status = false;
        for (int i = 0; i < 15; i++) {
            if (getWifiNetworkStatus() == 1) {
                status = true;
                break;
            } else
                utils.miscUtils.sleep(2000);
        }
        return status;
    }

    public int getWifiNetworkStatus() {
        String output = "";
        String command = "adb -s " + ID + " shell dumpsys wifi";
        output = command(command);
        if (output.contains("state: CONNECTED/CONNECTED")) {
            MyLogger.log.info("Wifi is connected to SSID");
            return 1;
        } else {
            MyLogger.log.info("Wifi is not connected to SSID");
            return 0;
        }
    }

    public void waitForWifiToConnect(int waitSeconds) {
        MyLogger.log.info("waiting for Wifi to be connected");
        for (int i = 0; i < waitSeconds / 2; i++) {
            if (getWifiNetworkStatus() == 0) {
                MyLogger.log.info("waiting for 2 seconds");
                utils.miscUtils.sleep(2000);
            } else
                break;
        }
    }

   /* public String turnAirPlaneModeOn() {
        command("adb -s " + ID + " shell settings put global airplane_mode_on 1");
        return command("adb -s " + ID + " shell am broadcast -a android.intent.action.AIRPLANE_MODE");
    }

    public String turnAirPlaneModeOff() {
        command("adb -s " + ID + " shell settings put global airplane_mode_on 0");
        return command("adb -s " + ID + " shell am broadcast -a android.intent.action.AIRPLANE_MODE");
    }*/

    public int getWifiStatus() {
        String output = "";
        String command = "adb -s " + ID + " shell dumpsys wifi";
        output = command(command);
        if (output.contains("Wi-Fi is enabled")) {
            MyLogger.log.info("Wifi is enabled");
            return 1;
        } else {
            MyLogger.log.info("Wifi is disabled");
            return 0;
        }
    }

    public boolean isAirplaneModeOn() {
        String output = "";
        String command = "adb -s " + ID + " shell settings get global airplane_mode_on";
        output = command(command);
        if (output.trim().equals("0")) {
            MyLogger.log.info("AirPlane mode is not active");
            return false;
        } else {
            MyLogger.log.info("AirPlane mode is active");
            return true;
        }
    }

    public void openWiFiSettings() {
        closeApp("com.android.settings");
        String command = "adb -s " + ID + " shell am start -n com.android.settings/.wifi.WifiPickerActivity";
        command(command);
    }

    public void turnAirPlaneModeOn() {
        // closeApp("com.android.settings");
        MyLogger.log.info("Turning on AirPlane mode");
        // String command = "adb -s " + ID + " shell am start -a android.settings.WIRELESS_SETTINGS";
        //command(command);
        sleep(2);
        // waitForElementID("Advanced", 10);

        if (!isAirplaneModeOn()) {
            // MyLogger.log.info("AirPlan mode is turned OFF; Turning On AirPlane mode");
            //int[] bounds = getBoundsID("Advanced");
            //tapToAnElementWithBounds(bounds);
            waitForElementID("Automatic", 10);
            int[] bounds1 = getBoundsID("Airplane mode");
            tapToAnElementWithBounds(bounds1);

        }

    }

    public void turnAirPlaneModeOff() {
        closeApp("com.android.settings");
        MyLogger.log.info("Turning off AirPlane mode");
        String command = "adb -s " + ID + " shell am start -a android.settings.WIRELESS_SETTINGS";
        command(command);
        sleep(2);
        waitForElementID("Advanced", 10);

        if (isAirplaneModeOn()) {
            MyLogger.log.info("AirPlan mode is turned OFF; Turning On AirPlane mode");
            int[] bounds = getBoundsID("Advanced");
            tapToAnElementWithBounds(bounds);
            waitForElementID("Automatic", 10);
            int[] bounds1 = getBoundsID("Airplane mode");
            tapToAnElementWithBounds(bounds1);
        }

    }

    public String getSSIDName() {
        String command = "adb -s " + ID + " shell dumpsys wifi";
        String output = command(command);
        String[] strArr = null;
        if (output.contains("mWifiInfo SSID:")) {
            strArr = output.split("mWifiInfo SSID:");
            //TODO How many characters we need to take as substring
            return strArr[1].substring(0, 19);
        } else return "";
    }

    public int getNetworkLTEStatus() {
        String output = "";
        String command = "adb -s " + ID + " shell dumpsys telephony.registry";
        output = command(command);
        if (output.contains("accessNetworkTechnology=LTE")) {
            MyLogger.log.info("Network is LTE");
            return 1;
        } else {
            MyLogger.log.info("Network is not LTE");
            return 0;
        }
    }


//    public int getImsRegistrationStatus(){
//        String output = "";
//        command("adb -s " + ID + " shell am start -a android.intent.action.VIEW tel:*");
//        command("adb -s " + ID + " shell input keyevent \"KEYCODE_DEL\"");
//        command("adb -s " + ID + " shell input text *#*#4636#*#*");
//        command("adb -s " + ID + " shell input tap 259 215");
//        command("adb -s " + ID + " shell input tap 677 102");
//        command("adb -s " + ID + " shell input tap 428 479");
//        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
//        output= command(command);
//        if(!output.contains("IMS Registration: Registered")) {
//            MyLogger.log.info("IMS is not registered");
//            return 0;
//        }
//        else {
//            MyLogger.log.info("IMS is registered");
//            return 1;
//        }
//    }

    public void waitForLTEToCamp(int waitSeconds) {
        MyLogger.log.info("waiting for " + waitSeconds + " LTE to be camped");
        for (int i = 0; i < waitSeconds / 2; i++) {
            if (getLTEConnectionStatus() == 0) {
                utils.miscUtils.sleep(2000);
            } else
                break;
        }

    }

    public int getLTEConnectionStatus() {
        String output = "";
        String command = "adb -s " + ID + " shell dumpsys telephony.registry";
        output = command(command);
        if (output.contains("mDataConnectionState=2")) {
            MyLogger.log.info("Network is connected to LTE");
            return 1;
        } else {
            MyLogger.log.info("Network is not connected to LTE");
            return 0;
        }
    }

    public boolean getImsRegistrationStatus() {
        String output = "";
        command("adb -s " + ID + " shell am start -a android.intent.action.VIEW tel:*");
        command("adb -s " + ID + " shell input keyevent \"KEYCODE_DEL\"");
        command("adb -s " + ID + " shell input text *#*#4636#*#*");
        command("adb -s " + ID + " shell input tap 259 215");
        command("adb -s " + ID + " shell input tap 677 102");
        command("adb -s " + ID + " shell input tap 428 479");
        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
        output = command(command);
        if (!output.contains("IMS Registration: Registered")) {
            MyLogger.log.info("IMS is not registered");
            return false;
        } else {
            MyLogger.log.info("IMS is registered");
            return true;
        }
    }

    //TODO method is IP
    public int connectToNetwork(String ssid, String password) {
        String output = "";
        command("adb -s " + ID + " root");

        String command = "adb -s " + ID + " shell wpa_cli flush";
        output = command(command);
        System.out.println(output);
        command = "adb -s " + ID + " shell wpa_cli add_network";
        output = command(command);
        System.out.println(output);
        command = "adb -s " + ID + " shell wpa_cli set_network 0 ssid ^'\\\"" + ssid + "\\\"^'";
        output = command(command);
        System.out.println(output);
        //command = "adb -s " + ID + " shell wpa_cli set_network 0 psk ^'\\\""+ password +"\\\"^'";
        command = "adb -s " + ID + " shell wpa_cli set_network 0 ssid ^'\\\"" + password + "\\\"^'";
        output = command(command);
        System.out.println(output);
        command = "adb -s " + ID + " shell wpa_cli enable_network 0";
        output = command(command);
        System.out.println(output);
        command = "adb -s " + ID + " shell wpa_cli save_config";
        output = command(command);
        System.out.println(output);


//        command("adb -s " + ID + " shell wpa_cli set_network 0 ssid "+ssid);
//        command("adb -s " + ID + " shell wpa_cli flush");
//        command("adb -s " + ID + " shell wpa_cli add_network");
//        command("adb -s " + ID + " shell wpa_cli set_network 0 ssid ^'\\\""+ ssid +"\\\"^'");
//        command("adb -s " + ID + " shell wpa_cli set_network 0 psk ^'\\\""+ password +"\\\"^'");
//        command("adb -s " + ID + " shell wpa_cli enable_network 0");
//        command("adb -s " + ID + " shell wpa_cli save_config");
        command = "adb -s " + ID + " shell wpa_cli reassociate";
        output = command(command);
        System.out.println(output);
        if (output.contains("OK")) return 1;
        return 0;


    }

    public int disconnectFromNetwork() {
        String output = "";
        if (getWifiNetworkStatus() == 1) {
            String command = "adb -s " + ID + " shell wpa_cli flush";
            output = command(command);
            if (output.contains("OK")) return 1;
            return 0;
        } else {
            return 0;
        }
    }

    public int getCallStatus() {
        String command = "curl -X POST https://1edd3b95292d4b02bca59be475fc1790@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"dumpsys telephony.registry\"";
        //String command = "adb -s " + ID + " shell dumpsys telephony.registry";
        String output = command(command);
        String[] strArr = null;
        if (output.contains("mCallState")) {
            strArr = output.split("mCallState=");
        }
        if (strArr[1].charAt(0) == '0') {
            MyLogger.log.info("Call is not active on " + ID);
            return 0;
        } else if (strArr[1].charAt(0) == '1') {
            MyLogger.log.info("Call is ringing on " + ID);
            return 1;
        } else {
            MyLogger.log.info("Call is active on " + ID);
            return 2;
        }

    }

    public boolean waitForIncomingCall(int waitSeconds) {
        MyLogger.log.info("waiting for incoming call on the Reference Device");
        boolean status = false;
        for (int i = 0; i < waitSeconds / 2; i++) {
//            while (waitTimer <= timer){
            if (getCallStatus() == 1) {
                MyLogger.log.info("Device is receiving call ");
                status = true;
                break;
            } else
                utils.miscUtils.sleep(3000);
        }
        return status;
    }

    public String getIncomingCallNumber() {
        String command = "adb -s " + ID + " shell dumpsys telephony.registry";
        String output = command(command);
        String[] strArr = null;
        if (output.contains("mCallIncomingNumber")) {
            strArr = output.split("mCallIncomingNumber=");
            return strArr[1].substring(0, 12);
        } else return "";
    }

    public Object startLogcat(final String logID, final String grep) {
        ArrayList<String> pidBefore = getLogcatProcesses();

        Thread logcat = new Thread(new Runnable() {
            @Override
            public void run() {
                if (grep == null) command("adb -s " + ID + " shell logcat -v threadtime > /sdcard/" + logID + ".txt");
                else
                    command("adb -s " + ID + " shell logcat -v threadtime | grep -i '" + grep + "'> /sdcard/" + logID + ".txt");
            }
        });
        logcat.setName(logID);
        logcat.start();
        logcat.interrupt();

        ArrayList<String> pidAfter = getLogcatProcesses();
        DateTime timer = new DateTime();
        timer.start();
        while (!timer.expired(5)) {
            if (pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
            if (pidAfter.size() > 0) break;
            pidAfter = getLogcatProcesses();
        }

        if (pidAfter.size() == 1) return pidAfter.get(0);
        else if (pidAfter.size() > 1)
            throw new RuntimeException("Multiple logcat processes were started when only one was expected!");
        else throw new RuntimeException("Failed to start logcat process!");
    }

    //test purpose
    public void createDirectory(String path, String directoryName) {
        //String pathname=ServerManager.USER_HOME + "\\test-output\\";

        String dirName = path.concat(directoryName);
        File directory = new File(dirName);
        if (!directory.exists()) {
            directory.mkdir();
            if (!directory.exists()) {
                MyLogger.log.info("Directory not created");
            }
        }
    }

    public void stopLocat(Object PID) {
        command("adb -s " + ID + " shell kill " + PID);
    }

/*
    public void checkLogs(String device, Scenario scenario, String reportFolderName) {
        try {
            Runtime.getRuntime().exec("cmd.exe /c mkdir -p " + ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\LOGCAT\\" + scenario.getName().replaceAll(" ", "_") + "\\");
            String pathName = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\LOGCAT\\";
            // String directoryName= ;
            String dirName = pathName.concat(scenario.getName().replaceAll(" ", "_"));

            // createDirectory(ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\","LOGCAT");
            // createDirectory(ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\LOGCAT\\",scenario.getName().replaceAll(" ", "_"));
            */
/*File directory = new File(dirName);
            if(!directory.exists()){
                directory.mkdir();
            }*//*


        } catch (Exception e) {
        }
//String pathLogcat = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\LOGCAT\\" + scenario.getName().replaceAll(" ", "_") + "\\";
        String pathANR = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\ANR\\";
        String pathAppCrash = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\App_Crash\\";
        scenario.getName().replaceAll(" ", "_");

        String appANRName = scenario.getName().replaceAll("", "_") + "-" + device + "-" + new SimpleDateFormat("MMddyyyyhhmmss").format(new Date()) + ".txt";
        String appCrushName = scenario.getName() + "-" + device + "-" + new SimpleDateFormat("MMddyyyyhhmmss").format(new Date()) + ".txt";
        scenario.getName().replaceAll(" ", "_");
        new SimpleDateFormat("MMddyyyyhhmmss").format(new Date());

        String output = driver.manage().logs().get("logcat").toString();

        //Gathering required information for crash report (Sumit)

// int iterator = scenario.getName().getMethod().getCurrentInvocationCount();
        //String loopNumber = itr.getTestName();
        String scenarioname = scenario.getName();
        //String eventName = itr.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("MM/dd/yyyy_hh-mm-ss").format(new Date());
//        String beforeStorage = utils.testInfo.getBeforeDiskUsage();
//        String afterStorage = getDiskSpaceDetails();
//        String beforeRAM = utils.testInfo.getBeforeRAMUsage();
//        String afterRAM = getRAMUsageDetails();
        String CrashFolderName = utils.testInfo.getCrashFolderName();

        if (isAppCrashDetected(output)) {
            try {
                error_reporting.writeDeviceInfo(jsonReader.getJSONValue(Constants.DEVICE_MO, "oem"), jsonReader.getJSONValue(Constants.DEVICE_MO, "udid"),
                        jsonReader.getJSONValue(Constants.DEVICE_MO, "model"), driver.getCapabilities().getVersion(), driver.getPlatformName(),
                        driver.getDeviceTime(), "Summary");
                //Runtime.getRuntime().exec("cmd.exe /c mkdir -p " + ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\App_Crash\\");
                String path = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\";
                String directoryName = path.concat("App_Crash");
                createDirectory(path, directoryName);
            } catch (Exception e) {
            }
            try {
                //update crash report (Sumit)
                error_reporting = new ExcelReporting(CrashFolderName, CrashFolderName);
                error_reporting.LOGAllDetails("AppCrash", scenarioname, timestamp, "AppCrash");
            } catch (Exception e) {
            }
            saveLogcatToFile(output, pathAppCrash, appCrushName);
        }

        if (isAppANRDetected(output)) {
            try {
                error_reporting.writeDeviceInfo(jsonReader.getJSONValue(Constants.DEVICE_MO, "oem"), jsonReader.getJSONValue(Constants.DEVICE_MO, "udid"),
                        jsonReader.getJSONValue(Constants.DEVICE_MO, "model"), driver.getCapabilities().getVersion(), driver.getPlatformName(),
                        driver.getDeviceTime(), "Summary");
                //Runtime.getRuntime().exec("cmd.exe /c mkdir -p " + ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\ANR\\");
                String path = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\";
                String directoryName = path.concat("ANR");
                createDirectory(path, directoryName);
            } catch (Exception e) {
            }
            try {
                //update crash report (Sumit)
                error_reporting = new ExcelReporting(CrashFolderName, CrashFolderName);
                error_reporting.LOGAllDetails("ANR", scenarioname, timestamp, "ANR");
            } catch (Exception e) {
            }
            saveLogcatToFile(output, pathANR, appANRName);
        }

//        if (isLMKDetected(output)) {
////update crash report (Sumit)
//            error_reporting=  new ExcelReporting(CrashFolderName ,CrashFolderName);
//            error_reporting.LOGAllDetails("LMK",appID, loopNumber, testSection, eventName ,iterator,timestamp , beforeStorage, afterStorage,beforeRAM,afterRAM,"LMK");
//            saveLogcatToFile(output, pathLMK, lmkName);
//        }

//        if (isOutOfMemoryDetected(output)) {
////update crash report (Sumit)
//            error_reporting=  new ExcelReporting(CrashFolderName ,CrashFolderName);
//            error_reporting.LOGAllDetails("OutOfMemory",appID, loopNumber, testSection, eventName ,iterator,timestamp , beforeStorage, afterStorage,beforeRAM,afterRAM,"OutOfMemory");
//            saveLogcatToFile(output, pathOOM,oomName);
//        }
        //saveLogcatToFile(output, pathLogcat, scenario.getName().replaceAll(" ", "_"));
    }
*/

    public void saveLogcatFailure(String device, Messages.GherkinDocument.Feature.Scenario scenario, String reportFolderName) {
        try {
            Runtime.getRuntime().exec("cmd.exe /c mkdir -p " + ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\LOGCAT-FAILS\\");
            //createDirectory(ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\","LOGS");
            //createDirectory(ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\","LOGCAT-FAILS");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pathLogcat = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\LOGCAT-FAILS\\";
        String logcatName = "Logcat-" + scenario.getName().replaceAll(" ", "_") + "-" + device + "-" + new SimpleDateFormat("MMddyyyyhhmmss").format(new Date()) + ".txt";

        String output = getLogcat();

        saveLogcatToFile(output, pathLogcat, logcatName);
    }

    public void checkAppLogs(String appPackage) {
        String output = getLogcat();
        if (isAppCrashDetected(output, appPackage)) {
            Assert.fail("APP CRASH DETECTED IN " + appPackage);
        }
        if (isAppANRDetected(output, appPackage)) {
            Assert.fail("APP ANR DETECTED IN " + appPackage);
        }

    }

    public String getLogcat() {

        //return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \" logcat -b all -d\"");

        return command("adb -s " + ID + " logcat -b all -d");
    }

    public boolean checkProtocol(String protocol) {
        String output = command("adb -s " + ID + " logcat -d");
        String[] lines = output.split("\\r?\\n");
        for (String line : lines) {
            //   System.out.println(line);
            if (line.trim().contains("sendNotifyMessageState:") && line.trim().contains("state=SUCCESS") && line.trim().contains("protocol=" + protocol)) {
                MyLogger.log.info("Protocol " + protocol + " is displayed");
                return true;
            }
        }
        return false;
    }

    public boolean checkGroupChatNonIPME() {
        int count = 0;
        String output = command("adb -s " + ID + " logcat -b all  -d");
        String[] lines = output.split("\\r?\\n");
        for (String line : lines) {
            //   System.out.println(line);
            if (line.trim().contains("SendMessageAction: Sending MMS message") || line.trim().contains("InsertNewMessageAction: send MMS")) {
                count++;
            }
        }
        if (count == 2)
            return true;
        return false;
    }

    public void openLink(String url) {
        String command = "adb -s " + ID + " shell am start -n com.android.chrome/com.google.android.apps.chrome.Main -a android.intent.action.VIEW -d " + url;
        command(command);
    }

    public boolean isAppCrashDetected(String logs) {
        if (logs.contains("E AndroidRuntime: FATAL EXCEPTION:")) {
            MyLogger.log.info("APP CRASH DETECTED");
            return true;
        } else {
            MyLogger.log.info("App Crash not detected");
            return false;
        }
    }

    public boolean isAppCrashDetected(String logs, String appPackageName) {
        if (logs.contains("E AndroidRuntime: FATAL EXCEPTION:") && logs.contains("E AndroidRuntime: Process: " + appPackageName)) {
            MyLogger.log.info("APP CRASH DETECTED");
            return true;
        } else {
            MyLogger.log.info("No app crash detected");
            return false;
        }
    }

    public boolean isAppANRDetected(String logs) {
        if (logs.contains("E ActivityManager: ANR in ")) {
            MyLogger.log.info("APP ANR DETECTED");
            return true;
        } else {
            MyLogger.log.info("ANR not detected");
            return false;
        }
    }

    public boolean isAppANRDetected(String logs, String appPackageName) {
        if (logs.contains("E ActivityManager: ANR in " + appPackageName)) {
            MyLogger.log.info("APP ANR DETECTED");
            return true;
        } else {
            MyLogger.log.info("No app ANR detected");
            return false;
        }
    }

    public boolean isLMKDetected(String logs) {
        if (logs.contains("WIN DEATH:")) {
            MyLogger.log.info("LMK DETECTED");
            return true;
        } else {
            MyLogger.log.info("LMK not detected");
            return false;
        }
    }

    public boolean isOutOfMemoryDetected(String logs) {
        if (logs.contains("OutOfMemory")) {
            MyLogger.log.info("Out Of Memory DETECTED");
            return true;
        } else {
            MyLogger.log.info("Out Of Memory not detected");
            return false;
        }
    }

    public void saveLogcatToFile(String logs, String path, String fileName) {
        try {

            PrintWriter out = new PrintWriter(path + fileName);
            out.print(logs);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkRAMandStorage(String reportFolderName, String className, String methodName) {
        try {
            //Runtime.getRuntime().exec("cmd.exe /c mkdir -p " + ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\Storage\\");
            String path = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\";
            String directoryName = path.concat("Storage");
            createDirectory(path, directoryName);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String pathRAM = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\RAM\\";
        String pathStorage = ServerManager.USER_HOME + "\\test-output\\" + reportFolderName + "\\LOGS\\Storage\\";

//        String RAMName = className + "-RAM-"+ new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss").format(new Date()) + "-" + methodName + ".txt";
        String storageName = className + "-STORAGE-" + new SimpleDateFormat("MM-dd-yyyy_hh-mm-ss").format(new Date()) + "-" + methodName + ".txt";

//        String RAMString = getRAMUsageDetails();
        String STORAGEString = getDiskSpaceDetails();
//        saveLogcatToFile(RAMString, pathRAM, RAMName);
        saveLogcatToFile(STORAGEString, pathStorage, storageName);
    }

    public void openRttSettingsPage() {
        String output = "";
        String command = "adb -s " + ID + " shell am start -a android.telecom.action.SHOW_CALL_ACCESSIBILITY_SETTINGS";
        output = command(command);
        if (output.contains("Starting: Intent { act=android.telecom.action.SHOW_CALL_ACCESSIBILITY_SETTINGS }")) {
            MyLogger.log.info("RTT settings page opened");
        } else {
            MyLogger.log.info("RTT settings page not opened");
        }
    }

    public void openCallSettingsPage() {
        String output = "";
        String command = "adb -s " + ID + " shell am start -a android.telecom.action.SHOW_CALL_SETTINGS";
        output = command(command);
        if (output.contains("Starting: Intent { act=android.telecom.action.SHOW_CALL_SETTINGS }")) {
            MyLogger.log.info("RTT settings page opened");
        } else {
            MyLogger.log.info("RTT settings page not opened");
        }
    }

    public boolean isVideoCallOffloadToWifi() {
        MyLogger.log.info("Checking WiFi Call offload");
        String output = getLogcat();
        sleep(4);
        if (output.contains("connectionLabel: Wi-Fi Call")) {
            MyLogger.log.info("The call is offloaded to Wi-Fi");
            return true;
        } else {
            MyLogger.log.info("The call is not offloaded to Wi-Fi");
            return false;
        }
    }

    public boolean isVideoButtonEnabled() {
        String output = "";
        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
        output = command(command);

        if (output.contains("resource-id=\"com.android.contacts:id/third_icon\" class=\"android.widget.ImageView\" package=\"com.android.contacts\" content-desc=\"Make video call\" checkable=\"false\" checked=\"false\" clickable=\"true\" enabled=\"true\"")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isVideoButtonEnabledDialer() {
        String output = "";
        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
        output = command(command);

        if (output.contains("resource-id=\"com.android.dialer:id/dialpad_floating_action_button_vt\" class=\"android.widget.ImageButton\" package=\"com.android.dialer\" content-desc=\"dial\" checkable=\"false\" checked=\"false\" clickable=\"false\" enabled=\"true\"")) {
            return true;
        } else {
            return false;
        }
    }

    public void checkBatteryPopUp() {
        String output = "";
        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
        output = command(command);

        if (output.contains("Please unplug the charger from the wall.")) {
            command("adb -s " + ID + "shell input tap 876 1157");
        }
    }

    public boolean isMessageSent() {
        String output = getLogcat();
        if (output.contains("Mms/Provider/Sms")) {
            MyLogger.log.info("SMS is sent");
            return true;
        } else {
            MyLogger.log.info("SMS is not sent");
            return false;
        }
        //after this need to save logs
    }

    public void saveLogcatToFile(String path, String fileName) {
        String command = "adb -s " + ID + " logcat -d > " + path + "\\" + fileName;
        command(command);
    }

    public void composeEmailWithoutAttachment(String mailTo, String subject, String body, String appPackage) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SEND -t \"text/plain\" " +
                "--es to \"" + mailTo + "\" " +
                "--es subject \"" + subject + "\" " +
                "--es body \"" + body + "\" " +
                appPackage;
        command(command);
    }

    //adb -s BEB00001604  shell am start -a android.intent.action.SEND -t "text/plain"
    // --es to "techmstbf108@gmail.com"
    // --es subject "hi"
    // --es body "hello"
    // --eu android.intent.extra.STREAM file:///storage/emulated/0/Download/Test\ Data/Images/1MB.png com.google.android.gm
    public void composeEmailWithAttachment(String mailTo, String subject, String body, String path, String appPackage) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SEND -t \"text/plain\" " +
                "--es to \"" + mailTo + "\" " +
                "--es subject \"" + subject + "\" " +
                "--es body \"" + body + "\" " +
                "--eu android.intent.extra.STREAM " + path + " " +
                appPackage;
        command(command);
    }

    public void clickSendButton() {
        String command = "adb -s " + ID + " shell input tap 600 91";
        command(command);
    } //adb shell uiautomator dump

    public boolean isEmailSent(String subject) {
        String output = "";
        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
        output = command(command);

        if (output.contains(subject)) {
            return true;
        } else {
            return false;
        }
    }

    public void swipe(int startX, int startY, int endX, int endY, int durationMillis) {

        String command = "adb -s " + ID + " shell input touchscreen swipe " + startX + " " + startY + " " + endX + " " + endY + " " + durationMillis;
        command(command);
    }

    public void tapEmailXY(int x, int y) {
        String command = "adb -s " + ID + " shell input tap " + x + " " + y;
        command(command);
    }

    public void openEmailApp(String packageName, String activityName) {
        String command = "adb -s " + ID + " shell am start -n " + packageName + "/" + activityName;
        command(command);
    }

    public void root() {
        command("adb -s " + ID + " root");
    }

    public void closeEmailApp(String packageName) {
        MyLogger.log.info("Rooting the device");
        String command = "adb -s " + ID + " shell am force-stop " + packageName;
        command(command);
    }
    //TODO Separate APP  Specific ABD commands from ADB core

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {

        }
    }

    //Ashok
    public boolean isMsgSent() {
        MyLogger.log.info("Checking if Message is sent");
        boolean isSent = false;
        sleep(5);
        for (int i = 0; i < 10; i++) {
            MyLogger.log.info("Checking Message status");
            String dump = getUIAutomatorDump();
            if (dump.contains("text=\"Now\"")) {
                isSent = true;
                MyLogger.log.info("Messsage is sent");
                break;
            } else {
                MyLogger.log.info("Waiting for 2 seconds");
                sleep(2);
            }
        }
        if (!isSent)
            MyLogger.log.error("Message is not sent");
        return isSent;
    }

    public void sendMMSWithAttachment(String type, String attachmentFile, String appPackage) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SEND -t " + type + "/* --eu android.intent.extra.STREAM " + attachmentFile + " " + appPackage;
        MyLogger.log.debug("Command is: " + command);
        command(command);
    }

    public void sendMMSWithAttachmentAndText(String receiverPhoneNumber) {
        MyLogger.log.info("Sending MMS Attachment with Text to phoneNumber : " + receiverPhoneNumber);
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SENDTO -d sms:" +
                receiverPhoneNumber;
        MyLogger.log.debug("Command is: " + command);
        command(command);
    }

    public int sendSMSGeneric(String receiverPhoneNumber, String textMessage, String appPackage) {
        MyLogger.log.info("Sending a Text message with Phone Number " + receiverPhoneNumber);
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SENDTO -d sms:" +
                receiverPhoneNumber + " --es sms_body " + textMessage + " " + appPackage;
        MyLogger.log.debug("Command is: " + command);
        String output = command(command);
        MyLogger.log.debug("input receiverPhoneNumber:" + receiverPhoneNumber + " and text message:" + textMessage);
        MyLogger.log.debug("output of the command: " + output);
        if (output.contains("Starting")) {
            MyLogger.log.info("Successfully Sent MMS with Text");
            return 1;
        } else {
            Assert.fail("Message is not sent with attachment");
            return 0;
        }
    }

    public void startPlayStoreActivity() {
        String command = "adb -s " + ID + " shell am start -n com.android.vending/com.android.vending.AssetBrowserActivity";
        command(command);
    }

    public void tab() {
        keyEvent(22);
    }

    public void enter() {
        keyEvent(66);
    }

    public String keyEvent(int keyEventNumber) {
        String command = "adb -s " + ID + " shell input keyevent " + keyEventNumber;
        String output = command(command);
        return output;
    }

    public int startMessagingActivity() {
        MyLogger.log.info("Message activity is getting started");
        // String command = "adb -s " + ID + " shell am start -n com.android.messaging/com.android.messaging.ui.conversationlist.ConversationListActivity";
        String command = "adb -s " + ID + " shell am start -n com.android.mms/com.android.mms.ui.ConversationList";
        String output = command(command);
        //if (output.equals("Starting: Intent { cmp=com.android.messaging/.ui.conversationlist.ConversationListActivity }")) {
        if (output.equals("Starting: Intent { cmp=com.android.mms/.ui.ConversationList }")) {
            MyLogger.log.info("Message Activity is started");
            return 1;
        } else {
            //Assert.fail("Message is not sent with attachment");
            return 0;
        }
    }

    public void tap(int x, int y) {
        String command = "adb -s " + ID + " shell input tap " + x + " " + y;
        command(command);
    }

    public void restartServer() {
        MyLogger.log.info("Restarting ADB Server");
        String command = "adb kill-server";
        command(command);
        utils.miscUtils.sleep(10000);
        command = "adb start-server";
        command(command);
        utils.miscUtils.sleep(10000);
        MyLogger.log.info("Restarted ADB Server");

    }

    public void restartServerQuick() {
        MyLogger.log.info("Restarting ADB Server");
        String command = "adb kill-server";
        command(command);
        utils.miscUtils.sleep(2000);
        command = "adb start-server";
        command(command);
        utils.miscUtils.sleep(2000);
        MyLogger.log.info("Restarted ADB Server");

    }

    public void typeText(String text) {
        MyLogger.log.info("Input text : " + text);
        String command = "adb -s " + ID + " shell input text " + text;
        command(command);
    }

    public String getDiskSpaceDetails() {
        MyLogger.log.info("Getting Storage Disk space usage details");
        return command("adb -s " + ID + " shell df -h");
        //return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \" df -h\"");

    }

    public void playSong(String filePath, int duration) {
        MyLogger.log.info("Playing a Song : " + filePath);
        command("adb -s " + ID + " shell am start -a android.intent.action.VIEW -d " + filePath + " -t audio/mp3 com.google.android.apps.youtube.music");
        utils.miscUtils.sleep(duration);
    }

    public String getRAMUsageDetails() {
        MyLogger.log.info("Getting RAM usage details");
        return command("adb -s " + ID + " shell dumpsys meminfo");
        //return command("curl -X POST https://5765718029324d9786ba7e352bdb4e21@api-dev.headspin.io/v0/adb/" + ID + "/shell -d \"dumpsys meminfo\"");
    }

    public void captureImage() {
        MyLogger.log.info("Capturing Image");
        command("adb -s " + ID + " shell am start -a android.media.action.IMAGE_CAPTURE");
        command("adb -s " + ID + " shell input keyevent KEYCODE_FOCUS");
        command("adb -s " + ID + " shell input keyevent KEYCODE_CAMERA");
    }

    public void captureVideo(int duration) {
        MyLogger.log.info("Capturing Video for " + duration + " Seconds");
        command("adb -s " + ID + " shell am start -a android.media.action.VIDEO_CAPTURE");
        command("adb -s " + ID + " shell input keyevent KEYCODE_FOCUS");
        command("adb -s " + ID + " shell input keyevent KEYCODE_CAMERA");
        utils.miscUtils.sleep(duration);
        command("adb -s " + ID + " shell input keyevent KEYCODE_FOCUS");
        command("adb -s " + ID + " shell input keyevent KEYCODE_CAMERA");
    }

    public void addContact(String contactName, String phoneNumber) {
        MyLogger.log.info("Adding contact; Name: " + contactName + ", phoneNumber : " + phoneNumber);
        String command = "adb -s " + ID + " shell am start -a android.intent.action.INSERT -t vnd.android.cursor.dir/contact -e name " + contactName + " -e phone " + phoneNumber;
        command(command);
        if (waitForElementID("Store the contact to", 10)) {
            tapToAnElementWithBounds(getBoundsID("Google"));
        }
        waitForElementID("SAVE", 10);
        MyLogger.log.info("Tapping on Save Button");
//        tap(651, 107);
        tapToAnElementWithBounds(getBoundsID("SAVE"));
        utils.miscUtils.sleep(3000);
    }

    public String getUIAutomatorDump() {
        String output = "";
        String command = "adb -s " + ID + " exec-out uiautomator dump /dev/tty";
        output = command(command);
        return output;
    }

    public void longPress(int x, int y, int longPressMillis) {
        String command = "adb -s " + ID + " shell input touchscreen swipe " + x + " " + y + " " + x + " " + y + " " + longPressMillis;
        System.out.println(command);
        command(command);

    }

    public void unlockScreen() {
        String command = "adb -s " + ID + " shell input keyevent 82";
        command(command);
    }

    public void clearAppCache(String packageName) {
        String command = "adb -s " + ID + " shell pm clear " + packageName;
        command(command);
    }


    /*public void isDeviceRestarted() {
        int count = 0;
        MyLogger.log.info("Checking if Device got restarted");
        List<String> deviceList = getConnectedDevices();
        MyLogger.log.info("Connected Devices are : " + deviceList);
        for (String deviceID : deviceList) {
            System.out.println(deviceID);
            if (deviceID.trim().equals(ID)) {
                count = 1;
                MyLogger.log.info("DUT is NOT RESTARTED");
            }
        }
        if (count == 0) {
            MyLogger.log.info("***********************************DUT has RESTARTED");
            waitForDevie();
            pullBugReport();
            MyLogger.log.info("************************Terminating the CYCLE ***************************");

        }
    }*/

    public void trimCache() {
        String command = "adb -s " + ID + " shell pm trim-caches 650M";
        command(command);
    }

    public void clearAppCacheBulk(JSONReader jsonReader) {

        String[] appPackages = {
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "messagingapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "cameraapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "browserapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "settingsapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "musicapp", "appPackage"),
                jsonReader.getJSONValue(Constants.DEVICE_MO, "emailapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "telephonyapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "photoapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "playstoreapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "clockapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "calendarapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "launcherscreen", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "youtubemusicapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "contactsapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "mcpttapp", "appPackage"),
//                jsonReader.getJSONValue(Constants.DEVICE_MO, "moviesapp", "appPackage"),
        };

        for (int i = 0; i < appPackages.length; i++) { //adb shell su -c "rm -rf /data/data/<app's package>/cache/*"
            MyLogger.log.info("Clearing cache for " + appPackages[i]);
            String command = "adb -s " + ID + " shell su -c \"rm -rf /data/data/" + appPackages[i] + "/cache/*\"";
            command(command);
        }

    }

    public void openApp(String packageName, String activityName) {
        String command = "adb -s " + ID + " shell am start -n " + packageName + "/" + activityName;
        command(command);
    }

    public void appSwitch() {
        String command = "adb -s " + ID + " shell input keyevent KEYCODE_APP_SWITCH";
        command(command);
    }

    public void goHome() {
        MyLogger.log.info("Going to Home Screen");
        String command = "adb -s " + ID + " shell input keyevent KEYCODE_HOME";
        command(command);
    }

//    public boolean waitForElementID(String elementID, int waitSeconds) {
//        MyLogger.log.info("Checking if element " + elementID + " is present");
//        boolean isDisplayed = false;
//        for (int i = 0; i < waitSeconds / 2; i++) {
//            String dump = getUIAutomatorDump();
////            System.out.println("****************************************" + dump);
//            if (dump.contains(elementID)) {
//                isDisplayed = true;
//                MyLogger.log.info("Element : " + elementID + " is present");
//                break;
//            } else {
//                MyLogger.log.info("Waiting for 2 Seconds...");
//                sleep(2);
//            }
//        }
//        if (!isDisplayed)
//            MyLogger.log.error("Element is not present");
//        return isDisplayed;
//
//    }

    public boolean waitForElementIDIPMEImage(String elementID, int waitSeconds) {
        MyLogger.log.info("Checking if element ID is present");
        boolean isDisplayed = false;
        for (int i = 0; i < waitSeconds / 2; i++) {
            try {
                String[] dump = getUIAutomatorDump().split("com.vinsmart.rcs.messaging:id/message_image");
                if (dump[1].contains(elementID)) {
                    isDisplayed = true;
                    MyLogger.log.info("Element : " + elementID + " is present");
                    break;
                } else {
                    MyLogger.log.info("Waiting for 2 Seconds...");
                    sleep(2);
                }
            } catch (Exception e) {

            }
        }
        if (!isDisplayed)
            MyLogger.log.error("Element is not present");
        return isDisplayed;

    }

    public boolean waitForElementIDIPMEVideo(String elementID, int waitSeconds) {
        MyLogger.log.info("Checking if element ID is present");
        boolean isDisplayed = false;
        for (int i = 0; i < waitSeconds / 2; i++) {
            try {
                String[] dump = getUIAutomatorDump().split("com.vinsmart.rcs.messaging:id/video_thumbnail_image");
                if (dump[1].contains(elementID)) {
                    isDisplayed = true;
                    MyLogger.log.info("Element : " + elementID + " is present");
                    break;
                } else {
                    MyLogger.log.info("Waiting for 2 Seconds...");
                    sleep(2);
                }
            } catch (Exception e) {

            }

        }
        if (!isDisplayed)
            MyLogger.log.error("Element is not present");
        return isDisplayed;

    }

    public boolean waitForElementID(String elementID, double waitSeconds) {
        MyLogger.log.info("Checking if element " + elementID + " is present");
        boolean isDisplayed = false;
        for (double i = 0; i < waitSeconds / 2.5; i++) {
            if (getUIAutomatorDump().contains(elementID)) {
                isDisplayed = true;
                MyLogger.log.info("Element : " + elementID + " is present");
                break;
            }
        }
        if (!isDisplayed)
            MyLogger.log.error("Element " + elementID + " is NOT present");
        return isDisplayed;
    }

    public void closeAppBySwiping(int count) throws InterruptedException {
        appSwitch();
        Thread.sleep(500);
        for (int i = 0; i < count; i++) {
            swipe(100, 1405, 1070, 1405, 200); //clear app
            Thread.sleep(500);
        }
        goHome();
        Thread.sleep(500);
        MyLogger.log.info("Successfully closed the latest app");
    }

    public void closeAllAppsBySwiping(ADB adb) throws InterruptedException {
        appSwitch();
        Thread.sleep(500);
        swipe(50, 700, 650, 700, 50);
        swipe(50, 700, 650, 700, 50);
        Thread.sleep(500);
        adb.tap(158, 638);
        Thread.sleep(500);
        MyLogger.log.info("Successfully closed all the open apps");
    }

    public void deleteAllAlarms() {
        int i = 0;
        while (true) {
            if (waitForElementID("No Alarms", 30)) {
                i++;
                MyLogger.log.info("Tapping on Expand Button");
                tap(636, 413);
                utils.miscUtils.sleep(2000);
                MyLogger.log.info("Tapping on Delete Button");
                tap(153, 731);
                utils.miscUtils.sleep(2000);
            }
            if (i == 7)
                break;
        }
    }

    public void waitForSpecificTime(int sec) {
        try {
            int timer = 0;
            while (timer < sec) {
                Thread.sleep(1000);
                timer += 1;
            }
            MyLogger.log.info("Successfully waited " + sec + " seconds ");
        } catch (Exception e) {
            MyLogger.log.debug("Not able to wait for specific time " + e.getMessage());
            Assert.fail("Not able to wait for specific time " + e.getMessage());
        }
    }

    public void goBack() {
        MyLogger.log.info("Going to Previous Screen");
        String command = "adb -s " + ID + " shell input keyevent KEYCODE_BACK";
        command(command);
    }

    public void emptyTrash() {
        String appPackage = jsonReader.getJSONValue(Constants.DEVICE_MO, "photoapp", "appPackage");
        String appActivity = jsonReader.getJSONValue(Constants.DEVICE_MO, "photoapp", "appActivity");
        openApp(appPackage, appActivity);
        // turning off back up
        if (waitForElementID("Sign in to back up", 10)) {
            tap(639, 1069);
            sleep(1);
            backKeyEvent();
            waitForElementID("Keep backup off?", 10);
            tap(436, 865);
        }
        // handling different types of backup pop ups
        else if (waitForElementID("You’re missing out", 10))
            backKeyEvent();
        else if (waitForElementID("Keep your memories safe", 10))
            tap(181, 1356);
        waitForElementID("Show Navigation Drawer", 10);
        MyLogger.log.info("Tapping on Menu options");
        tap(80, 111);
        waitForElementID("Trash", 10);
        MyLogger.log.info("Tapping on Trash Button");
        tap(139, 896);
        if (!waitForElementID("No items", 5)) {
            waitForElementID("photos_overflow_icon", 10);
            MyLogger.log.info("Tapping on Hamburger Menu");
            tap(678, 101);
            waitForElementID("Empty trash", 10);
            MyLogger.log.info("Tapping on Empty Trash Button");
            tap(425, 193);
            waitForElementID("button1", 10);
            MyLogger.log.info("Confirming Empty Trash");
            tap(602, 829);
            waitForElementID("No items", 10);
        } else
            MyLogger.log.info("There is no any items in Trash Bin");
        closeApp(appPackage);
    }

    public void emptyTrash2() {
        String appPackage = jsonReader.getJSONValue(Constants.DEVICE_MO, "photoapp", "appPackage");
        String appActivity = jsonReader.getJSONValue(Constants.DEVICE_MO, "photoapp", "appActivity");
        openApp(appPackage, appActivity);
        // turning off back up
        if (waitForElementID("Do not back up", 10)) {
            tap(395, 1393);
            sleep(2);
        } else if (waitForElementID("Sign in to back up", 10)) {
            tap(639, 1069);
            sleep(1);
            backKeyEvent();
            waitForElementID("Keep backup off?", 10);
            tap(436, 865);
        }
        // handling different types of backup pop ups
        else if (waitForElementID("You’re missing out", 10))
            backKeyEvent();
        else if (waitForElementID("Keep your memories safe", 10))
            tap(181, 1356);
        waitForElementID("Show Navigation Drawer", 10);
        MyLogger.log.info("Tapping on Menu options");
        tap(80, 111);
        waitForElementID("Trash", 10);
        MyLogger.log.info("Tapping on Trash Button");
        tap(139, 896);
        if (!waitForElementID("No items", 5)) {
            waitForElementID("photos_overflow_icon", 10);
            MyLogger.log.info("Tapping on Hamburger Menu");
            tap(678, 101);
            waitForElementID("Empty trash", 10);
            MyLogger.log.info("Tapping on Empty Trash Button");
            tap(425, 193);
            waitForElementID("button1", 10);
            MyLogger.log.info("Confirming Empty Trash");
            tap(602, 829);
            waitForElementID("No items", 10);
        } else
            MyLogger.log.info("There is no any items in Trash Bin");
        closeApp(appPackage);
    }

    public boolean isActivityRunningForPackages(int currentCount) {
        String pack = "";
        String appPackageCont = jsonReader.getJSONValue(Constants.DEVICE_MO, "contactsapp", "appPackage");
        String appPackageCal = jsonReader.getJSONValue(Constants.DEVICE_MO, "calendarapp", "appPackage");
        String appPackageStore = jsonReader.getJSONValue(Constants.DEVICE_MO, "playstoreapp", "appPackage");
        String appPackageTel = jsonReader.getJSONValue(Constants.DEVICE_MO, "telephonyapp", "appPackage");
        String appPackageMes = jsonReader.getJSONValue(Constants.DEVICE_MO, "messagingapp", "appPackage");
        String appPackageBrow = jsonReader.getJSONValue(Constants.DEVICE_MO, "browserapp", "appPackage");
        String appPackageCam = jsonReader.getJSONValue(Constants.DEVICE_MO, "cameraapp", "appPackage");
        switch (currentCount % 7) {
            case 0:
                pack = appPackageCont;
//                pack = "com.android.contacts";
                break;
            case 1:
                pack = appPackageCal;
//                pack = "com.google.android.calendar";
                break;
            case 2:
                pack = appPackageStore;
//                pack = "com.android.vending";
                break;
            case 3:
                pack = appPackageTel;
//                pack = "com.android.dialer";
                break;
            case 4:
                pack = appPackageMes;
//                pack = "com.android.messaging";
                break;
            case 5:
                pack = appPackageBrow;
//                pack = "com.android.chrome";
                break;
            case 6:
                pack = appPackageCam;
//                pack = "com.hmdglobal.app.camera";
                break;
        }
        MyLogger.log.info("Checking if activity is running for the package: " + pack);
        boolean isRunning = false;
        String output = command("adb -s " + ID + " shell dumpsys activity activities");
        //System.out.println(output);
        if (output.contains("mResumedActivity:")) {
            String[] lines = output.split("\\r?\\n");
            for (String line : lines) {
                //   System.out.println(line);
                if (line.trim().contains("mResumedActivity") && line.contains(pack)) {
                    MyLogger.log.info("Package :" + pack + "  is running");
                    isRunning = true;
                    break;
                }
            }
        }
        return isRunning;
    }

    public void navigateToAnotherAppADB(int currentCount) {
        String appPackageCont = jsonReader.getJSONValue(Constants.DEVICE_MO, "contactsapp", "appPackage");
        String appActivityCont = jsonReader.getJSONValue(Constants.DEVICE_MO, "contactsapp", "appActivity");
        String appPackageCal = jsonReader.getJSONValue(Constants.DEVICE_MO, "calendarapp", "appPackage");
        String appActivityCal = jsonReader.getJSONValue(Constants.DEVICE_MO, "calendarapp", "appActivity");
        String appPackageStore = jsonReader.getJSONValue(Constants.DEVICE_MO, "playstoreapp", "appPackage");
        String appActivityStore = jsonReader.getJSONValue(Constants.DEVICE_MO, "playstoreapp", "appActivity");
        String appPackageTel = jsonReader.getJSONValue(Constants.DEVICE_MO, "telephonyapp", "appPackage");
        String appActivityTel = jsonReader.getJSONValue(Constants.DEVICE_MO, "telephonyapp", "appActivity");
        String appPackageMes = jsonReader.getJSONValue(Constants.DEVICE_MO, "messagingapp", "appPackage");
        String appActivityMes = jsonReader.getJSONValue(Constants.DEVICE_MO, "messagingapp", "appActivity");
        String appPackageBrow = jsonReader.getJSONValue(Constants.DEVICE_MO, "browserapp", "appPackage");
        String appActivityBrow = jsonReader.getJSONValue(Constants.DEVICE_MO, "browserapp", "appActivity");
        String appPackageCam = jsonReader.getJSONValue(Constants.DEVICE_MO, "cameraapp", "appPackage");
        String appActivityCam = jsonReader.getJSONValue(Constants.DEVICE_MO, "cameraapp", "appActivity");

        try {
            if (currentCount % 7 == 0) { //open contacts
                openApp(appPackageCont, appActivityCont);
                waitForActivity(appPackageCont, 10);
//                openApp("com.android.contacts", "com.android.contacts.activities.PeopleActivity");
//                waitForActivity("com.android.contacts", 10);
            } else if (currentCount % 7 == 1) { //open calendar
                openApp(appPackageCal, appActivityCal);
                waitForActivity(appPackageCal, 10);
//                openApp("com.google.android.calendar", "com.android.calendar.LaunchActivity");
//                waitForActivity("com.google.android.calendar", 10);
            } else if (currentCount % 7 == 2) { //open play store
                openApp(appPackageStore, appActivityStore);
                waitForActivity(appPackageStore, 10);
//                openApp("com.android.vending", "com.android.vending.AssetBrowserActivity");
//                waitForActivity("com.android.vending", 10);
            } else if (currentCount % 7 == 3) { //open telephony
                openApp(appPackageTel, appActivityTel);
                waitForActivity(appPackageTel, 10);
//                openApp("com.android.dialer", "com.android.dialer.main.impl.MainActivity");
//                waitForActivity("com.android.dialer", 10);
            } else if (currentCount % 7 == 4) { //open messaging
                openApp(appPackageMes, appActivityMes);
                waitForActivity(appPackageMes, 10);
//                openApp("com.android.messaging", "com.android.messaging.ui.conversationlist.ConversationListActivity");
//                waitForActivity("com.android.messaging", 10);
            } else if (currentCount % 7 == 5) { //open browser
                openApp(appPackageBrow, appActivityBrow);
                waitForActivity(appPackageBrow, 10);
//                openApp("com.android.chrome", "com.google.android.apps.chrome.Main");
//                waitForActivity("com.android.chrome", 10);
            } else if (currentCount % 7 == 6) { //open camera
                openApp(appPackageCam, appActivityCam);
                waitForActivity(appPackageCam, 10);
//                openApp("com.hmdglobal.app.camera", "com.hmdglobal.app.camera.CameraActivity");
//                waitForActivity("com.hmdglobal.app.camera", 10);
            }
        } catch (Exception e) {
            MyLogger.log.info("Failed to tap other application " + e.getMessage(), e);
            Assert.fail("Failed to tap other application " + e.getMessage(), e);
        }
    }

    public void openAppInStore(String packageName) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.VIEW -d 'market://details?id=" + packageName + "'";
        command(command);
    }

    public boolean isVideoRecorded() {
        String output = getLogcat();
        if (output.contains("D MPEG4Writer: Writer thread stopped")) {
            MyLogger.log.info("Video is recorded");
            return true;
        } else {
            MyLogger.log.info("Video is not recorded");
            return false;
        }
        //after this need to save logs
    }

    public boolean isPictureTaken() {
        String output = "";
        for (int i = 0; i < 15; i++) {
            output = getLogcat();
            if (output.contains("CameraApp: [Storage] addImage L215 addImage")) {
                MyLogger.log.info("Picture is taken");
                return true;
            } else {
                MyLogger.log.info("Picture is not taken");
                sleep(1);
            }
        }
        return false;
        //after this need to save logs
    }

    public boolean isPictureOpened() {
        String output = getLogcat();
        if (output.contains("ActivityManager: START u0 {act=com.android.camera.action.REVIEW dat=content://media/external/images/media")) {
            MyLogger.log.info("Picture is opened");
            return true;
        } else {
            MyLogger.log.info("Picture is not opened");
            return false;
        }
        //after this need to save logs
    }

    public boolean isMediaDeleted() {
        String output = getLogcat();
        if (output.contains("No item at content://media/external/images/media")) {
            MyLogger.log.info("Picture is deleted");
            return true;
        } else {
            MyLogger.log.info("Picture is not deleted");
            return false;
        }
        //after this need to save logs
    }

    public boolean isVideoPlayed() {
        String output = getLogcat();
        if (output.contains("I ActivityManager: Displayed com.google.android.apps.photos/.pager.HostPhotoPagerActivity:")) {
            MyLogger.log.info("Video is played");
            return true;
        } else {
            MyLogger.log.info("Video is not played");
            return false;
        }
        //after this need to save logs
    }

    public boolean isYoutubePageOpened() {
        String output = getLogcat();
        if (output.contains("ActivityManager: START u0 {act=android.intent.action.VIEW dat=https://www.youtube.com/")) {
            MyLogger.log.info("Youtube Page is opened");
            return true;
        } else {
            MyLogger.log.info("Youtube Page is not opened");
            return false;
        }
        //after this need to save logs
    }

    public boolean isMusicAppOpened() {
        String output = getLogcat();
        if (output.contains("I ActivityManager: Displayed com.google.android.music/com.android.music.activitymanagement.TopLevelActivity:")) {
            MyLogger.log.info("Music app is opened");
            return true;
        } else {
            MyLogger.log.info("Music app is not opened");
            return false;
        }
        //after this need to save logs
    }

    public boolean isMusicAppClosed() {
        String output = getLogcat();
        if (output.contains("W ActivityManager: Invalid packageName: com.google.android.music")) {
            MyLogger.log.info("Music app is closed");
            return true;
        } else {
            MyLogger.log.info("Music app is not closed");
            return false;
        }
        //after this need to save logs
    }

    public boolean isSongPlayed() {
        String output = getLogcat();
        if (output.contains("I ActivityTaskManager: START u0 {act=android.intent.action.VIEW dat=file:///storage/emulated/0/Download/Test Data/Audio/")) {
            MyLogger.log.info("Song is played");
            return true;
        } else {
            MyLogger.log.info("Song is not played");
            return false;
        }
    }

    public List<String> getMenuOptions() {
        List<String> list = new ArrayList<String>();
        String dump = getUIAutomatorDump();
        String[] arr = dump.split("<node");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains("text")) {
                String[] arr2 = arr[i].split("=");
                for (int j = 0; j < arr2.length; j++) {
                    if (arr2[j].contains("text")) {
                        String[] arr3 = arr2[j + 1].split("\"");
                        System.out.println(arr3[1]);
                        if (arr3[1] != null) {
                            list.add(arr3[1]);
                        }
//                        System.out.println(arr2[j+1]);
                    }
                }
            }
        }
        return list;
    }

    public int[] getBoundsID(String ID) {
        int[] arr4 = new int[4];
        String dump = getUIAutomatorDump();
        if (dump.contains(ID)) {
            String[] arr = dump.split("<");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains(ID)) {
                    String[] arr2 = arr[i].split("=");
                    for (int j = 0; j < arr2.length; j++) {
                        if (arr2[j].contains("bounds")) {
                            String bounds = arr2[j + 1];
                            bounds = bounds.replace("\"", "");
                            bounds = bounds.replace("[", "");
                            bounds = bounds.replace("]", ",");
                            bounds = bounds.replace(">", "");
                            bounds = bounds.replace("/", "");
                            bounds = bounds.replace(" ", "");

                            String[] arr3 = bounds.split(",");

                            for (int k = 0; k < arr3.length; k++) {
                                System.out.println(arr3[k]);
                                arr4[k] = Integer.parseInt(arr3[k]);
                            }

                            break;
                        }
                    }

                    break;
                }
            }
        }
        return arr4;
    }

    public void tapToAnElementWithBounds(int[] bounds) {
        tap((bounds[2] - bounds[0]) / 2 + bounds[0], (bounds[3] - bounds[1]) / 2 + bounds[1]);
    }

    public boolean isElementPresentID(String elementID, int waitSeconds) {
        MyLogger.log.info("Checking if " + elementID + " is present");
        boolean isDisplayed = false;
        for (int i = 0; i < waitSeconds / 2; i++) {
            String dump = getUIAutomatorDump();
            if (dump.contains(elementID)) {
                isDisplayed = true;
                MyLogger.log.info("Element : " + elementID + " is present");
                break;
            } else {
                MyLogger.log.info("Waiting for 2 Seconds...");
                sleep(2);
            }
        }
        if (!isDisplayed)
            MyLogger.log.error(elementID + " is not present");
        return isDisplayed;

    }

    public void backKeyEvent() {
        MyLogger.log.info("Tapped on Navigate Back button");
        String command = "adb -s " + ID + " shell input keyevent KEYCODE_BACK";
        command(command);
    }

    public void powerEvent() {
        MyLogger.log.info("Terminating call on " + ID);
        String command = "adb -s " + ID + " shell input keyevent 26";
        MyLogger.log.debug("Command is: " + command);
        command(command);

    }

    public void callKeyEvent() {
        String command = "adb -s " + ID + " shell input keyevent KEYCODE_CALL";
        command(command);
    }

    public void longPressPTTBtn() {
        String command = "adb -s " + ID + " shell input keyevent  --longpress  KEYCODE_PTT";
        command(command);
    }

    public void pressPTTBtn() {
        String command = "adb -s " + ID + " shell input keyevent  KEYCODE_PTT";
        command(command);
    }

    /*public boolean isDeviceRestarted() {
        String REPORT_FOLDER_NAME = "";

        jsonReader = new JSONReader(ServerManager.USER_HOME + "\\src\\main\\resources\\" + "deviceDetails.json");
        jsonReader = new JSONReader(ServerManager.USER_HOME + "\\src\\main\\resources\\" + "deviceDetails.json");
        String message = "Alert!!! >>>Device: " + ID + " is restarted<<< ;";
        String output = "";
        String command = "adb -s " + ID + " shell settings list global|grep \"boot_count=\"|cut -d= -f2|head -n 1|xargs echo \"Booted:\"|sed 's/$/ times/g'";
        output = command(command);
        String bootCount = "";
        try {
            bootCount = output.split(" ")[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            MyLogger.log.info("Index out of bound exception" + e);
        }
        if (Integer.parseInt(bootCount) > Integer.parseInt(ConfigManager.getProperty("MO_DEVICE_REBOOT_COUNT"))) {
            MyLogger.log.info("Device is restarted");
            TwilioCalls.sendDeviceDisconnectedNotificaitonSMS(jsonReader.getJSONValue("phoneNumber"), message);
            File dirPath = new File(ServerManager.USER_HOME + "\\test-output\\restartedBugreport");
            if (!dirPath.exists())
                try {
                    dirPath.mkdir();
                    MyLogger.log.info("RestartedBugreport directory");
                } catch (Exception e) {
                    MyLogger.log.info("Could not create RestartedBugreport directory");
                }
            MyLogger.log.info("Capturing bugreport for restart");
            captureBugreport();

            return true;
        } else {
            MyLogger.log.info("Device is not restarted");
            return false;
        }
    }*/

    public void changingDarkThemeOption() {
        closeApp("com.android.settings");
        MyLogger.log.info("Changing Dark Theme Option");
        String command = "adb -s " + ID + " shell am start -a android.settings.DISPLAY_SETTINGS";
        command(command);
        waitForElementID("Advanced", 10);
        tap(176, 817);
        waitForElementID("Dark theme", 10);
        tap(188, 804);
        if (waitForElementID("GOT IT", 10))
            tap(598, 906);
    }

//    public void navigateContactsAppMenuOptions() {
//        String[] options = {"Select", "Select all", "Settings"};
//        String[] verifications = {"selection_count_text", "selected", "Navigate up"};
//        MyLogger.log.info("Navigating Contacts App menu options");
//        for (int i = 0; i < options.length; i++) {
//            MyLogger.log.info("Tapping hamburger menu button");
//            tapToAnElementWithBounds(getBoundsID("more_button"));
//            MyLogger.log.info("Tapping " + options[i] + " option.");
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            Assert.assertTrue(waitForElementID(verifications[i], 10));
//            goBack();
//            waitForElementID("more_button", 10);
//        }
//
//    }
//
//    public void navigateGmailAppMenuOptions() {
//        String[] options = {"Primary", "Social", "Promotions",
//                "Starred", "Snoozed", "Important",
//                "Sent", "Scheduled", "Outbox",
//                "Drafts", "All mail", "Spam",
//                "Trash", "Calendar", "Contacts", "Settings", "feedback"};
//
//        MyLogger.log.info("Navigating Gmail App menu options");
//        for (int i = 0; i < options.length; i++) {
//            if (i == 0) {
//                for (int j = 0; j < 5; j++) {
//                    if (waitForElementID("New in Gmail", 15)) {
//                        tap(362, 1288);
//                        if (waitForElementID("com.google.android.gm:id/account_address", 15)) {
//                            tap(362, 1288);
//                            waitForElementID("PRIMARY", 15);
//                            break;
//                        } else {
//                            launchApp("com.google.android.gm", "com.google.android.gm.GmailActivity");
//                            continue;
//                        }
//                    } else {
//                        launchApp("com.google.android.gm", "com.google.android.gm.GmailActivity");
//                        continue;
//                    }
//                }
//
//            }
//            MyLogger.log.info("Tapping hamburger menu button");
//            tapToAnElementWithBounds(getBoundsID("Open navigation drawer"));
//            waitForElementID("Outbox", 10);
//            if (options[i].contains("Drafts"))
//                swipe(300, 1100, 300, 300, 200);
//            MyLogger.log.info("Tapping " + options[i] + " option.");
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            if (!(options[i].equals("Calendar") ||
//                    options[i].equals("Contacts") ||
//                    i == options.length - 1 ||
//                    i == options.length - 2)) {
//                Assert.assertTrue(waitForElementID("com.google.android.gm:id/open_search_bar_text_view", 10));
//            } else if (options[i].equals("Calendar")) {
//                Assert.assertTrue(waitForElementID("com.google.android.calendar", 10));
//                goBack();
//                waitForElementID("Open navigation drawer", 10);
//
//            } else if (options[i].equals("Contacts")) {
//                Assert.assertTrue(waitForElementID("Install", 10));
//                goBack();
//                waitForElementID("Open navigation drawer", 10);
//            } else if (i == options.length - 2) {
//                Assert.assertTrue(waitForElementID("General settings", 10));
//                goBack();
//                waitForElementID("Open navigation drawer", 10);
//            } else if (i == options.length - 1) {
//                Assert.assertTrue(waitForElementID("Describe your issue", 10));
//                goBack();
//                waitForElementID("Open navigation drawer", 10);
//            } else ;
//        }
//
//        goHome();
//
//    }
//    public void navigateCalenderAppMenuOptions() {
//        String[] options = {"Schedule", "Day", "3 days",
//                "Week", "Month", "Search"};
//        //"Events, checked",
//        //                "Reminders, checked", "Holidays, checked",
//        //                "Settings", "feedback"
//        String[] verifications = {"Jump to Today", "Jump to Today",
//                "Jump to Today", "Jump to Today",
//                "Jump to Today", "com.google.android.calendar:id/search_text",
//        };
//        //  "Events, unchecked", "Reminders, unchecked",
//        //                "Holidays, unchecked", "Events from Gmail", "Describe your issue"
//        for (int i = 0; i < options.length; i++) {
//            if (i == 0) {
//                for(int j=0;j<3;j++){
//                    if (isElementPresentID("Make the most of every day.", 6)) {
//                        tap(658, 1279);
//                        waitForElementID("Easy to scan and lovely to look at", 10);
//                        tap(658, 1279);
//                        waitForElementID("Events from Gmail", 10);
//                        tap(366, 1219);
//                        waitForElementID("action_today", 10);
//                        break;
//                    }
//                    else{
//                        launchApp("com.google.android.calendar", "com.android.calendar.LaunchActivity");
//                        continue;
//                    }
//                }
//
//            }
//            MyLogger.log.info("Tapping hamburger menu button");
//            tap(60, 100);
//            waitForElementID("Search", 10);
//            MyLogger.log.info("Tapping " + options[i] + " option");
//           /* if (options[i].equals("Events")) {
//                swipe(300, 1245, 300, 700, 500);
//            }*/
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
//            Assert.assertTrue(isElementPresentID(verifications[i], 10));
//
//            /*if (options[i].equals("Events") || options[i].equals("Reminder") ||
//                    options[i].equals("Holiday")) {
//                tapToAnElementWithBounds(getBoundsID(verifications[i]));
//            }*/
//
//            if (options[i].equals("Search")) {
//                backKeyEvent();
//                if (options[i].equals("Search")) {
//                    backKeyEvent();
//                }
//                waitForElementID("Jump to Today", 10);
//            }
//           /* if (options[i].equals("Search") || options[i].equals("Settings") ||
//                    options[i].equals("feedback")) {
//                backKeyEvent();
//                if (options[i].equals("Search")) {
//                    backKeyEvent();
//                }
//                waitForElementID("Jump to Today", 10);
//            }*/
//        }
//    }
//
//    public void navigatePlayStoreAppMenuOptions() {
//        String[] options = {"My apps &amp; games", "Notifications",
//                "Subscriptions",
//                "Wishlist", "Account", "Payment methods",
//                "Play Protect", "Settings",
//                "Redeem", "Help &amp; feedback",
//                "Parent Guide", "About Google Play"};
//        String[] verifications = {"Installed", "Notifications",
//                "Subscriptions",
//                "Wishlist", "Preferences", "Add payment method",
//                "Recently scanned apps", "Google Play preferences",
//                "Enter code", "Support",
//                "Parent Guide to Google Play", "com.android.chrome"};
//        String[] appOptions = {"Open Movies &amp; TV app", "Open Books app", "Open Music app", "Browse music"};
//        String[] appVerifications = {"com.google.android.videos", "CANCEL", "CANCEL", "com.android.vending"};
////"Play Pass", "Play Points",
////"More fun", "Google Play Points",
//
//
//        MyLogger.log.info("Navigating PlayStore App menu options");
//        for (int i = 0; i < options.length; i++) {
//            MyLogger.log.info("Tapping hamburger menu button");
//            tap(105, 100);
//            MyLogger.log.info("Tapping " + options[i] + " option.");
//            if (options[i].equals("Settings"))
//                swipe(300, 1100, 300, 300, 100);
//
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
//            if ((options[i].equals("Play Pass") || options.equals("Play Points"))) {
//                Assert.assertTrue(!waitForElementID(verifications[i], 10));
//            } else {
//                Assert.assertTrue(waitForElementID(verifications[i], 10));
//            }
//            goBack();
//            if (options[i].equals("Redeem"))
//                goBack();
//        }
//        for (int i = 0; i < 4; i++) {
//            MyLogger.log.info("Tapping hamburger menu button");
//            tap(105, 100);
//            MyLogger.log.info("Tapping " + appOptions[i] + " option.");
//            tapToAnElementWithBounds(getBoundsID(appOptions[i]));
//            if (i == 0 || i == 3) {
//                waitForActivity(appVerifications[i], 10);
//                MyLogger.log.info("Verifying the app opened");
//                Assert.assertTrue(isActivityRunning(appVerifications[i]));
//                MyLogger.log.info("The app successfully opened");
//            } else {
//                MyLogger.log.info("Verifying " + appVerifications[i] + " is present.");
//                Assert.assertTrue(waitForElementID(appVerifications[i], 10));
//            }
//            goBack();
//        }
//
//        MyLogger.log.info("Navigated successfully PlayStore App menu options");
//        goHome();
//    }
//
//    public void navigatePhotosAppMenuOptions() {
//        String[] options = {"Select items", "Settings"};
//        String[] verifications = {"Select items", "Google Photos"};
//        MyLogger.log.info("Navigating Photos App menu options");
//        for (int i = 0; i < options.length; i++) {
//            if (i == 0) {
//                if (waitForElementID("Welcome to Gallery Go", 10)) {
//                    tap(354, 1158);
//                    waitForElementID("Folders", 10);
//                }
//            }
//            MyLogger.log.info("Tapping hamburger menu button");
//            tapToAnElementWithBounds(getBoundsID("More options"));
//            MyLogger.log.info("Tapping " + options[i] + " option.");
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
//            Assert.assertTrue(waitForElementID(verifications[i], 10));
//            goBack();
//        }
//        MyLogger.log.info("Navigated successfully Photos App menu options");
//        goHome();
//    }
//
//
//    public void navigateChromeAppMenuOptions() {
//        String[] options = {"New tab", "Bookmarks", "Recent tabs",
//                "History", "Downloads", "Settings",
//                "Help &amp; feedback", "New incognito tab"};
//        String[] verifications = {"More options", "Mobile bookmarks",
//                "Recently closed", "clear_browsing_data_button",
//                "MY FILES", "Sync and Google services",
//                "Support", "You've gone incognito"};
//
//        MyLogger.log.info("Navigating Chrome App menu options");
//        for (int i = 0; i < options.length; i++) {
//            if (i == 0) {
//                waitForElementID("Welcome to Chrome", 10);
//                tapToAnElementWithBounds(getBoundsID("com.android.chrome:id/terms_accept"));
//                waitForElementID("No thanks", 10);
//                tapToAnElementWithBounds(getBoundsID("No thanks"));
//                waitForElementID("com.android.chrome:id/home_button", 10);
//            } else {
//                waitForElementID("com.android.chrome:id/home_button", 10);
//            }
//
//            MyLogger.log.info("Tapping hamburger menu button");
//            tap(666, 94);
//            if (i == 0) {
//                Assert.assertTrue(isElementPresentID("Desktop site", 10));
//                MyLogger.log.info("Desktop site option is present");
//            }
//            MyLogger.log.info("Tapping " + options[i] + " option.");
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            if (i == options.length - 2)
//                sleep(2);
//            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
//            Assert.assertTrue(waitForElementID(verifications[i], 10));
//            if (i != 0)
//                goBack();
//        }
//        MyLogger.log.info("Navigated successfully Chrome App menu options");
//        goHome();
//    }
//
//
//    public void navigateMoviesAppMenuOptions() {
//        String[] options = {"Settings", "Manage services", "Help &amp; feedback"};
//        String[] verifications = {"Parental controls", "Where do you watch stuff?", "Support"};
//        MyLogger.log.info("Navigating Movies App menu options");
//        for (int i = 0; i < options.length; i++) {
//            MyLogger.log.info("Tapping hamburger menu button");
//            tap(91, 110);
//            Assert.assertTrue(waitForElementID("Settings", 10));
//            MyLogger.log.info("Tapping " + options[i] + " option.");
//            tapToAnElementWithBounds(getBoundsID(options[i]));
//            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
//            Assert.assertTrue(waitForElementID(verifications[i], 10));
//            goBack();
//        }
//        MyLogger.log.info("Navigated successfully Movies App menu options");
//        goHome();
//    }

    public int getDeviceRebootTimes() {
        int count = 0;
        String output = "";
        String command = "adb -s " + ID + " shell settings list global|grep \"boot_count=\"|cut -d= -f2|head -n 1|xargs echo \"Booted:\"|sed 's/$/ times/g'";
        output = command(command);
        System.out.println(output);
        String bootCount = "";
        if (!output.contains("Booted:"))
            output = command(command);
        try {
            bootCount = output.split(" ")[1];

        } catch (ArrayIndexOutOfBoundsException e) {
            MyLogger.log.info("Index out of bound exception" + e);
        }
        count = Integer.parseInt(bootCount);
        return count;
    }

    public boolean confirmPageLoadedAdb() {
        for (int i = 0; i < 30; i++) {
            if (!getUIAutomatorDump().contains("android.widget.ProgressBar")) {
                return true;
            }
            sleep(1);
        }
        return false;
    }

    public boolean isThemeChanged() {
        String output = "";
        for (int i = 0; i < 9; i++) {
            output = getLogcat();
            if (output.contains("DisplaySettings/dark_ui_mode")) {
                MyLogger.log.info("Theme is changed");
                return true;
            } else {
                MyLogger.log.info("Theme is not changed");
                sleep(1);
            }
        }
        return false;
    }

    public void closeCMD() {
        MyLogger.log.info("Closing all CMDs");
        command("taskkill /IM cmd.exe");
    }

    public ArrayList<String> getWiFiRouter(String networkName) {
        String text = "";
        String password = "";
        ArrayList<String> info = new ArrayList<String>();
        try {
            switch (networkName) {
                case "Apple":
                    text = "AppleSta";
                    password = "techm1234";
                    break;
                case "SamsungS9":
                    text = "SamsungS9";
                    password = "987654321";
                    break;
                case "TMA":
                    text = "TMA Redmond";
                    password = "Tech%12345";
                    break;
                case "TechM":
                    text = "TechmAmati";
                    password = "techm123$";
                    break;
                case "TechM5G":
                    text = "TechmAmati_5G";
                    password = "techm123$";
                    break;
                case "Asus":
                    text = "ASUS";
                    password = "password1";
                    break;
                case "NDA":
                    text = "NDA Project";
                    password = "TMARedmond2018";
                    break;
                case "SouthSafaris":
                    text = "South-Safaris";
                    password = "Southspice3025";
                    break;
                case "ATT":
                    text = "ATT-WIFI-423f";
                    password = "W4U26f73";
                    break;
                case "REFERENCE102":
                    text = "REFERENCE102";
                    password = "techm123";
                    break;
                case "REFERENCE1":
                    text = "REFERENCE1";
                    password = "techmahindra";
                    break;
                case "REFERENCE2":
                    text = "REFERENCE2";
                    password = "techmahindra";
                    break;
                case "hotspotv":
                    text = "hotspotv";
                    password = "techmpassword";
                    break;
                case "hotspot":
                    text = "hotspot";
                    password = "techmpassword";
                    break;
                case "ORBI00":
                    text = "ORBI00";
                    password = "sunnycartoon490";
                    break;
                case "Art":
                    text = "OnHub";
                    password = "84e2f6ca";
                    break;
                case "Vahdet":
                    text = "NETGEAR81-5G";
                    password = "slowlake441";
                    break;
                case "StabilityHS1":
                    text = "StabilityHS1";
                    password = "techmahindra";
                    break;
                case "TechmHS1":
                    text = "TechmHS1";
                    password = "Techm%12345";
                    break;
            }
            info.add(text);
            info.add(password);
           /* for (int i = 0; i < 5; i++) {
                if(getUIAutomatorDump().contains(text)) {
                    return info;
                } else {
                    sleep(2);
                }
            }*/

        } catch (Exception e) {
            MyLogger.log.debug("Not able to find " + text + " network " + e.getMessage());
        }
        return info;
    }

    public int[] getBoundsContact(String contactName) {
        int[] arr4 = new int[4];
        String dump = getUIAutomatorDump();
        if (dump.contains(contactName)) {
            String[] arr = dump.split("<");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].contains("text=\"" + contactName + "\"")) {
                    String[] arr2 = arr[i].split("=");
                    for (int j = 0; j < arr2.length; j++) {
                        if (arr2[j].contains("bounds")) {
                            String bounds = arr2[j + 1];
                            bounds = bounds.replace("\"", "");
                            bounds = bounds.replace("[", "");
                            bounds = bounds.replace("]", ",");
                            bounds = bounds.replace(">", "");
                            bounds = bounds.replace("/", "");
                            bounds = bounds.replace(" ", "");

                            String[] arr3 = bounds.split(",");

                            for (int k = 0; k < arr3.length; k++) {
                                System.out.println(arr3[k]);
                                arr4[k] = Integer.parseInt(arr3[k]);
                            }

                            break;
                        }
                    }

                    break;
                }
            }
        }
        return arr4;
    }

    public void clearStorageIPME() {
        String command = "adb -s " + ID + " shell pm clear com.summit.nexos.sdk.app.service";
        command(command);
        command = "adb -s " + ID + " shell am force-stop com.summit.nexos.sdk.app.service";
        command(command);
        command = "adb -s " + ID + " shell pm trim-caches 650M";
        command(command);
    }

    public void addCalendarEvent(String title) {
        String command = "adb -s " + ID + " shell am start -a android.intent.action.INSERT -t vnd.android.cursor.item/event -e title " + title;
        command(command);
    }

    public void deletePhotosFromShell() {
        String command = "adb -s " + ID + " shell \"cd storage/emulated/0/DCIM/Camera && ls \"";
        String output = command(command).replace("\n", " ").replace("\r", " ").replace("\r\n", " ");
        if (output.contains("IMG") || output.contains("VID")) {
            MyLogger.log.info("Removing following items from Camera folder in shell: " + output);
            String command2 = "adb -s " + ID + " shell \"cd storage/emulated/0/DCIM/Camera && rm " + output + " \"";
            command(command2);
        } else {
            System.out.println("Nothing to delete from shell Camera folder");
        }
    }

    public void disableRTT(ADB adbDUT, ADB adbReference, String appPackage, String appActivity) {
        Callable<Void> callable1 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                adbDUT.closeApp(appPackage);
                adbDUT.openApp(appPackage, appActivity);
                adbDUT.waitForElementID("com.android.dialer:id/main_options_menu_button", 10);
                adbDUT.tap(664, 112);
                adbDUT.waitForElementID("Settings", 10);
                adbDUT.tap(469, 283);
                adbDUT.waitForElementID("Accessibility", 10);
                adbDUT.tap(140, 785);
                adbDUT.waitForElementID("RTT call option", 10);
                adbDUT.tap(200, 352);
                adbDUT.waitForElementID("CANCEL", 10);

                //turn off
                adbDUT.tap(300, 732);
                adbDUT.waitForElementID("Accessibility", 10);
                adbDUT.closeApp(appPackage);
                adbDUT.backKeyEvent();
                return null;
            }
        };

        Callable<Void> callable2 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                adbReference.closeApp(appPackage);
                adbReference.openApp(appPackage, appActivity);
                adbReference.waitForElementID("com.android.dialer:id/main_options_menu_button", 10);
                adbReference.tap(664, 112);
                adbReference.waitForElementID("Settings", 10);
                adbReference.tap(469, 283);
                adbReference.waitForElementID("Accessibility", 10);
                adbReference.tap(140, 785);
                adbReference.waitForElementID("RTT call option", 10);
                adbReference.tap(200, 352);
                adbReference.waitForElementID("CANCEL", 10);

                //turn off
                adbReference.tap(300, 732);
                adbReference.waitForElementID("Accessibility", 10);
                adbReference.closeApp(appPackage);
                adbReference.backKeyEvent();
                return null;
            }
        };

        //add to a list
        List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(callable1);
        taskList.add(callable2);

        //create a pool executor with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            //start the threads and wait for them to finish
            executor.invokeAll(taskList);
        } catch (InterruptedException ie) {
            //do something if you care about interruption;
        }
        executor.shutdown();
    }

    public void enableRTT(ADB adbDUT, ADB adbReference, String appPackage, String appActivity) {
        Callable<Void> callable1 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                adbDUT.closeApp(appPackage);
                adbDUT.openApp(appPackage, appActivity);
                adbDUT.waitForElementID("com.android.dialer:id/main_options_menu_button", 10);
                adbDUT.tap(664, 112);
                adbDUT.waitForElementID("Settings", 10);
                adbDUT.tap(469, 283);
                adbDUT.waitForElementID("Accessibility", 10);
                adbDUT.tap(140, 785);
                adbDUT.waitForElementID("RTT call option", 10);
                adbDUT.tap(200, 352);
                adbDUT.waitForElementID("CANCEL", 10);

                //turn off
                adbDUT.tap(300, 822);
                adbDUT.waitForElementID("Accessibility", 10);
                adbDUT.closeApp(appPackage);
                adbDUT.backKeyEvent();
                return null;
            }
        };

        Callable<Void> callable2 = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                adbReference.closeApp(appPackage);
                adbReference.openApp(appPackage, appActivity);
                adbReference.waitForElementID("com.android.dialer:id/main_options_menu_button", 10);
                adbReference.tap(664, 112);
                adbReference.waitForElementID("Settings", 10);
                adbReference.tap(469, 283);
                adbReference.waitForElementID("Accessibility", 10);
                adbReference.tap(140, 785);
                adbReference.waitForElementID("RTT call option", 10);
                adbReference.tap(200, 352);
                adbReference.waitForElementID("CANCEL", 10);

                //turn off
                adbReference.tap(300, 822);
                adbReference.waitForElementID("Accessibility", 10);
                adbReference.closeApp(appPackage);
                adbReference.backKeyEvent();
                return null;
            }
        };

        //add to a list
        List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(callable1);
        taskList.add(callable2);

        //create a pool executor with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            //start the threads and wait for them to finish
            executor.invokeAll(taskList);
        } catch (InterruptedException ie) {
            //do something if you care about interruption;
        }
        executor.shutdown();
    }

    public int sendSMSArmStrong(String receiverPhoneNumber, String textMessage) {
        MyLogger.log.info("Sending a Text message with Phone Number " + receiverPhoneNumber);
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SENDTO -d sms:" + receiverPhoneNumber + " --es sms_body " + textMessage + " com.android.messaging";
        MyLogger.log.debug("Command is: " + command);
        String output = command(command);
        MyLogger.log.debug("input receiverPhoneNumber:" + receiverPhoneNumber + " and text message:" + textMessage);
        MyLogger.log.debug("output of the command: " + output);
        if (output.contains("Starting")) {
            MyLogger.log.info("Successfully Sent MMS with Text");
            return 1;
        } else {
            Assert.fail("Message is not sent with attachment");
            return 0;
        }
    }

    public int sendMMSWithAttachmentAndText(String receiverPhoneNumber, String attachmentFile, String textMessage) {
        MyLogger.log.info("Sending MMS Attachment with Text to phoneNumber : " + receiverPhoneNumber);
        String command = "adb -s " + ID + " shell am start -a android.intent.action.SEND -t image/jpg --es address " + receiverPhoneNumber + " --es sms_body " + textMessage + " --eu android.intent.extra.STREAM " + attachmentFile + " com.android.messaging";
        MyLogger.log.debug("Command is: " + command);
        String output = command(command);
        MyLogger.log.debug("input receiverPhoneNumber:" + receiverPhoneNumber + " and filepath:" + attachmentFile + " text message: " + textMessage);
        MyLogger.log.debug("output of the command: " + output);
        if (output.contains("Starting: Intent { act=android.intent.action.SEND typ=image/jpg pkg=com.android.messaging (has extras) }")) {
            MyLogger.log.info("Successfully Sent MMS with Text");
            return 1;
        } else {
            Assert.fail("Message is not sent with attachment");
            return 0;
        }
    }

    public boolean isMsgSentHMD(String message) {
        MyLogger.log.info("Checking if Message is sent");
        boolean isSent = false;
        for (int i = 0; i < 15; i++) {
            MyLogger.log.info("Checking Message status");
            String dump = getUIAutomatorDump();
            int msgIndex = dump.lastIndexOf(message);
            int statusIndex = dump.lastIndexOf("Now");
            System.out.println(msgIndex);
            System.out.println(statusIndex);
            if (statusIndex > msgIndex) {
                isSent = true;
                MyLogger.log.info("Messsage is sent");
                break;
            } else {
                MyLogger.log.info("Waiting for 2 seconds");
                sleep(1);
            }
        }

        if (isSent == false)
            MyLogger.log.error("Message is not sent");
        return isSent;
    }

    public void navigateContactsAppMenuOptions() {
        String[] options = {"Cricket Service Contacts", "Imported on", "Create label", "Phone contact", "Settings"};
        String[] verifications = {"More options", "More option", "Create label", "Open navigation drawer", "My info"};
        MyLogger.log.info("Navigating Contacts App menu options");
        for (int i = 0; i < options.length; i++) {
            MyLogger.log.info("Tapping hamburger menu button");
            tapToAnElementWithBounds(getBoundsID("Open navigation drawer"));
            waitForElementID("Settings", 10);

            MyLogger.log.info("Tapping " + options[i] + " option.");
            tapToAnElementWithBounds(getBoundsID(options[i]));
            if (options[i].equals("Create label")) {
                if (waitForElementID("Choose account", 10)) {
                    tap(305, 752);
                }
            }
            Assert.assertTrue(waitForElementID(verifications[i], 10));
            goBack();
            if (options[i].equals("Create label")) {
                goBack();
            }
            waitForElementID("Open navigation drawer", 10);
        }

    }

    public void navigateGmailAppMenuOptions() {
        String[] options = {"Primary", "Social", "Promotions",
                "Starred", "Snoozed", "Important",
                "Sent", "Scheduled", "Outbox",
                "Drafts", "All mail", "Spam",
                "Trash", "Calendar", "Contacts", "Settings", "feedback"};

        MyLogger.log.info("Navigating Gmail App menu options");
        for (int i = 0; i < options.length; i++) {
            if (i == 0) {
                for (int j = 0; j < 5; j++) {
                    if (waitForElementID("New in Gmail", 15)) {
                        tap(350, 1467);
                        if (waitForElementID("com.google.android.gm:id/account_address", 15)) {
                            tap(350, 1467);
                            waitForElementID("PRIMARY", 15);
                            break;
                        } else {
                            launchApp("com.google.android.gm", "com.google.android.gm.GmailActivity");
                            continue;
                        }
                    } else {
                        launchApp("com.google.android.gm", "com.google.android.gm.GmailActivity");
                        continue;
                    }
                }

            }
            MyLogger.log.info("Tapping hamburger menu button");
            tapToAnElementWithBounds(getBoundsID("Open navigation drawer"));
            waitForElementID("Outbox", 10);
            if (options[i].contains("Drafts"))
                swipe(300, 1100, 300, 300, 200);
            MyLogger.log.info("Tapping " + options[i] + " option.");
            tapToAnElementWithBounds(getBoundsID(options[i]));
            if (!(options[i].equals("Calendar") ||
                    options[i].equals("Contacts") ||
                    i == options.length - 1 ||
                    i == options.length - 2)) {
                Assert.assertTrue(waitForElementID("com.google.android.gm:id/open_search_bar_text_view", 10));
            } else if (options[i].equals("Calendar")) {
                Assert.assertTrue(waitForElementID("com.google.android.calendar", 10));
                goBack();
                waitForElementID("Open navigation drawer", 10);

            } else if (options[i].equals("Contacts")) {
                Assert.assertTrue(waitForElementID("Install", 10));
                goBack();
                waitForElementID("Open navigation drawer", 10);
            } else if (i == options.length - 2) {
                Assert.assertTrue(waitForElementID("General settings", 10));
                goBack();
                waitForElementID("Open navigation drawer", 10);
            } else if (i == options.length - 1) {
                Assert.assertTrue(waitForElementID("Describe your issue", 10));
                goBack();
                waitForElementID("Open navigation drawer", 10);
            } else ;
        }

        goHome();

    }

    public void navigateCalenderAppMenuOptions() {
        String[] options = {"Schedule", "Day", "3 days",
                "Week", "Month", "Search"};
        //"Events, checked",
        //                "Reminders, checked", "Holidays, checked",
        //                "Settings", "feedback"
        String[] verifications = {"Jump to Today", "Jump to Today",
                "Jump to Today", "Jump to Today",
                "Jump to Today", "com.google.android.calendar:id/search_text",
        };
        //  "Events, unchecked", "Reminders, unchecked",
        //                "Holidays, unchecked", "Events from Gmail", "Describe your issue"
        for (int i = 0; i < options.length; i++) {
            if (i == 0) {
                for (int j = 0; j < 3; j++) {
                    if (isElementPresentID("Make the most of every day.", 6)) {
                        tap(664, 1459);
                        waitForElementID("Easy to scan and lovely to look at", 10);
                        tap(664, 1459);
                        waitForElementID("Events from Gmail", 10);
                        tap(350, 1409);
                        waitForElementID("action_today", 10);
                        break;
                    } else {
                        launchApp("com.google.android.calendar", "com.android.calendar.LaunchActivity");
                        continue;
                    }
                }

            }
            MyLogger.log.info("Tapping hamburger menu button");
            tap(60, 101);
            waitForElementID("Search", 10);
            MyLogger.log.info("Tapping " + options[i] + " option");
           /* if (options[i].equals("Events")) {
                swipe(300, 1245, 300, 700, 500);
            }*/
            tapToAnElementWithBounds(getBoundsID(options[i]));
            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
            Assert.assertTrue(isElementPresentID(verifications[i], 10));

            /*if (options[i].equals("Events") || options[i].equals("Reminder") ||
                    options[i].equals("Holiday")) {
                tapToAnElementWithBounds(getBoundsID(verifications[i]));
            }*/

            if (options[i].equals("Search")) {
                backKeyEvent();
                if (options[i].equals("Search")) {
                    backKeyEvent();
                }
                waitForElementID("Jump to Today", 10);
            }
           /* if (options[i].equals("Search") || options[i].equals("Settings") ||
                    options[i].equals("feedback")) {
                backKeyEvent();
                if (options[i].equals("Search")) {
                    backKeyEvent();
                }
                waitForElementID("Jump to Today", 10);
            }*/
        }
    }

    public void navigatePlayStoreAppMenuOptions() {
        String[] options = {"My apps &amp; games", "Notifications",
                "Subscriptions",
                "Wishlist", "Account", "Payment methods",
                "Play Protect", "Settings",
                "Redeem", "Help &amp; feedback",
                "Parent Guide", "About Google Play"};
        String[] verifications = {"Installed", "Notifications",
                "Subscriptions",
                "Wishlist", "Preferences", "Add payment method",
                "Recently scanned apps", "Google Play preferences",
                "Enter code", "Support",
                "Parent Guide to Google Play", "com.android.chrome"};
        String[] appOptions = {"Open Movies &amp; TV app", "Open Books app", "Open Music app", "Browse music"};
        String[] appVerifications = {"com.google.android.videos", "CANCEL", "CANCEL", "com.android.vending"};
//"Play Pass", "Play Points",
//"More fun", "Google Play Points",


        MyLogger.log.info("Navigating PlayStore App menu options");
        for (int i = 0; i < options.length; i++) {
            MyLogger.log.info("Tapping hamburger menu button");
            tap(85, 103);
            MyLogger.log.info("Tapping " + options[i] + " option.");
            if (options[i].equals("Settings"))
                swipe(300, 1100, 300, 300, 100);

            tapToAnElementWithBounds(getBoundsID(options[i]));
            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
            if ((options[i].equals("Play Pass") || options.equals("Play Points"))) {
                Assert.assertTrue(!waitForElementID(verifications[i], 10));
            } else {
                Assert.assertTrue(waitForElementID(verifications[i], 10));
            }
            goBack();
            if (options[i].equals("Redeem"))
                goBack();
        }
        for (int i = 0; i < 4; i++) {
            MyLogger.log.info("Tapping hamburger menu button");
            tap(85, 103);
            MyLogger.log.info("Tapping " + appOptions[i] + " option.");
            tapToAnElementWithBounds(getBoundsID(appOptions[i]));
            if (i == 0 || i == 3) {
                waitForActivity(appVerifications[i], 10);
                MyLogger.log.info("Verifying the app opened");
                Assert.assertTrue(isActivityRunning(appVerifications[i]));
                MyLogger.log.info("The app successfully opened");
            } else {
                MyLogger.log.info("Verifying " + appVerifications[i] + " is present.");
                Assert.assertTrue(waitForElementID(appVerifications[i], 10));
            }
            goBack();
        }

        MyLogger.log.info("Navigated successfully PlayStore App menu options");
        goHome();
    }

    public void navigatePhotosAppMenuOptions() {
        String[] options = {"Device folders", "Archive", "Trash", "Settings", "PhotoScan", "feedback"};
        String[] verifications = {"Navigate up", "Navigate up", "Navigate up", "Navigate up", "com.android.vending", "Support"};
        MyLogger.log.info("Navigating Photos App menu options");
        for (int i = 0; i < options.length; i++) {
            if (i == 0) {
                if (waitForElementID("Keep your memories safe", 10)) {
                    tapToAnElementWithBounds(getBoundsID("Do not back up"));
                    waitForElementID("Folders", 10);
                } else if (waitForElementID("You’re missing out", 10))
                    backKeyEvent();
            }
            MyLogger.log.info("Tapping hamburger menu button");
            tapToAnElementWithBounds(getBoundsID("Show Navigation Drawer"));
            MyLogger.log.info("Tapping " + options[i] + " option.");
            tapToAnElementWithBounds(getBoundsID(options[i]));
            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
            Assert.assertTrue(waitForElementID(verifications[i], 10));
            goBack();
            if (options.equals("PhotoScan")) {
                goBack();
            }
        }
        MyLogger.log.info("Navigated successfully Photos App menu options");
        goHome();
    }

    public void navigateChromeAppMenuOptions() {
        String[] options = {"New tab", "Bookmarks", "Recent tabs",
                "History", "Downloads", "Settings",
                "Help &amp; feedback", "New incognito tab"};
        String[] verifications = {"More options", "Mobile bookmarks",
                "Recently closed", "clear_browsing_data_button",
                "MY FILES", "Sync and Google services",
                "Support", "You've gone incognito"};

        MyLogger.log.info("Navigating Chrome App menu options");
        for (int i = 0; i < options.length; i++) {
            if (i == 0) {
                if (waitForElementID("Welcome to Chrome", 10)) {
                    tapToAnElementWithBounds(getBoundsID("com.android.chrome:id/terms_accept"));
                    waitForElementID("No thanks", 10);
                    tapToAnElementWithBounds(getBoundsID("No thanks"));
                    waitForElementID("com.android.chrome:id/home_button", 10);
                }

            } else {
                waitForElementID("com.android.chrome:id/home_button", 10);
            }

            MyLogger.log.info("Tapping hamburger menu button");
            tapToAnElementWithBounds(getBoundsID("More options"));
            if (i == 0) {
                Assert.assertTrue(isElementPresentID("Desktop site", 10));
                MyLogger.log.info("Desktop site option is present");
            }
            MyLogger.log.info("Tapping " + options[i] + " option.");
            tapToAnElementWithBounds(getBoundsID(options[i]));
            if (i == options.length - 2)
                sleep(2);
            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
            Assert.assertTrue(waitForElementID(verifications[i], 10));
            if (i != 0)
                goBack();
        }
        MyLogger.log.info("Navigated successfully Chrome App menu options");
        goHome();
    }

    public void navigateMoviesAppMenuOptions() {
        String[] options = {"Settings", "Manage services", "Help &amp; feedback"};
        String[] verifications = {"Parental controls", "Where do you watch stuff?", "Support"};
        MyLogger.log.info("Navigating Movies App menu options");
        for (int i = 0; i < options.length; i++) {
            MyLogger.log.info("Tapping hamburger menu button");
            tapToAnElementWithBounds(getBoundsID("Show navigation drawer"));
            Assert.assertTrue(waitForElementID("Settings", 10));
            MyLogger.log.info("Tapping " + options[i] + " option.");
            tapToAnElementWithBounds(getBoundsID(options[i]));
            MyLogger.log.info("Verifying " + verifications[i] + " is present.");
            Assert.assertTrue(waitForElementID(verifications[i], 10));
            goBack();
        }
        MyLogger.log.info("Navigated successfully Movies App menu options");
        goHome();
    }

    public boolean waitForWFCTrue(int waitSeconds) {
        MyLogger.log.info("Waiting for WiFi Call settings to connect");
        for (int i = 0; i < waitSeconds; i++) {
            String output = getLogcat();
            if (output.contains("imsRegoverWfc true")) {
                MyLogger.log.info("WiFi Call + is CONNECTED; ready to make WIFI Call");
                return true;
            } else {
                MyLogger.log.info("Waiting for 1 seconds");
                sleep(1);
            }
        }
        return false;
    }

    public void handleAirplaneModePopUp() {
        String command = "adb -s " + ID + " shell am start -a android.settings.WIRELESS_SETTINGS";
        command(command);
        sleep(2);
        waitForElementID("Advanced", 10);

        if (!isAirplaneModeOn()) {
            MyLogger.log.info("AirPlan mode is turned OFF; Turning On AirPlane mode");
            int[] bounds = getBoundsID("Advanced");
            tapToAnElementWithBounds(bounds);
            waitForElementID("Automatic", 10);
            int[] bounds1 = getBoundsID("Airplane mode");
            tapToAnElementWithBounds(bounds1);
            waitForElementID("Do not show again", 10);
            tapToAnElementWithBounds(getBoundsID("Do not show again"));
            tapToAnElementWithBounds(getBoundsID("TURN ON"));
            waitForElementID("Automatic", 10);
            tapToAnElementWithBounds(bounds1);

        }
    }

    public void handleRTTPopUp(String appPackage, String appActivity) {
        closeApp(appPackage);
        openApp(appPackage, appActivity);
        waitForElementID("com.android.dialer:id/main_options_menu_button", 10);
        tapToAnElementWithBounds(getBoundsID("com.android.dialer:id/main_options_menu_button"));
        waitForElementID("Settings", 10);
        tapToAnElementWithBounds(getBoundsID("Settings"));
        waitForElementID("Accessibility", 10);
        tapToAnElementWithBounds(getBoundsID("Accessibility"));
        waitForElementID("RTT call option", 10);
        tapToAnElementWithBounds(getBoundsID("RTT call option"));
        waitForElementID("Do not show again", 10);
        tapToAnElementWithBounds(getBoundsID("Do not show again"));
        tapToAnElementWithBounds(getBoundsID("OK"));
        waitForElementID("CANCEL", 10);

        //turn ON
        tapToAnElementWithBounds(getBoundsID("Always visible"));
        waitForElementID("Accessibility", 10);
        closeApp(appPackage);
        backKeyEvent();
    }

    public void handleFirstCallRTTPopUp(String appPackage, String appActivity, String phoneNumber, ADB adbReceiver) {
        makeCall(phoneNumber);
        waitForElementID("RTT Call", 10);
        tapToAnElementWithBounds(getBoundsID("OK"));
        adbReceiver.waitForIncomingCall(15);
        adbReceiver.tapToAnElementWithBounds(adbReceiver.getBoundsID("ANSWER"));
        waitForElementID("RTT", 10);
        adbReceiver.tapToAnElementWithBounds(adbReceiver.getBoundsID("RTT"));
        sleep(5);
        endCall();
        sleep(5);
    }

    public void setScreenTimeoutToMAX() {
        String command = "adb -s " + ID + " shell am start -n com.android.settings/.Settings\\$DisplaySettingsActivity";
        command(command);
        sleep(2);
        waitForElementID("Advanced", 10);
        tapToAnElementWithBounds(getBoundsID("Advanced"));
        waitForElementID("Screen timeout", 10);
        tapToAnElementWithBounds(getBoundsID("Screen timeout"));
        waitForElementID("30 minutes", 10);
        tapToAnElementWithBounds(getBoundsID("30 minutes"));
        sleep(2);
        backKeyEvent();
    }

    public void openVideoCamera(String appPackage, String appActivity) {
        String command = "adb -s " + ID + " shell am start-activity -n " + appPackage + "/" + appActivity + " --ez extra_turn_screen_on true -a android.media.action.VIDEO_CAMERA --ez android.intent.extra.USE_FRONT_CAMERA true";
        command(command);
    }

    public void openImageCamera(String appPackage, String appActivity) {
        String command = "adb -s " + ID + " shell am start-activity -n " + appPackage + "/" + appActivity + " --ez extra_turn_screen_on true -a android.media.action.STILL_IMAGE_CAMERA --ez android.intent.extra.USE_FRONT_CAMERA true";
        command(command);
    }


}
