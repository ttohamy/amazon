package tests;


import org.testng.annotations.Test;

public class PriceFilterTest extends TestBase {
    @Test
    public void checkThatUserCanOpenVideoGamesPage() throws InterruptedException {
        homePage.openLoginPage(driver)
                .addMobileNumber(driver, mobile)
                .clickContinue(driver)
                .addPassword(driver, password)
                .clickSignInButton(driver)
                .openAllMenu(driver)
                .expandCategories(driver);
        logger.info("assert that video game button appears");
        softAssert.assertTrue(homePage.isElementAppears(driver, homePage.videoGameButton));
        homePage.clickOnVideoGames(driver)
                .clickOnAllVideoGame(driver);
        logger.info("assert that video game page open and the header is Video Games");
        softAssert.assertTrue(homePage.isElementContainsText(driver, videoGamesPage.videoGamesHeader, "Video Games"));
        softAssert.assertAll();
    }

    @Test
    public void checkThatTheUserCanFilter() throws InterruptedException {
        videoGamesPage.clickFreeShipping(driver)
                      .clickNew(driver);
        logger.info("assert that filter done and user redirected to the result page");
        softAssert.assertTrue(homePage.isElementContainsText(driver, videoGamesPage.resultsHeader, "Results"));
        logger.info("assert that the url contains free shipping");
        softAssert.assertTrue(driver.getCurrentUrl().contains("free_shipping_eligible"));
        softAssert.assertAll();
    }

    @Test
    public void checkThatUserCanSort() {
        videoGamesPage.clickSort(driver)
                      .sortFromHighToLow(driver);
        logger.info("assert that the url contains price-desc-rank");
        softAssert.assertTrue(driver.getCurrentUrl().contains("s=price-desc-rank"));
        logger.info("assert that the selected filter appears on the sort dropdown");
        softAssert.assertTrue(videoGamesPage.isElementContainsText(driver, videoGamesPage.sortButton, "Price: High to Low"));
        softAssert.assertAll();
    }

    @Test
    public void checkThatUserCanAddItemsToCart() {
        videoGamesPage.openProductsGreaterThan15(driver)
                      .openCart(driver);
        logger.info("assert that the selected product's count is equal to the items appears on the cart screen");
        softAssert.assertEquals(videoGamesPage.returnProductsCount(), cartPage.cartItems(driver));
        logger.info("assert that the selected product's total amount is equal to the cart total amount");
        softAssert.assertEquals(videoGamesPage.returnProductsTotal(), cartPage.cartAmount(driver));
        softAssert.assertAll();
    }

    @Test
    public void checkThatUserCanProceedToCheckOut() {
        cartPage.proceedToBuy(driver);
        logger.info("assert that the current page is checkout page");
        softAssert.assertTrue(driver.getCurrentUrl().contains("checkout"));
        logger.info("assert that the current page is checkout page");
        softAssert.assertEquals(videoGamesPage.returnProductsTotal(), checkOutPage.returnOrderTotalAmount(driver));
        softAssert.assertAll();
    }

    @Test
    public void checkThatUserCanAddNewAddress() {
        checkOutPage.addNewAddress(driver);
        logger.info("assert that success msg 'address added' appears ");
        softAssert.assertTrue(checkOutPage.isAddressAdded(driver));
        softAssert.assertAll();
    }

}
