package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactListScreen extends BaseScreen{

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']" +
            "/android.widget.TextView")
    MobileElement activityTextView;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logoutButton;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOption;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement plusButton;

    public boolean isContactListActivityPresent(){
        return shouldHave(activityTextView, "Contact list", 15);
    }

    public AuthenticationScreen logout(){
        if(isDisplayedWithException(moreOption)) {
            moreOption.click();
            logoutButton.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen assertContactListActivityPresent(){
        Assert.assertTrue(isContactListActivityPresent());
        return this;
    }

    public AddNewContactScreen openContactForm(){
        waitElement(plusButton, 15);
        plusButton.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isErrorMessageContainsTextMust(String text) {

        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this;
    }

    public boolean isErrorMessageContainsWrongLogin(String text){
        Alert alert = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();
        return this.isContactListActivityPresent();
    }

    public void navigateToInitialScreen() {

            driver.resetApp();
        }
    }
