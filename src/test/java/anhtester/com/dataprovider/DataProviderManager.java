/*
 * Copyright (c) 2022. Anh Tester
 * Automation Framework Selenium
 */

package anhtester.com.dataprovider;

import anhtester.com.constants.FrameworkConstants;
import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.helpers.Helpers;
import anhtester.com.helpers.PropertiesHelpers;
import anhtester.com.projects.website.crm.models.ClientModel;
import anhtester.com.projects.website.crm.models.SignInModel;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.Iterator;

public final class DataProviderManager {

    private DataProviderManager() {
        super();
        PropertiesHelpers.loadAllFiles();
    }


    @DataProvider(name = "getSignInDataHashTable")
    public static Object[][] getSignInData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn", 1, 1);
        System.out.println("getSignInData: " + data);
        return data;
    }

    @DataProvider(name = "getRegisterUserData")
    public static Object[][] getUserData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "RegisterUser", 1, 1);
        System.out.println("getUserData: " + data);
        return data;
    }

    @DataProvider(name = "getLanguageList")
    public static Object[][] getLanguageList() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "Language", 1, 3);
        System.out.println("getUserData: " + data);
        return data;
    }

}
