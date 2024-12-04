package com.sevenmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class DashBoardPage {
	WebDriver driver;
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();
	@FindBy(xpath = "//div[@class='info']//a[contains(text(),'Admin')]")
	private WebElement profileName;
	@FindBy(xpath = "//p[text()='Admin Users']/../../div/following-sibling::a")
	private WebElement adminUserMoreInfo;
	@FindBy(xpath = "//h3//following-sibling::p")
	private List<WebElement> allCategory;
	@FindBy(xpath = "//li//a[contains(text(),'Home')]")
	private WebElement homeButton;
	@FindBy(xpath = "//div[@class='inner']//p[starts-with(text(),'Category')]/../../div//following-sibling::a")
	private WebElement categoryMoreInfoLink;
	@FindBy(xpath = "//div[@class='inner']//following-sibling::a")
	private List<WebElement> alMoreInfoLink;
	private String lastWord;
	@FindBy(xpath = "//a[@class='nav-link']//i//following-sibling::p[contains(text(),'Dashboard')]//i")
	private WebElement dashboardList;
	@FindBy(xpath = "//nav[@class='mt-2']//li[@class='nav-item has-treeview menu-open']//ul//a")
	private List<WebElement> dashboardListElements;
	@FindBy(xpath = "//p[contains(text(),'Manage product')]/../.")
	private WebElement manageProductClick;
	@FindBy(xpath = "//*[starts-with(@class,'nav-icon fas fa')]//following-sibling::p")
	private List<WebElement> dashboardAdminComponents;
	@FindBy(xpath = "//*[starts-with(@class,'nav-icon fas fa')]//following-sibling::p/..")
	private List<WebElement> clickDashboardComponents;
	@FindBy(xpath = "//i[starts-with(@class,'nav-icon fas fa-f')]//preceding::a[@href='https://groceryapp.uniqassosiates.com/admin/list-category']")
	private WebElement dashboardCategory;
	@FindBy(xpath = "//h1")
	private WebElement headingsofEachNewTab;
	@FindBy(xpath = "//li[@class='nav-item dropdown']//a//i[@class='ace-icon fa fa-power-off']/..")
	private WebElement logout;
	@FindBy(xpath = "//li[@class='nav-item dropdown']//a//i[@class='ace-icon fa fa-cog']/..")
	private WebElement settings;
	@FindBy(xpath = "//b")
	private WebElement loginTitle;
	private String headings ;
	

	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
	}

	public String getProfileName() {
		return profileName.getText();
	}

	public void clickDashboardList() {
		pageutility.jsClick(dashboardList);
	}

	public List<String> getDashboardListElements() {
		List<String> dashboardElements = generalutility.getTextOfElements(dashboardListElements);
		System.out.println(dashboardElements);
		return dashboardElements;
	}

	public List<String> getAllDashboardAdminComp() {
		List<String> alldata = generalutility.getTextOfElements(dashboardAdminComponents);
		return alldata;
	}

	public void clickanyDashboardElement() throws InterruptedException {
		List<String> dashboardElements = generalutility.getTextOfElements(dashboardListElements);
		System.out.println(dashboardElements);
		for (String each : dashboardElements) {
			System.out.println(each);
			if (each.equals("Manage product")) {
				pageutility.jsClick(manageProductClick);
				driver.navigate().back();
			}

		}

	}

	public AdminUsersPage clickAdminMoreInfo() {
		adminUserMoreInfo.click();
		return new AdminUsersPage(driver);
	}

	public List<String> getAllCategoryNames() {
		List<String> actualCategoryNames = new ArrayList<String>();
		actualCategoryNames = generalutility.getTextOfElements(allCategory);
		System.out.println(actualCategoryNames);
		return actualCategoryNames;

	}

	public void clickHomeButton() {
		homeButton.click(); 
	}
	public String getHeadingsTab()
	{
		headings = generalutility.getHeadings(headingsofEachNewTab);
		System.out.println(headings);
		return headings;
	}

	public void clickAllDashboardComp(String categoryName) throws InterruptedException {
		List<String> eachCategoryNames = generalutility.getTextOfElements(dashboardAdminComponents);
		for (int i = 0; i < eachCategoryNames.size(); i++) {
			for (WebElement element : clickDashboardComponents) {
				String eachHref = element.getAttribute("href");
				String[] parts = eachHref.split("/");
				String lastSegment = parts[parts.length - 1];				
				if (categoryName.equals(lastSegment)) {
					try {
						element.click();
						getHeadingsTab();	
						return ;									

					} catch (StaleElementReferenceException e) {
						System.out.println("Stale element reference: " + e.getMessage());

					}
				}
			}
		}
		
	}

	public void clickEachCategory2(String categoryName) throws InterruptedException {
		List<String> getEachCategory = generalutility.getTextOfElements(allCategory);
		for (int i = 0; i < getEachCategory.size(); i++) {
			for (WebElement element : alMoreInfoLink) {
				String eachHref = element.getAttribute("href");							
				String[] parts = eachHref.split("/");
				String lastSegment = parts[parts.length - 1];										
				if (categoryName.equals(lastSegment)) {
					try {
						
						element.click();
						getHeadingsTab();		
						return;						
					} catch (StaleElementReferenceException e) { 
						System.out.println("Stale element reference: " + e.getMessage());
						
					}
				}
			}
		}

	}
	
	public String clickLogoutUser()
	{
		pageutility.jsClick(logout);
		String actualLoginTitle = loginTitle.getText();
		return actualLoginTitle;
	}

}
