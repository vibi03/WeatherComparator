package utilities;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
	
	private static WebDriver driver;
	
	/* Waits for element to be visible,clickable and clicks on the Webelement when condition is met*/ 
	public static void clickElement(WebElement element){
		
		Assert.assertEquals("Verify Element is visible",true,Waits.fluentWaitForElementToBeClickable(element));
		element.click();		
	}

	/* Waits for element to be visible and once element is visible, enters value passed to the Webelement */
	public static void enterValue(WebElement element, String value) {
		
		Assert.assertEquals("Verify Element is visible",true, Waits.fluentWaitForElement(element));
		element.sendKeys(value);
	}
	
	/*
	 * Waits for element to be visible, clickable and clicks on the element when condition is met
	 * Parameter passed is Xpath
	 */
	public static void clickElement(String xpath) {
		driver=BrowserFactory.getWebDriver();
		WebElement element = driver.findElement(By.xpath(xpath));
		Assert.assertEquals("Verify Element is visible",true,Waits.fluentWaitForElement(element));
		element.click();
	}

	/* Waits for element to be visible and once element is visible, gets text available in text area */
	public static String getTextFromTextarea(WebElement element) {
		
		Assert.assertEquals("Verify Element is visible",true, Waits.fluentWaitForElement(element));
		return element.getText();
	}

	/*
	 * Waits for element to be visible, clickable and clicks on the element using
	 * JSexecutor once element is visible
	 */
	public static void clickElement_JSExecutor(String xpath){
		
		driver=BrowserFactory.getWebDriver();
		WebElement element = driver.findElement(By.xpath(xpath));
		Assert.assertEquals("Verify Element is visible",true,Waits.fluentWaitForElement(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
	}
}
