package com.sevenmartsupermarket.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;
import com.sevenmartsupermarket.utilities.WaitUtility;

public class AdminUsersPage {
	WebDriver driver;
	PageUtility pageutility;
	GeneralUtility generalutility;
	WaitUtility waitutility;

	By user_Name = By.xpath("//input[@id='username']");
	@FindBy(xpath = "//a[text()='Home']")
	WebElement homeBtn;
	@FindBy(xpath = "//div[@class='info']//a")
	WebElement dashboardProfileName;
	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	WebElement newUser;
	@FindBy(xpath = "//input[@id='username']")
	WebElement newUserName;
	@FindBy(xpath = "//input[@id='password']")
	WebElement newUserPassword;
	@FindBy(xpath = "//select[@id='user_type']")
	WebElement userType;
	@FindBy(xpath = "//button[@name='Create']")
	WebElement saveButton;
	@FindBy(xpath = "//h5")
	WebElement alertMessage;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement alreadyExistAlertMessage;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successAlertMessage;
	@FindBy(xpath = "//a[@onclick='click_button(2)']")
	WebElement searchButtonTop;
	@FindBy(xpath = "//input[@id='un']")
	WebElement searchUserNameField;
	@FindBy(xpath = "//select[@id='ut']")
	WebElement userTypeSelect;
	@FindBy(xpath = "//button[@value='sr']")
	WebElement searchButtonBelow;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-warning' and contains(text(),'Reset')]")
	WebElement resetBtnTop;
	@FindBy(xpath = "//table//tbody//tr//td")
	List<WebElement> searchButtonBelowResultTable;
	@FindBy(xpath = "//h1[contains(text(),'Admin Users')]")
	WebElement adminUserHeading;
	@FindBy(xpath = "//table//tbody//tr//td[5]//a[starts-with(@href, 'https://groceryapp.uniqassosiates.com/admin/user/delete?')]")
	WebElement deleteUser;
	@FindBy(xpath = "//table//tbody//tr//td//center")
	WebElement alreadyDeleteUserMsg;
	@FindBy(xpath = "//table//tbody//tr//td[1]")
	List<WebElement> getAllNamesFromTable;
	@FindBy(xpath = "//table//tbody//tr[1]//td[1]")
	WebElement getSearchNameFromTable;
	@FindBy(xpath = "//table//tbody//tr[1]//td[2]")
	List<WebElement> getAlluserTypeTable;
	@FindBy(xpath = "//table//tbody//tr")
	List<WebElement> singleUserRow;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement deleteAlert;
	@FindBy(xpath = "//table//tr//td[3]//a//span")
	WebElement previousActiveStatus;
	@FindBy(xpath = "//button[@name='Update']")
	WebElement updateEditBtn;

	String deleteAlertMsg;
	String alertText;
	String result;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
		generalutility = new GeneralUtility();
		waitutility = new WaitUtility(driver);

	}
	
	public String clickHomeBtn()
	{
		pageutility.jsClick(homeBtn);
		return dashboardProfileName.getText();
	}

	public String getAdminUserHeading() {
		return adminUserHeading.getText();
	}

	public void clickNewUser() {

		waitutility.waitElementForClickable(newUser, 20);
		newUser.click();
	}

	public void enterNewUserName(String newName) {
		waitutility.waitElementForVisible(newUserName, 20);
		newUserName.sendKeys(newName);
	}

	public void enterNewPassword(String newPassword) {
		waitutility.waitElementForVisible(newUserPassword, 20);
		newUserPassword.sendKeys(newPassword);
	}

	public void enterDetails(String uname, String password) {

		enterNewUserName(uname);
		enterNewPassword(password);
	}

	public AdminUsersPage selectUserType() {

		pageutility.selectDropDownByIndex(userType, 2);		
		return this;
	}

	public void clickSaveButton() {
		waitutility.waitElementForClickable(saveButton, 20);
		saveButton.click();

	}

	public String alertNewUser() {

		return alertMessage.getText();
	}

	public String alertSuccessMsg() {
		System.out.println(successAlertMessage.getText());
		String alertMessage = successAlertMessage.getText().substring(2, 8);
		String message = successAlertMessage.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}

	public String alertAlreadyExistUserMsg() {

		String alertMessage = alreadyExistAlertMessage.getText().substring(2, 8);
		String message = alreadyExistAlertMessage.getText().substring(9);
		String actualMsg = alertMessage + message;
		return actualMsg;
	}

	public String newUserBtnBackgroundColor() {
		String actualNewUserBGC = generalutility.getCssProperty(newUser, "background-color");
		System.out.println("The backgroundcolor of new user is " + actualNewUserBGC);
		return actualNewUserBGC;
	}

	public void searchUserClick() {
		searchButtonTop.click();
	}

	public void searchUserNameInSearchBtn(String uname, String userType) {
		waitutility.waitElementForVisible(searchUserNameField, 10);
		searchUserNameField.sendKeys(uname);
		pageutility.selectDropDownByValue(userTypeSelect, userType);

	}

	public void searchBelowButton() {
		searchButtonBelow.click();
	}

	public void resetButtonTop() {
		resetBtnTop.click();
		String resetBtnTopBGC = generalutility.getCssProperty(resetBtnTop, "background-color");
		System.out.println("The reset top button background color " + resetBtnTopBGC);

	}

	public List<String> getTableOfSearchedUser() {
		List<String> tableRowValues = new ArrayList<String>();
		tableRowValues = generalutility.getTextOfElements(searchButtonBelowResultTable);
		System.out.println(tableRowValues);
		return tableRowValues;

	}

	public String deleteNameOfSearchUserTable(String name) throws InterruptedException {
		String uname = getSearchNameFromTable.getText();
		if (uname.equals(name)) {
			deleteAlertMsg = deleteUserFromTable(uname);
		}
		return deleteAlertMsg;
	}

	public String getNameFromTable() {
		return getSearchNameFromTable.getText();
	}

	public String deleteUserFromTable(String uname) {

		List<String> getAllNames = getAllNamesAdminUserTable();
		for (String eachName : getAllNames) {
			if (eachName.equals(uname)) {
				pageutility.jsClick(deleteUser);
				driver.switchTo().alert().accept();
				result = alertSuccessMsg();
				break;
			}
		}
		return result;
	}

	public String deleteUserCancel() throws InterruptedException {
		Thread.sleep(2000);
		pageutility.jsClick(deleteUser);
		Thread.sleep(2000);
		alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().dismiss();
		return alertText;
	}

	public String getAlreadyDeletedUserMsg() {
		return alreadyDeleteUserMsg.getText();
	}

	public List<String> getAllNamesAdminUserTable() {
		List<String> allNames = generalutility.getTextOfElements(getAllNamesFromTable);
		return allNames;
	}

	public String deleteUserFromAdminTable(String uname) {
		List<String> allTableNames = generalutility.getTextOfElements(getAllNamesFromTable);
		int index = 0;
		for (index = 0; index < allTableNames.size(); index++) {
			if (uname.equals(allTableNames.get(index))) {
				
				index++;
				break;
			}
		}

		WebElement deleteActionTable = driver.findElement(By.xpath("//table//tbody//tr[" + index + "]//td[5]//a[3]"));
		pageutility.jsClick(deleteActionTable);
		driver.switchTo().alert().accept();
		String actualDeleteMsg = getDeleteAlertMsg();
		return actualDeleteMsg;
	}

	public String getDeleteAlertMsg() {
		String alertMessage = deleteAlert.getText().substring(2, 8);
		String message = deleteAlert.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}

	public String getAdminTableCancelAlert(String uname) throws InterruptedException {
		List<String> getAllNames = generalutility.getTextOfElements(getAllNamesFromTable);
		int index;
		for (index = 0; index < getAllNames.size(); index++) {
			if (uname.equals(getAllNames.get(index))) {
				System.out.println(getAllNames.get(index));
				index++;
				break;
			}
		}
		WebElement deleteActionTable = driver.findElement(By.xpath("//table//tbody//tr[" + index + "]//td[5]//a[3]"));
		pageutility.jsClick(deleteActionTable);
		driver.switchTo().alert().dismiss();
		return uname;
	}
	
	public List<String> getAdminTableActiveClick(String uname) throws InterruptedException{
		List<String> getAllNames = generalutility.getTextOfElements(getAllNamesFromTable);
		int index;
		for (index = 0; index < getAllNames.size(); index++) {
			if (uname.equals(getAllNames.get(index))) {
				System.out.println(getAllNames.get(index));
				index++;
				break;
			}
		}
		WebElement activeActionTable = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[5]//a[1]"));
		String actualStatus = previousActiveStatus.getText();
		pageutility.jsClick(activeActionTable);		
		String alertMsg = alertSuccessMsg() ;
		List<String> actualStatusAndAlert =  new ArrayList<String>();
		actualStatusAndAlert.add(actualStatus);
		actualStatusAndAlert.add(alertMsg);
		System.out.println(actualStatusAndAlert);
		return actualStatusAndAlert;
	}
	
	public List<String> getAdminTableEditButtonClick(String uname) throws InterruptedException{
		List<String> getAllNames = generalutility.getTextOfElements(getAllNamesFromTable);
		int index;
		for (index = 0; index < getAllNames.size(); index++) {
			if (uname.equals(getAllNames.get(index))) {
				System.out.println(getAllNames.get(index));
				index++;
				break;
			}
		}
		WebElement editActionTable = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[5]//a[2]"));
		pageutility.jsClick(editActionTable);			
		String actualUserName = newUserName.getText();		
		newUserName.clear();
		newUserName.sendKeys("Amy");
		String changedUserName = generalutility.get_Attribute(newUserName, "value");
		pageutility.jsClick(updateEditBtn);
		String alertMsg = alertSuccessMsg() ;
		List<String> actualUserNameAndAlert =  new ArrayList<String>();
		actualUserNameAndAlert.add(changedUserName);
		actualUserNameAndAlert.add(alertMsg);
		System.out.println(actualUserNameAndAlert);
		return actualUserNameAndAlert;
	}
}
