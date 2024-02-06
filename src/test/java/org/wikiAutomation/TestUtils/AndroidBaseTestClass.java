package org.wikiAutomation.TestUtils;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.wikiAutomation.pageObjects.android.FormPage;
import org.wikiAutomation.utils.AppiumUtils;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTestClass extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream FIS = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//wikiAutomation//resources//data.properties");
		prop.load(FIS);
		String ip = System.getProperty("ipAddress")!= null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		//String ip = prop.getProperty("ipAddress");
		String port_num = prop.getProperty("port");
		String deviceName = prop.getProperty("AndroidDeviceName");
		service = startAppiumServer(ip, Integer.valueOf(port_num));
		// This is used to set the Desired Capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		// To run on a real device, give a generic device name like below
		//options.setDeviceName("Android Device");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);

	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()),
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

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
