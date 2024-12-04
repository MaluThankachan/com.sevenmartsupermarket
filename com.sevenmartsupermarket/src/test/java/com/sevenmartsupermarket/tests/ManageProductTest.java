package com.sevenmartsupermarket.tests;

import org.testng.annotations.Test;

import com.sevenmartsupermarket.base.Base;
import com.sevenmartsupermarket.pages.DashBoardPage;
import com.sevenmartsupermarket.pages.Data_Providers;
import com.sevenmartsupermarket.pages.LoginPage;
import com.sevenmartsupermarket.pages.ManageProductPage;
import com.sevenmartsupermarket.pages.SubCategoriesPage;

public class ManageProductTest extends Base {
	LoginPage loginpage;
	DashBoardPage dashboardpage;
	ManageProductPage manageproductpage;
	
	@Test(dataProvider = "addmanageproduct" , dataProviderClass = Data_Providers.class)
	public void verifyNewProductClick(String title,String tagName,String weight,
			String maxQuantity,String price,String mrp,String stockAvailable, 
			String purchaseprice,String description) throws InterruptedException
	{
		loginpage = new LoginPage(driver);		
		dashboardpage = loginpage.login("admin", "admin");
		manageproductpage = new ManageProductPage(driver);
		dashboardpage.clickEachCategory2("list-product");
		manageproductpage.addNewProduct(title,tagName,weight,maxQuantity,price,mrp,stockAvailable,purchaseprice,description);
		
	}
	
	
}
