package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase{
    By mobileField = By.id("ap_email");
    By passwordField = By.id("ap_password");
    By continueButton = By.id("continue");
    By signInButton = By.id("signInSubmit");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void addMobileNumber(WebDriver driver , String mobile){
        addTextToField(driver,mobileField,mobile);
    }
    public void clickContinue(WebDriver driver){
        clickElement(driver,continueButton);
    }
    public void addPassword(WebDriver driver , String password){
        addTextToField(driver,passwordField,password);
    }
    public void clickSignInButton(WebDriver driver){
        clickElement(driver,signInButton);
    }

}
