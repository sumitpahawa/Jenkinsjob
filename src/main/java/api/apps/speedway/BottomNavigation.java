package api.apps.speedway;

import core.uiactions.UiActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class BottomNavigation extends UiActions {
    public BottomNavigation(AndroidDriver<io.appium.java_client.android.AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "collection icon")
    private AndroidElement collectionIcon;

    @AndroidFindBy(accessibility = "search icon")
    private AndroidElement searchIcon;


    @AndroidFindBy(accessibility = "news icon")
    private AndroidElement newsIcon;

    @AndroidFindBy(accessibility = "profile icon")
    private AndroidElement profileIcon;


    public void tapOnCollectionIcon() {
        waitTime(5000);
        tap(collectionIcon);
    }

    public void tapOnNewIcon() {
        tap(newsIcon);
    }

    public void tapOnCSearchIcon() {
        tap(searchIcon);
    }

    public void tapOnProfileIcon() {
        tap(profileIcon);
    }

    public boolean isCollectionIconDisplayed(){
        return isElementPresent(collectionIcon);
    }
}


