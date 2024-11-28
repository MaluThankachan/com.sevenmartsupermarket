package com.sevenmartsupermarket.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {
	WebDriver driver;
	Actions actions;
	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	public void selectDropDownByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void selectDropDownByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void selectDropDownByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void scrollIntoElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void doubleClickMouseAction(WebElement element) {

		actions.doubleClick(element).build().perform();
	}

	public void jsClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public boolean isClicked(WebElement element) {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollAndClick(WebElement element) {
		int index = 0;
		while (!isClicked(element)) {
			js.executeScript("window.scrollBy(0," + index + ")");
			index = index + 2;
		}
	}
	
}
