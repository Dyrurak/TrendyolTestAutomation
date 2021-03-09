package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/java/feature"} , plugin = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
        glue = {"steps"}, monochrome = true)

public class TrendyolRunner extends AbstractTestNGCucumberTests {
}
