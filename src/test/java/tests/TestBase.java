package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import reports.ExtentManager;
import utils.ConfigReader;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestBase {
    SoftAssert softAssert;
    public static WebDriver driver;
    HomePage homePage ;
    LoginPage loginPage;
    VideoGamesPage videoGamesPage ;
    ProductDetailsPage productDetailsPage ;
    CartPage cartPage ;
    CheckoutPage checkOutPage;
    public static String mobile ;
    public static String password ;
    Logger logger = LogManager.getLogger(this);


    @BeforeClass
    protected void getSoftAssert() {
        softAssert = new SoftAssert();
    }

    @BeforeClass
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();

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
//        driver.manage().window().maximize();
        driver.navigate().to("https://www.amazon.eg/");
    }
    @BeforeClass
    public void initObjects(){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        videoGamesPage = new VideoGamesPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
        checkOutPage = new CheckoutPage(driver);
    }
    @BeforeClass
    public void initCreds(){
       mobile = ConfigReader.getProperty("mobile");
       password = ConfigReader.getProperty("password");
    }
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String currentDir = System.getProperty("user.dir")+"/screenshots/" ;
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(currentDir+ testResult.getName() + "-"+formater.format(calendar.getTime()) +".jpg"));
        }
    }
    @BeforeSuite
    public void beforeSuite() throws IOException {
        ExtentManager.setExtent();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.endReport();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

}
