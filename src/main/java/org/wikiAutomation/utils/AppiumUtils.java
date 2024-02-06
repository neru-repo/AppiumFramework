package org.wikiAutomation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		// Start Appium Server Using Code. The AppiumJS is a file and we give the path of appium main file. IP address and Ports are constant
		service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium/build//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();

		// starts the server
		service.start();
		return service;
	}
	 
	
	public void waitForElement(WebElement ele, String attr1, String text, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.attributeContains((ele), attr1 , text));
		
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String,String>>>() {
			
		});
		
		return data;

	}
	
	
	public String getScreenshot(String testcaseName, AppiumDriver driver) throws IOException {
		String destination = System.getProperty("user.dir")+"//reports"+testcaseName+".png"; 
		File source = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	
	
	
}
