package tests;

import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void checkThatTheUserCanLogin() {
        homePage.openLoginPage(driver)
                .addMobileNumber(driver, mobile)
                .clickContinue(driver)
                .addPassword(driver, password)
                .clickSignInButton(driver);
        softAssert.assertTrue(homePage.isElementContainsText(driver, homePage.loginButton, "Hello, Mohamed"));
        softAssert.assertAll();
    }
    @Test
    public void checkLogger(){
        logger.info("logger testing using log4j2");
        logger.warn("warning ...");
    }
}
