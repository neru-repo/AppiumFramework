package org.wikiAutomation.TestUtils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.wikiAutomation.pageObjects.iOS.HomePage;
import org.wikiAutomation.utils.AppiumUtils;

import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class iOSBaseTestClass extends AppiumUtils {

	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void configureAppium() throws IOException {
		Properties prop = new Properties();
		FileInputStream FIS = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//org//wikiAutomation//resources//data.properties");
		prop.load(FIS);
		String ip = prop.getProperty("ipAddress");
		String port_num = prop.getProperty("port");
		service = startAppiumServer(ip, Integer.valueOf(port_num));

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 13 Pro");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//UIKitCatalog.app");
		//options.setApp("//Users//vignesh//git//AppiumCode//AppiumBeginners//src//test//java//resources//TestApp 3.app");
		options.setPlatformVersion("17.2");
		// this is for WebdriverAgent to kick started
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));	
		
		// initialize iOS drivers
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);

	}
	

	@AfterMethod
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
