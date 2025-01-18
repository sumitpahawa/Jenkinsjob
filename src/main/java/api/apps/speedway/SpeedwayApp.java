package api.apps.speedway;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.*;

public class SpeedwayApp {
    public AndroidDriver driver;
    public SpeedwayOnboardingScreen speedwayOnboardingScreen;
    public SpeedwayLoginScreen speedwayLoginScreen;
    public SpeedwayCollectionScreen speedwayCollectionScreen;
    public BottomNavigation bottomNavigation;
    public ProfileScreen profileScreen;
    public NewsScreen newsScreen;
    public SearchScreen searchScreen;


    public SpeedwayApp(AndroidDriver driver) {
        speedwayOnboardingScreen = new SpeedwayOnboardingScreen(driver);
        speedwayLoginScreen =  new SpeedwayLoginScreen(driver);
        speedwayCollectionScreen = new SpeedwayCollectionScreen(driver);
        bottomNavigation = new BottomNavigation(driver);
        profileScreen = new ProfileScreen(driver);
        newsScreen = new NewsScreen(driver);
        searchScreen = new SearchScreen(driver);

    }



}
