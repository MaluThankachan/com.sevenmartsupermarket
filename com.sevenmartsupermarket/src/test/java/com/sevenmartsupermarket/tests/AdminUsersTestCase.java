package com.sevenmartsupermarket.tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hpsf.Array;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.AdminUsersPage;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.ExcelReader;
import com.sevenmartsupermarket.utilities.GeneralUtility;

public class AdminUsersTestCase extends Base {
	AdminUsersPage adminuserspage;
	DashBoardPage dashboardpage;
	LoginPage loginpage;
	ExcelReader excelreader = new ExcelReader();
	SoftAssert softassert = new SoftAssert();

	@Test
	public void verifyClickAdminUserMoreInfo() {
		loginpage = new LoginPage(driver);
		excelreader.setExcelFile("LoginDetails", "LoginCredentials");
		String userName = excelreader.getCellData(0, 1);
		String password = excelreader.getCellData(1, 1);
		System.out.println(userName);
		System.out.println(password);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		
	}
	@Test(groups = "regression")
	public void verifyNewUseButtonOfAdminUsers() {
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickNewUser();		
		String nameOfUser = GeneralUtility.getRandomName();
		adminuserspage.enterDetails(nameOfUser, "newuser10");
		adminuserspage.selectUserType();
		adminuserspage.clickSaveButton();
		String actualNewUserAlert = adminuserspage.alertSuccessMsg();		
		String expectedNewUserAlert = "Alert!User Created Successfully";
		Assert.assertEquals(actualNewUserAlert, expectedNewUserAlert);
		
	}
	@Test
	public void verifyAlreadyExistUserAlert() {
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickNewUser();		
		adminuserspage.enterDetails("Isela Hills", "newuser10");
		adminuserspage.selectUserType();
		adminuserspage.clickSaveButton();
		String actualNewUserAlert = adminuserspage.alertAlreadyExistUserMsg();
		String expectedNewUserAlert = "Alert!Username already exists.";		
		Assert.assertEquals(actualNewUserAlert, expectedNewUserAlert);
		
	}
	
	@Test
	public void verifyHomeBtn()
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickNewUser();	
		String actualAdminName = adminuserspage.clickHomeBtn();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualAdminName, expectedProfileName);
		
	}
	@Test
	public void verifySearchUser()
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.searchUserClick();
		adminuserspage.searchUserNameInSearchBtn("Isela Hills","admin");
		adminuserspage.searchBelowButton();
		List<String> actualTableSearchValues = adminuserspage.getTableOfSearchedUser();
		List<String> expectedTableSearchValues = new ArrayList<String>();
		expectedTableSearchValues.add("Isela Hills");
		expectedTableSearchValues.add("admin");
		expectedTableSearchValues.add("Active");
		expectedTableSearchValues.add("Details");
		expectedTableSearchValues.add("");
		expectedTableSearchValues.add("");
		Assert.assertEquals(actualTableSearchValues, expectedTableSearchValues);
		
		
	}
	@Test
	public void verifyResetButton()
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.searchUserClick();
		adminuserspage.searchUserNameInSearchBtn("Vaidu","admin");
		adminuserspage.searchBelowButton();
		adminuserspage.resetButtonTop();
		boolean expected = true;
		boolean actual = adminuserspage.getSearchAdminTableHeading();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void verifySearchBtnTableDeleteAction() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		adminuserspage = new AdminUsersPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.searchUserClick();
		adminuserspage.searchUserNameInSearchBtn("Vaidu","admin");
		adminuserspage.searchBelowButton();		
		String actualAlertDeleteMsg =adminuserspage.deleteNameOfSearchUserTable("Vaidu");
		String expectedAlertDeleteMsg = "Alert!User Deleted Successfully";
		Assert.assertEquals(actualAlertDeleteMsg, expectedAlertDeleteMsg);		
	}
	
	@Test
	public void verifyAdminSearchTableCancelAlert() throws InterruptedException
	{loginpage = new LoginPage(driver);
	loginpage.login("admin", "admin");
	dashboardpage = new DashBoardPage(driver);
	adminuserspage = new AdminUsersPage(driver);
	dashboardpage.clickAdminMoreInfo();
	adminuserspage = new AdminUsersPage(driver);
	adminuserspage.searchUserClick();
	adminuserspage.searchUserNameInSearchBtn("Farha","admin");
	adminuserspage.searchBelowButton();		
	adminuserspage.deleteUserCancel();
	String expectedName = "Farha";
	String actualName = adminuserspage.getNameFromTable();
	Assert.assertEquals(actualName, expectedName);
		
	}
	
	@Test
	public void verifySearchBtnResultNotFoundMsg() 
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.searchUserClick();
		adminuserspage.searchUserNameInSearchBtn("merlyn","admin");	
		adminuserspage.searchBelowButton();		
		String actualAlertDeleteMsg= adminuserspage.getAlreadyDeletedUserMsg();
		String expectedAlertDeleteMsg = ".........RESULT NOT FOUND.......";
		Assert.assertEquals(actualAlertDeleteMsg, expectedAlertDeleteMsg);	
		
		
	}
	
	@Test
	public void verifyAllAdminTableNames()
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.getAllNamesAdminUserTable();
		String expected = "Admin Users";
		String actual = adminuserspage.getAdminUserHeading();
		Assert.assertEquals(actual, expected);		
	}
	

	@Test
	public void verifyAdminUserDeleteBtn()
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		String actualDeleteMsg = adminuserspage.deleteUserFromAdminTable("Farh");
		String expectedAlertDeleteMsg = "Alert!User Deleted Successfully";
		softassert.assertEquals(actualDeleteMsg, expectedAlertDeleteMsg);
		
	}
	
	
	
	
	@Test
	public void verifyAdminTableCancelAlert() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		String actualAlertText = adminuserspage.getAdminTableCancelAlert("Farha");
		String expectedAlertText ="Farha";
		Assert.assertEquals(actualAlertText, expectedAlertText);
	}
	
	@Test
	public void verifyAdminTableActiveButton() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		List<String> actual = adminuserspage.getAdminTableActiveClick("Zaqura");
		List<String> expected = new ArrayList<String>();
		expected.add("Inactive");
		expected.add("Alert!User Status Changed Successfully");
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void verifyAdminTableInActiveButton() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		List<String> actual = adminuserspage.getAdminTableActiveClick("Kala");
		List<String> expected = new ArrayList<String>();
		expected.add("Active");
		expected.add("Alert!User Status Changed Successfully");
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void verifyAdminTableEditButton()throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		List<String> actual = adminuserspage.getAdminTableEditButtonClick("Annu");
		List<String> expected = new ArrayList<String>();
		expected.add("Amy");
		expected.add("Alert!User Updated Successfully");
		Assert.assertEquals(actual, expected);
		
	}
	
	

}
