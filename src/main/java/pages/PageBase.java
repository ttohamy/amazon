package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class PageBase {
protected WebDriver driver;
protected Logger logger = LogManager.getLogger(this);
    public PageBase(WebDriver driver){
        this.driver = driver;
    }

    //wait before interact with element and ignoring some exceptions
    public void waitBeforeInteract(WebDriver driver, By element) {
        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class).ignoring(TimeoutException.class).withMessage("Element NOT Found");
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public void clickElement(WebDriver driver , By locator){
        try {
            waitBeforeInteract(driver,locator);
            driver.findElement(locator).click();
            logger.info("clicking element : " + locator.toString());
        }catch (Exception e){
            logger.warn("can not click the element : "+locator.toString()+" " + e);
        }
    }
    public String getText(WebDriver driver , By locator){
        String text ;
        try {
            waitBeforeInteract(driver,locator);
            text = driver.findElement(locator).getText();
            logger.info("getting text from filed : "+ locator.toString() + "and the text is : "+text );
            return text;
        }catch (Exception e){
            logger.warn("can not get the text from the element :  "+locator.toString()+" " + e);
            return "";
        }
    }
    public void addTextToField(WebDriver driver , By locator,String text){
        try {
            waitBeforeInteract(driver,locator);
            driver.findElement(locator).sendKeys(text);
            logger.info("adding text to field : "+locator.toString());
        }catch (Exception e){
            logger.warn("can not add text to element : "+locator.toString()+" " + e);
        }
    }
    public boolean isElementContainsText(WebDriver driver , By locator , String text){
        return getText(driver, locator).contains(text);
    }
    public void scrollOnElement(WebDriver driver , By locator) throws InterruptedException {
        waitBeforeInteract(driver,locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(locator));
        js.executeScript("arguments[0].scrollBy(0, 500);", driver.findElement(locator));
    }
    public void scroll(WebDriver driver) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0, 500);");
    }
    public boolean isElementAppears(WebDriver driver , By locator){
        boolean appears  ;
        try {
            waitBeforeInteract(driver,locator);
            appears =true;
        }catch (Exception e){
            appears = false;
            logger.warn("Exception !"+locator+" " + e);
        }
        return appears;
    }
}
