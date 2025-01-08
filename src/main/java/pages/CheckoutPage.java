package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CheckoutPage extends PageBase{
    By orderTotalText = By.xpath("//*[@id=\"subtotals-marketplace-table\"]/tbody/tr[5]/td[2]");
    By changeAddressButton = By.xpath("//*[@id=\"checkout-deliveryAddressPanel\"]/div/div[2]/span/a");
    By addNewAddressButton = By.id("add-new-address-desktop-sasp-tango-link");
    By fullNameText = By.id("address-ui-widgets-enterAddressFullName");
    By mobileNumberText = By.id("address-ui-widgets-enterAddressPhoneNumber");
    By streetNameText = By.id("address-ui-widgets-enterAddressLine1");
    By buildingNameText = By.id("address-ui-widgets-enter-building-name-or-number");
    By cityDropDown = By.id("address-ui-widgets-enterAddressCity");
    By selectCityText = By.id("address-ui-widgets-autoCompleteResult-1");
    By districtDropDown = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    By selectDistrict = By.id("address-ui-widgets-autoCompleteResult-0");
    By nearestLandmark = By.id("address-ui-widgets-landmark");
    By homeOptionRadioButton = By.id("address-ui-widgets-addr-details-res-radio-input");
    By useThisAddressButton = By.xpath("//*[@id=\"pagelet-layout-section\"]/span");
    By scrollablePopUp = By.xpath("//*[@id=\"a-popover-2\"]/div");
    By deliverToAddressText = By.id("deliver-to-address-text");
    Faker faker = new Faker();
    String fullAddress = faker.address().fullAddress();
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    public void addNewAddress(WebDriver driver)  {
        clickElement(driver,changeAddressButton);
        clickElement(driver,addNewAddressButton);
        addTextToField(driver,fullNameText,faker.name().fullName());
        addTextToField(driver,mobileNumberText,"015"+faker.number().digits(8));
        addTextToField(driver,streetNameText,fullAddress);
        addTextToField(driver,buildingNameText,faker.address().buildingNumber());
//        clickElement(driver,cityDropDown);
        selectCity(driver , cityDropDown,"diarb ");
        clickElement(driver,selectCityText);
        clickElement(driver,districtDropDown);
        selectCity(driver,districtDropDown,"sas");
        clickElement(driver,selectDistrict);
        addTextToField(driver,nearestLandmark,faker.address().secondaryAddress());
        clickElement(driver,homeOptionRadioButton);
        wheelScroll(driver,homeOptionRadioButton);
//        try {
//            scrollOnElement(driver,scrollablePopUp);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        clickElement(driver,useThisAddressButton);
    }
    public double returnOrderTotalAmount(WebDriver driver){
        String text = driver.findElement(orderTotalText).getText().replaceAll(",", "").replaceAll("[^0-9.]", "");
        double itemsPrice = Double.parseDouble(text);
        System.out.println("Checkout page item price : "+itemsPrice);
        return  itemsPrice;
    }
    public boolean isAddressAdded(WebDriver driver){
        return getText(driver,deliverToAddressText).contains(fullAddress) ;
    }
    public void selectCity(WebDriver driver , By locator ,String text){

        for (char ch : text.toCharArray()) {
            try {
                driver.findElement(locator).sendKeys(String.valueOf(ch)); // Send one character
                Thread.sleep(500); // Small delay for dropdown to react
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
