package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    //Provide the dataProvider and the dataProviderClass(location)
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"datadriven"})
    public void verify_LoginDDT(String email, String pwd, String exp) {

        //we need to pass 3 parameters to the test method - email, password and expected result
        logger.info("**** Starting TC003_LoginDDT ****");

        try {
            //HomePage
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            //LoginPage
            LoginPage lp = new LoginPage(driver);
            lp.setTxtEmailAddress(email);
            lp.setTxtPassword(pwd);
            lp.clickLogin();


            //MyAccountPage
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPage = myAccountPage.isMyAccountPageDisplayed();

            /*
            Data is valid   =>  login success => test passed => logout
                            =>  login failed  => test failed
            Data is invalid =>  login success => test failed => logout
                            =>  login failed  => test passed
            */

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("**** Finished TC003_LoginDDT ****");
    }
}
