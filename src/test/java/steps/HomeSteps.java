package steps;

import cucumber.api.java.en.Given;
import model.HomePage;
import org.junit.Assert;
import java.io.IOException;
import java.util.List;

import static service.CsvFileWriter.writeValuesIntoFile;

public class HomeSteps {
    HomePage homePage;
    List<List<String>> linksAndStatusCodes;


    public HomeSteps() {
        this.homePage = new HomePage();
    }

    @Given("^gelen popup kapatilir$")
    public void closePopup(){
        homePage.clickButtonClose();
    }

    @Given("^butik linkleri alinir$")
    public void getBoutiqueLinks(){
        linksAndStatusCodes = homePage.getLinks();
    }

    @Given("^butik linkleri ve status codeları csv dosyaya yazilir$")
    public void writeBoutiqueAndStatusCode(){
        try {
            writeValuesIntoFile(linksAndStatusCodes);
        }
        catch (IOException exception){
            Assert.fail(exception.getMessage());
        }
    }
}
