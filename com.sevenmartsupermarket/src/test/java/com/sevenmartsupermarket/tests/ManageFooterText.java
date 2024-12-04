package com.sevenmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.ManageFooterTextPage;

public class ManageFooterText extends Base {
	LoginPage loginpage;
	DashBoardPage dashboardpage;
	ManageFooterTextPage managefooterpage;

	@Test
	public void verifyEditMangeFooterText() throws InterruptedException {
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		managefooterpage= new ManageFooterTextPage(driver);		
		dashboardpage.clickEachCategory2("list-footertext");	
		String actualAlertMsg = managefooterpage.EditContact("knpy");
		String expectedAlertMsg = "Alert!Contact Updated Successfully";
		Assert.assertEquals(actualAlertMsg, expectedAlertMsg);
}
}
