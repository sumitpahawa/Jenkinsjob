package core.managers.baseutils;

import core.managers.logmanager.MyLogger;


public class MiscUtils {

    private String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvwxyz";
    private String AlphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz";

//    public void copyToClipboard(AndroidDriver driver, String text) {
//        driver.ge(text);
//        MyLogger.log.info("Successfully copied text to clipboard");
//    }


    //RandomString
    public String getRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaString.charAt(index));
        }
        return sb.toString();
    }

    //default string length is 144
    public String getRandomStringWithTimeStamp() {
        String timeStamp = new DateTime().getTimeStampString() + " ";
        StringBuilder sb = new StringBuilder(144);

        //add timestamp to final string
        sb.append(timeStamp);
        for (int i = 0; i < 144 - timeStamp.length(); i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    //length cannot be less than than the timestamp value
    public String getRandomStringWithTimeStamp(int length) {
        String timeStamp = new DateTime().getTimeStampString() + " ";

        StringBuilder sb = new StringBuilder(length);
        //add timestamp to final string
        sb.append(timeStamp);
        for (int i = 0; i < length - timeStamp.length(); i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }


    public void sleep(int milliseconds) {
        MyLogger.log.info("Waiting for " + milliseconds + " milliSeconds");
        try {
            Thread.sleep(milliseconds);
            System.out.println("sleep worked");
        } catch (InterruptedException e) {
            System.out.println("Sleep method failed");
        }
    }
}
