package com.sevenmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.WaitUtility;
import com.sevenmartsupermarket.constants.Constants;

public class LoginPage {
	WebDriver driver;
	WaitUtility waitutility;
	Properties properties = new Properties();
	@FindBy(xpath = "//b")
	WebElement loginHeadingWebElement;
	@FindBy(xpath = "//p[@class='login-box-msg']")
	WebElement signInStartText;
	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement userNameField;
	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordField;
	@FindBy(xpath = "//button[contains(text(),'Sign')]")
	private WebElement submitField;
	@FindBy(xpath = "//label[contains(text(),'Remember')]")
	private WebElement rememberMeField;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		waitutility = new WaitUtility(driver);
		PageFactory.initElements(driver, this);
		try {
			FileInputStream fileinputstream = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(fileinputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterUserName(String userName) {
		waitutility.waitElementForVisible(userNameField, 20);
		userNameField.sendKeys(userName);
	}


	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickOnSignInBtn() {
		waitutility.waitElementForClickable(submitField, 20);
		submitField.click();
	}

	public DashBoardPage login(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInBtn();
		return new DashBoardPage(driver);
	}

	public DashBoardPage login() {
	
		String userName = properties.getProperty("username");
		String password = properties.getProperty("password");
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInBtn();
		return new DashBoardPage(driver);
	}
	public String loginHeading()
	{
		return loginHeadingWebElement.getText();
	}
	public String siginstartHeading()
	{
		return signInStartText.getText();
	}

}
