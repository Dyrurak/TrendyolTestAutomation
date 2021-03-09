package model;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static Utils.DriverUtilities.getDriver;

public class BasePage {
    public WebDriver webDriver;


    public BasePage(){
        webDriver = getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void getUrl(String url)
    {
        webDriver.get(url);
    }

    public void click(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void setText(WebElement webElement, String text){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(text);
    }

    public void scrollTo(WebElement webElement){
        String jsStmt = String.format("window.scrollTo(%d,%d)", webElement.getLocation().x, webElement.getLocation().y);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)webDriver;
        javascriptExecutor.executeScript(jsStmt, true);
    }

    public boolean isElementVisible(WebElement webElement){
        boolean isElementSeen;
        try{
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            isElementSeen = true;
        }
        catch(Exception e) {
            isElementSeen = false;
        }
        return isElementSeen;
    }

    public String getTextOfElement(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }


    public void closeBrowser(){
        webDriver.quit();
    }
}
