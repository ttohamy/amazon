package tests;


import org.testng.annotations.Test;

public class PriceFilterTest extends TestBase{
    @Test
    public void checkThatUserCanOpenVideoGamesPage() throws InterruptedException {
        homePage.openLoginPage(driver)
                .addMobileNumber(driver, mobile)
                .clickContinue(driver)
                .addPassword(driver, password)
                .clickSignInButton(driver);
        homePage.openAllMenu(driver);
        homePage.expandCategories(driver);
        softAssert.assertTrue(homePage.isElementAppears(driver,homePage.videoGameButton));
        homePage.clickOnVideoGames(driver);
        homePage.clickOnAllVideoGame(driver);
        softAssert.assertTrue(homePage.isElementContainsText(driver, videoGamesPage.videoGamesHeader, "Video Games"));
        softAssert.assertAll();
    }
    @Test
    public void checkThatTheUserCanFilter() throws InterruptedException {
        videoGamesPage.clickFreeShipping(driver);
        videoGamesPage.clickNew(driver);
        softAssert.assertTrue(homePage.isElementContainsText(driver, videoGamesPage.resultsHeader, "Results"));
        softAssert.assertAll();
    }
    @Test
    public void checkThatUserCanSort(){
        videoGamesPage.clickSort(driver);
        videoGamesPage.sortFromHighToLow(driver);
        videoGamesPage.checkPrices(driver);
    }
}
