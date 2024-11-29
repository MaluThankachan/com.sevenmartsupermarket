package com.sevenmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class CategoriesPage {
	WebDriver driver;
	
	@FindBy(xpath = "//h1[text()='List Categories']")
	WebElement heading;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	WebElement newUserBtn;
	@FindBy(xpath = "//input[@id='category']")
	WebElement enterCategoryField;
	@FindBy(xpath = "//div[@class='ms-selectable']//li")
	WebElement selectgroups;
	@FindBy(xpath = "//input[@id='main_img']")
	WebElement chooseFile;
	@FindBy(xpath = "//input[@type='radio' and @name='top_menu' and @value='yes']")
	WebElement showOnTopMenuYes;
	@FindBy(xpath = "//input[@type='radio' and @name='top_menu' and @value='no']")
	WebElement showOnTopMenuNo;
	@FindBy(xpath = "//input[@type='radio' and @name='show_home' and @value='yes']")
	WebElement showOnLeftMenuYes;
	@FindBy(xpath = "//input[@type='radio' and @name='show_home' and @value='no']")
	WebElement showOnLeftMenuNo;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveButton;
	@FindBy(xpath = "//a[text()='Cancel']")
	WebElement cancelButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement alertCreatedMsg;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-warning' and contains(text(),'Reset')]")
	WebElement resetBtnTop;
	@FindBy(xpath = "//a[@onclick='click_button(2)']")
	WebElement searchButtonTop;
	@FindBy(xpath = "//input[@placeholder='Category']")
	WebElement categorySearch;
	@FindBy(xpath = "//button[@value='sr']")
	WebElement searchButtonBelow;
	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	WebElement resetSearch;
	@FindBy(xpath = "//table//tbody//tr//td[1]")
	WebElement searchUserTableName;
	@FindBy(xpath = "//table//tbody//tr//td[1]")
	List<WebElement> categoryTableNames;
	@FindBy(xpath = "//a[starts-with(@href,'https://groceryapp.uniqassosiates.com/admin/category/delete?')]")
	WebElement deleteActionTable;
	@FindBy(xpath = "//table//tbody//tr")
	List<WebElement> alRowsFromTable;
	
	boolean status = false;
	
	String path= "C:\\Users\\MaluL\\OneDrive\\Desktop\\success.png";
	GeneralUtility generalutility = new GeneralUtility();
	PageUtility pageutility ;

	public CategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
	}
	
	public void clickNewCategory()
	{
		generalutility.clickNewBtn(newUserBtn);
	}
	
	public void enterCategoryName(String categoryName)
	{
		enterCategoryField.sendKeys(categoryName);
	}
	
	public void selectGroups()
	{
		selectgroups.click();
	}
	
	public void chooseImageFile()
	{
		chooseFile.sendKeys(path);
	}
	
	public void clickShowTopMenuYes()
	{
		if(!showOnTopMenuYes.isSelected())
		{
			showOnTopMenuYes.click();
			status=true;
			
		}
	}
	
	public void clickShowTopMenuNo()
	{
		if(status == true)
		{
			showOnTopMenuNo.isSelected();
		}
	}
	
	public void clickShowLeftMenuYes()
	{
		if(!showOnLeftMenuYes.isSelected())
		{
			showOnTopMenuYes.click();
			status=true;
			
		}
	}
	
	public void clickShowLeftMenuNo()
	{
		if(status == true)
		{
			showOnLeftMenuNo.isSelected();
		}
	}
	
	public String saveCategory()
	{
		pageutility.jsClick(saveButton);
		String actualAlertMsg = getSuccessAlertMsg();
		return actualAlertMsg;
	}
	
	public String getSuccessAlertMsg()
	{
		String alertMessage = alertCreatedMsg.getText().substring(2, 8);
		String message = alertCreatedMsg.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}
	
	public String resetButtonTop() {
		resetBtnTop.click();
		String actualCategoryPageTitle = heading.getText();
		return actualCategoryPageTitle;

	}
	public void searchClick() {
		searchButtonTop.click();
	}
	
	public String searchCategoryClick(String categoryName)
	{
		searchClick();
		categorySearch.sendKeys(categoryName);
		searchButtonBelow.click();
		String actualUserName = searchUserTableName.getText();
		return actualUserName;
	}
	
	public String searchCategoryClickReset(String categoryName)
	{
		searchClick();
		categorySearch.sendKeys(categoryName);
		resetSearch.click();
		String actualCategoryPageTitle = heading.getText();
		return actualCategoryPageTitle;
	}
	
	public void getAllCategoryNamesTable(String deleteName)
	{
		
		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		for(String each : allTableNames)
		{
			if(each.equals(deleteName))
			{
				
				deleteActionTable.click();
				driver.switchTo().alert().accept();
				
				
			}
		}
	}
}
