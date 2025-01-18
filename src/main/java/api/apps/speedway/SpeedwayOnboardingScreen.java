package api.apps.speedway;

import core.uiactions.UiActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SpeedwayOnboardingScreen extends UiActions {
    @AndroidFindBy(accessibility = "Login with Mattel Account")
    AndroidElement loginWithMattelAccountBtn;

    @AndroidFindAll(value = {
            @AndroidBy(xpath = "//*[@resource-id=\"com.android.chrome:id/signin_fre_dismiss_button\"]"),
            @AndroidBy(xpath = "//android.widget.FrameLayout/android.widget.Button")
    })
    AndroidElement chromeBtn;

    public SpeedwayOnboardingScreen(AndroidDriver<?> driver) {
        super(driver);
    }

    public boolean isMattelAccountButtonExists(){
        return isElementPresent(loginWithMattelAccountBtn);
    }

    public void clickOnLoginWithMattelAccountButton(){
        waitTime(2000);
        tap(loginWithMattelAccountBtn);
    }

    public void clickOnChromeOption() throws Exception {
        if(isElementPresent(chromeBtn))
             tap(chromeBtn);
    }

}
