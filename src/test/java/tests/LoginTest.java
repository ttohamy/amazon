package tests;

import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void checkThatTheUserCanLogin(){
        homePage.openLoginPage(driver);
        loginPage.addMobileNumber(driver,mobile);
        loginPage.clickContinue(driver);
        loginPage.addPassword(driver,password);
        loginPage.clickSignInButton(driver);
        softAssert.assertTrue(homePage.isElementContainsText(driver,homePage.loginButton,"Hello, Mohamed"));
        softAssert.assertAll();
    }
}
