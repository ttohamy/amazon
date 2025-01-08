package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VideoGamesPage extends PageBase {
    public By videoGamesHeader = By.xpath("//*[@id=\"a-page\"]/div[1]/div[2]/div[1]/div/div/h1/b");
    By freeShippingCheckBox = By.xpath("//*[@id=\"s-refinements\"]/div[2]/ul/li/span/a/div[1]");
    By newButton = By.xpath("//*[@id=\"p_n_condition-type/28071525031\"]/span/a");
    public By resultsHeader = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[1]/div/span[1]/div/div/h2");
    public By sortButton = By.xpath("//*[@id=\"search\"]/span/div/h1/div/div[4]/div/div/form/span");
    By highToLow = By.id("s-result-sort-select_2");
    By price = By.className("a-price-whole");
    By addedToCartText = By.id("sw-atc-details-single-container");
    By cartButton = By.id("nav-cart-count-container");
    By paginationNextButton = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[18]/div/div/span/ul/li[4]/span/a");
    private  List<Double> filteredElements ;

    public VideoGamesPage(WebDriver driver) {
        super(driver);
    }

    public VideoGamesPage clickFreeShipping(WebDriver driver) {
        clickElement(driver, freeShippingCheckBox);
        return this;
    }

    public VideoGamesPage clickNew(WebDriver driver) throws InterruptedException {
        scroll(driver);
        clickElement(driver, newButton);
        return this;
    }

    public VideoGamesPage clickSort(WebDriver driver) {
        clickElement(driver, sortButton);
        return this;
    }

    public void sortFromHighToLow(WebDriver driver) {
        clickElement(driver, highToLow);

    }
    public void openCart(WebDriver driver){
        clickElement(driver,cartButton);
    }

    public VideoGamesPage openProductsGreaterThan15(WebDriver driver) {
        // Initialize Product Details Page
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        filteredElements = new ArrayList<>();

        // Explicit wait declaration
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for all price elements to load
            waitBeforeInteract(driver, price);
            List<WebElement> elements = driver.findElements(price);
            System.out.println("Total Elements Found: " + elements.size());

            for (int i = 0; i < elements.size(); i++) {
                // Re-locate elements after any navigation to avoid stale element reference
                elements = driver.findElements(price);
                WebElement element = elements.get(i);

                // Scroll element into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                // Wait until the element is clickable
                wait.until(ExpectedConditions.elementToBeClickable(element));
                // Extract price text and filter prices
                String text = element.getText().replaceAll(",", "").replaceAll("[^0-9.]", "");
                if (!text.isEmpty()) {
                    double itemPrice = Double.parseDouble(text);
                    if (itemPrice > 15000.0) {
                        filteredElements.add(itemPrice);
                        // Handle ElementClickInterceptedException by retrying the click with JavaScript if needed
                        try {
                            element.click(); // Attempt normal click
                        } catch (ElementClickInterceptedException e) {
                            System.out.println("ElementClickInterceptedException: Clicking with JavaScript.");
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                        }
                        // Perform actions on the product details page
                        productDetailsPage.addToCart(driver);
                        productDetailsPage.clickNoThanks(driver);
                        waitBeforeInteract(driver, addedToCartText);
                        // Navigate back twice and wait for prices to reload
                        driver.navigate().back();
                        driver.navigate().back();
                        waitBeforeInteract(driver, price);
                    }else {
                        System.out.println("There are no items with price > 15000.0");
                        clickElement(driver,paginationNextButton);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log any errors
        }
        driver.navigate().refresh();
        return this;
    }
    public double returnProductsTotal(){
        double totalSum = 0;
        for (double value : filteredElements) {
            totalSum += value;
        }
        return totalSum;
    }
    public int returnProductsCount(){
        return filteredElements.size();
    }
}


