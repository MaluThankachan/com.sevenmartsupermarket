package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.CategoriesPage;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.SubCategoriesPage;

public class SubCategoriesTest extends Base {
	LoginPage loginpage;
	DashBoardPage dashboardpage;
	SubCategoriesPage subcategoriespage;
	@Test
	public void verifyCreateSubCategoryButtonIsClickable() throws InterruptedException {
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		subcategoriespage = new SubCategoriesPage(driver);
		dashboardpage.clickEachCategory2("list-sub-category");	
		subcategoriespage.clickNewSubCategory();
		subcategoriespage.selectCategory();
		subcategoriespage.enterSubCategoryName("BrocoliVeg");
		subcategoriespage.chooseImageFile();
		String actualAlertMsg =subcategoriespage.saveCategory();	
		String expectedAlertMsg = "Alert!Sub Category Created Successfully";
		Assert.assertEquals(actualAlertMsg, expectedAlertMsg);
	}
	
	@Test
	public void verifyGoHomeIsClickable() throws InterruptedException
	{
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		subcategoriespage = new SubCategoriesPage(driver);
		dashboardpage.clickEachCategory2("list-sub-category");	
		subcategoriespage.clickNewSubCategory();
		String actualProfilename = subcategoriespage.homeBtnClick();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfilename, expectedProfileName);
	}
	
	@Test
	public void verifySearchButtonIsClickable() throws InterruptedException
	{
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		subcategoriespage = new SubCategoriesPage(driver);
		dashboardpage.clickEachCategory2("list-sub-category");	
		String actual = subcategoriespage.searchCategoryClick("brocoli");
		String expected = "List Sub Categories";
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void verifyIsResetButtonClickable() throws InterruptedException
	{
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		subcategoriespage = new SubCategoriesPage(driver);
		dashboardpage.clickEachCategory2("list-sub-category");	
		String actual = subcategoriespage.searchResetCategoryClick("veggies");
		String expected = "List Sub Categories";
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void verifyDeleteEntriesFromTableOfSubCategory() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-sub-category");
		subcategoriespage = new SubCategoriesPage(driver);	
		String actualDeleteMsg = subcategoriespage.deleteCategory("ss");
		String expectedDeleteMsg = "Alert!Sub Category Deleted Successfully";
		Assert.assertEquals(actualDeleteMsg, expectedDeleteMsg);
	}
	
	@Test
	public void verifyEditCategoryFromTable() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		dashboardpage = loginpage.login("admin", "admin");
		dashboardpage.clickEachCategory2("list-sub-category");
		subcategoriespage = new SubCategoriesPage(driver);	
		String actualDeleteMsg = subcategoriespage.EditCategory("Gus");
		String expectedDeleteMsg = "Alert!Sub Category Updated Successfully";
		Assert.assertEquals(actualDeleteMsg, expectedDeleteMsg);
	}
	
	
}
