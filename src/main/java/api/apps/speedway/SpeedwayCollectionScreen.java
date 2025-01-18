package api.apps.speedway;


import com.google.common.collect.ImmutableMap;
import core.uiactions.UiActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.JavascriptExecutor;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpeedwayCollectionScreen extends UiActions {
    public SpeedwayCollectionScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "Vehicles")
    private AndroidElement Vehicles;


    @AndroidFindBy(accessibility = "Series")
    private AndroidElement Series;

    @AndroidFindBy(accessibility = "Wishlist")
    private AndroidElement Wishlist;

    @AndroidFindBy(xpath = "//android.widget.ImageView/android.view.View")
    private List<AndroidElement> minusIcon;


    @AndroidFindBy(xpath = "//android.view.View//following-sibling::android.widget.ImageView")
    private List<AndroidElement> wishListItems;

    @AndroidFindBy(xpath = "(//android.view.View//following-sibling::android.widget.ImageView)[5]")
    private AndroidElement wishListFistITem;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Your Garage is empty.']")
    private AndroidElement emptyCollectionText;

    @AndroidFindBy(xpath = "//*[@content-desc='wishlist_filled icon']")
    private AndroidElement wishListSelectedIcon;

    @AndroidFindBy(accessibility = "wishlist icon")
    private AndroidElement wishListIcon;

    @AndroidFindBy(accessibility = "back_arrow icon")
    private AndroidElement backArrow;

    @AndroidFindBy(accessibility = "Your Wishlist is empty.")
    private AndroidElement emptyWishlistMessage;

    @AndroidFindBy(xpath = "//android.widget.ImageView/android.view.View[1]")
    private AndroidElement removeVehicleIcon;

    @AndroidFindBy (xpath = "//android.widget.ImageView/android.widget.HorizontalScrollView/android.view.View[1]")
    private List <AndroidElement> vehicleCountOnCard;

    @AndroidFindBy (xpath = "//android.widget.ImageView/android.widget.HorizontalScrollView/android.view.View[2]")
    private List <AndroidElement> vehicleYearOnCard;

    @AndroidFindBy (xpath = "//android.widget.ImageView/android.widget.HorizontalScrollView/android.view.View[3]")
    private List <AndroidElement> vehicleSeriesOnCard;

    @AndroidFindBy (accessibility = "back_arrow icon")
    private AndroidElement vehicleDetailsPageBackButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(2)")
    private AndroidElement DetailPageVehicleImage;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[1]/android.view.View/android.widget.ImageView")
    private AndroidElement detailPageVehicleImage;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"filter-icon icon\")")
    private AndroidElement filterIcon;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Recently Collected (Newest)\"]")
    private AndroidElement recentlyCollectedNewestFilterOption;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Recently Collected (Oldest)\"]")
    private AndroidElement recentlyCollectedOldestFilterOption;


    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Recently Collected (Newest)\"]")
    private AndroidElement recentlyWishlistedNewestFilterOption;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Recently Collected (Oldest)\"]")
    private AndroidElement recentlyWishlistedOldestFilterOption;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Release Date (Newest)\"]")
    private AndroidElement releaseDateNewestFilterOption;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Release Date (Oldest)\"]")
    private AndroidElement releaseDateOldestFilterOption;


    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Alphabetical (A-Z)\n\"]")
    private AndroidElement alphabeticalA_Z_FilterOption;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Alphabetical (Z-A)\"]")
    private AndroidElement alphabeticalZ_A_FilterOption;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Show Results\"]")
    private AndroidElement showResultButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"FILTER\"]")
    private AndroidElement closeFilterButton;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"CLEAR ALL\"]")
    private AndroidElement clearFilterButton;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"SORT\"]")
    private AndroidElement sortTitle;

    public void selectAlphabetical_Z_A_FilterOption(){
        tap(alphabeticalZ_A_FilterOption);
    }

    public void selectAlphabeticalA_Z_FilterOption(){
        tap(alphabeticalA_Z_FilterOption);
    }

    public void selectReleaseDateNewestFilterOption(){
        tap(releaseDateNewestFilterOption);
    }

    public void selectRecentlyCollectedOldestFilterOption(){
        tap(recentlyCollectedOldestFilterOption);
    }

    public void selectRecentlyWishlistedNewestFilterOption(){
        tap(recentlyWishlistedNewestFilterOption);
    }

    public void selectRecentlyWishlistedOldestFilterOption(){
        tap(recentlyWishlistedOldestFilterOption);
    }

    public void selectRecentlyCollectedNewestFilterOption(){
        tap(recentlyCollectedNewestFilterOption);
    }

    public void selectReleaseDateOldestFilterOption(){
        tap(releaseDateOldestFilterOption);
    }


    public void tapOnShowResultButton(){
        tap(showResultButton);
    }

    public void tapOnCloseFilterButton(){
        tap(closeFilterButton);
    }

    public void tapOnClearFilterButton(){
        tap(closeFilterButton);
    }

    public void selectManufacturingPlantFilter(String mp, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(mp,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+mp+"\"]"));
    }
    public void selectWheelFilter(String wheel, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(wheel,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+wheel+"\"]"));
    }
    public void selectScaleFilter(String scale, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(scale,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+scale+"\"]"));

    }
    public void selectProductDesignerFilter(String productDesigner, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(productDesigner,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+productDesigner+"\"]"));
    }

    public void selectGraphicDesignerFilter(String graphicDesigner, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(graphicDesigner,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+graphicDesigner+"\"]"));
    }

    public void selectCategoryFilter(String categoryName, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(categoryName,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+categoryName+"\"]"));
    }

    public void selectBodyColorFilter(String bodyColor, AndroidDriver driver){
        waitTime(2000);
        scrollToContentDesc(bodyColor,driver);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+bodyColor+"\"]"));
    }
    public void selectYearFilter(String yyyy){
        waitTime(2000);
        tap((AndroidElement)driver.findElementByXPath("//android.view.View[@content-desc=\""+yyyy+"\"]"));
    }

    public void tapOnFilterIcon(){
        tap(filterIcon);
    }

    public boolean isFilterDisplayed(){
        return isElementPresent(filterIcon);
    }

    public boolean isFilterOptionsDisplayedForSorting(String filterName, AndroidDriver driver){
        AndroidElement element = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" + ".setAsHorizontalList().scrollIntoView(new UiSelector().descriptionContains(\""+filterName+"\").instance(0))");
        return isElementPresent(element);
    }

    public void tapOnSortingOption(String filterName){
        AndroidElement element = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" + ".setAsHorizontalList().scrollIntoView(new UiSelector().descriptionContains(\""+filterName+"\").instance(0))");
        tap(element);
    }

    public boolean isSortOverlayWindowOpened(){
        return isElementPresent(sortTitle);
    }

    public String getYearByVehicleIndex(int index, String yy){
        AndroidElement ele =(AndroidElement) driver.findElementsByXPath("//android.view.View[contains(@content-desc,\"" + yy + "\")]").get(index-1);
        return ele.getAttribute("content-desc");
    }

    public boolean isSelectedYearVehicleDisplayed(String yyyy){
        AndroidElement ele =(AndroidElement) driver.findElementByXPath("//android.view.View[contains(@content-desc,\"" + yyyy + "\")]");
        return isElementPresent(ele);
    }
    public boolean isSelectedYearVehicleNotDisplayed(String yyyy){
        return (driver.findElementsByXPath("//android.view.View[contains(@content-desc,\"" + yyyy + "\")]").isEmpty());
    }
    public String getVehicleTitleFromDetailPage(String vehicleName){
       return  driver.findElementByXPath("//android.view.View[contains(@content-desc,\""+vehicleName+"\")]").getAttribute("content-desc");
    }

    public boolean isLabelDisplayedOnDetailPage(String label){
        return driver.findElementByAccessibilityId(label.toUpperCase()).isDisplayed();
    }

    public boolean isDetailDisplayedOnDetailPage(String name){
        return driver.findElementByAccessibilityId(name.toUpperCase()).isDisplayed();
    }
    public void tapOnVehicleImageOnDetailPage(){
        tap(detailPageVehicleImage);
    }

    public boolean isVehicleImageOnDetailPage(){
        return detailPageVehicleImage.isDisplayed();
    }

    public boolean isRemoveIconPresentOnVehicleCard() {
        return isElementPresent(removeVehicleIcon);
    }

    public void clickOnVehicleCardByName(String fullName, AndroidDriver<?> driver) {
        scrollToContentDesc( driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+fullName+"\")]/android.view.View[1]").getAttribute("content-desc"),driver);
        driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+fullName+"\")]/android.view.View[1]").click();
    }

    public void clickOnVehicleCardByIndex(int number, AndroidDriver<?> driver) {
        driver.findElementsByXPath("//android.widget.ScrollView//android.widget.ImageView").get(number).click();
    }
    public boolean isRemoveIconOnVehicleCardBByName(String fullName) {
        return isElementPresent(driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+fullName+"\")]/android.view.View"));
    }

    public void clickRemoveIconOnVehicleCard() {
        tap(removeVehicleIcon);
    }

    public void tapOnVehicle() {
        tap(Vehicles);
    }

    public void tapOnWishlist() {
        tap(Wishlist);
    }

    public void tapOnSeries() {
        tap(Series);
    }

    public void tapOnVehicleCard(String vehicleName){
        waitTime(2000);
        driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+vehicleName+"\")]").click();
    }

    public boolean isVehicleCardDisplayed(String vehicleName){
       return (!driver.findElementsByXPath("//android.widget.ImageView[contains(@content-desc,\"" + vehicleName + "\")]").isEmpty());
    }

    public boolean isVehicleCardNotDisplayed(String vehicleName){
        return (driver.findElementsByXPath("//android.widget.ImageView[contains(@content-desc,\"" + vehicleName + "\")]").isEmpty());
    }

    public void selectWishListIcon(){
        tap(wishListIcon);
    }

    public void tapOnBackBButton(){
        tap(vehicleDetailsPageBackButton);
    }

    public void removeCarsFromCollection() throws InterruptedException {
        if (!minusIcon.isEmpty()) {
            for (AndroidElement element : minusIcon) {
                try {
                    if (minusIcon.get(0).isDisplayed())
                        tap(minusIcon.get(0));
                } catch(Exception e) {
                    if (minusIcon.get(0).isDisplayed())
                        tap(minusIcon.get(0));
                }
                Thread.sleep(5000);
            }
        }
    }

    public String getEmptyCollectionText() {
        return emptyCollectionText.getAttribute("content-desc");
    }

    public boolean isGarageEmpty() {
        return isElementPresent(emptyCollectionText);
    }

    public void removeFromWishlist()  {
            if (!isElementPresent(emptyWishlistMessage)) {
                System.out.println("Inside if Loop");
                while(!isElementPresent(emptyWishlistMessage)) {
                    tap(wishListFistITem);
                    tap(wishListSelectedIcon);
                    tap(backArrow);
                }
            }
    }
    public void addWishlist(int index, AndroidDriver<AndroidElement> driver){
        if (isElementPresent(emptyWishlistMessage)) {
            System.out.println("inside prcondtion");
            new BottomNavigation(driver).tapOnCSearchIcon();
            clickOnVehicleCardByIndex(index,driver);
            if(isElementPresent(wishListIcon)){
                 tap(wishListIcon);
             }
            tap(backArrow);
        }
    }

    public void tapOnWishListIcon(){
        if(isElementPresent(wishListIcon)){
            tap(wishListIcon);
        }
        tap(backArrow);
    }

    public boolean isVehiclePresentInWishlist(){
        return isElementPresent(wishListFistITem);
    }

    public void clickOnAddIconOnFirstWishlistCard(){
        if(isElementPresent(wishListFistITem)){
            tap(driver.findElementByXPath("(//android.view.View//following-sibling::android.widget.ImageView)[5]//android.view.View[1]"));
        }
    }


    public String getEmptyWishlistText() {
        return waitToAppear(emptyWishlistMessage).getAttribute("content-desc");
    }

    public String getSeriesByCardIndex(int index){
        return waitToAppear(vehicleSeriesOnCard.get(index)).getAttribute("content-desc");
    }

    public String getCountByCardIndex(int index){
        return waitToAppear(vehicleSeriesOnCard.get(index)).getAttribute("content-desc");
    }


    public String getYearByCardIndex(int index){
        return vehicleSeriesOnCard.get(index).getAttribute("content-desc");
    }

    public String getSeriesByVehicleName(AndroidDriver<?> driver, String fullName){
        return driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+fullName+"\")]//android.widget.HorizontalScrollView/android.view.View[3]").getAttribute("content-desc");
    }


    public String getCountByVehicleName(AndroidDriver<?> driver, String fullName){
        return driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+fullName+"\")]//android.widget.HorizontalScrollView/android.view.View[1]").getAttribute("content-desc");
    }


    public String getYearByVehicleName(AndroidDriver<?> driver, String fullName){
        return driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+fullName+"\")]//android.widget.HorizontalScrollView/android.view.View[2]").getAttribute("content-desc");
    }

    public void tapOnSeriesCard(String name){
        tap(driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+name+"\")]"));
    }

    public boolean isSeriesDetailPageOpened(String name) {
            waitTime(3000);
            return isElementPresent((AndroidElement) driver.findElementByXPath("//android.view.View[contains(@content-desc,\"" + name + "\")]"));
    }

}

