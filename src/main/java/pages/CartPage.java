package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends PageBase{
    By subtotalItems = By.id("sc-subtotal-label-buybox");
    By subtotalAmount = By.id("sc-subtotal-amount-buybox");
    By proceedToBuyButton = By.name("proceedToRetailCheckout");

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public int cartItems(WebDriver driver){
        String itemsCountText = getText(driver,subtotalItems).replaceAll("[^0-9]", "");
        int itemsCount = Integer.parseInt(itemsCountText);
        System.out.println("item count cart screen : "+ itemsCount);
        return itemsCount;
    }
    public double cartAmount(WebDriver driver){
        String itemsTotalText = getText(driver,subtotalAmount).replaceAll(",", "").replaceAll("[^0-9.]", "");
        double itemsTotal = Double.parseDouble(itemsTotalText);
        System.out.println("items amount cart screen : " + itemsTotal);
        return itemsTotal;
    }
    public void proceedToBuy(WebDriver driver){
        clickElement(driver,proceedToBuyButton);
    }
}
