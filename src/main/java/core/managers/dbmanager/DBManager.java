package core.managers.dbmanager;

import core.managers.filemanager.ConfigManager;

import java.sql.*;

public class DBManager {
    Statement stmt = null;
    ConfigManager configManager;

    public void createConnection() {
        configManager = new ConfigManager(); //TODO change later
        System.out.println("Connecting to a selected database...");
        //Register JDBC driver
        try {
            System.out.println(ConfigManager.getProperty("DRIVER"));
            Class.forName(ConfigManager.getProperty("DRIVER")).newInstance();
            //Allocate a database 'Connection' object
            //private static String dbTable = "Pixel_02_16_2021_TEST"; //TODO change this
            Connection conn = DriverManager.getConnection(
                    ConfigManager.getProperty("DB_ADDRESS") + ConfigManager.getProperty("DB_NAME"),
                    ConfigManager.getProperty("USER_NAME"), ConfigManager.getProperty("PASSWORD"));
            // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            System.out.println(stmt.toString() + "hhhh");
            System.out.println("Statement created successfully...");
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void updateDB(String... values) {
        try {
            createConnection();
            System.out.println(values[0] + values[1] + values[2] + values[3] + values[4] + values[5] + values[6] + values[7] + values[8] + values[9]);
            String sql = "INSERT INTO ATT_QE (model, osName, os, featureName, scenarioName, build, runNumber, startTime, endTime, result) " +
                    "VALUES (\"" +
                    values[0] + "\", \"" +
                    values[1] + "\", \"" +
                    values[2] + "\", \"" +
                    values[3] + "\", \"" +
                    values[4] + "\", \"" +
                    values[5] + "\", " +
                    Integer.parseInt(values[6]) + ", \"" +
                    values[7] + "\", \"" +
                    values[8] + "\", \"" +
                    values[9] + "\")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateMetricsDB(String startType, int appLoadTime, String RAMUsage, String ANR, String appCrash, int networkInBytes, String CPUUsage, String batteryUsage, String testStartTime) {
        try {
            createConnection();
            String sql = "INSERT INTO ATT_QE_Metrics (appStartType, appLoadTime, RAMUsage, ANR, appCrash, networkInBytes, CPUUsage, batteryUsage, testStartTime) " +
                    "VALUES (\"" +
                    startType + "\", " +
                    appLoadTime + ", \"" +
                    RAMUsage + "\", \"" +
                    ANR + "\", \"" +
                    appCrash + "\", " +
                    networkInBytes + ", \"" +
                    CPUUsage + "\", \"" +
                    batteryUsage + "\", \"" +
                    testStartTime + "\")";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int queryDBForRunNumber(String model) {
        int runNumber = 0;
        try {
            createConnection();
            String sql = "SELECT runNumber FROM ATT_QE WHERE model = \"" + model + "\" ORDER BY id DESC LIMIT 1";
            System.out.println(sql);
            runNumber = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return runNumber;
    }

    public ResultSet fetchDB(String dbTable) throws SQLException {
        createConnection();
        return stmt.executeQuery("SELECT * FROM " + dbTable + "");
    }
}