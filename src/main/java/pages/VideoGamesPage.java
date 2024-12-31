package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class VideoGamesPage extends PageBase {
    public By videoGamesHeader = By.xpath("//*[@id=\"a-page\"]/div[1]/div[2]/div[1]/div/div/h1/b");
    By freeShippingCheckBox = By.xpath("//*[@id=\"s-refinements\"]/div[2]/ul/li/span/a/div[1]");
    By newButton = By.xpath("//*[@id=\"p_n_condition-type/28071525031\"]/span/a");
    public By resultsHeader = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[1]/div/span[1]/div/div/h2");
    By sortButton = By.xpath("//*[@id=\"search\"]/span/div[2]/h1/div/div[4]/div/div/form/span");
    By highToLow = By.id("s-result-sort-select_2");
    By price = By.className("a-price-whole");

    public VideoGamesPage(WebDriver driver) {
        super(driver);
    }

    public void clickFreeShipping(WebDriver driver) {
        clickElement(driver, freeShippingCheckBox);
    }

    public void clickNew(WebDriver driver) throws InterruptedException {
        scroll(driver);
        clickElement(driver, newButton);
    }

    public void clickSort(WebDriver driver) {
        clickElement(driver, sortButton);
    }

    public void sortFromHighToLow(WebDriver driver) {
        clickElement(driver, highToLow);
    }

    public void checkPrices(WebDriver driver) {
        try {
            List<Integer> filteredElements = new ArrayList<>();
            Thread.sleep(5000);
            // Iterate through elements and filter values greater than 15000
            for (WebElement element : driver.findElements(price)) {
                System.out.println("elements size : " + driver.findElements(price).size());
                String text = element.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
                System.out.println("elements text : " + text);
                if (!text.isEmpty()) {

                    int price = Integer.parseInt(text); // Convert text to integer
                    if (price > 15000) {
                        System.out.println("text : " + text);
                        System.out.println("price : " + price);
                        filteredElements.add(price); // Add the original text if price > 15000
                    }
                }

            }
            // Print the filtered values
            System.out.println("Filtered Elements (Price > 15000):");
            for (int value : filteredElements) {
                System.out.println("filtered Value : " + value);
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.getStackTrace());
        }

    }
}


