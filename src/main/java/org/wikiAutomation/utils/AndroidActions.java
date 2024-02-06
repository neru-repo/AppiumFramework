package org.wikiAutomation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}
	
	
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ele),
				"duration", 4000);
	}

	public void scrollWithTargetElement(String tar_ele) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+tar_ele+"\"));"));
	}

	public void scrollWithoutTargetElement(String locator) {
		boolean canScrollMore;
		boolean ele_visible;

		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
			try {

				ele_visible = driver.findElement(By.xpath(locator)).isDisplayed();
				System.out.println(ele_visible);

			}

			catch(Exception e) {

				ele_visible = false;
			}

			if (ele_visible) {

				break;

			}

			else {

				continue;

			}


		} while(canScrollMore);
	}

	public void swipeAction(WebElement ele, String direction, double percent) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "direction", direction,
			    "percent", percent
			));
	}

}
