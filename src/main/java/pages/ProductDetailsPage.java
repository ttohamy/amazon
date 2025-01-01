package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends PageBase{
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    By addToCartButton  = By.id("add-to-cart-button");
    By noThanksButton = By.id("attachSiNoCoverage");

    public void addToCart(WebDriver driver){
        clickElement(driver,addToCartButton);
    }
    public void clickNoThanks(WebDriver driver){
        clickElement(driver,noThanksButton);
    }

}
