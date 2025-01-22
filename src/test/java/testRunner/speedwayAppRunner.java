package testRunner;

import core.BaseTest;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/resources/featurefiles/CollectionScreen.feature", glue = "stepdefinitions",
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "junit:target/cucumber-reports/Cucumber.xml","json:target/cucumber-reports/Cucumber.json","rerun:target/failed_scenarios.txt"},
        monochrome = true)
public class speedwayAppRunner extends BaseTest {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        System.out.println("Cucumber Test Class Before");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void feature(PickleWrapper pk, FeatureWrapper featureWrapper) {
        System.out.println(pk.getPickle());
        testNGCucumberRunner.runScenario(pk.getPickle());
    }

    @DataProvider
    public Object[][] features() {
        System.out.println("Data Provider test Class");
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

}
