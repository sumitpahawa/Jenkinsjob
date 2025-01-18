package core.reporting;

import core.managers.servermanager.ServerManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Reporting class to write everything to the file
 */
public class ExcelReporting {
    private static String EXCEL_DETAILED_REPORTING = "";
    private static Workbook wb;
    private static Workbook wb2;
    private String EXCEL_DETAILED_ERROR_REPORTING = "";

    /**
     * init method for the reporting class
     */
    public ExcelReporting(String folderName) {
        EXCEL_DETAILED_REPORTING = ServerManager.USER_HOME + "\\test-output\\" + folderName + "\\" + folderName + ".xlsx";
        try (InputStream inp = new FileInputStream(EXCEL_DETAILED_REPORTING)) {
            wb = WorkbookFactory.create(inp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExcelReporting(String folderName, String filename) {
        EXCEL_DETAILED_ERROR_REPORTING = ServerManager.USER_HOME + "\\test-output\\" + folderName + "\\" + filename + ".xlsx";
//        try(InputStream inp = new FileInputStream(EXCEL_DETAILED_ERROR_REPORTING)) {
//            wb2 = WorkbookFactory.create(inp);
//            XSSFSheet sheet = (XSSFSheet) wb2.createSheet("Summary");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            wb2 = new XSSFWorkbook();
            XSSFSheet sheet = (XSSFSheet) wb2.createSheet("Summary");
            File file = new File(EXCEL_DETAILED_ERROR_REPORTING);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file, true);
                wb2.write(out);
            }
            InputStream inp = new FileInputStream(EXCEL_DETAILED_ERROR_REPORTING);
            wb2 = WorkbookFactory.create(inp);
            System.out.println("Excel written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExcelReporting() {

    }

    public void LOGAllDetails(int testLoopNumber, String scenarioName, int iterationNumber, String startDateTime, String endDateTime, String duration, String testResult) {
        try {
            Date startTimeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateTime);
            Date startEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateTime);
            Date durationTime = new SimpleDateFormat("HH:mm:ss").parse(duration);

            int workBookSheet = 3;
            Sheet sheet = wb.getSheetAt(workBookSheet);

            CreationHelper creationHelper = wb.getCreationHelper();
            CellStyle styleDateTime = wb.createCellStyle();
            styleDateTime.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

            CellStyle styleDuration = wb.createCellStyle();
            styleDuration.setDataFormat(creationHelper.createDataFormat().getFormat("HH:mm:ss"));

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            Cell cell1 = row.createCell(1);
            Cell cell2 = row.createCell(2);
            Cell cell3 = row.createCell(3);
            Cell cell4 = row.createCell(4);
            Cell cell5 = row.createCell(5);
            Cell cell6 = row.createCell(6);
            Cell cell7 = row.createCell(7);

            cell1.setCellValue(testLoopNumber);
            cell2.setCellValue(scenarioName);
            cell3.setCellValue(iterationNumber + 1);
            cell4.setCellValue(startTimeDate);
            cell4.setCellStyle(styleDateTime);
            cell5.setCellValue(startEndDate);
            cell5.setCellStyle(styleDateTime);
            cell6.setCellValue(durationTime);
            cell6.setCellStyle(styleDuration);
            cell7.setCellValue(testResult);

            try (OutputStream fileOut = new FileOutputStream(EXCEL_DETAILED_REPORTING)) {
                wb.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void LOGEvents(int testLoopNumber, String scenarioName, int testLoopCount, int passLoopCount, String startDateTime, String endDateTime, String duration) {
        try {
            Date startTimeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDateTime);
            Date startEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDateTime);
            Date durationTime = new SimpleDateFormat("HH:mm:ss").parse(duration);

            int workBookSheet = 2;
            Sheet sheet = wb.getSheetAt(workBookSheet);

            CreationHelper creationHelper = wb.getCreationHelper();
            CellStyle styleDateTime = wb.createCellStyle();
            styleDateTime.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

            CellStyle styleDuration = wb.createCellStyle();
            styleDuration.setDataFormat(creationHelper.createDataFormat().getFormat("HH:mm:ss"));

            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            Cell cell1 = row.createCell(1);
            Cell cell2 = row.createCell(2);
            Cell cell3 = row.createCell(3);
            Cell cell4 = row.createCell(4);
            Cell cell5 = row.createCell(5);
            Cell cell6 = row.createCell(6);
            Cell cell7 = row.createCell(7);
            //        Cell cell8 = row.createCell(9);

            cell1.setCellValue(testLoopNumber);
            cell2.setCellValue(scenarioName);
            cell3.setCellValue(testLoopCount);
            cell4.setCellValue(passLoopCount);
            cell5.setCellValue(startTimeDate);
            cell5.setCellStyle(styleDateTime);
            cell6.setCellValue(startEndDate);
            cell6.setCellStyle(styleDateTime);
            cell7.setCellValue(durationTime);
            cell7.setCellStyle(styleDuration);

            //        cell8.setCellValue("=IFERROR(IF(H" + ++rowCount + "<>\"\",TIMEVALUE(H" + ++rowCount + "),\"\"),\"\")");

            try (OutputStream fileOut = new FileOutputStream(EXCEL_DETAILED_REPORTING)) {
                wb.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void LOGAllDetails(String errorType, String appId, String loopNumber, String testSection, String eventName, int iterationNumber, String timeStamp, String sheetName) {

        Sheet sheet = wb2.getSheet(sheetName);
        System.out.println(sheet);
        if (sheet == null) {
            System.out.println("creating");
            wb2.createSheet(sheetName);
            sheet = wb2.getSheet(sheetName);
            System.out.println(sheet);

        }

        CreationHelper creationHelper = wb2.getCreationHelper();
        CellStyle styleDateTime = wb2.createCellStyle();
        styleDateTime.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        CellStyle styleDuration = wb2.createCellStyle();
        styleDuration.setDataFormat(creationHelper.createDataFormat().getFormat("HH:mm:ss"));
        CellStyle style = wb2.createCellStyle();//Create style
        Font font = wb2.createFont();//Create font
        font.setItalic(true);
        font.setBold(true);//Make font bold
        style.setFont(font);//set it to bold
        int rowCount = sheet.getLastRowNum();

        Row row;
        System.out.println(rowCount + "rrrrrrrr");
        if (0 == rowCount) {
            System.out.println("inner");
            row = sheet.getRow(rowCount);
            row = sheet.createRow(rowCount);
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(style);
            Cell cell2 = row.createCell(2);
            cell2.setCellStyle(style);
            Cell cell3 = row.createCell(3);
            cell3.setCellStyle(style);
            Cell cell4 = row.createCell(4);
            cell4.setCellStyle(style);
            Cell cell5 = row.createCell(5);
            cell5.setCellStyle(style);
            Cell cell6 = row.createCell(6);
            cell6.setCellStyle(style);
            Cell cell7 = row.createCell(7);
            cell7.setCellStyle(style);
            Cell cell8 = row.createCell(8);
            cell8.setCellStyle(style);
            Cell cell9 = row.createCell(9);
            cell9.setCellStyle(style);
            Cell cell10 = row.createCell(10);
            cell10.setCellStyle(style);
            Cell cell11 = row.createCell(11);
            cell11.setCellStyle(style);

            cell1.setCellValue("Error-Type");
            cell2.setCellValue("APP-ID");
            cell3.setCellValue("Loop#");
            cell4.setCellValue("Test Section");
            cell5.setCellValue("Test Event");
            cell6.setCellValue("Iteration#");
            cell7.setCellValue("TimeStamp");
            cell8.setCellValue("Before Storage");
            cell9.setCellValue("After Storage");
            cell10.setCellValue("Before RAM");
            cell11.setCellValue("After RAM");
        }
        rowCount = sheet.getLastRowNum();
        System.out.println(rowCount);
        row = sheet.createRow(++rowCount);
        Cell cell11 = row.createCell(1);
        Cell cell12 = row.createCell(2);
        Cell cell13 = row.createCell(3);
        Cell cell14 = row.createCell(4);
        Cell cell15 = row.createCell(5);
        Cell cell16 = row.createCell(6);
        Cell cell17 = row.createCell(7);
        Cell cell18 = row.createCell(8);
        Cell cell19 = row.createCell(9);
        Cell cell20 = row.createCell(10);
        Cell cell21 = row.createCell(11);

        cell11.setCellValue(errorType);
        cell12.setCellValue(appId);
        cell13.setCellValue(loopNumber);
        cell14.setCellValue(testSection);
        cell15.setCellValue(eventName);
        cell16.setCellValue(iterationNumber + 1);
        cell17.setCellValue(timeStamp);
        cell17.setCellStyle(styleDateTime);

        System.out.println(EXCEL_DETAILED_ERROR_REPORTING);


        try (OutputStream fileOut = new FileOutputStream(EXCEL_DETAILED_ERROR_REPORTING)) {
            wb2.write(fileOut);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void LOGAllDetails(String errorType, String scenarionName, String timeStamp, String sheetName) {

        Sheet sheet = wb2.getSheet(sheetName);
        System.out.println(sheet);
        if (sheet == null) {
            System.out.println("creating");
            wb2.createSheet(sheetName);
            sheet = wb2.getSheet(sheetName);
            System.out.println(sheet);

        }

        CreationHelper creationHelper = wb2.getCreationHelper();
        CellStyle styleDateTime = wb2.createCellStyle();
        styleDateTime.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        CellStyle styleDuration = wb2.createCellStyle();
        styleDuration.setDataFormat(creationHelper.createDataFormat().getFormat("HH:mm:ss"));
        CellStyle style = wb2.createCellStyle();//Create style
        Font font = wb2.createFont();//Create font
        font.setItalic(true);
        font.setBold(true);//Make font bold
        style.setFont(font);//set it to bold
        int rowCount = sheet.getLastRowNum();

        Row row;
        System.out.println(rowCount + "rrrrrrrr");
        if (0 == rowCount) {
            System.out.println("inner");
            row = sheet.getRow(rowCount);
            row = sheet.createRow(rowCount);
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(style);
            Cell cell2 = row.createCell(2);
            cell2.setCellStyle(style);
            Cell cell3 = row.createCell(3);
            cell3.setCellStyle(style);


            cell1.setCellValue("Error-Type");
            cell2.setCellValue("Scenario Name");
            cell3.setCellValue("TimeStamp");

        }
        rowCount = sheet.getLastRowNum();
        System.out.println(rowCount);
        row = sheet.createRow(++rowCount);
        Cell cell31 = row.createCell(1);
        Cell cell32 = row.createCell(2);
        Cell cell33 = row.createCell(3);

        cell31.setCellValue(errorType);
        cell32.setCellValue(scenarionName);
        cell33.setCellValue(timeStamp);

        System.out.println(EXCEL_DETAILED_ERROR_REPORTING);


        try (OutputStream fileOut = new FileOutputStream(EXCEL_DETAILED_ERROR_REPORTING)) {
            wb2.write(fileOut);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeDeviceInfo(String OEM, String UDID, String model, String osVersion, String buildNumber, String IMEI, String sheetname) {
        Sheet sheet = wb2.getSheet(sheetname);
        if (sheet == null) {
            wb2.createSheet(sheetname);
            sheet = wb2.getSheet(sheetname);
        }
        Row row;
        Cell cell;
        row = sheet.getRow(3);
        if (row == null) {
            row = sheet.createRow(3);
        }
        cell = row.getCell(1);
        if (cell == null) {
            cell = row.createCell(1);
            cell.setCellValue("OEM");
        }
        row = sheet.getRow(4);
        if (row == null) {
            row = sheet.createRow(4);
        }
        cell = row.getCell(1);
        if (cell == null) {
            cell = row.createCell(1);
            cell.setCellValue("UDID");
        }
        row = sheet.getRow(5);
        if (row == null) {
            row = sheet.createRow(5);
        }
        cell = row.getCell(1);
        if (cell == null) {
            cell = row.createCell(1);
            cell.setCellValue("MODEL");
        }
        row = sheet.getRow(6);
        if (row == null) {
            row = sheet.createRow(6);
        }
        cell = row.getCell(1);
        if (cell == null) {
            cell = row.createCell(1);
            cell.setCellValue("osVersion");
        }
        row = sheet.getRow(7);
        if (row == null) {
            row = sheet.createRow(7);
        }
        cell = row.getCell(1);
        if (cell == null) {
            cell = row.createCell(1);
            cell.setCellValue("Build-Number");
        }
        row = sheet.getRow(8);
        if (row == null) {
            row = sheet.createRow(8);
        }
        cell = row.getCell(1);
        if (cell == null) {
            cell = row.createCell(1);
            cell.setCellValue("IMEI");
        }
        row = sheet.getRow(3);
        cell = row.getCell(2);
        if (cell == null) {
            cell = row.createCell(2);
            cell.setCellValue(OEM);
        }
        row = sheet.getRow(4);
        cell = row.getCell(2);

        if (cell == null) {
            cell = row.createCell(2);
            cell.setCellValue(UDID);
        }
        row = sheet.getRow(5);
        cell = row.getCell(2);

        if (cell == null) {
            cell = row.createCell(2);
            cell.setCellValue(model);
        }
        row = sheet.getRow(6);
        cell = row.getCell(2);

        if (cell == null) {
            cell = row.createCell(2);
            cell.setCellValue(osVersion);
        }

        row = sheet.getRow(7);
        cell = row.getCell(2);

        if (cell == null) {
            cell = row.createCell(2);
            cell.setCellValue(buildNumber);
        }

        row = sheet.getRow(8);
        cell = row.getCell(2);
        if (cell == null) {
            cell = row.createCell(2);
            cell.setCellValue(IMEI);
        }
        try (OutputStream fileOut = new FileOutputStream(EXCEL_DETAILED_ERROR_REPORTING)) {
            wb2.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDeviceInfo(String OEM, String UDID, String model, String osVersion, String buildNumber, String IMEI) {
        int workBookSheet = 2;
        Sheet sheet = wb.getSheetAt(workBookSheet);

        Row row;
        Cell cell;

        row = sheet.getRow(3);
        cell = row.getCell(2);
        cell.setCellValue(OEM);

        row = sheet.getRow(4);
        cell = row.getCell(2);
        cell.setCellValue(UDID);

        row = sheet.getRow(5);
        cell = row.getCell(2);
        cell.setCellValue(model);

        row = sheet.getRow(6);
        cell = row.getCell(2);
        cell.setCellValue(osVersion);

        row = sheet.getRow(7);
        cell = row.getCell(2);
        cell.setCellValue(buildNumber);

        row = sheet.getRow(8);
        cell = row.getCell(2);
        cell.setCellValue(IMEI);

        try (OutputStream fileOut = new FileOutputStream(EXCEL_DETAILED_REPORTING)) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}