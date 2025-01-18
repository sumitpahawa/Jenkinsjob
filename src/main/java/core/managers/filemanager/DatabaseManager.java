package core.managers.filemanager;

import java.sql.*;

//public class Test {
//    public static final void main(String[] args) {
//        DataBaseManager db = new DataBaseManager();
//        db.initDB();
//    }
//}

class DatabaseManager {
    private static Connection conn;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String dbAddress = "jdbc:mysql://34.82.244.246:3306/";
    private static String dbName = "ATT";
    private static String userName = "root";
    private static String password = "techm@auto";

    DatabaseManager() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(dbAddress, userName, password);
        } catch (Exception e) {
            System.out.println("Connection denied");
            e.printStackTrace();
        }
    }

    public static void insertToDatabase(String queryString) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        stmt.executeUpdate(queryString);
    }

    public static ResultSet selectFromDatabase(String queryString) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(queryString);
    }

    //TODO maybe add a timestamp to each table so it is a brand new table each test run

    //TODO parse db to 2d array


    public static void initDB() {
        createDB();
        useDB();
        createAllTables();
    }

    private static void createDB() {
        try {
            Class.forName(driver);
            Statement s = conn.createStatement();
            int Result = s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void useDB() {
        try {
            Class.forName(driver);
            Statement s = conn.createStatement();
            int Result = s.executeUpdate("USE " + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createAllTables() {
        try {
            createTestExecutionTable();
            createTestEventTable();
            createTestSectionTable();
            createTestStepTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTestExecutionTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS TestExecution"
                + "("
                + "ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "test_execution_date DATE NOT NULL,"
                + "oem VARCHAR(25) NOT NULL,"
                + "udid VARCHAR(25) NOT NULL,"
                + "device_model VARCHAR(10) NOT NULL,"
                + "os_version VARCHAR(10) NOT NULL,"
                + "device_build_number VARCHAR(20) NOT NULL,"
                + "PRIMARY KEY (ID)"
                + ");";

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
    }

    private static void createTestEventTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS TestEvents"
                + "("
                + "ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "test_event_name VARCHAR(20) NOT NULL,"
                + "time_start_event TIMESTAMP(6) NULL DEFAULT NULL,"
                + "time_end_event TIMESTAMP(6)  NULL DEFAULT NULL,"
                + "number_of_iterations INT(10) NOT NULL,"
                + "PRIMARY KEY (ID)"
                + ");";

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
    }

    private static void createTestSectionTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS TestSections"
                + "("
                + "ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "test_section_name VARCHAR(20) NOT NULL,"
                + "time_start_iteration TIMESTAMP(6) NULL DEFAULT NULL,"
                + "time_end_iteration TIMESTAMP(6) NULL DEFAULT NULL,"
                + "iteration_status VARCHAR(10) NOT NULL,"
                + "PRIMARY KEY (ID)"
                + ");";

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
    }

    private static void createTestStepTable() throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS TestSteps"
                + "("
                + "ID INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "test_step_name VARCHAR(20) NOT NULL,"
                + "test_step_start_time TIMESTAMP(6) NULL DEFAULT NULL,"
                + "test_step_status VARCHAR(10) NOT NULL,"
                + "PRIMARY KEY (ID)"
                + ");";

        Statement stmt = conn.createStatement();
        stmt.execute(sqlCreate);
    }
}