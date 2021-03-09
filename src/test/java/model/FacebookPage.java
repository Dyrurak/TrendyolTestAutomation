package model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static Utils.DriverUtilities.getDriver;

public class FacebookPage extends BasePage{
    public FacebookPage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(how = How.ID, using = "email")
    public WebElement txtEmail;

    @FindBy(how = How.ID, using = "pass")
    public WebElement txtPassword;

    @FindBy(how = How.ID, using = "loginbutton")

    public WebElement btnLogin;

    public void setEmail(String email){
        setText(txtEmail, email);
    }

    public void setPassword(String password){
        setText(txtPassword, password);
    }

    public void clickLogin(){ click(btnLogin); }

}
