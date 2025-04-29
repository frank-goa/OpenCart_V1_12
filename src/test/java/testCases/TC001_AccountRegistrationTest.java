package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"regression", "master"})
    public void verify_account_registration() {

        logger.info("**** Starting TC001_AccountRegistrationTest ****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account");

            hp.clickRegister();
            logger.info("Clicked on Register");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            String firstName = randomString();
            String email = firstName.toLowerCase() + "@gmail.com";

            regpage.setFirstName(firstName.toUpperCase());
            regpage.setLastName(randomString().toUpperCase());
            regpage.setEmail(email);

            String pwd = randomAlphaNumeric();
            regpage.setTelephone(randomNumber());
            regpage.setPassword(pwd);
            regpage.setConfirmPassword(pwd);
            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            logger.info("Clicked on Continue");

            logger.info("Verifying account registration");
            String confirmationMessage = regpage.getConfirmationMessage();
            AssertJUnit.assertEquals(confirmationMessage, "Your Account Has Been Created!");
        } catch (Exception e) {
            logger.error("Test case failed: " + e.getMessage());
            logger.debug("Debug info: " + e);
            AssertJUnit.fail();
        }

        logger.info("**** Finished TC001_AccountRegistrationTest ****");

    }

}
