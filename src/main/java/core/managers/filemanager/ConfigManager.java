package core.managers.filemanager;

/**
 * Manager for properties files
 *
 * @author sumit pahawa
 * @version 1.0
 * @since 0.0.1
 **/

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {

    private static Properties configProp = null;
    private static FileInputStream fis = null;

    /**
     * A String to hold path of the config.properties file
     */
    private static String configpropertiespath = "";

    /**
     * A String that concatnates path of the config.properties fil
     */
    private static String configProperties = "";


    /**
     * Constructs config file path and loads the file
     */
    public ConfigManager() {

        configProp = new Properties();
        configpropertiespath = (new StringBuilder(System.getProperty("user.dir")).append(File.separator).append("src")
                .append(File.separator).append("main").append(File.separator).append("resources").append(File.separator).append("propfiles"))
                .append(File.separator).toString();

        configProperties = String.format("%sconfig.properties", configpropertiespath);

        // //Utils.addLog.debug("config.properties file path is : {} ", configProperties
        // );

        try {
            fis = new FileInputStream(configProperties);

            configProp.load(fis);

            // //Utils.addLog.debug("Config file is loaded");

        } catch (FileNotFoundException e) {
            // //Utils.addLog.debug("config.properties file is not found at the paht : {}",
            // configpropertiespath);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reload() throws IOException {
        try {
            configProp.load(fis);
        } catch (Exception e) {

        }
    }

    public static void reloadPropertiesFile() {
        try {
            fis = new FileInputStream(configProperties);
            configProp.load(fis);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     *
     * get value from config.properties file
     *
     * @param key
     * @throws @return
     * @author Sumit
     *
     */
    public static String getProperty(String key) {
        String value = "";
        if (configProp.containsKey(key)) {
            // //Utils.addLog.debug("Key : {} is present and the Value is : {} ", key,
            // configProp.getProperty(key));
            value = configProp.getProperty(key);
        } else {
            // //Utils.addLog.debug("Key : {} is not present ", key );
        }
        try {
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return value;

    }

    /**
     *
     * Sets key and Value pair in config.properties file
     *
     * @param key, value
     * @throws IOException
     * @return
     * @author Sumit
     *
     */
    public void setProperty(String key, String value) {
        // //Utils.addLog.debug("Set Key : {} and Value : {} ", key, value);
        try {
            // set the properties key and Value
            configProp.setProperty(key, value);
            // save properties to project root folder
            configProp.store(new FileOutputStream(configProperties), null);
            // //Utils.addLog.debug("Key and Value are Set and Stored");
        } catch (IOException e) {
            // //Utils.addLog.debug("Key and Value are not Set", e.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void setProperties(HashMap<String, String> map) throws IOException {
        // //Utils.addLog.debug("Set Key : {} and Value : {} ", key, value);
        reload();

        try {
            for (Map.Entry<String, String> entrySet : map.entrySet()) {
                configProp.setProperty(entrySet.getKey(), entrySet.getValue());
            }
            // set the properties key and Value
            // save properties to project root folder
            configProp.store(new FileOutputStream(configProperties), null);
            // //Utils.addLog.debug("Key and Value are Set and Stored");
        } catch (IOException e) {
            // //Utils.addLog.debug("Key and Value are not Set", e.getMessage());
        }
    }


}