package com.sevenmartsupermarket.utilities;

import java.time.Duration;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	WebDriverWait explicitwait;
	FluentWait<WebDriver> fluentwait;

	public WaitUtility(WebDriver driver) {
		this.driver =  driver;
		
	}
	
	public void waitElementForClickable(WebElement element, long time) //dynamic passing of time
	{
		explicitwait = new WebDriverWait(driver, Duration.ofSeconds(time));
		explicitwait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitElementForVisible(WebElement element, long time)
	{
		fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(6))
				.ignoring(ElementNotInteractableException.class);
		
		fluentwait.until(ExpectedConditions.visibilityOf(element));
	}
	

}
