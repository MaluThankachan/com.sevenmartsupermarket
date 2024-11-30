package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.ManageContactPage;
import com.sevenmartsupermarket.pages.SubCategoriesPage;

public class ManageContactTest extends Base {
	
	LoginPage loginpage;
	DashBoardPage dashboardpage;
	ManageContactPage managecontactpage;
	
	@Test
	public void verifyEditMangeContact() throws InterruptedException {
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		managecontactpage = new ManageContactPage(driver);		
		dashboardpage.clickEachCategory2("list-contact");	
		String actualAlertMsg = managecontactpage.EditContact("hellloobsqura@gmail.com");
		String expectedAlertMsg = "Alert!Contact Updated Successfully";
		Assert.assertEquals(actualAlertMsg, expectedAlertMsg);
}
}
