package com.sevenmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sevenmartsupermarket.utilities.GeneralUtility;
import com.sevenmartsupermarket.utilities.PageUtility;

public class ManageProductPage {
	WebDriver driver;
	GeneralUtility generalutility = new GeneralUtility();
	PageUtility pageutility;

	@FindBy(xpath = "//a[@onclick='click_button(1)']")
	WebElement newUserBtn;
	@FindBy(xpath = "//input[@placeholder='Enter the Title']")
	WebElement enterTitleFieldNew;
	@FindBy(xpath = "//label[text()='Product Type']//following::label[input[@value='Veg'] or input[@value='Nonveg'] or input[@value='Others']]")
	List<WebElement> productTypeRadioBtn;
	@FindBy(xpath = "//input[@name='tag']")
	WebElement enterTagName;
	@FindBy(xpath = "//select[@id = 'cat_id']")
	WebElement selectCategory;
	@FindBy(xpath = "//select[@id = 'sub_id']")
	WebElement selectSubCtaegory;
	@FindBy(xpath = "//select[@id = 'grp_id']")
	WebElement selectGroup;
	@FindBy(xpath = "//label[text()='Product Type']//following::label[input[@value='weight'] or  input[@value='piece'] or  input[@value='litre'] or input[@value='serves']]")
	List<WebElement> priceTypeRadioBtn;
	@FindBy(xpath = "//input[@id='m_weight']")
	WebElement enterWeight;
	@FindBy(xpath = "//select[@id='w_unit']")
	WebElement selectWeightUnit;
	@FindBy(xpath = "//input[@id='max_weight']")
	WebElement enterMaxQuantity;
	@FindBy(xpath = "//input[@id='w_price']")
	WebElement enterPrice;
	@FindBy(xpath = "//input[@id='w_mrp']")
	WebElement enterMRP;
	@FindBy(xpath = "//input[@id='w_stock']")
	WebElement enterStockAvailability;
	@FindBy(xpath = "//input[@id='w_pp']")
	WebElement enterPurchasePrice;
	@FindBy(xpath = "//input[@type='checkbox' and @name='unlimitw[]']")
	WebElement checkUnlimitedStock;
	@FindBy(xpath = "//div[@class='note-editable card-block']")
	WebElement descriptionField;
	@FindBy(xpath = "//label[text()='Stock']//following::input[@type='radio' and (@value='yes' or @value='no') and @name='stock']")
	WebElement stockRadioBtn;
	@FindBy(xpath = "//label[text()='Stock']//following::input[@type='radio' and (@value='yes' or @value='no') and @name='featured']")
	WebElement featureRadioBtn;
	@FindBy(xpath = "//label[text()='Stock']//following::input[@type='radio' and (@value='yes' or @value='no') and @name='combo']")
	WebElement comboRadioBtn;
	@FindBy(xpath = "//input[@id='main_img']")
	WebElement chooseFile;
	@FindBy(xpath = "//input[@id='main_imgs']")
	WebElement subimageChooseFile;
	@FindBy(xpath = "//span[@class='fas fa-trash-alt']/..")
	WebElement deleteImage;
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	WebElement saveButton;
	@FindBy(xpath = "//a[@class='btn btn-default btn-fix']")
	WebElement cancelButton;
	
	String path= "C:\\Users\\MaluL\\OneDrive\\Desktop\\chicken.png";
	String subpath= "C:\\Users\\MaluL\\OneDrive\\Desktop\\eggs.png";	
	

	public ManageProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageutility = new PageUtility(driver);
	}
	
	public void addNewProduct(String title,String tagName,String weight,String maxQuantity,
			String price,String mrp,String stockAvailable, String purchaseprice,
			String description)
	{
		enterTitleFieldNew.sendKeys(title);
		for(WebElement each : productTypeRadioBtn)
		{
			if(!each.isSelected())
			{
				each.click();
				break;
			}
		}
		enterTagName.sendKeys(tagName);
		pageutility.selectDropDownByIndex(selectCategory, 0);
		pageutility.selectDropDownByIndex(selectSubCtaegory, 0);
		pageutility.selectDropDownByValue(selectGroup, "134");
		for(WebElement each : priceTypeRadioBtn)
		{
			if(each.isSelected())
			{
				each.click();
				break;
			}
		}
		enterWeight.sendKeys(weight);
		pageutility.selectDropDownByIndex(selectWeightUnit, 1);
		enterMaxQuantity.sendKeys(maxQuantity);
		enterPrice.sendKeys(price);
		enterMRP.sendKeys(mrp);
		enterStockAvailability.sendKeys(stockAvailable);
		enterPurchasePrice.sendKeys(purchaseprice);
		checkUnlimitedStock.click();
		descriptionField.sendKeys(description);
		if(!stockRadioBtn.isSelected())
		{
			stockRadioBtn.click();
		}
		chooseFile.sendKeys(path);
		subimageChooseFile.sendKeys(subpath);
		if(!featureRadioBtn.isSelected())
		{
			featureRadioBtn.click();
		}
		if(!comboRadioBtn.isSelected())
		{
			comboRadioBtn.click();
		}
	}
}
