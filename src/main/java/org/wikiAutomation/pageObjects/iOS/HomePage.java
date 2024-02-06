package org.wikiAutomation.pageObjects.iOS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.wikiAutomation.utils.iOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class HomePage extends iOSActions {
	
	public IOSDriver driver;

	public HomePage(IOSDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	// driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		@iOSXCUITFindBy(accessibility="Alert Views")
		private WebElement alertViews;
	
	
	// Actions
	
	public AlertPage clickAlertViews() {
		alertViews.click();
		return new AlertPage(driver);
		
	}

}
