package com.sevenmartsupermarket.pages;

import org.testng.annotations.DataProvider;

import com.sevenmartsupermarket.utilities.ExcelReader;

public class Data_Providers {
	ExcelReader excelreader = new ExcelReader();

	@DataProvider(name = "searchdashboarditems") // this should be given in our test case
	public Object[][] searchitems() {
		excelreader.setExcelFile("DashBoardItems", "Items");
		return excelreader.getMultidimentionalData(6, 2);
	}

	@DataProvider(name = "addmanageproduct")
	public Object[][] enterproducts() {
		return new Object[][] {
				{ "ProductNew", "ProductNew", "Chickens", "6", "12", "3000", "4000", "20", "2800", "Contact " } };
	}

}
