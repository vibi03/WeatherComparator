package utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Waits {

	private static WebDriver driver;
	
	/*
	 * Receives Webelement and waits for element to be visible till given span of
	 * time
	 */
	public static boolean fluentWaitForElement(WebElement element)
	{
		try
		{
			driver = BrowserFactory.getWebDriver();
			
			 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(Duration.ofSeconds(20))
				       .pollingEvery(Duration.ofSeconds(2))
				       .ignoring(NoSuchElementException.class)
				       .ignoring(ElementNotVisibleException.class)
				       .ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element)); 	   
			return true;
			
		}catch(Exception e)
		{
			System.out.println("Element not found");
			e.printStackTrace();
			return false;
		}
		
	}
	

	/*
	 * Receives Webelement and waits for element to be visible and clickable till
	 * given span of time
	 */
	public static boolean fluentWaitForElementToBeClickable(WebElement element)
	{
		try
		{
			driver = BrowserFactory.getWebDriver();
			
			 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(Duration.ofSeconds(20))
				       .pollingEvery(Duration.ofSeconds(2))
				       .ignoring(NoSuchElementException.class)
				       .ignoring(ElementNotVisibleException.class)
				       .ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
			
		}catch(Exception e)
		{
			System.out.println("Element not found");
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * Receives xpath of element and waits for element to be visible and clickable till
	 * given span of time
	 */
	public static boolean fluentWaitForElement(String xpath)
	{
		try
		{
			driver = BrowserFactory.getWebDriver();
			
			 Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				       .withTimeout(Duration.ofSeconds(20))
				       .pollingEvery(Duration.ofSeconds(2))
				       .ignoring(NoSuchElementException.class)
				       .ignoring(ElementNotVisibleException.class)
				       .ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 	   
			return true;
			
		}catch(Exception e)
		{
			System.out.println("Element not found");
			e.printStackTrace();
			return false;
		}
		
	}
}
