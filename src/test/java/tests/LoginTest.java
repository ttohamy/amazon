package tests;

import org.testng.annotations.Test;
import utils.RetryAnalyzer;

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
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void checkLogger(){
        logger.info("Running testExample retry...");
        // Simulate a flaky test
        if (0 > 0.5) {
            throw new RuntimeException("Test failed!");
    }
    }
}
