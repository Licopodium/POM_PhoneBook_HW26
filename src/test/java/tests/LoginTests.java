package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

    public class LoginTests extends AppiumConfig {

        @Test
        public void loginPositive() {
            Assert.assertTrue(
                    new SplashScreen(driver)
                            .gotoAuthenticationScreen()
                            .fillEmail("coral@gmail.com")
                            .fillPassword("565656Ca$")
                            .submitLogin()
                            .isContactListActivityPresent()
            );
        }

        @Test
        public void loginPositiveModel() {
            Assert.assertTrue(
                    new SplashScreen(driver)
                            .gotoAuthenticationScreen()
                            .login(
                                    Auth.builder()
                                            .email("coral@gmail.com")
                                            .password("565656Ca$")
                                            .build()
                            )
                            .isContactListActivityPresent()
            );
        }

        @Test
        public void loginNegativeWrongEmail() {
         new SplashScreen(driver)
                    .gotoAuthenticationScreen()
                    .registrationNegative(
                            Auth.builder()
                                    .email("coral@gmail@com")
                                    .password("565656Ca$")
                                    .build()
                    )
                    .isErrorMessageContainsTextInAlertWrongLogin("username");
        }


        @Test
        public void loginNegativeWrongPassword() {
            new SplashScreen(driver)
                    .gotoAuthenticationScreen()
                    .registrationNegative(
                            Auth.builder()
                                    .email("coral@gmail.com")
                                    .password("5656567a$")
                                    .build()
                    )
                    .isErrorMessageContainsTextInAlertWrongPassword("password");
        }

                @AfterMethod
        public void postCondition(){
            new ContactListScreen(driver).logout();
            new SplashScreen(driver);
        }


    }
