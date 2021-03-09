package model;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static Utils.DriverUtilities.getDriver;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(how = How.XPATH, using = "//a[@title='Close']")
    public WebElement btnClosePopup;

    @FindBy(how = How.XPATH, using = "//*[@class='component-item']/a")
    public List<WebElement> lblBoutiques;

    @FindBy(how = How.XPATH, using = "//*[@class='link-text' and text()='Hesabım']")
    public WebElement btnMyAccount;

    public List<List<String>> getLinks(){
        List<List<String>> linksAndStatusCodes = new ArrayList<List<String>>();
        for(WebElement boutique : lblBoutiques) {
            String link = boutique.getAttribute("href");
            Response response = RestAssured.get(link);
            List<String> linkAndStatusCode = new ArrayList<String>();
            linkAndStatusCode.add(link);
            linkAndStatusCode.add(String.valueOf(response.statusCode()));
            linksAndStatusCodes.add(linkAndStatusCode);
        }
        return linksAndStatusCodes;
    }

    public void clickButtonClose(){
        btnClosePopup.click();
    }

    public boolean isMyAccountVisible(){
         return isElementVisible(btnMyAccount);
    }
}
