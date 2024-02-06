package org.wikiAutomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.wikiAutomation.TestUtils.AndroidBaseTestClass;
import org.wikiAutomation.pageObjects.android.CartPage;
import org.wikiAutomation.pageObjects.android.ProductsCatalog;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.SupportsContextSwitching;

public class ECom_TestCase_3 extends AndroidBaseTestClass {

	
	@Test(dataProvider="getData")

	public void totalCartAmount(HashMap<String, String> input) throws InterruptedException {
				
		formPage.selectCountry(input.get("country"));
		formPage.sendKeys(input.get("name"));
		ProductsCatalog productsCatalog = formPage.clickSubmit();
		
		productsCatalog.selectProducts(0);
		productsCatalog.selectProducts(1);
		float total = 0;
		total = productsCatalog.getPrice(total, 0);
		total = productsCatalog.getPrice(total, 1);
		System.out.println(total);
		
		CartPage cartPage = productsCatalog.addToCart();
		String totalCartAmount = cartPage.getTotalPrice();
		System.out.println(totalCartAmount);
		AssertJUnit.assertEquals(totalCartAmount, String.valueOf(total));
		
		cartPage.openTermsAndConditions();
		cartPage.closeTermsAndConditions();
		cartPage.clickCheckbox();
		cartPage.proceedToCheckout();
		Thread.sleep(20000);

		Set<String> contexts = ((SupportsContextSwitching) driver).getContextHandles();
		for (String contextName : contexts) {
			System.out.println(contextName);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys(input.get("searchWord"));
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		System.getProperty("user.dir")+"//src//test//java//org//wikiAutomation//testData//eCommerce.json
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//org//wikiAutomation//testData//eCommerce.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
