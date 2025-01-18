package stepdefinitions;

import api.apps.speedway.SpeedwayApp;
import core.ThreadLocalDriver;
import core.managers.baseutils.DateTime;
import core.managers.dbmanager.DBManager;
import core.managers.filemanager.JSONReader;
import core.managers.logmanager.MyLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.repeat;

public class Hooks {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
    JSONReader jsonReader = new JSONReader("deviceDetails.json");
    Date startDate = new Date(new DateTime().getTimeStamp());
    Date endDate = new Date(new DateTime().getTimeStamp());
    private DBManager db;
    SpeedwayApp speedwayApp;

    @Before()
    public void beforeScenario(Scenario scenario) {
        System.out.println("BEFORE**************************");
/*
        db = new DBManager();*/
        MyLogger.log.info("#######################" + repeat("#", scenario.getName().length()) + "#######################");
        MyLogger.log.info("###########  START OF SCENARIO : " + scenario.getName() + formatter.format(startDate) + "##########");
        MyLogger.log.info("#######################" + repeat("#", scenario.getName().length()) + "#######################");

        //this updates the runNumber from the database
    /*    if (runNumber == -1) {
            runNumber = db.queryDBForRunNumber(getModel()) + 1;
        }*/
    }


    @After()
    public void after(Scenario scenario) {
        formatter.format(endDate);
        MyLogger.log.info("##########################" + repeat("#", scenario.getName().length() + scenario.getStatus().toString().length()) + "###########################");
        MyLogger.log.info("### END OF SCENARIO : " + scenario.getName() + " ,Time taken:" + formatter.format(endDate) + " mm:ss, Status: " + capitalize(scenario.getStatus().toString()));
        MyLogger.log.info("##########################" + repeat("#", scenario.getName().length() + scenario.getStatus().toString().length()) + "###########################");
       if (scenario.getStatus().equals(Status.FAILED)) {
           scenario.attach(((TakesScreenshot) ThreadLocalDriver.getTLDriver()).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
           MyLogger.log.info(scenario.getName()+"is Failed");
       }
    }


  /*  private String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String[] tab = scenario.getId().split("/");
        int rawFeatureNameLength = tab.length;
        String featureName = tab[rawFeatureNameLength - 1].split(":")[0];
        System.out.println("featureName: " + featureName);
        return featureName;
    }
/*
    private String getModel() {
        return jsonReader.getJSONValue(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName"), "model");
    }

    private String getOS() {
        return jsonReader.getJSONValue(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName"), "os");
    }

    private String getBuild() {
        return jsonReader.getJSONValue(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName"), "build");
    }

    private String getOSName() {
        return jsonReader.getJSONValue(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("deviceName"), "platformname");
    }

    private String getTestStartTime() {
        return formatter.format(startDate) + "";
    }

    private String getTestEndTime() {
        return formatter.format(endDate) + "";
    }*/


}
