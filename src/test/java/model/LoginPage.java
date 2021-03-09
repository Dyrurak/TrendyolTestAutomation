package model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static Utils.DriverUtilities.getDriver;

public class LoginPage extends BasePage{

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(how = How.ID, using = "login-email")
    public WebElement txtEmail;

    @FindBy(how = How.ID, using = "login-password-input")
    public WebElement txtPassword;

    @FindBy(how = How.XPATH, using = "//*[@class='q-primary q-fluid q-button-medium q-button submit']")
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//*[@class='message']")
    public WebElement lblErrorMessage;

    @FindBy(how = How.XPATH, using = "(//*[@class='q-layout social-login-button flex flex-1'])[1]")
    public WebElement btnLoginWithFacebook;

    @FindBy(how = How.XPATH, using = "(//*[@class='q-layout social-login-button flex flex-1'])[2]")
    public WebElement btnLoginWithGoogle;

    @FindBy(how = How.XPATH, using = "//*[@class='forgot-password']//span")
    public WebElement lblForgotPassword;

    @FindBy(how = How.XPATH, using = "//*[@class='ty-bg-beige ty-input ty-textbox ty-bordered ty-input-small']")
    public WebElement txtEmailForForgotPassword;

    @FindBy(how = How.XPATH, using = "//*[@class='ty-font-w-semi-bold ty-button ty-bordered ty-transition ty-input-small ty-primary']")
    public WebElement btnResetPassword;

    @FindBy(how = How.XPATH, using = "//*[@class='title']")
    public WebElement lblTitle;



    public void setEmail(String email){
        setText(txtEmail, email);
    }

    public void setPassword(String password){
        setText(txtPassword, password);
    }

    public void clickButtonLogin(){
        click(btnLogin);
    }

    public void clickButtonLoginWithFacebook(){
        click(btnLoginWithFacebook);
    }

    public void clickButtonLoginWithGoogle(){
        click(btnLoginWithGoogle);
    }

    public void clickButtonForgotPassword(){
        click(lblForgotPassword);
    }

    public void setEmailForForgotPassword(String email){
        setText(txtEmailForForgotPassword, email);
    }

    public void clickResetPassword(){
        click(btnResetPassword);
    }

    public String getErrorMessage(){
        return getTextOfElement(lblErrorMessage);
    }

    public String getTitle(){
        return getTextOfElement(lblTitle);
    }

}
