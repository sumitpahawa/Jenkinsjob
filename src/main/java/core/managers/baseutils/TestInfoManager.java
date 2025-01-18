package core.managers.baseutils;

import core.managers.logmanager.MyLogger;

public class TestInfoManager {

    public String TIME_START_EVENT;
    public String TIME_START_ITERATION;
    public String TEST_EVENT_NAME;
    public Integer NUMBER_OF_ITERATIONS;
    public Integer CURRENT_ITERATION;
    public String TIME_END_EVENT;

    public TestInfoManager() {

    }


    public TestInfoManager setTimeStartEvent(String value) {
        TIME_START_EVENT = value;
        return this;
    }

    public TestInfoManager setTimeStartIteration(String value) {
        TIME_START_ITERATION = value;
        return this;
    }

    public TestInfoManager setTestEventName(String testEventName) {
        TEST_EVENT_NAME = "Test Event - " + testEventName + " - started ";
        MyLogger.log.info("Test Event - " + TEST_EVENT_NAME + " - started ");
        return this;
    }

    public TestInfoManager setNumberOfIterations(int value) {
        NUMBER_OF_ITERATIONS = value;
        return this;
    }

    public TestInfoManager setCurrentInteratation(int currentInteratation) {
        CURRENT_ITERATION = currentInteratation;
        return this;
    }

    public TestInfoManager setTimeEndEvent(String value) {
        TIME_END_EVENT = value;
        return this;
    }
}
