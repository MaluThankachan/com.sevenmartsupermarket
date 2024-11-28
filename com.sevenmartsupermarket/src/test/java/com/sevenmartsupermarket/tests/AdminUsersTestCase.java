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
	@Test
	public void verifyNewUserAlert() {
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickNewUser();		
		String expectedNewUserBGC = "rgba(218, 51, 67, 1)";
		String actualNewUserBGC = adminuserspage.newUserBtnBackgroundColor();
		String nameOfUser = GeneralUtility.getRandomName();
		adminuserspage.enterDetails(nameOfUser, "newuser10");
		adminuserspage.selectUserType();
		adminuserspage.clickSaveButton();
		String actualNewUserAlert = adminuserspage.alertSuccessMsg();
		String expectedNewUserAlert = "Alert!User Created Successfully";
		softassert.assertEquals(actualNewUserAlert, expectedNewUserAlert);
		softassert.assertEquals(actualNewUserBGC, expectedNewUserBGC);
		softassert.assertAll();
	}
	@Test
	public void verifyAlreadyExistUserAlert() {
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.clickNewUser();
		String expectedNewUserBGC = "rgba(220, 53, 69, 1)";
		String actualNewUserBGC = adminuserspage.newUserBtnBackgroundColor();
		adminuserspage.enterDetails("newuser4", "newuser4");
		adminuserspage.selectUserType();
		adminuserspage.clickSaveButton();
		String actualNewUserAlert = adminuserspage.alertAlreadyExistUserMsg();
		String expectedNewUserAlert = "Alert!Username already exists.";		
		softassert.assertEquals(actualNewUserAlert, expectedNewUserAlert);
		softassert.assertEquals(actualNewUserBGC, expectedNewUserBGC);
		softassert.assertAll();
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
		adminuserspage.searchUserNameInSearchBtn("merlyn","admin");
		/*String actualAboveSearchBGC = adminuserspage.searchUserBGC();
		String expectedAboveSearchBGC= "rgba(0, 105, 217, 1)";
		softassert.assertEquals(actualAboveSearchBGC, expectedAboveSearchBGC);
		adminuserspage.searchBelowButton();
		String actualBelowSearchBGC= adminuserspage.searchBelowButtonBGC();
		String expectedBelowSearchBGC= "rgba(215, 49, 65, 1)";
		softassert.assertEquals(actualBelowSearchBGC, expectedBelowSearchBGC);
		softassert.assertAll();*/
		adminuserspage.searchBelowButton();
		List<String> actualTableSearchValues = adminuserspage.getTableOfSearchedUser();
		List<String> expectedTableSearchValues = new ArrayList<String>();
		expectedTableSearchValues.add("merlyn");
		expectedTableSearchValues.add("admin");
		expectedTableSearchValues.add("active");
		Assert.assertEquals(actualTableSearchValues, expectedTableSearchValues);
		
		//adminuserspage.resetButtonTop();
		
	}
	@Test
	public void verifyResetButton()
	{
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin");
		dashboardpage = new DashBoardPage(driver);
		dashboardpage.clickAdminMoreInfo();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.resetButtonTop();
	}

}
