package com.sevenmartsupermarket.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

public class GeneralUtility {
	public String get_Attribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	public String getCssProperty(WebElement element, String attribute) {
		return element.getCssValue(attribute);
	}

	public List<String> getTextOfElements(List<WebElement> elements) {
		List<String> data = new ArrayList<String>();
		for (WebElement element : elements) {
			data.add(element.getText());
		}
		return data;
	}
	public String getHeadings(WebElement element)
	{
		return element.getText();
	}

	public static String getRandomName() {
		Faker faker = new Faker();
		return faker.name().fullName();
	}

	public static String getRandomAddress() {
		Faker faker = new Faker();
		return faker.address().fullAddress();
	}

	public static String getTimeStamp() {
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		return timeStamp;

	}
}
