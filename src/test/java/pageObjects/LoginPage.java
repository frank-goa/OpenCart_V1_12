package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Add locators for the login page elements here

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmailAddress;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement btnLogin;


    // Add methods to interact with the login page elements here
    public void setTxtEmailAddress(String email) {
        txtEmailAddress.sendKeys(email);
    }

    public void setTxtPassword(String password) {
        txtPassword.sendKeys(password);
    }

    public void clickLogin() {
        btnLogin.click();
    }
}
