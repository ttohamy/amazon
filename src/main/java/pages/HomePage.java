package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase{
    public By loginButton = By.id("nav-link-accountList");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public LoginPage openLoginPage(WebDriver driver){
        clickElement(driver,loginButton);
        return new LoginPage(driver);
    }


}
