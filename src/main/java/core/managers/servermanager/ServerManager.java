package core.managers.servermanager;

import core.managers.logmanager.MyLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ServerManager {

    public static String USER_HOME = System.getProperty("user.dir");
    public static String FOLDER_DATE = "";
    public static String USER_DIR = System.getProperty("user.dir");
    private static String OS;
    private static String ANDROID_HOME;

    public static String getAndroidHome() {
        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null)
                throw new RuntimeException("Failed to find ANDROID_HOME, make sure the environment variable is set");
        }
        return ANDROID_HOME;
    }

    public static String getOS() {
        if (OS == null) OS = System.getenv("os.name");
        return OS;
    }

    public static boolean isWindows() {
        return getOS().startsWith("Windows");
    }

    public static boolean isMac() {
        return getOS().startsWith("Mac");
    }

    public static String runCommand(String command) {
        String output = null;
        System.out.println(command);
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) output = scanner.next();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }

    public static String getWorkingDir() {
        return System.getProperty("user.dir");
    }

    public static String read(File file) {
        StringBuilder output = new StringBuilder();
        try {
            String line;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) output.append(line + "\n");
            bufferedReader.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
        return output.toString();
    }

    public static void write(File file, String content) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(content);
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void uninstallNativeAppsStoreFront(String udid) {
        MyLogger.log.info("Uninstalling Native Storefront Apps");
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "uninstallstorefrontapps.bat " + udid);
            System.out.println(pb.command());
            File dir = new File(USER_DIR + "\\src\\main\\resources");
            pb.directory(dir);
            pb.start();
            MyLogger.log.info("Successfully uninstalled all installed Native Apps");
        } catch (IOException e) {
            MyLogger.log.fatal("File Not found");
        }

    }

    public static void uninstallApps(String udid) {
        String[] appArray = {"org.lukeallen.bomber", "cz.hipercalc", "com.buak.Link2SD", "mark.via.gp",
                "com.google.android.stardroid", "com.bs.smarttouch.gp", "in.asissuthar.cricket", "com.bloketech.lockwatch",
                "com.johnayliff.seedship", "com.filepursuit.filepursuit"};
        for (int i = 0; i < appArray.length; i++) {
            try {
                runCommand("adb -s " + udid + " uninstall " + appArray[i]);
            } catch (Exception e) {
                MyLogger.log.fatal("File Not found");
            }
        }
    }

    public static void startAppiumServer() {
        try {
            MyLogger.log.info("Killing any active CMD windows");
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "killCMD.bat");
            File dir = new File(USER_DIR + "\\grid-mobile-work");
            pb.directory(dir);
            Process p = pb.start();
            Thread.sleep(5000);
            MyLogger.log.info("StartCucumber Base Test Before-login-test-cucumber\n"
                    + "/home/abc/Raku/RakutenProject-main/target/classes/deviceDetails.json\n"
                    + "12-14-2020 18:46:59 - [core.uiactions.AndroidUiActions / <init>][INFO ] - AndroidUiActions Objects instance is created\n"
                    + "Cucumber Test Class Before\n"
                    + "Data Provider test Class\n"
                    + "TestNG Before\n"
                    + "/home/abc/Raku/RakutenProject-main/target/classes/deviceDetails.json\n"
                    + "Dec 14, 2020 6:47:26 PM io.appium.java_client.remote.AppiumCommandExecutor$1 lambda$0\n"
                    + "INFO: Detected dialect: W3C\n"
                    + "/home/abc/Raku/RakutenProject-main/target/classes/deviceDetails.json\n"
                    + "12-14-2020 18:47:29 - [core.uiactions.AndroidUiActions / <init>][INFO ] - AndroidUiActions Objects instance is created\n"
                    + "io.cucumber.testng.Pickle@111872c0ing Appium Servers");
            ProcessBuilder pb2 = new ProcessBuilder("cmd", "/c", "allInOne.bat");
            File dir2 = new File(USER_DIR + "\\grid-mobile-work");
            pb2.directory(dir2);
            Process p2 = pb2.start();

            Thread.sleep(5000);
        } catch (IOException | InterruptedException e) {
            MyLogger.log.fatal("COULD NOT RESTART APPIUM!!! Terminating the Execution");
            System.exit(1);
        }
    }

    public static String curlGetRequest(String token, String host, String resource) {
        try {
            token = System.getProperty("TOKEN");
            String cmd = "curl https://" + token + "@" + host + "/" + resource + "";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            return output;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

    public static String curlPostRequest(String token, String host, String resource, String json) {
        try {
            token = System.getProperty("TOKEN");
            JSONParser parser = new JSONParser();
            JSONObject jo = (JSONObject) parser.parse(json);
            String cmd = "curl -X POST https://" + token + "@" + host + "/" + resource + " " + "--data" + " " + jo + " "
                    + "-H" + " " + "Content-Type: application/json";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            return output;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public static String curlPostRequest(String token, String host, String resource) {
        try {

            token = System.getProperty("TOKEN");
            String cmd = "curl -X POST https://" + token + "@" + host + "/" + resource + "";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            return output;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public static JSONObject curlPostRequest(String token, String host, String resource, String udid, String json) {
        try {
            token = System.getProperty("TOKEN");
            JSONParser parser = new JSONParser();
            JSONObject jo = (JSONObject) parser.parse(json);
            String cmd = "curl -X POST https://" + token + "@" + host + "/" + resource + "/" + udid + " " + "--data"
                    + " " + jo + " " + "-H" + " " + "Content-Type: application/json";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            JSONObject json1 = (JSONObject) parser.parse(output);

            return json1;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject curlPostRequestAudioUpload(String token, String host, String route, String tagname, String path) {
        try {

            String cmd = "curl --request POST https://" + token + "@" + host + route + "?tag=" + tagname + " " + "--data-binary "
                    + path + "";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            System.out.println(output);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(output);
            return json;

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject getRequest(String token, String host, String route) {
        try {

            String cmd = "curl --request POST https://" + token + "@" + host + route + "";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            System.out.println(output);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(output);
            return json;

        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject getRequestWithHeaders(String token, String host, String route) {
        try {
            String cmd = "curl --remote-name --remote-header-name POST https://" + token + "@" + host + route + "";
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            System.out.println(output);
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(output);
            return json;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String RunLinuxCommand(String cmd) throws IOException {
        String linuxCommandResult = "";
        Process p = Runtime.getRuntime().exec(cmd);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        try {
            while ((linuxCommandResult = stdInput.readLine()) != null) {
                return linuxCommandResult;
            }
            while ((linuxCommandResult = stdError.readLine()) != null) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return linuxCommandResult;
    }

    public static JSONObject postRequestWithJsonData(String token, String host, String route, String json) {
        try {
            token = System.getProperty("TOKEN");
            JSONParser parser = new JSONParser();
            JSONObject jo = (JSONObject) parser.parse(json);
            String cmd = "curl -X POST https://" + token + "@" + host + "/" + route + " " + "--data"
                    + " " + jo;
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            JSONObject json1 = (JSONObject) parser.parse(output);
            return json1;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getRequestWithJsonData(String token, String host, String route, String json) {
        try {
            token = System.getProperty("TOKEN");
            JSONParser parser = new JSONParser();
            JSONObject jo = (JSONObject) parser.parse(json);
            String cmd = "curl -X POST https://" + token + "@" + host + "/" + route + " " + "--data"
                    + " " + jo;
            System.out.println(cmd);
            String output = RunLinuxCommand(cmd);
            JSONObject json1 = (JSONObject) parser.parse(output);
            return json1;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkIfServerIsRunnning(int MOServerPort, int MTServerPort) {
        MyLogger.log.info("Checking If Servers are still running for the ports" + MOServerPort + ",  " + MTServerPort);

        boolean isServerRunning = false;
        ServerSocket serverSocketMO, serverSocketMT;
        try {
            serverSocketMO = new ServerSocket(MOServerPort);
            serverSocketMO.close();
            MyLogger.log.info("MO Server with port number " + MOServerPort + " is running");
            serverSocketMT = new ServerSocket(MOServerPort);
            serverSocketMT.close();
            MyLogger.log.info("MO Server with port number " + MOServerPort + " is running");
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocketMO = null;
            serverSocketMT = null;
        }
        return isServerRunning;
    }
}