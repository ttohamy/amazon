package tests;

import org.testng.annotations.Test;
import utils.RetryAnalyzer;

public class LoginTest extends TestBase {

    @Test
    public void checkThatTheUserCanLogin() throws InterruptedException {
        homePage.openLoginPage(driver)
                .addMobileNumber(driver, mobile)
                .clickContinue(driver)
                .addPassword(driver, password)
                .clickSignInButton(driver);
        logger.info("assert that the user is logged in and welcome msg appears");
        softAssert.assertTrue(driver.getCurrentUrl().contains("https://www.amazon.eg/?ref_=nav_ya_signin"));
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
