package tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class TestBase {
    SoftAssert softAssert;
    public static WebDriver driver;

    @BeforeClass
    protected void getSoftAssert() {
        softAssert = new SoftAssert();
    }

    @BeforeClass
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (browserName.equalsIgnoreCase("mobileView")) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Nexus 5");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("chrome-headless")) {
            System.out.println("Headless is perfect");
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");
            option.setPageLoadStrategy(PageLoadStrategy.NONE);
            option.addArguments("window-size=2000,3000");
            driver = new ChromeDriver(option);
        }
        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.eg/");
    }

}
