package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.ScreenshotCapture;

public class LoginTest extends Base {
	LoginPage loginpage;
	DashBoardPage dashBoardPage;
	SoftAssert softassert = new SoftAssert();
	
	@Test(retryAnalyzer = com.sevenmartsupermarket.listeners.RetryAnalyzer.class)
	public void verifyAdminUserLogin() {
		loginpage = new LoginPage(driver);		
		dashBoardPage = loginpage.login("admin", "admin");
		System.out.println(GeneralUtility.getRandomName());
		System.out.println(GeneralUtility.getRandomAddress());		
		String actualProfileName = dashBoardPage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName);

	}

	@Test(groups = {"smoke","regression"})
	public void verifyHeadings()
	{
		loginpage = new LoginPage(driver);
		String actualHeading = loginpage.loginHeading();
		String expectedHeading = "7rmart supermarket";
		softassert.assertEquals(actualHeading, expectedHeading);
		String actualSignHeading = loginpage.siginstartHeading();
		String expectedSignInHeading = "Sign in to start your session";
		softassert.assertEquals(actualSignHeading, expectedSignInHeading);
	}
	
	@Test
	public void verifyInvalidUserName()
	{
		dashBoardPage = new DashBoardPage(driver);
		loginpage = new LoginPage(driver);
		loginpage.login("admin342", "admin");
		String expectedUserName = "admin";
		Assert.assertEquals("admin342", expectedUserName,"Invalid Username/Password");
	}
	
	
	@Test
	public void verifyInvalidPassword()
	{
		dashBoardPage = new DashBoardPage(driver);
		loginpage = new LoginPage(driver);
		loginpage.login("admin", "admin342");
		String expectedUserName = "admin";
		Assert.assertEquals("admin342", expectedUserName,"Invalid Username/Password");
	}
	
	@Test
	public void verifyInvalidUsernameAndPassword()
	{
		dashBoardPage = new DashBoardPage(driver);
		loginpage = new LoginPage(driver);
		loginpage.login("admin342", "admin342");
		String expectedUserName = "admin";
		Assert.assertEquals("admin342", expectedUserName,"Invalid Username/Password");
	}
	
	

}
