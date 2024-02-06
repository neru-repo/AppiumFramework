package org.wikiAutomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.wikiAutomation.TestUtils.iOSBaseTestClass;
import org.wikiAutomation.pageObjects.iOS.AlertPage;


public class IOSBasics extends iOSBaseTestClass {
	
	@Test
	
	public void iOSBasicTest() {
		
		// iOS locators - xpath, className, IOSPredicateString, iOSClassChain, accessibility id, id
		
		AlertPage alertPage = homePage.clickAlertViews();
		alertPage.textEntryOption();
		alertPage.enterText("Harry");
		alertPage.clickOnOkay();
		alertPage.clickConfirmOrCancel();
		String alert_message = alertPage.confirmOrCancelHeader();
		System.out.println(alert_message);
		AssertJUnit.assertEquals(alert_message, "A message should be a short, complete sentence.");
		alertPage.clickCancelButton();
		
		// IOSPredicateString uses any matching string
		// we can concatenate multiple matching strings using AND
		// regex like BEGINSWITH and ENDSWITH can be used. BEGINSWITH[c]/ENDSWITH[c] makes it case-sensitive
		
		
	}

}
