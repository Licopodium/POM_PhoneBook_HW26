package tests;

import config.AppiumConfig;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginPositive(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("coral@gmail.com")
                .fillPassword("565656Ca$")
                .submitLogin();
    }

}
