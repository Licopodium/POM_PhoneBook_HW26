package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;
import org.testng.annotations.AfterMethod;


import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    int i;

    @BeforeMethod
    public void precondition(){
        i = new Random().nextInt(1000) + 1000;
        new SplashScreen(driver)
                .gotoAuthenticationScreen()
                .fillEmail("coral@gmail.com")
                .fillPassword("565656Ca$")
                .submitLogin();
    }

    @Test
    public void addNewContactPositive(){
        Contact contact = Contact.builder()
                .name("AddNewContact_" + i)
                .lastName("Positive")
                .email("addNewContact_" + i + "@mail.com")
                .phone("1234567" + i)
                .address("Rehovot")
                .description("NewContact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm();
    }


    @Test
    public void addNewContactNegativeName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Positive")
                .email("addNewContact_" + i + "@mail.com")
                .phone("1234567" + i)
                .address("Rehovot")
                .description("NewContact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextMust("name");

        new ContactListScreen(driver).navigateToInitialScreen();
    }

    @Test
    public void addNewContactNegativeLastName() {
        Contact contact = Contact.builder()
                .name("Lolita")
                .lastName("")
                .email("addNewContact_" + i + "@mail.com")
                .phone("1234567" + i)
                .address("Rehovot")
                .description("NewContact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextMust("lastName");

        new ContactListScreen(driver).navigateToInitialScreen();
    }

    @Test
    public void addNewContactNegativeEmail() {
        Contact contact = Contact.builder()
                .name("Lolita")
                .lastName("Nabokova")
                .email("addNewContact_" + i + "@mail@com")
                .phone("1234567" + i)
                .address("Rehovot")
                .description("NewContact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextMust("email");

        new ContactListScreen(driver).navigateToInitialScreen();
    }


    @Test
    public void addNewContactNegativePhoneNumber() {
        Contact contact = Contact.builder()
                .name("Lolita")
                .lastName("Nabokova")
                .email("addNewContact_" + i + "@mail.com")
                .phone("123" + i)
                .address("Rehovot")
                .description("NewContact_" + i)
                .build();
        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isErrorMessageContainsTextMust("phone");

        new ContactListScreen(driver).navigateToInitialScreen();
    }

}





