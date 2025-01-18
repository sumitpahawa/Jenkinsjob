package api.apps.speedway;

import core.uiactions.UiActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class SearchScreen extends UiActions {
    public SearchScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search for series, vehicles, etc...\"]\n")
    private AndroidElement searchTextbox;
    @AndroidFindBy(xpath = "//android.widget.EditText")
    private AndroidElement searchBox;
    @AndroidFindBy(accessibility = "DISCOVER")
    private AndroidElement searchScreenTitle;
    @AndroidFindBy(xpath ="//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.widget.Button[1]")
    private AndroidElement backArrow;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"back_arrow icon\"]")
    private AndroidElement detailsPageBackArrow;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"All Series\"]")
    private AndroidElement allSeriesTab;
    @AndroidFindBy(xpath = "//*[@content-desc='wishlist_filled icon']")
    private AndroidElement wishListSelectedIcon;

    @AndroidFindBy(accessibility = "wishlist icon")
    private AndroidElement wishListIcon;


    public void tapOnAllSeriesTab(){
        tap(allSeriesTab);
    }

    public boolean isTitleDisplayedOnSearchScreen() {
        return isElementPresent(searchScreenTitle);
    }

    public void enterTextInSearchBox(String text, AndroidDriver<?> driver) {
        tap(searchTextbox);
        typeText(searchBox,text);
        pressEnterKey(driver);

    }

    public boolean isItemPresentInSearchResult(AndroidDriver<?> driver, String name) throws InterruptedException {
        Thread.sleep(5000);
        return isElementPresent(driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+name+"\")]"));
    }

    public void addVehicleInCollection(AndroidDriver<?> driver, String name){
        driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+name+"\")]").findElement(By.xpath("//android.view.View[1]")).click();
        tap(backArrow);
    }

    public boolean isCardsDisplayedOnAllSeriesTab(){
        return driver.findElementsByXPath("//android.widget.ImageView[contains(@content-desc,'%')]").size()>0;

    }
    public boolean isCardDisplayedOnAllSeriesTab(){
        return isElementPresent((AndroidElement) driver.findElementsByXPath("//android.widget.ImageView[contains(@content-desc,'%')]").get(1));

    }
    public void tapOnVehicleCard(String vehicleName){
        driver.findElementByXPath("//android.widget.ImageView[contains(@content-desc,\""+vehicleName+"\")]").click();
    }
    public void tapOnWishListIcon(){
        if(isElementPresent(wishListIcon)){
            tap(wishListIcon);
        }
    }

    public void tapOnBackArrow(){
        tap(backArrow);
    }
    public void tapOnBackArrowDetailPage(){
        tap(detailsPageBackArrow);
    }

}
