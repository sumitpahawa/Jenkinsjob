package stepdefinitions.speedwaApp;

import api.apps.speedway.SpeedwayApp;
import com.google.common.collect.ImmutableMap;
import core.ThreadLocalDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CollectionScreen {
    SpeedwayApp speedwayApp = new SpeedwayApp(ThreadLocalDriver.getTLDriver());

    @Given("No cars should be added to the Vehicles tab")
    public void noCarsShouldBeAddedToTheVehiclesTab() throws InterruptedException {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.removeCarsFromCollection();
    }

    @When("I'm in the Vehicles tab")
    public void iMInTheVehiclesTab() {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.tapOnVehicle();
    }

    @Then("I should be able to see {string}.")
    public void iShouldBeAbleToSee(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getEmptyCollectionText(), arg0);
    }

    @When("I'm in the Series tab")
    public void iMInTheSeriesTab() {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.tapOnSeries();
    }

    @Then("I should be able to see the Series Hot Wheels Collection empty with Nothing in my collection text")
    public void iShouldBeAbleToSeeTheSeriesHotWheelsCollectionEmptyWithNothingInMyCollectionText() {
    }

    @When("I'm in the Wishlist tab")
    public void iMInTheWishlistTab() {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.tapOnWishlist();

    }

    @Given("No vehicle are present in Wishlist")
    public void noVehicleArePresentInWishlist() {
        speedwayApp.speedwayCollectionScreen.removeFromWishlist();
    }

    @Then("I should be able to see empty wishlist message {string}.")
    public void iShouldBeAbleToSeeEmptyWishlistMessage(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getEmptyWishlistText(), arg0);
    }

    @When("I tap Profile from the Navigation Menu at the bottom")
    public void iTapProfileFromTheNavigationMenuAtTheBottom() {
        speedwayApp.bottomNavigation.tapOnProfileIcon();
    }

    @Then("I should go to User Profile page")
    public void iShouldGoToUserProfilePage() {
        Assert.assertTrue(speedwayApp.profileScreen.isSettingIconDisplayed());
        Assert.assertTrue(speedwayApp.profileScreen.isVehicleCollectedHeadingPresentOnProfileScreen());
        Assert.assertTrue(speedwayApp.profileScreen.isSeriesCollectedHeadingPresentOnProfileScreen());
    }

    @When("I tap News from the Navigation Menu at the bottom")
    public void iTapNewsFromTheNavigationMenuAtTheBottom() {
        speedwayApp.bottomNavigation.tapOnNewIcon();
    }

    @Then("I should go to News page")
    public void iShouldGoToNewsPage() {
        Assert.assertTrue(speedwayApp.newsScreen.isTitleDisplayedOnNewsScreen());
    }

    @When("I tap Search from the Navigation Menu at the bottom")
    public void iTapSearchFromTheNavigationMenuAtTheBottom() {
        speedwayApp.bottomNavigation.tapOnCSearchIcon();
    }

    @Then("I should go to Search page")
    public void iShouldGoToSearchPage() {
        Assert.assertTrue(speedwayApp.searchScreen.isTitleDisplayedOnSearchScreen());
    }

    @When("I input the {string} into the search textbox,")
    public void iInputTheIntoTheSearchTextbox(String arg0) {
        speedwayApp.searchScreen.enterTextInSearchBox(arg0, ThreadLocalDriver.getTLDriver());
    }

    @Then("I should see the {string} car on the screen.")
    public void iShouldSeeTheCarOnTheScreen(String arg0) throws InterruptedException {
        speedwayApp.searchScreen.isItemPresentInSearchResult(ThreadLocalDriver.getTLDriver(), arg0);
    }

    @When("I add cars with {string}.")
    public void iAddCarsWithHONDAACCORD(String arg0) {
        speedwayApp.searchScreen.addVehicleInCollection(ThreadLocalDriver.getTLDriver(), arg0);
    }

    @When("I navigate to Vehicles tab")
    public void iNavigateToVehiclesTab() {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.tapOnVehicle();
    }

    @Then("I should see the {string} in the Vehicles tab.")
    public void iShouldSeeTheInTheVehiclesTab(String arg0) throws InterruptedException {
        speedwayApp.searchScreen.isItemPresentInSearchResult(ThreadLocalDriver.getTLDriver(), arg0);
    }

    @And("I close the App")
    public void iCloseTheApp() {

        ThreadLocalDriver.getTLDriver().executeScript("mobile:terminateApp", ImmutableMap.of("appId", "com.mattel.hwcollector"));
        //ThreadLocalDriver.getTLDriver().executeScript("mobile: launchApp", "adb shell am force-stop com.mattel.hwcollector");

    }

    @And("I re-launch the App")
    public void iReLaunchTheApp() {
        // ThreadLocalDriver.getTLDriver().executeScript("mobile:shell", "adb shell am start -n com.mattel.hwcollector/com.mattel.hwcollector.MainActivity");
        ThreadLocalDriver.getTLDriver().executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.mattel.hwcollector/com.mattel.hwcollector.MainActivity"));

    }

    @Then("I should see the Remove icon for {string}")
    public void iShouldSeeTheRemoveIconFor(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isRemoveIconOnVehicleCardBByName(arg0));
    }


    @Then("I should see the Series Name {string} for {string}")
    public void iShouldSeeTheSeriesNameFor(String arg0, String arg1) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getSeriesByVehicleName(ThreadLocalDriver.getTLDriver(),arg1),arg0);
    }

    @Then("I should see the Year {string} for {string}")
    public void iShouldSeeTheYearFor(String arg0, String arg1) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleName(ThreadLocalDriver.getTLDriver(),arg1),arg0);

    }

    @Given("Vehicle are already in Wishlist")
    public void vehicleAreAlreadyInWishlist() {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.tapOnWishlist();
        speedwayApp.speedwayCollectionScreen.addWishlist(1,ThreadLocalDriver.getTLDriver());

    }

    @And("I see a list of Vehicles Cards")
    public void iSeeAListOfVehiclesCards() {
        speedwayApp.bottomNavigation.tapOnCollectionIcon();
        speedwayApp.speedwayCollectionScreen.tapOnWishlist();
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isVehiclePresentInWishlist());


    }

    @When("I tap in the plus icon of one vehicle car")
    public void iTapInThePlusIconOfOneVehicleCar() {
        speedwayApp.speedwayCollectionScreen.clickOnAddIconOnFirstWishlistCard();
    }

    @When("I tap in the minus icon of one vehicle car")
    public void iTapInTheMinusIconOfOneVehicleCar() {
        speedwayApp.speedwayCollectionScreen.clickOnAddIconOnFirstWishlistCard();
    }
    @Then("I should see the {string} options instead of + button.")
    public void iShouldSeeTheOptionsInsteadOfButton(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isRemoveIconPresentOnVehicleCard());
    }

    @Then("I should see the {int} car is displayed correctly in My Collection")
    public void iShouldSeeTheCarIsDisplayedCorrectlyInMyCollection(int arg0) {
        Assert.assertFalse(speedwayApp.speedwayCollectionScreen.isGarageEmpty());

    }

    @When("I tap {string} vehicle card")
    public void iTapVehicleCard(String arg0) {
        speedwayApp.speedwayCollectionScreen.tapOnVehicleCard(arg0);
    }

    @Then("Then I should be redirected to the Vehicle's Details Screen with {string}")
    public void thenIShouldBeRedirectedToTheVehicleSDetailsScreenWith(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.getVehicleTitleFromDetailPage(arg0).contains(arg0));
    }

    @When("I tap in the minus icon of one vehicle card")
    public void iTapInTheMinusIconOfOneVehicleCard() {
        speedwayApp.speedwayCollectionScreen.clickRemoveIconOnVehicleCard();
    }



    @And("I should see the number of vehicles in my library decreased by {int}, indicating that the selected vehicle has been successfully removed")
    public void iShouldSeeTheNumberOfVehiclesInMyLibraryDecreasedByIndicatingThatTheSelectedVehicleHasBeenSuccessfullyRemoved(int arg0) {
       Assert.assertTrue(speedwayApp.speedwayCollectionScreen.getEmptyCollectionText().contains("empty"));

    }

    @When("I click in the All Series tab")
    public void iClickInTheAllSeriesTab() {
        speedwayApp.searchScreen.tapOnAllSeriesTab();

    }

    @Then("I see a list of Series Cards")
    public void iSeeAListOfSeriesCards() {
        Assert.assertTrue(speedwayApp.searchScreen.isCardsDisplayedOnAllSeriesTab());

    }

    @Then("I should see the Series card displays with image")
    public void iShouldSeeTheSeriesCardDisplaysWithImage() {
        Assert.assertTrue(speedwayApp.searchScreen.isCardsDisplayedOnAllSeriesTab());

    }


    @When("I tap on {string} series card")
    public void iTapOnSeriesCard(String arg0) {
        speedwayApp.speedwayCollectionScreen.tapOnSeriesCard(arg0);

    }

    @Then("I should see in my screen the {string} Series Detail corresponding to the selected series card")
    public void iShouldSeeInMyScreenTheSeriesDetailCorrespondingToTheSelectedSeriesCard(String arg0) {
        speedwayApp.speedwayCollectionScreen.isSeriesDetailPageOpened(arg0);
    }

    @When("I tab on filter icon")
    public void iTabOnFilterIcon() {
        speedwayApp.speedwayCollectionScreen.tapOnFilterIcon();
    }

    @When("I tab on show result button")
    public void iTabOnShowResultButton() {
        speedwayApp.speedwayCollectionScreen.tapOnShowResultButton();
    }

    @Then("I should see the Year {string} AUDI LMS on first position")
    public void iShouldSeeTheYearAUDIRLMSOnFirstPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(1,"20"),arg0);
    }

    @Then("I should see the Year {string} DODGE MACHO on second position")
    public void iShouldSeeTheYearDODGEMACHOOnFirstPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(2,"20"),arg0);
    }

    @Then("I should see the Year {string} FORD GT on third position")
    public void iShouldSeeTheYearFORDGTOnThirdPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(3,"20"),arg0);
    }

    @When("I select Release Date \\(Newest) filter.")
    public void iSelectReleaseDateNewestFilter() {
        speedwayApp.speedwayCollectionScreen.selectReleaseDateNewestFilterOption();
    }

    @When("I select Release Date \\(Oldest) filter")
    public void iSelectReleaseDateOldestFilter() {
        speedwayApp.speedwayCollectionScreen.selectReleaseDateOldestFilterOption();
    }

    @When("I select Recently Collected \\(Oldest) filter.")
    public void iSelectRecentlyCollectedOldestFilter() {
        speedwayApp.speedwayCollectionScreen.selectRecentlyCollectedOldestFilterOption();

    }

    @When("I select Recently Wishlisted \\(Oldest) filter.")
    public void iSelectRecentlyWishlistedOldestFilter() {
        speedwayApp.speedwayCollectionScreen.selectRecentlyWishlistedOldestFilterOption();

    }
    @When("I select Recently Wishlisted \\(Newest) filter.")
    public void iSelectRecentlyWishlistedNewestFilter() {
        speedwayApp.speedwayCollectionScreen.selectRecentlyWishlistedNewestFilterOption();

    }

    @When("I select Recently Collected \\(Newest) filter.")
    public void iSelectRecentlyCollectedNewestFilter() {
        speedwayApp.speedwayCollectionScreen.selectRecentlyCollectedNewestFilterOption();

    }

    @When("I select {string} year filter.")
    public void iSelectYearFilter(String arg0) {
        speedwayApp.speedwayCollectionScreen.selectYearFilter(arg0);

    }

    @Then("I should not see the Year {string} DODGE MACHO on first position")
    public void iShouldNotSeeTheYearDODGEMACHOOnFirstPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(2,"20"),arg0);

    }

    @Then("I should not see the Year {string} FORD GT on first position")
    public void iShouldNotSeeTheYearFORDGTOnFirstPosition(String arg0) {
        Assert.assertFalse(speedwayApp.speedwayCollectionScreen.isSelectedYearVehicleDisplayed(arg0));

    }

    @Then("I should see the Year {string} AUDI LMS on third position")
    public void iShouldSeeTheYearAUDILMSOnThirdPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(3,"20"),arg0);

    }


    @Then("I should see the Year {string} DODGE MACHO.")
    public void iShouldSeeTheYearDODGEMACHO(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isSelectedYearVehicleDisplayed(arg0));

    }

    @Then("I should not see the Year {string} AUDI LMS.")
    public void iShouldNotSeeTheYearAUDILMS(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isSelectedYearVehicleNotDisplayed(arg0));

    }

    @Then("I should not see the Year {string} FORD GT.")
    public void iShouldNotSeeTheYearFORDGT(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isSelectedYearVehicleNotDisplayed(arg0));
    }

    @Then("I should see the Year {string} DODGE MACHO on Second position")
    public void iShouldSeeTheYearDODGEMACHOOnSecondPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(2,"20"),arg0);

    }
    @Then("I should see the Year {string} FORD GT on first position")
    public void iShouldSeeTheYearFORDGTOnFirstPosition(String arg0) {
        Assert.assertEquals(speedwayApp.speedwayCollectionScreen.getYearByVehicleIndex(1,"20"),arg0);

    }

    @When("I select {string} product designer filter.")
    public void iSelectProductDesignerFilter(String arg0) {
        speedwayApp.speedwayCollectionScreen.selectProductDesignerFilter(arg0,ThreadLocalDriver.getTLDriver());

    }

    @Then("I should see the {string}.")
    public void iShouldSeeThe(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isVehicleCardDisplayed(arg0));

    }

    @Then("I should not see the {string}.")
    public void iShouldNotSeeThe(String arg0) {
        Assert.assertTrue(speedwayApp.speedwayCollectionScreen.isVehicleCardNotDisplayed(arg0));

    }

    @When("I select {string} graphic designer filter.")
    public void iSelectGraphicDesignerFilter(String arg0) {
        speedwayApp.speedwayCollectionScreen.selectGraphicDesignerFilter(arg0,ThreadLocalDriver.getTLDriver());

    }

    @When("I select {string} body color filter.")
    public void iSelectBodyColorFilter(String arg0) {
        speedwayApp.speedwayCollectionScreen.selectBodyColorFilter(arg0,ThreadLocalDriver.getTLDriver());

    }

    @When("I tap on Wishlist icon.")
    public void iTapOnWishlistIcon() {
        speedwayApp.searchScreen.tapOnWishListIcon();
    }

    @When("I tap on back icon.")
    public void iTapOnBackIcon() {
        speedwayApp.searchScreen.tapOnBackArrow();

    }

    @When("I tap on back icon on detail page.")
    public void iTapOnBackIconOnDetailPage() {
        speedwayApp.searchScreen.tapOnBackArrowDetailPage();

    }

    @Then("I see the filter icon")
    public void iSeeTheFilterIcon() {
        speedwayApp.speedwayCollectionScreen.isFilterDisplayed();
    }

    @And("I see the {string} sorting option")
    public void iSeeTheSortingOption(String arg0) {
        speedwayApp.speedwayCollectionScreen.isFilterOptionsDisplayedForSorting(arg0,ThreadLocalDriver.getTLDriver());
        speedwayApp.speedwayCollectionScreen.waitTime(5000);
    }
}
