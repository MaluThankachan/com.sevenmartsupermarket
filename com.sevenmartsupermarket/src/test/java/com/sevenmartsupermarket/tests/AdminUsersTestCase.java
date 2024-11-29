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
	public void verifyNewUserAlert() {
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
		adminuserspage.enterDetails("Hilton Nikolaus", "newuser10");
		adminuserspage.selectUserType();
		adminuserspage.clickSaveButton();
		String actualNewUserAlert = adminuserspage.alertAlreadyExistUserMsg();
		String expectedNewUserAlert = "Alert!Username already exists.";		
		Assert.assertEquals(actualNewUserAlert, expectedNewUserAlert);
		
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
		adminuserspage.searchUserNameInSearchBtn("gsdb","admin");
		adminuserspage.searchBelowButton();
		List<String> actualTableSearchValues = adminuserspage.getTableOfSearchedUser();
		List<String> expectedTableSearchValues = new ArrayList<String>();
		expectedTableSearchValues.add("gsdb");
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
		adminuserspage.searchUserNameInSearchBtn("gsdb","admin");
		adminuserspage.searchBelowButton();
		adminuserspage.resetButtonTop();
		String expected = "Admin Users";
		String actual = adminuserspage.getAdminUserHeading();
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
		adminuserspage.searchUserNameInSearchBtn("keva","admin");
		adminuserspage.searchBelowButton();		
		String actualAlertDeleteMsg =adminuserspage.getNameOfSearchUserTable("keva");
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
	adminuserspage.searchUserNameInSearchBtn("Antony","admin");
	adminuserspage.searchBelowButton();		
	adminuserspage.deleteUserCancel();
	String expectedName = "Antony";
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
		String actualDeleteMsg = adminuserspage.DeleteAdminTableUser("admin756");
		String expectedAlertDeleteMsg = "Alert!User Deleted Successfully";
		softassert.assertEquals(actualDeleteMsg, expectedAlertDeleteMsg);
		String actualDeleteName = adminuserspage.checkDeleteAdminUserTable("admin756");
		String expectedDelete = "Deleted";
		softassert.assertEquals(actualDeleteName, expectedDelete);
	}
	
	
	
	
	@Test
	public void verifyAdminTableCancelAlert() throws InterruptedException
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		String actualAlertText = adminuserspage.getAdminTableCancelAlert("Antony");
		String expectedAlertText =actualAlertText;
		Assert.assertEquals(actualAlertText, expectedAlertText);
	}
	
	

}
