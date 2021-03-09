package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import model.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.io.File;
import java.io.IOException;
import static Utils.DriverUtilities.getDriver;
import static Utils.DriverUtilities.initDriver;

public class BaseSteps  {
    BasePage basePage;


    public BaseSteps() {
        basePage = new BasePage();
    }

    @BeforeTest
    @Parameters("browser")
    public void beforeTest(String browser){
        initDriver(browser);
    }

    @Given("^'(.*)' ekrani acilir$")
    public void openPage(String url) {
        basePage.getUrl(url);
    }

    @After
    public void afterTest(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(String.format("C:\\Trendyol\\src\\test\\java\\testresults\\%s-%s.png", scenario.getName(), System.currentTimeMillis()));
            FileUtils.copyFile(SrcFile, DestFile);
        }
        basePage.closeBrowser();
    }
}