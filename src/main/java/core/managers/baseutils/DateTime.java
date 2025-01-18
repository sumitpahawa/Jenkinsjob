package core.managers.baseutils;

import core.managers.filemanager.ConfigManager;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTime {

    public long startStamp;

    public static String getTimeStampString(long dateTime) throws InterruptedException {
        try {
            return new SimpleDateFormat(ConfigManager.getProperty("TIME_STAMP_FORMAT")).format(dateTime);
        } catch (Exception e) {
            return "";
        }
    }

    public void start() {
        startStamp = getTimeStamp();
    }

    public long getTimeStamp() {
        return new Date().getTime();
    }

    public String getTimeStampString() {
        try {
            return new SimpleDateFormat(ConfigManager.getProperty("TIME_STAMP_FORMAT")).format(new Date());
        } catch (Exception e) {
            return "";
        }
    }

    public String getDate() {
        try {
            return new SimpleDateFormat(ConfigManager.getProperty("DATE_FORMAT")).format(new Date());
        } catch (Exception e) {
            return "";
        }
    }

    public boolean expired(int seconds) {
        int difference = (int) ((getTimeStamp() - startStamp) / 1000);
        return difference > seconds;
    }
}
