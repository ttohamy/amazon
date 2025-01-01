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
    public LoginPage addMobileNumber(WebDriver driver , String mobile){
        addTextToField(driver,mobileField,mobile);
        return this;
    }
    public LoginPage clickContinue(WebDriver driver){
        clickElement(driver,continueButton);
        return this;
    }
    public LoginPage addPassword(WebDriver driver , String password){
        addTextToField(driver,passwordField,password);
        return this;
    }
    public HomePage clickSignInButton(WebDriver driver){
        clickElement(driver,signInButton);
        return new HomePage(driver);
    }

}
