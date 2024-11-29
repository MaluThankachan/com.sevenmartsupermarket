package com.sevenmartsupermarket.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
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

	String deleteAlertMsg;
	String alertText;
	String result ;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
		generalutility = new GeneralUtility();
		waitutility = new WaitUtility(driver);

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

		pageutility.selectDropDownByIndex(userType, 3);
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

	public String searchUserBGC() {

		String actualSearchBGC = generalutility.getCssProperty(searchButtonTop, "background-color");
		System.out.println("The backgroundcolor of search user is " + actualSearchBGC);
		return actualSearchBGC;

	}

	public void searchUserNameInSearchBtn(String uname, String userType) {
		waitutility.waitElementForVisible(searchUserNameField, 10);
		searchUserNameField.sendKeys(uname);
		pageutility.selectDropDownByValue(userTypeSelect, userType);

	}

	public void searchBelowButton() {
		searchButtonBelow.click();
	}

	public String searchBelowButtonBGC() {
		String actualSearchBelowBGC = generalutility.getCssProperty(searchButtonBelow, "background-color");
		System.out.println("The background color of below search button is " + actualSearchBelowBGC);
		return actualSearchBelowBGC;

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

	public String getNameOfSearchUserTable(String name) throws InterruptedException {
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
				// deleteUser.click();
				driver.switchTo().alert().accept();
				result= alertSuccessMsg();
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

	String deleteName;

	public String checkDeleteAdminUserTable(String uname) {
		List<String> allNames = generalutility.getTextOfElements(getAllNamesFromTable);

		for (String eachName : allNames) {
			if (!eachName.equals(uname)) {
				deleteName = "Deleted";
			}
		}
		return deleteName;
	}

	public List<String> getAllUserTypeAdminUserTable() {
		List<String> allUserType = generalutility.getTextOfElements(getAlluserTypeTable);
		return allUserType;
	}

	public String DeleteAdminTableUser(String uname) {

		List<String> getAllNames = getAllNamesAdminUserTable();
		for (String eachName : getAllNames) {
			if (eachName.equals(uname)) {
			
				deleteAlertMsg = deleteUserFromTable(eachName);
				System.out.println(eachName);
				break;
			}
		}
		return deleteAlertMsg;

	}

	public String getAdminTableCancelAlert(String uname) throws InterruptedException {
		List<String> getAllNames = getAllNamesAdminUserTable();
		for (String eachName : getAllNames) {
			if (eachName.equals(uname)) {
				alertText = deleteUserCancel();

			}
		}

		return alertText;
	}

}
