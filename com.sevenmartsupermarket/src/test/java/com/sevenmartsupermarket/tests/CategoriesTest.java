package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.CategoriesPage;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.LoginPage;

public class CategoriesTest extends Base {
	LoginPage loginpage;
	DashBoardPage dashboardpage;
	CategoriesPage categoriespage;

	@Test
	public void verifyCreateCategory() throws InterruptedException {
		loginpage = new LoginPage(driver);
		categoriespage = new CategoriesPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");		
		categoriespage.clickNewCategory();
		categoriespage.enterCategoryName("Sample4");
		categoriespage.selectGroups();
		categoriespage.chooseImageFile();
		categoriespage.clickShowTopMenuYes();
		categoriespage.clickShowLeftMenuYes();
		String actualAlertMsg = categoriespage.saveCategory();
		String expectedAlertMsg = "Alert!Category Created Successfully";
		System.out.println(expectedAlertMsg);
		Assert.assertEquals(actualAlertMsg, expectedAlertMsg);
	}

	@Test
	public void verifyGoHome() throws InterruptedException {
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);
		categoriespage.clickNewCategory();
		dashboardpage.clickHomeButton();
		String actualProfileName = dashboardpage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName);

	}

	@Test
	public void verifyResetButton() throws InterruptedException {
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);
		String actualTitle = categoriespage.resetButtonTop();
		String expectTitle = "List Categories";
		Assert.assertEquals(actualTitle, expectTitle);
	}

	@Test
	public void verifySearchCategory() throws InterruptedException {
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);
		String actualName = categoriespage.searchCategoryClick("Sample2");
		String expectedName = "Sample2";
		Assert.assertEquals(actualName, expectedName);

	}

	@Test
	public void verifySearchResetButton() throws InterruptedException {
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);		
		String actualtitle = categoriespage.searchCategoryClickReset("Sample2");
		String expectedTitle="List Categories";
		Assert.assertEquals(actualtitle, expectedTitle);
	}
	
	@Test
	public void verifyDeleteCategoryFromTable() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);	
		String actualDeleteMsg = categoriespage.deleteCategory("Small Leather Table");
		String expectedDeleteMsg = "Alert!Category Deleted Successfully";
		Assert.assertEquals(actualDeleteMsg, expectedDeleteMsg);
	}
	
	@Test
	public void verifyEditCategoryFromTable() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);	
		String actualMsg = categoriespage.EditEnterCategory("Sample4", "EditValue1");
		String expectedEditMsg = "EditValue1";
		Assert.assertNotEquals(actualMsg,expectedEditMsg);
		
	}
	
	@Test
	public void verifyEditSelectGroupCategory() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);	
		String actualMsg = categoriespage.EditSelectGroupCategory("EditValue1");
		String expectedEditMsg = " ";
		Assert.assertNotEquals(actualMsg,expectedEditMsg);
		
	}
	
	@Test
	public void verifyDeleteImageEdit() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-category");
		categoriespage = new CategoriesPage(driver);
		String actualSRC = categoriespage.EditChangeImageCategory("Grocery");
		String expectedSRC= "success.png";
		Assert.assertNotEquals(actualSRC,expectedSRC);
	}
}
