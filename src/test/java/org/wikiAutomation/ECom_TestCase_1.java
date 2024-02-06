package org.wikiAutomation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.AssertJUnit;
import org.wikiAutomation.TestUtils.AndroidBaseTestClass;
import org.wikiAutomation.pageObjects.android.ProductsCatalog;

import com.google.common.collect.ImmutableMap;


public class ECom_TestCase_1 extends AndroidBaseTestClass {
	
	
//	@BeforeMethod
//	public void preSetup() {
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.Main"));
//	}
//	
	@Test (priority=0, groups= {"Smoke"})

	public void fillFormWithoutName() throws InterruptedException {
		
		formPage.selectCountry("Argentina");
		formPage.clickFemaleRadioButton();
		ProductsCatalog productsCatalog = formPage.clickSubmit();
		String toast_message = formPage.getErrorbannerMessage("name");
		AssertJUnit.assertEquals(toast_message, "Please enter your name");
		Thread.sleep(2000);
	}
	
	@Test (priority=1, groups= {"Smoke"})

	public void fillForm() throws InterruptedException {
		formPage.selectCountry("Argentina");
		String name_header = formPage.getNameHeader();
		AssertJUnit.assertEquals(name_header, "Your Name");
		formPage.sendKeys("VG");
		formPage.clickFemaleRadioButton();
		ProductsCatalog productsCatalog = formPage.clickSubmit();
		Thread.sleep(2000);

	}


	

}
