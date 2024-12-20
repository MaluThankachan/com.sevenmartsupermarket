package com.sevenmartsupermarket.pages;

import java.util.List;

import org.bouncycastle.crypto.digests.GeneralDigest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;
import com.sevenmartsupermarket.utilities.WaitUtility;

public class SubCategoriesPage {
	WebDriver driver;
	WaitUtility waitutility;
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();

	@FindBy(xpath = "//h1[text()='List Categories']")
	private WebElement heading;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	private WebElement newUserBtn;
	@FindBy(xpath = "//select[@id='cat_id']")
	private WebElement selectCategoryType;
	@FindBy(xpath = "//input[@id='subcategory']")
	private WebElement enterSubCategory;
	@FindBy(xpath = "//input[@id='main_img']")
	private WebElement chooseFile;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement alertCreatedMsg;
	@FindBy(xpath = "//a[text()='Cancel']")
	private WebElement cancelButton;
	@FindBy(xpath = "//a[text()='Home']")
	private WebElement homeButton;
	@FindBy(xpath = "//div[@class='info']//a")
	private WebElement dashboardProfileName;
	@FindBy(xpath = "//a[@onclick='click_button(2)']")
	private WebElement searchButtonTop;
	@FindBy(xpath = "//select")
	private WebElement searchSelectType;
	@FindBy(xpath = "//input[@placeholder='Sub Category']")
	private WebElement searchSubCtaegory;
	@FindBy(xpath = "//button[@value='sr']")
	private WebElement searchButtonBelow;
	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	private WebElement resetSearch;
	@FindBy(xpath = "//h4[text()='List Sub Categories']")
	private WebElement searchTableTitle;
	@FindBy(xpath = "//table//tbody//tr//td[1]")
	private List<WebElement> categoryTableNames;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement deleteAlertMsg;
	@FindBy(xpath = "//h1[text()='Edit Category']")
	private WebElement editCategoryTitle;
	@FindBy(xpath = "//button[text()='Update']")
	private WebElement updateEditBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement editAlertSuccess;

	String path = "C:\\Users\\MaluL\\OneDrive\\Desktop\\brocoli.png";

	public SubCategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
		waitutility = new WaitUtility(driver);
	}

	public String getTitle() {
		return heading.getText();
	}

	public String getTitleSearchTable() {
		return searchTableTitle.getText();
	}

	public void clickNewSubCategory() {
		pageutility.jsClick(newUserBtn);
	}

	public void selectCategory() {
		pageutility.selectDropDownByIndex(selectCategoryType, 1);
	}

	public void enterSubCategoryName(String categoryName) {
		enterSubCategory.sendKeys(categoryName);
	}

	public void chooseImageFile() {
		generalutility.uploadImage(chooseFile,"brocoli");
	}

	public String saveCategory() {
		pageutility.jsClick(saveButton);
		String actualAlertMsg = getSuccessAlertMsg();
		return actualAlertMsg;
	}

	public String getSuccessAlertMsg() {
		String alertMessage = alertCreatedMsg.getText().substring(2, 8);
		String message = alertCreatedMsg.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}

	public void cancelCreateSubCtaegory() {
		pageutility.jsClick(cancelButton);
	}

	public String homeBtnClick() {
		pageutility.jsClick(homeButton);
		return dashboardProfileName.getText();
	}

	public void searchClick() {
		searchButtonTop.click();
	}

	public String searchCategoryClick(String subCategoryName) {
		searchClick();
		pageutility.selectDropDownByIndex(searchSelectType, 6);
		searchSubCtaegory.sendKeys(subCategoryName);
		searchButtonBelow.click();
		String actualTitle = getTitleSearchTable();
		return actualTitle;
	}

	public String searchResetCategoryClick(String subCategoryName) {
		searchClick();
		pageutility.selectDropDownByIndex(searchSelectType, 1);
		searchSubCtaegory.sendKeys(subCategoryName);
		resetSearch.click();
		String actualTitle = getTitleSearchTable();
		return actualTitle;
	}

	public String deleteCategory(String categoryname) {
		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		int index = 0;
		for (index = 0; index < allTableNames.size(); index++) {
			if (categoryname.equals(allTableNames.get(index))) {
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}

		WebElement deleteActionTable = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[5]//a[2]"));
		pageutility.scrollAndClick(deleteActionTable);
		driver.switchTo().alert().accept();
		String actualDeleteMsg = getDeleteAlertMsg();
		return actualDeleteMsg;
	}

	public String getDeleteAlertMsg() {
		String alertMessage = deleteAlertMsg.getText().substring(2, 8);
		String message = deleteAlertMsg.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}
	

	public String getEditAlertMsg() {
		String alertMessage = editAlertSuccess.getText().substring(2, 8);
		String message = editAlertSuccess.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}
	
	public String EditCategory(String categoryname) {
		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		int index = 0;
		for (index = 0; index < allTableNames.size(); index++) {
			if (categoryname.equals(allTableNames.get(index))) {
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}

		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[5]//a[1]"));
		pageutility.scrollAndClick(editAction);	
		selectCategory();
		enterSubCategory.clear();
		enterSubCategoryName("spinach");
		pageutility.scrollAndClick(updateEditBtn);
		return getEditAlertMsg();
		
	}
	

}
