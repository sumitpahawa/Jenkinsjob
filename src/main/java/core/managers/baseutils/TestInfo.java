package core.managers.baseutils;

import core.managers.logmanager.MyLogger;
import core.reporting.ExcelReporting;
import org.testng.ITestResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestInfo {
    private static String CRASH_FOLDER_NAME;
    private String TEST_EXECUTION_DATE;
    private String TEST_SECTION_NAME;
    private String TEST_EVENT_NAME;
    private String TIME_START_EVENT;
    private String TIME_END_EVENT;
    private Integer NUMBER_OF_ITERATIONS;
    private Integer CURRENT_ITERATION;
    private String TIME_START_ITERATION;
    private String TIME_END_ITERATION;
    private String ITERATION_STATUS;
    private String TEST_STEP_NAME;
    private String TEST_STEP_START_TIME;
    private String TEST_STEP_STATUS;
    private int TOTAL_EVENT_FAIL;
    private String TEST_FOLDER_NAME;
    private String RAM_BEFORE_USAGE;
    private String DISK_BEFORE_USAGE;

    public void reset() {
        TEST_EXECUTION_DATE = null;

        TEST_EVENT_NAME = null;
        TIME_START_EVENT = null;
        TIME_END_EVENT = null;
        NUMBER_OF_ITERATIONS = 0;

        TEST_SECTION_NAME = null;
        TIME_START_ITERATION = null;
        TIME_END_ITERATION = null;
        ITERATION_STATUS = null;

        TEST_STEP_NAME = null;
        TEST_STEP_START_TIME = null;
        TEST_STEP_STATUS = null;

        TOTAL_EVENT_FAIL = 0;

        TEST_FOLDER_NAME = null;
        DISK_BEFORE_USAGE = null;

        RAM_BEFORE_USAGE = null;

        CRASH_FOLDER_NAME = null;
    }

    public TestInfo setTestStepName(String value, HashMap<Object, Object> map) {
        TEST_STEP_NAME = value;
        return this;
    }

    public TestInfo incrementTotalEventFailed() {
        TOTAL_EVENT_FAIL += 1;
        return this;
    }

    public int getTotalEventFailed() {
        return TOTAL_EVENT_FAIL;
    }

    public TestInfo setTotalEventFailed(int value) {
        TOTAL_EVENT_FAIL = value;
        return this;
    }

    public String getTestExecutionDate() {
        return TEST_EXECUTION_DATE;
    }

    public TestInfo setTestExecutionDate(String value) {
        TEST_EXECUTION_DATE = value;
        return this;
    }

    public String getTestEventName() {
        return TEST_EVENT_NAME;
    }

    public TestInfo setTestEventName(String testEventName) {
        TEST_EVENT_NAME = testEventName;
        MyLogger.log.info("Test Event - " + TEST_EVENT_NAME + " - started ");
        return this;
    }

    public String getTimeStartEvent() {
        return TIME_START_EVENT;
    }

    public TestInfo setTimeStartEvent(String value) {
        TIME_START_EVENT = value;
        return this;
    }

    public String getTimeEndEvent() {
        return TIME_END_EVENT;
    }

    public TestInfo setTimeEndEvent(String value) {
        TIME_END_EVENT = value;
        return this;
    }

    public Integer getNumberOfIterations() {
        return NUMBER_OF_ITERATIONS;
    }

    public TestInfo setNumberOfIterations(int value) {
        NUMBER_OF_ITERATIONS = value;
        return this;
    }

    public String getTestSectionName() {
        return TEST_SECTION_NAME;
    }

    public TestInfo setTestSectionName(String value) {
        TEST_SECTION_NAME = value;
        return this;
    }

    public String getTimeStartIteration() {
        return TIME_START_ITERATION;
    }

    public TestInfo setTimeStartIteration(String value) {
        TIME_START_ITERATION = value;
        return this;
    }

    public String getTimeEndIteration() {
        return TIME_END_ITERATION;
    }

    public TestInfo setTimeEndIteration(String value) {
        TIME_END_ITERATION = value;
        return this;
    }

    public String getIterationStatus() {
        return ITERATION_STATUS;
    }

    public TestInfo setIterationStatus(String value) {
        ITERATION_STATUS = value;
        return this;
    }

    public String getTestStepName() {
        return TEST_STEP_NAME;
    }

    public TestInfo setTestStepName(String value) {
        TEST_STEP_NAME = value;
        return this;
    }

    public String getTestStepStartTime() {
        return TEST_STEP_START_TIME;
    }

    public TestInfo setTestStepStartTime(String value) {
        TEST_STEP_START_TIME = value;
        return this;
    }

    public String getTestStepStatus() {
        return TEST_STEP_STATUS;
    }

    public TestInfo setTestStepStatus(String value) {
        TEST_STEP_STATUS = value;
        return this;
    }

    public Integer getCurrentIteration() {
        return CURRENT_ITERATION;
    }

    public TestInfo setCurrentIteration(int currentIteration) {
        CURRENT_ITERATION = currentIteration;
        return this;
    }

    public String getTestFolderName() {
        return TEST_FOLDER_NAME;
    }

    public TestInfo setTestFolderName(String value) {
        TEST_FOLDER_NAME = value;
        return this;
    }

    public String getDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormatter.format(date);
    }

    public int getTotalInvocationCount(String method) {
        int total = 0;

        String[] totalInvocationCount = method.split(", ");
        for (String s : totalInvocationCount) {
            String[] fields = s.split("=");
            if (fields[0].equals("invocationCount")) {
                total = Integer.parseInt(fields[1]);
                break;
            }
        }
        return total;
    }

    public void setIterationDataStart(String method, int currentCount, String methodName) {
        setTimeStartIteration(getDate());
        if (currentCount == 0) {
            setTimeStartEvent(getDate());
        }
        setCurrentIteration(currentCount);
        setTestEventName(methodName);
        MyLogger.log.info("Test Event: " + getTestEventName() + " Iteration: " + currentCount + " started");

        int totalCount = getTotalInvocationCount(method);
        setNumberOfIterations(totalCount);
    }

    public void setIterationDataEnd(String method, int currentCount, ITestResult itr) {
        setTimeEndIteration(getDate());
        int totalCount = getTotalInvocationCount(method);

        ExcelReporting reporting = new ExcelReporting();

        reporting.LOGAllDetails(
                1,
                itr.getTestClass().getRealClass().getSimpleName() + "_" + itr.getName(),
                getCurrentIteration(),
                getTimeStartIteration(),
                getTimeEndIteration(),
                getDifference(getTimeStartIteration(), getTimeEndIteration()),
                getIterationStatus()
        );

        if (currentCount == totalCount - 1) {
            setTimeEndEvent(getDate());
            reporting.LOGEvents(
                    1,
                    itr.getTestClass().getRealClass().getSimpleName() + "_" + itr.getName(),
                    getNumberOfIterations(),
                    getNumberOfIterations() - getTotalEventFailed(),
                    getTimeStartEvent(),
                    getTimeEndEvent(),
                    getDifference(getTimeStartEvent(), getTimeEndEvent())
            );
            setTotalEventFailed(0);
        }
    }

    public String getDifference(String startTime, String endTime) {
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = format.parse(startTime);
            Date d2 = format.parse(endTime);
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            result = String.format("%02d", diffHours) + ":" + String.format("%02d", diffMinutes) + ":" + String.format("%02d", diffSeconds);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getCrashFolderName() {
        return CRASH_FOLDER_NAME;
    }

    public TestInfo setCrashFolderName(String value) {
        CRASH_FOLDER_NAME = value;
        return this;
    }

    public String getBeforeRAMUsage() {
        return RAM_BEFORE_USAGE;
    }

    public TestInfo setBeforeRAMUsage(String value) {
        RAM_BEFORE_USAGE = value;
        return this;
    }

    public String getBeforeDiskUsage() {
        return DISK_BEFORE_USAGE;
    }

    public TestInfo setBeforeDiskUsage(String value) {
        DISK_BEFORE_USAGE = value;
        return this;
    }
}
