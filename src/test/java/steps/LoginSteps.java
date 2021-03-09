package steps;

import com.opencsv.bean.CsvToBeanBuilder;
import cucumber.api.java.en.Given;
import entities.UserInformation;
import model.FacebookPage;
import model.HomePage;
import model.LoginPage;
import org.testng.Assert;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import static constants.Constants.csvPathForDataReading;

public class LoginSteps {
    LoginPage loginPage;
    HomePage homePage;
    FacebookPage facebookPage;
    UserInformation userInformation;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.homePage = new HomePage();
        this.facebookPage = new FacebookPage();
        this.userInformation = new UserInformation();
    }


    @Given("^'(.*)' tipteki kullanıcının bilgileri cekilir$")
    public void getUserInformation(String userType) throws FileNotFoundException {
        List<UserInformation> listUserInformation = new CsvToBeanBuilder<UserInformation>(new FileReader(csvPathForDataReading))
                .withType(UserInformation.class).build().parse();
        for(UserInformation row : listUserInformation){
            if(row.customerType.equals(userType) ){
                this.userInformation = row;
                break;
            }
        }
    }

    @Given("^Email alanina email girilir$")
    public void setEmail(){
        loginPage.setEmail(userInformation.email);
    }

    @Given("^Sifre alanina sifre girilir$")
    public void setPassword(){
        loginPage.setPassword(userInformation.password);
    }

    @Given("^Giris yap butonuna tiklanir$")
    public void clickLoginButton(){
        loginPage.clickButtonLogin();
    }

    @Given("^Basarili giris yapildigi gorulur$")
    public void checkLoginIsSuccessful(){
        Assert.assertTrue(homePage.isMyAccountVisible(), "Basarili giris yapilamadi!");
    }

    @Given("^facebook ile giris yap butonuna tiklanir$")
    public void clickFacebookButton(){
        loginPage.clickButtonLoginWithFacebook();
    }

    @Given("^email alanina facebook email girilir$")
    public void setEmailFacebook(){
        facebookPage.setEmail(userInformation.email);
    }

    @Given("^password alanina facebook sifre girilir$")
    public void setPasswordFacebook(){
        facebookPage.setPassword(userInformation.password);
    }

    @Given("^facebook giris yap butonuna tiklanir$")
    public void clickLoginFacebook(){
        facebookPage.clickLogin();
    }

    @Given("^'(.*)' hata mesaji gorulur$")
    public void checkErrorMessage(String expectedMessage){
        Assert.assertEquals(loginPage.getErrorMessage(),expectedMessage,  String.format("%s hata mesaji gelmemistir!", expectedMessage));
    }

    @Given("^sifremi unuttuma tiklanir$")
    public void ClickForgotPassword(){
        loginPage.clickButtonForgotPassword();
    }

    @Given("^eposta alanina eposta yazilir$")
    public void clickForgotPassword(){
        loginPage.setEmailForForgotPassword(userInformation.email);
    }

    @Given("^sifremi yenile butonuna tiklanir$")
    public void clickResetPassword(){
        loginPage.clickResetPassword();
    }

    @Given("^'(.*)' yazisi gorulur$")
    public void checkTitleForForgotPassword(String title){
        Assert.assertEquals(loginPage.getTitle(), title, String.format("%s basligi ekrana gelmedi!", title));
    }

    @Given("^google ile giris yap butonuna tiklanir$")
    public void clickLoginWithGoogle(){
        loginPage.clickButtonLoginWithGoogle();
    }
}
