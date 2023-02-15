package anhtester.com.projects.website.crm.pages.Dashboard;

import anhtester.com.projects.website.crm.pages.CommonPage;
import org.openqa.selenium.By;

public class DashboardPage extends CommonPage {

    public DashboardPage() {
        super();
    }

    public String pageText = "Dashboard";
    public String pageUrl = "/domain/list";

    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuClients = By.xpath("//span[normalize-space()='Clients']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");

}
