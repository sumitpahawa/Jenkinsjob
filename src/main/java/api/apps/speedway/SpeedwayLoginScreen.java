package api.apps.speedway;

import core.uiactions.UiActions;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SpeedwayLoginScreen extends UiActions {
    public SpeedwayLoginScreen(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//input[@name='email']")
    private AndroidElement email;

    @AndroidFindBy(xpath = "//input[@name='password']")
    private AndroidElement password;

    @AndroidFindBy(xpath = "//input[@name='submit']")
    private AndroidElement continueBtn;

    @AndroidFindBy(xpath = "//a[.='Sign Up']")
    private AndroidElement signUp;

    @AndroidFindBy(xpath = "//a[.='Forgot password?']")
    private AndroidElement forgotPassword;

    @AndroidFindBy(xpath = "//input[@name='registerOptin']")
    private AndroidElement signUpConsentCheckbox1;

    @AndroidFindBy(xpath = "//small//label/input[@value='on']")
    private AndroidElement signUpConsentCheckbox2;

    @AndroidFindBy(xpath = "//span[.='At least 8 characters in length']")
    private AndroidElement passwordLengthError;

    public void login(AndroidDriver driver, String username, String pass) throws Exception {
            Thread.sleep(10000);
            switchToWebContext(driver);
            Thread.sleep(12000);
             ((WebDriver) driver).findElement(By.xpath("//input[@name='email']")).sendKeys(username);
            ((WebDriver) driver).findElement(By.xpath("//input[@name='password']")).sendKeys(pass);


    }

    public boolean isEmailExist(){
        return isElementPresent(email);
    }

    public void clickOnContinueButton(AndroidDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        ((WebDriver)driver).findElement(By.xpath("//button[@name='submit']")).click();
        waitTime(5000);
        driver.context("NATIVE_APP");
    }

}
