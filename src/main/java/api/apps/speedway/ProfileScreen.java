package api.apps.speedway;

import com.github.javafaker.Faker;
import core.uiactions.UiActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProfileScreen extends UiActions {
    public ProfileScreen(AndroidDriver<io.appium.java_client.android.AndroidElement> driver) {
        super(driver);
    }


    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"0\"])[1]")
    private AndroidElement countOfVehiclesCollected;
    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"0\"])[2]")
    private AndroidElement countOfSeriesCollected;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.Button")
    private AndroidElement settingIcon;
    @AndroidFindBy(accessibility = "SERIES COLLECTED")
    private AndroidElement profileSeriesCollectedHeadingTxt;
    @AndroidFindBy(accessibility = "VEHICLES COLLECTED")
    private AndroidElement profileVehiclesCollectedHeadingTxt;
    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Edit Profile']")
    private AndroidElement editProfile;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Log Out\"]")
    private AndroidElement logout;
    @AndroidFindBy(accessibility = "back_arrow icon")
    private AndroidElement backButton;
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(0)")
    private AndroidElement editProfileImagePlaceHolder;
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Username']")
    private AndroidElement editProfileUsername;
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'@')]")
    private AndroidElement profileUsername;
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'Email Address']")
    private AndroidElement editProfileEmailAddress;
    @AndroidFindBy(className = "android.widget.EditText")
    private AndroidElement editUsernameTextbox;
    @AndroidFindBy(xpath = "android.widget.Button" )
    private AndroidElement removeUsernameButton;
    @AndroidFindBy(accessibility = "Update Username")
    private AndroidElement UpdateUsernameButton;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.ImageView[2]")
    private AndroidElement editProfilePictureIcon;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Choose from Library\"]")
    private AndroidElement chooseFromLibrary;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Take a Photo\"]")
    private AndroidElement takeAPhoto;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_thumbnail\"])[1]")
    private AndroidElement firstThumbnailFromGallery;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Crop\"]")
    private AndroidElement confirmCropImage;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Delete Account\"]")
    private AndroidElement deleteAccountButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
    private AndroidElement permissionWhileUsingTheApp;
    @AndroidFindBy(id = "com.sec.android.app.camera:id/navigation_bar_item_small_label_view")
    private AndroidElement cameraOkButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Delete Account\"]")
    private AndroidElement deleteAccountConfirmationButton;
    @AndroidFindBy(id="com.sec.android.app.camera:id/bottom_background")
    private AndroidElement capturePhotoButton;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_thumbnail\"])[1]")
    private AndroidElement firstThumbnailGallery;

    Faker faker = new Faker();
    public boolean isDefaultCountOfVehiclesDisplayed(){
        return isElementPresent(countOfVehiclesCollected);
    }
    public boolean isDefaultCountOfSeriesDisplayed(){
        return isElementPresent(countOfSeriesCollected);
    }

    public void updateUsername(){
        tap(editProfileUsername);
        tap(removeUsernameButton);
        String firstName = faker.name().firstName();
        typeText(editUsernameTextbox,firstName);
        tap(UpdateUsernameButton);
        tap(backButton);
    }
    public void addProfileButtonFromGallery(){
        tap(editProfilePictureIcon);
        tap(chooseFromLibrary);
        tap(firstThumbnailFromGallery);
        tap(confirmCropImage);
        tap(backButton);

    }
    public void addProfileButtonFromCamera(){
        tap(editProfilePictureIcon);
        tap(takeAPhoto);
        if(permissionWhileUsingTheApp.isDisplayed()){
            tap(permissionWhileUsingTheApp);
        }
        tap(capturePhotoButton);
        tap(cameraOkButton);
        tap(confirmCropImage);
        tap(backButton);
    }
    public boolean isProfileUsernameDisplayed(){
        return profileUsername.isDisplayed();
    }
    public boolean isEditProfileUsernameDisplayed(){
        return editProfileUsername.isDisplayed();
    }
    public boolean isEditProfileEmailAddressDisplayed(){
        return editProfileEmailAddress.isDisplayed();
    }


    public boolean isEditProfileImagePlaceHolderDisplayed(){
        return editProfileImagePlaceHolder.isDisplayed();
    }

    public boolean isSettingIconDisplayed() {
        return isElementPresent(settingIcon);
    }

    public void tapOnSettingIcon() {
         tap(settingIcon);
    }

    public void clickOnSettingIcon() {
         tap(settingIcon);
    }

    public void clickOnEditProfile() {
        tap(editProfile);
    }
    public boolean isVehicleCollectedHeadingPresentOnProfileScreen(){
            return isElementPresent(profileVehiclesCollectedHeadingTxt);}

    public boolean isSeriesCollectedHeadingPresentOnProfileScreen(){
        return isElementPresent(profileSeriesCollectedHeadingTxt);
    }
}


