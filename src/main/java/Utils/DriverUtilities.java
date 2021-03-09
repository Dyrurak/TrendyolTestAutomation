package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverUtilities {
    static WebDriver webDriver;

    public static void initDriver(String browser) {
        switch (browser){
            case "chrome":
                getChromeDriver();
                break;
            case "explorer":
                getInternetExplorerDriver();
                break;
            case "firefox":
                getFirefoxBrowser();
                break;
            default:
                break;
        }


    }

    private static void getChromeDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("disable-automatic-password-saving");
        chromeOptions.addArguments("allow-silent-push");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.setCapability("useAutomationExtension", false);

        WebDriverManager.chromedriver().browserVersion("89.0.4389.72").setup();
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
    }

    private static void getInternetExplorerDriver(){
        WebDriverManager.iedriver().setup();
        webDriver = new InternetExplorerDriver();
        webDriver.manage().window().maximize();
    }

    private static void getFirefoxBrowser(){
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
    }

    public static WebDriver getDriver() {

        if (webDriver == null ) {
            initDriver("chrome");
        }
        return webDriver;
    }

    public static void tearDown() {
        webDriver.quit();
        webDriver = null;
    }
}
