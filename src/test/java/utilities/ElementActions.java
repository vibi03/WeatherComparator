package utilities;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {
	
	private static WebDriver driver;
	
	public static void clickElement(WebElement element){
		
		Assert.assertEquals("Verify Element is visible",true,Waits.fluentWaitForElement(element));
		element.click();		
	}

	public static void enterValue(WebElement element, String value) {
		
		Assert.assertEquals("Verify Element is visible",true, Waits.fluentWaitForElement(element));
		element.sendKeys(value);
	}
	
	public static void clickElement(String xpath) {
		driver=BrowserFactory.getWebDriver();
		WebElement element = driver.findElement(By.xpath(xpath));
		Assert.assertEquals("Verify Element is visible",true,Waits.fluentWaitForElement(element));
		element.click();
	}

	public static String getTextFromTextarea(WebElement element) {
		
		Assert.assertEquals("Verify Element is visible",true, Waits.fluentWaitForElement(element));
		return element.getText();
	}

	public static void clickElement_JSExecutor(String xpath){
		
		driver=BrowserFactory.getWebDriver();
		WebElement element = driver.findElement(By.xpath(xpath));
		Assert.assertEquals("Verify Element is visible",true,Waits.fluentWaitForElement(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
	}
}
