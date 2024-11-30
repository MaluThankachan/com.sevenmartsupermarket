package com.sevenmartsupermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.constants.Constants;
import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;
import com.sevenmartsupermarket.utilities.WaitUtility;

public class CategoriesPage {
	WebDriver driver;
	WaitUtility waitutility;
	
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
	@FindBy(xpath = "//table//tbody//tr")
	List<WebElement> alRowsFromTable;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement deleteAlertMsg;
	@FindBy(xpath = "h1")
	WebElement editCategoryTitle;
	@FindBy(xpath = "//span[@class='fas fa-trash-alt']/..")
	WebElement deleteEditPage;
	@FindBy(xpath = "//button[text()='Update']")
	WebElement updateEditPage;
	@FindBy(xpath = "//div[@class='ms-selectable']//li")
	WebElement selectEditgroups;
	@FindBy(xpath = "//p//img")
	WebElement prevImage;
	
	boolean status = false;
	
	String path= "C:\\Users\\MaluL\\OneDrive\\Desktop\\success.png";
	GeneralUtility generalutility = new GeneralUtility();
	PageUtility pageutility ;

	public CategoriesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
		waitutility = new WaitUtility(driver);
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
		generalutility.uploadImage(chooseFile, "success");
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
	
	public String getDeleteAlertMsg()
	{
		String alertMessage = deleteAlertMsg.getText().substring(2, 8);
		String message = deleteAlertMsg.getText().substring(9);
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
	
		
	public String deleteCategory(String categoryname)
	{
		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		int index = 0;
		for(index =0;index<allTableNames.size();index++)
		{
			if(categoryname.equals(allTableNames.get(index)))
			{
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}		
		
		WebElement deleteActionTable = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[4]//a[2]"));
		deleteActionTable.click();
		driver.switchTo().alert().accept();
		String actualDeleteMsg = getDeleteAlertMsg();
		return actualDeleteMsg;
	}	
	
	public String EditEnterCategory(String categoryName,String editName)
	{
		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		int index = 0;
		for(index =0;index<allTableNames.size();index++)
		{
			if(categoryName.equals(allTableNames.get(index)))
			{
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}		
		
		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[4]//a[1]"));
		editAction.click();
		String actualEnterCategory = generalutility.get_Attribute(enterCategoryField, "value");
		enterCategoryField.clear();
		enterCategoryName(editName);
		pageutility.jsClick(updateEditPage);
		return actualEnterCategory;
	}
	
	public String EditSelectGroupCategory(String categoryName)
	{

		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		int index = 0;
		for(index =0;index<allTableNames.size();index++)
		{
			if(categoryName.equals(allTableNames.get(index)))
			{
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}		
		
		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[4]//a[1]"));
		editAction.click();
		String actualSelectValue = selectEditgroups.getText();
		pageutility.jsClick(selectEditgroups);
		String changedValue = selectEditgroups.getText();
		pageutility.jsClick(updateEditPage);
		return actualSelectValue;
		
	
		
	
	}	
	public String EditChangeImageCategory(String categoryName)
	{

		List<String> allTableNames = generalutility.getTextOfElements(categoryTableNames);
		int index = 0;
		for(index =0;index<allTableNames.size();index++)
		{
			if(categoryName.equals(allTableNames.get(index)))
			{
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}		
		
		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[4]//a[1]"));
		waitutility.waitElementForClickable(editAction, 20);
		pageutility.jsClick(editAction);
		String actualImage = generalutility.get_Attribute(prevImage, "src");
		String[] parts = actualImage.split("/");
		String lastValue = parts[parts.length - 1];
		System.out.println(lastValue);
		//waitutility.waitElementForVisible(chooseFile, 20);
		chooseImageFile();
		pageutility.jsClick(updateEditPage);
		return lastValue;
}	
	

}
