package org.wikiAutomation.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.wikiAutomation.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;

public class ProductsCatalog extends AndroidActions {
	
	AndroidDriver driver;
	
	public ProductsCatalog(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// (//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart'])
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart'])")
	private List<WebElement> products;
	
	// (//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])
	@FindBy(xpath="(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice'])")
	private List<WebElement> productPrice;
	
	
	// com.androidsample.generalstore:id/appbar_btn_cart
	@FindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;
	
	// Actions
	
	public void selectProducts(int index) {
		products.get(index).click();	
	}
	
	
	public float getPrice(float total, int index) {
		String price = productPrice.get(index).getText();
		price = price.replace("$", "");
		total += Double.valueOf(price);
		price = "";
		return total;
		
	}
	
	public CartPage addToCart() {
		cart.click();
		return new CartPage(driver);
	}
	
		
	
	

}
