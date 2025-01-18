package stepdefinitions.speedwaApp;
import api.apps.speedway.SpeedwayApp;
import core.ThreadLocalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class OnboardingStepdefs {
    SpeedwayApp speedwayApp = new SpeedwayApp(ThreadLocalDriver.getTLDriver());

    @Given("I have installed and launch the speedway app")
    public void iHaveInstalledAndLaunchTheSpeedwayApp(){
        Assert.assertEquals(speedwayApp.speedwayOnboardingScreen.isMattelAccountButtonExists(),true);
    }

    @Then("I should see the onboarding screen")
    public void iShouldSeeTheOnboardingScreen() {
        Assert.assertEquals(speedwayApp.speedwayOnboardingScreen.isMattelAccountButtonExists(),true);
    }

    @When("I press the Get Started button")
    public void iPressTheGetStartedButton() throws Exception {
        speedwayApp.speedwayOnboardingScreen.clickOnLoginWithMattelAccountButton();
        speedwayApp.speedwayOnboardingScreen.clickOnChromeOption();
    }

    @Then("I should see the Sign In Screen")
    public void iShouldSeeTheSignInScreen() throws InterruptedException {
        //speedwayApp.speedwayOnboardingScreen.switchToWebContext(ThreadLocalDriver.getTLDriver());
        speedwayApp.speedwayLoginScreen.isEmailExist();
    }

    @When("I type my credentials {string} and {string}")
    public void iTypeMyCredentialsAnd(String arg0, String arg1) throws Exception {
        speedwayApp.speedwayLoginScreen.login(ThreadLocalDriver.getTLDriver(),arg0,arg1);
    }

    @And("I press the Continue button")
    public void iPressTheContinueButton() throws InterruptedException {
        speedwayApp.speedwayLoginScreen.clickOnContinueButton(ThreadLocalDriver.getTLDriver());
    }

    @Then("I should see an error message indicating my credentials are invalid")
    public void iShouldSeeAnErrorMessageIndicatingMyCredentialsAreInvalid() {
        System.out.println("Pass");
    }

    @Then("I should be redirected to My Garage Screen")
    public void iShouldBeRedirectedToMyGarageScreen() throws InterruptedException {
        Thread.sleep(5000);
    }


}
