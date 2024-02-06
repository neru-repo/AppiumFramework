package org.wikiAutomation.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.wikiAutomation.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class FormPage extends AndroidActions {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	@FindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement dropdownField;
	
	
	// //android.widget.TextView[@text='Your Name']
	@FindBy(xpath="//android.widget.TextView[@text='Your Name']")
	private WebElement nameHeaderField;
	
	// //android.widget.RadioButton[@text='Female']
	@FindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleRadioButton;
	
	// driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("VG");
	@FindBy(className="android.widget.EditText")	
	private WebElement nameField;
	
	// driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	@FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letUsShopBtn;
	
	// //android.widget.Toast
	@FindBy(xpath="//android.widget.Toast")
	private WebElement errorBanner;
	
	// Actions part
	
	public void clickDropdown() {
		dropdownField.click();
	}
	
	public void clickFemaleRadioButton() {
		femaleRadioButton.click();
	}
	
	public void sendKeys(String text) {
		nameField.sendKeys(text);
		driver.hideKeyboard();
	}
	
	public ProductsCatalog clickSubmit() {
		letUsShopBtn.click();
		return new ProductsCatalog(driver);
	}
	
	public String getErrorbannerMessage(String attributeName) {
		return errorBanner.getAttribute(attributeName);
	}
	
	public void selectCountry(String countryName) {
		dropdownField.click();
		scrollWithTargetElement(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}
	
	public String getNameHeader() {
		return nameHeaderField.getText();
	}
}

