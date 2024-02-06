package org.wikiAutomation.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.wikiAutomation.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class CartPage extends AndroidActions {
	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// com.androidsample.generalstore:id/totalAmountLbl
	@FindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalPrice;
	
	// driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")
	@FindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement toolbarTitle;
	
	// com.androidsample.generalstore:id/termsButton
	@FindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditions;
	
	// //android.widget.Button[@text='CLOSE']
	@FindBy(xpath="//android.widget.Button[@text='CLOSE']")
	private WebElement termsAndConditionsClose;
	
	
	// android.widget.CheckBox
	@FindBy(className="android.widget.CheckBox")
	private WebElement checkbox;
	
	// com.androidsample.generalstore:id/btnProceed
	@FindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedToCheckout;
	
	// Actions
	
	public String getTotalPrice() {
		String totalCartAmount = totalPrice.getText();
		totalCartAmount = totalCartAmount.replace("$", "");
		totalCartAmount = totalCartAmount.trim();
		return totalCartAmount;
	}
	
	public void waitForEle() {
		waitForElement(toolbarTitle, "text", "Cart", driver);
	}
	
	public void openTermsAndConditions() {
		longPressAction(termsAndConditions);
	}
	
	public void closeTermsAndConditions() {
		termsAndConditionsClose.click();
	}
	
	public void clickCheckbox() {
		checkbox.click();
	}
	
	public void proceedToCheckout() {
		proceedToCheckout.click();
	}

}
