package tests;

import config.AppiumConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import models.Auth;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    int i;

    @BeforeMethod
    public void precondition() {
        i = new Random().nextInt(1000) + 1000;
    }


    @Test
    public void registrationPositive() {
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("coral_" + i + "@gmail.com")
                .fillPassword("565656Ca$")
                .submitRegistration()
                .assertContactListActivityPresent();
    }

    @Test
    public void registrationPositiveModel() {
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registration(
                        Auth.builder()
                                .email("coral_" + i + "@gmail.com")
                                .password("565656Ca$")
                                .build()
                )
                .isContactListActivityPresent();
    }

    @Test
    public void registrationWrongEmail(){
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("coral_" + i + "gmail.com")
                .fillPassword("565656Ca$")
                .submitRegistrationNegative()
                .isErrorMessageContainsText("email address");

    }

    @Test
    public void registrationWrongPassword() {
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registrationNegative(
                        Auth.builder()
                                .email("coral_" + i + "@gmail.com")
                                .password("5656567a$")
                                .build()
                )
//                .isErrorMessageContainsText("password");
                .isErrorMessageContainsTextInAlert("password");
    }


    @Test
    public void registrationUserExist() {
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .registrationNegative(
                        Auth.builder()
                                .email("coral@gmail.com")
                                .password("5656567Ca$")
                                .build()
                )
                .isErrorMessageContainsTextInAlertUser("User");
    }

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver).logout();
        new SplashScreen(driver);
    }
}