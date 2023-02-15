package anhtester.com.projects.website.crm.pages.SignIn;

import anhtester.com.constants.FrameworkConstants;
import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.helpers.PropertiesHelpers;
import anhtester.com.keywords.WebUI;

import static anhtester.com.config.ConfigFactory.getConfigs;
import static anhtester.com.keywords.WebUI.*;

import anhtester.com.projects.website.crm.models.SignInModel;
import anhtester.com.projects.website.crm.pages.CommonPage;
import anhtester.com.projects.website.crm.pages.Dashboard.DashboardPage;
import anhtester.com.utils.DecodeUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Hashtable;

public class SignInPage extends CommonPage {

    private String pageUrl = "/auth/realms/asians";
    private String pageTitle = "Sign in to Asians - User System";

    public By inputRegisterEmail = By.xpath("//*[@id='email']");
    public By confirmPassword = By.xpath("//input[@id='password-confirm']");
    public By buttonRegisterSubmit = By.xpath("//*[@id='kc-form-buttons']/input");
    public By inputEmail = By.xpath("//input[@id='username']");
    public By inputPassword = By.xpath("//input[@id='password']");
    public By buttonSignIn = By.xpath("//input[@id='kc-login']");
    public By invalidCredErrorMessage = By.xpath("//span[@id='input-error']");
    public By labelregisterErrorMessage = By.xpath("//*[@id='kc-content-wrapper']/div/span");
    public By buttonRegister = By.xpath("//div[@id='kc-registration']//a");
    public By labelLanguageSelected = By.xpath("//*[@id='kc-current-locale-link']");
    public By labelLanguageList = By.cssSelector("ul li a");

    public SignInPage() {
    }

    public DashboardPage signIn(Hashtable<String, String> data) {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(inputEmail);
        verifyContains(getCurrentUrl(), pageUrl, "The url of sign in page not match.");
        verifyEquals(getPageTitle(), pageTitle, "The title of sign in page not match.");
        clearText(inputEmail);
        clearText(inputPassword);
        setText(inputEmail, data.get(SignInModel.getEmail()));
        setText(inputPassword, DecodeUtils.decrypt(data.get(SignInModel.getPassword())));
        clickElement(buttonSignIn);
        waitForPageLoaded();
        verifyContains(getCurrentUrl(), getDashboardPage().pageUrl, "Sign in failed. Can not redirect to Dashboard page.");

        return new DashboardPage();
    }

    public DashboardPage selectLanguage(String language) {
        hoverOnElement(labelLanguageSelected);
        selectOptionDynamic(labelLanguageList, language);
        return new DashboardPage();
    }

}
