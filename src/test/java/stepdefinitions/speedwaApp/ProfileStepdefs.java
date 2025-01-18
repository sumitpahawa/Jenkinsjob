package stepdefinitions.speedwaApp;

import api.apps.speedway.SpeedwayApp;
import core.ThreadLocalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProfileStepdefs {

    SpeedwayApp speedwayApp = new SpeedwayApp(ThreadLocalDriver.getTLDriver());

    @When("I tap Settings Icon")
    public void iTapSettingsIcon() {
        speedwayApp.profileScreen.tapOnSettingIcon();
    }

    @Then("I should see the Settings Screen")
    public void iShouldSeeTheSettingsScreen() {
        Assert.assertTrue(speedwayApp.profileScreen.isEditProfileImagePlaceHolderDisplayed());
    }

    @And("I see the Settings Icon")
    public void iSeeTheSettingsIcon() {
        Assert.assertTrue(speedwayApp.profileScreen.isSettingIconDisplayed());


    }

    @And("I see the Profile Image")
    public void iSeeTheProfileImage() {
        Assert.assertTrue(speedwayApp.profileScreen.isEditProfileImagePlaceHolderDisplayed());

    }

    @And("I see the Username")
    public void iSeeTheUsername() {
        Assert.assertTrue(speedwayApp.profileScreen.isProfileUsernameDisplayed());
    }


    @And("I see the No. of Vehicles Collected")
    public void iSeeTheNoOfVehiclesCollected() {
        Assert.assertTrue(speedwayApp.profileScreen.isDefaultCountOfVehiclesDisplayed());

    }

    @And("I see the No. of Series Collected")
    public void iSeeTheNoOfSeriesCollected() {
        Assert.assertTrue(speedwayApp.profileScreen.isDefaultCountOfSeriesDisplayed());

    }

    @And("I see the Vehicles Collected")
    public void iSeeTheVehiclesCollected() {
        Assert.assertTrue(speedwayApp.profileScreen.isVehicleCollectedHeadingPresentOnProfileScreen());
    }

    @And("I see the Series Collected")
    public void iSeeTheSeriesCollected() {
        Assert.assertTrue(speedwayApp.profileScreen.isSeriesCollectedHeadingPresentOnProfileScreen());
    }

}
