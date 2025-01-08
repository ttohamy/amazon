package tests;


import org.testng.annotations.Test;

public class PriceFilterTest extends TestBase {
    @Test(priority = 0 )
    public void checkThatUserCanOpenVideoGamesPage() throws InterruptedException {
        homePage.openLoginPage(driver)
                .addMobileNumber(driver, mobile)
                .clickContinue(driver)
                .addPassword(driver, password)
                .clickSignInButton(driver)
                .openAllMenu(driver)
                .expandCategories(driver);
        softAssert.assertTrue(homePage.isElementAppears(driver, homePage.videoGameButton));
        homePage.clickOnVideoGames(driver)
                .clickOnAllVideoGame(driver);
        softAssert.assertTrue(homePage.isElementContainsText(driver, videoGamesPage.videoGamesHeader, "Video Games"));
        softAssert.assertAll();
    }

    @Test(priority = 1 , dependsOnMethods = "checkThatUserCanOpenVideoGamesPage")
    public void checkThatTheUserCanFilter() throws InterruptedException {
        videoGamesPage.clickFreeShipping(driver)
                      .clickNew(driver);
        softAssert.assertTrue(homePage.isElementContainsText(driver, videoGamesPage.resultsHeader, "Results"));
        softAssert.assertAll();
    }

    @Test(priority = 2 , dependsOnMethods = "checkThatTheUserCanFilter")
    public void checkThatUserCanSort() {
        videoGamesPage.clickSort(driver)
                      .sortFromHighToLow(driver);
        softAssert.assertTrue(videoGamesPage.isElementContainsText(driver, videoGamesPage.sortButton, "Price: High to Low"));
        softAssert.assertAll();
    }

    @Test(priority = 3 , dependsOnMethods = "checkThatUserCanSort")
    public void checkThatUserCanAddItemsToCart() {
        videoGamesPage.openProductsGreaterThan15(driver)
                      .openCart(driver);
        softAssert.assertEquals(videoGamesPage.returnProductsCount(), cartPage.cartItems(driver));
        softAssert.assertEquals(videoGamesPage.returnProductsTotal(), cartPage.cartAmount(driver));
        softAssert.assertAll();
    }

    @Test(priority = 4 , dependsOnMethods = "checkThatUserCanAddItemsToCart")
    public void checkThatUserCanProceedToCheckOut() {
        cartPage.proceedToBuy(driver);
        softAssert.assertTrue(driver.getCurrentUrl().contains("checkout"));
        softAssert.assertEquals(videoGamesPage.returnProductsTotal(), checkOutPage.returnOrderTotalAmount(driver));
        softAssert.assertAll();
    }

    @Test(priority = 5 , dependsOnMethods = "checkThatUserCanProceedToCheckOut")
    public void checkThatUserCanAddNewAddress() {
        checkOutPage.addNewAddress(driver);
        softAssert.assertTrue(checkOutPage.isAddressAdded(driver));
        softAssert.assertAll();
    }
    @Test()
    public void cccc() {
        homePage.openLoginPage(driver)
                .addMobileNumber(driver, mobile)
                .clickContinue(driver)
                .addPassword(driver, password)
                .clickSignInButton(driver)
                .openCart(driver);
        cartPage.proceedToBuy(driver);
        checkOutPage.addNewAddress(driver);
        softAssert.assertTrue(checkOutPage.isAddressAdded(driver));
        softAssert.assertAll();
    }

}
