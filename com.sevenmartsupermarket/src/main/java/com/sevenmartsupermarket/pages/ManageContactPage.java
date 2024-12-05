package com.sevenmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class ManageContactPage {
	WebDriver driver;
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();
	
	@FindBy(xpath = "//h1[text()='Contact Us']")
	private WebElement manageContactTitle;
	@FindBy(xpath = "//table//tbody//tr//td[1]")
	private List<WebElement> allTableContactNames;
	@FindBy(xpath = "//input[@name='phone']")
	private WebElement editPhoneField;
	@FindBy(xpath = "//input[@name='email']")
	private WebElement gmailField;
	@FindBy(xpath = "//label//following-sibling::textarea[@placeholder='Enter the Address']")
	private WebElement editAddressField;
	@FindBy(xpath = "//textarea[@name='del_time']")
	private WebElement timeField;
	@FindBy(xpath = "//input[@name='del_limit']")
	private WebElement limitField;
	@FindBy(xpath = "//button[@name='Update']")
	private WebElement editUpdateBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement editAlertSuccess;

	
	
	public ManageContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
	}
	
	public String EditContact(String contactName) {
		List<String> allTableNames = generalutility.getTextOfElements(allTableContactNames);
		int index = 0;
		for (index = 0; index < allTableNames.size(); index++) {
			if (contactName.equals(allTableNames.get(index))) {
				System.out.println(allTableNames.get(index));
				index++;
				break;
			}
		}

		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr[\"+index+\"]//td[6]//a"));
		pageutility.jsClick(editAction);
		editAddressField.clear();
		editAddressField.sendKeys("InfoPark,Cochin");
		pageutility.scrollAndClick(editUpdateBtn);
		return getEditAlertMsg();
		
		
	}
	
	public String EditContactUsingDataProvider(String phnum, String gmail ,String address,String time,
			String limit) {
		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr[\"+index+\"]//td[6]//a"));
		pageutility.jsClick(editAction);
		editPhoneField.clear();
		editPhoneField.sendKeys(phnum);
		gmailField.clear();
		gmailField.sendKeys(gmail);		
		editAddressField.clear();
		editAddressField.sendKeys(address);
		timeField.clear();
		timeField.sendKeys(time);
		limitField.clear();
		limitField.sendKeys(limit);		
		pageutility.scrollAndClick(editUpdateBtn);
		return getEditAlertMsg();
		
		
	}
	public String getEditAlertMsg() {
		String alertMessage = editAlertSuccess.getText().substring(2, 8);
		String message = editAlertSuccess.getText().substring(9);
		String actualMsg = alertMessage + message;
		System.out.println(actualMsg);
		return actualMsg;
	}
}
