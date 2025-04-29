package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups = {"sanity", "master"})
    public void verify_login() {
        logger.info("**** Starting TC002_LoginTest ****");

        try {
            // create an instance of the HomePage class
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");

            hp.clickLogin();
            logger.info("Clicked on Login");

            // create an instance of the LoginPage class
            LoginPage loginPage = new LoginPage(driver);

            loginPage.setTxtEmailAddress(properties.getProperty("email"));
            logger.info("Entered email address");

            loginPage.setTxtPassword(properties.getProperty("password"));
            logger.info("Entered password");

            loginPage.clickLogin();
            logger.info("Clicked on Login button");

            // Add assertions to verify successful login

            // create an instance of the MyAccountPage class
            MyAccountPage myAccountPage = new MyAccountPage(driver);


            // Verify that the My Account page is displayed
            Assert.assertEquals(myAccountPage.isMyAccountPageDisplayed(), true, "Login failed");
            //Assert.assertTrue(myAccountPage.isMyAccountPageDisplayed());

            logger.info("Login successful, My Account page is displayed");

        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("Ending TC002_LoginTest");
    }

}
