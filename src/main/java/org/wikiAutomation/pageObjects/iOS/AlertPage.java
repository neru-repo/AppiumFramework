package org.wikiAutomation.pageObjects.iOS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.wikiAutomation.utils.iOSActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertPage extends iOSActions {
	
	public IOSDriver driver;

	public AlertPage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
//  driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")).click();
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`name == 'Text Entry'`]")
	private WebElement textEntry;
	
//	driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeAlert[`name == 'A Short Title Is Best'`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]")).sendKeys("Harry");
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeAlert[`name == 'A Short Title Is Best'`]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]")
	private WebElement enterText;
	
//	driver.findElement(AppiumBy.accessibilityId("OK")).click();
	@iOSXCUITFindBy(accessibility="OK")
	private WebElement okElement;
	
//  driver.findElement(AppiumBy.iOSNsPredicateString("name == 'Confirm / Cancel'")).click();
	@iOSXCUITFindBy(iOSNsPredicate="name == 'Confirm / Cancel'")
	private WebElement confirmOrCancel;
	
//	driver.findElement(AppiumBy.iOSNsPredicateString("value CONTAINS 'A message'")).getText();
	@iOSXCUITFindBy(iOSNsPredicate="value CONTAINS 'A message'")
	private WebElement confirmOrCancelHeader;
	
//	driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Cancel'")).click();
	@iOSXCUITFindBy(iOSNsPredicate="label == 'Cancel'")
	private WebElement cancelButton;
	
//  Actions
	
	public void textEntryOption() {
		textEntry.click();
	}
	
	public void enterText(String text) {
		enterText.sendKeys(text);
	}
	
	public void clickOnOkay() {
		okElement.click();
	}
	
	public void clickConfirmOrCancel() {
		confirmOrCancel.click();
	}
	
	public String confirmOrCancelHeader() {
		return confirmOrCancelHeader.getText();
	}
	
	public void clickCancelButton() {
		cancelButton.click();
	}

}
