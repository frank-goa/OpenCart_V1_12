package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // Add locators for the My Account page elements here

    // Locator for the My Account page header
    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement lblMyAccount;

    // Locator for the Logout link
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement lnkLogout;

    // Method to verify if the My Account page is displayed
    public boolean isMyAccountPageDisplayed() {
        try {
            return lblMyAccount.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }


    // Method to click on the Logout link
    public void clickLogout() {
        lnkLogout.click();
    }

}
