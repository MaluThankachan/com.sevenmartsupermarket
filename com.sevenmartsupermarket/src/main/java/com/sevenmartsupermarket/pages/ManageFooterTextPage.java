package com.sevenmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;
import com.sevenmartsupermarket.utilities.WaitUtility;

public class ManageFooterTextPage {
	WebDriver driver;
	PageUtility pageutility;
	GeneralUtility generalutility = new GeneralUtility();
	WaitUtility waitutility ;
	
	@FindBy(xpath = "//h1[text()='Footer Text']]")
	WebElement manageFooterTitle;
	@FindBy(xpath = "//table//tbody//tr//td[1]")
	List<WebElement> allTableContactNames;
	@FindBy(xpath = "//input[@name='phone']")
	WebElement editphoneField;
	@FindBy(xpath = "//button[@name='Update']")
	WebElement editUpdateBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement editAlertSuccess;
	
	public ManageFooterTextPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
		waitutility = new WaitUtility(driver);
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
		
		WebElement editAction = driver.findElement(By.xpath("//table//tbody//tr["+index+"]//td[4]"));
		waitutility.waitElementForClickable(editAction, 20);
		editAction.click();
		editphoneField.clear();
		editphoneField.sendKeys("1111111111");
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
