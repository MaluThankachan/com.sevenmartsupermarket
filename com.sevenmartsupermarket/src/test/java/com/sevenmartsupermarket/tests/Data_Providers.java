package com.sevenmartsupermarket.tests;

import org.testng.annotations.DataProvider;

public class Data_Providers {
	
		@DataProvider(name = "searchdashboarditems") //this should be given in our test case 
		public Object[][] searchitems() {
			return new Object[][] { { "list-admin","Admin Users"}, { "list-footertext","Footer Text" }, { "list-category","List Categories" },{"list-sub-category","List Sub Categories"}
			,{"list-contact","Contact Us"},{"list-product","List Products"}};
		}
		@DataProvider(name="addmanageproduct")
		public Object[][] enterproducts(){
			return new Object[][] {{"ProductNew","ProductNew","Chickens","6","12","3000","4000","20","2800","Contact "}};
		}
		
}
