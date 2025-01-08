package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

public class HomePage extends PageBase{
    public By loginButton = By.id("nav-link-accountList");
    By burgerMenuButton = By.id("nav-hamburger-menu");
    By seeAllButton = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[14]/a[1]");
    public By videoGameButton = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/ul/li[11]/a/div");
    By burgerMenuDiv = By.id("hmenu-content");
    By burgerMenuScrollableView = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]");
    By allVideoGamesButton = By.xpath("//*[@id=\"hmenu-content\"]/ul[32]/li[3]");
    By cartButton = By.id("nav-cart-count-container");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public LoginPage openLoginPage(WebDriver driver){
        clickElement(driver,loginButton);
        return new LoginPage(driver);
    }
    public HomePage openAllMenu(WebDriver driver){
        clickElement(driver,burgerMenuButton);
        return this;
    }
    public HomePage expandCategories(WebDriver driver){
        clickElement(driver,seeAllButton);
        return this;
    }
    public HomePage clickOnVideoGames(WebDriver driver) throws InterruptedException {

        wheelScroll(driver,burgerMenuScrollableView);
//        scrollOnElement(driver,burgerMenuScrollableView);
        clickElement(driver,videoGameButton);
        return this;
    }
    public void clickOnAllVideoGame(WebDriver driver){
        clickElement(driver,allVideoGamesButton);
    }
    public void openCart(WebDriver driver){
        clickElement(driver,cartButton);
    }


}
