package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class PageBase {
protected WebDriver driver;
    public PageBase(WebDriver driver){
        this.driver = driver;
    }

    //wait before interact with element and ignoring some exceptions
    public void waitBeforeInteract(WebDriver driver, By element) {
        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class).ignoring(TimeoutException.class).withMessage("Element NOT Found");
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void clickElement(WebDriver driver , By locator){
        try {
            waitBeforeInteract(driver,locator);
            driver.findElement(locator).click();
        }catch (Exception e){
            System.out.println("Can not click the element :  "+locator.toString()+" " + e.getMessage());
        }
    }
    public String getText(WebDriver driver , By locator){
        String text ;
        try {
            waitBeforeInteract(driver,locator);
            text = driver.findElement(locator).getText();
            return text;
        }catch (Exception e){
            System.out.println("Can not get the text from the element :  "+locator.toString()+" " + e.getMessage());
            return "";
        }
    }
    public void addTextToField(WebDriver driver , By locator,String text){
        try {
            waitBeforeInteract(driver,locator);
            driver.findElement(locator).sendKeys(text);
        }catch (Exception e){
            System.out.println("can not add text to element : "+locator.toString()+" " + e.getMessage());
        }
    }
    public boolean isElementContainsText(WebDriver driver , By locator , String text){
        return getText(driver, locator).contains(text);
    }
}
