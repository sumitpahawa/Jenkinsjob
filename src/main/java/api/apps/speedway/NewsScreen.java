package api.apps.speedway;

import core.uiactions.UiActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NewsScreen extends UiActions {
    public NewsScreen(AndroidDriver<io.appium.java_client.android.AndroidElement> driver) {
        super(driver);
    }


    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"NEWS\")]")
    private AndroidElement  newsScreenTitle;


    public boolean isTitleDisplayedOnNewsScreen(){
        return isElementPresent(newsScreenTitle);

    }

}


