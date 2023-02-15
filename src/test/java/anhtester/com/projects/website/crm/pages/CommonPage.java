package anhtester.com.projects.website.crm.pages;

import anhtester.com.projects.website.crm.pages.Dashboard.DashboardPage;

import anhtester.com.projects.website.crm.pages.SignIn.SignInPage;
import org.openqa.selenium.By;

public class CommonPage {

    public SignInPage signInPage;
    public DashboardPage dashboardPage;

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

}
