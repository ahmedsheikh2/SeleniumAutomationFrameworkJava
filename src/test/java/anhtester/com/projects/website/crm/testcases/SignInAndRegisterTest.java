/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package anhtester.com.projects.website.crm.testcases;

import anhtester.com.common.BaseTest;
import anhtester.com.constants.FrameworkConstants;
import anhtester.com.dataprovider.DataProviderManager;
import static anhtester.com.keywords.WebUI.*;

import anhtester.com.projects.website.crm.models.SignInModel;
import anhtester.com.projects.website.crm.pages.SignIn.SignInPage;
import anhtester.com.utils.DataGenerateUtils;
import anhtester.com.utils.DecodeUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test Assignment")
@Feature("Sign In & Register Test")
public class SignInAndRegisterTest extends BaseTest {

    public SignInAndRegisterTest() {
        signInPage = new SignInPage();
    }

    //Using library DataProvider with read Hashtable
    @Test(priority = 1, description = "TC01_signInTestDataProvider", dataProvider = "getSignInDataHashTable", dataProviderClass = DataProviderManager.class)
    public void signInTest(Hashtable<String, String> data) {
        signInPage.signIn(data);

    }

    @Test(priority = 2, description = "TC02_signInTestAdminRole")
    public void signInErrorTest() {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(signInPage.inputEmail);
        setText(signInPage.inputEmail, "abc@gmail.com");
        setText(signInPage.inputPassword, "9990");
        clickElement(signInPage.buttonSignIn);
        verifyElementText(signInPage.invalidCredErrorMessage, " Invalid username or password. ");
    }

    @Test(priority = 3, description = "TC02_registerUser", dataProvider = "getRegisterUserData", dataProviderClass = DataProviderManager.class)
    public void registerUserSuccessTest(Hashtable<String, String> data) {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(signInPage.inputEmail);
        clickElement(signInPage.buttonRegister);
        setText(signInPage.inputRegisterEmail, DataGenerateUtils.randomString(3) + data.get(SignInModel.getEmail()));
        setText(signInPage.inputPassword, DecodeUtils.decrypt(data.get(SignInModel.getPassword())));
        setText(signInPage.confirmPassword, DecodeUtils.decrypt(data.get(SignInModel.getPassword())));
        clickElement(signInPage.buttonRegisterSubmit);
        verifyContains(getCurrentUrl(), getDashboardPage().pageUrl, "Register Failed. User is not able to Register Successfully");
    }

    @Test(priority = 4, description = "TC03_verifyEmailAlreadyExistError")
    public void verifyErrorIfEmailAlreadyRegisteredTest() {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(signInPage.inputEmail);
        clickElement(signInPage.buttonRegister);
        setText(signInPage.inputRegisterEmail, "abc@gmail.com");
        setText(signInPage.inputPassword, "9990");
        setText(signInPage.confirmPassword, "9990");
        clickElement(signInPage.buttonRegisterSubmit);
        verifyElementText(signInPage.labelregisterErrorMessage, "Email already exists.");
    }

    @Test(priority = 5, description = "TC04_verifyPasswordConfirmationError")
    public void verifyPasswordConfirmationErrorTest() {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(signInPage.inputEmail);
        clickElement(signInPage.buttonRegister);
        setText(signInPage.inputRegisterEmail, DataGenerateUtils.randomString(3) + "xyz@gmail.com");
        setText(signInPage.inputPassword, "9990");
        setText(signInPage.confirmPassword, "1234");
        clickElement(signInPage.buttonRegisterSubmit);
        verifyElementText(signInPage.labelregisterErrorMessage, "Password confirmation doesn't match.");
    }

    @Test(priority = 6, description = "TC_07verifyDefaultLanguage")
    public void verifyDefaultLanguageTest() {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(signInPage.inputEmail);
        clickElement(signInPage.buttonRegister);
        verifyElementText(signInPage.labelLanguageSelected, "English");
    }

    @Test(priority = 7, description = "TC08_verifyDefaultLanguage", dataProvider = "getLanguageList", dataProviderClass = DataProviderManager.class)
    public void registerUserInAllLanguagesTest(Hashtable<String, String> data) {
        getURL(FrameworkConstants.URL_CRM);
        waitForElementVisible(signInPage.inputEmail);
        clickElement(signInPage.buttonRegister);
        signInPage.selectLanguage(data.get(SignInModel.getLanguage()));
        verifyElementText(signInPage.labelLanguageSelected, data.get(SignInModel.getLanguage()));

        setText(signInPage.inputRegisterEmail, DataGenerateUtils.randomString(4) + data.get(SignInModel.getEmail()));
        setText(signInPage.inputPassword, DecodeUtils.decrypt(data.get(SignInModel.getPassword())));
        setText(signInPage.confirmPassword, DecodeUtils.decrypt(data.get(SignInModel.getPassword())));
        clickElement(signInPage.buttonRegisterSubmit);
        verifyContains(getCurrentUrl(), getDashboardPage().pageUrl, "Register Failed. User is not able to Register Successfully");

    }
}
