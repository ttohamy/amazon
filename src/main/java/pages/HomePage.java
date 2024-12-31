package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase{
    public By loginButton = By.id("nav-link-accountList");
    By burgerMenuButton = By.id("nav-hamburger-menu");
    By seeAllButton = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[14]/a[1]");
    public By videoGameButton = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/ul/li[11]/a/div");
    By burgerMenuDiv = By.id("hmenu-content");
    By burgerMenuScrollableView = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]");
    By allVideoGamesButton = By.xpath("//*[@id=\"hmenu-content\"]/ul[32]/li[3]");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public LoginPage openLoginPage(WebDriver driver){
        clickElement(driver,loginButton);
        return new LoginPage(driver);
    }
    public void openAllMenu(WebDriver driver){
        clickElement(driver,burgerMenuButton);
    }
    public void expandCategories(WebDriver driver){
        clickElement(driver,seeAllButton);
    }
    public void clickOnVideoGames(WebDriver driver) throws InterruptedException {
        scrollOnElement(driver,burgerMenuScrollableView);
        clickElement(driver,videoGameButton);
    }
    public void clickOnAllVideoGame(WebDriver driver){
        clickElement(driver,allVideoGamesButton);
    }


}
