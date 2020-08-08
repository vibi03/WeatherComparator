package pageObject;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserFactory;
import utilities.ElementActions;

public class HomePage {

	private static WebDriver driver;
	
	@FindBy(how=How.XPATH,using="//a[contains(text(),'No Thanks')]")
	private static WebElement alert_RejectButton;
	
	@FindBy(how=How.ID,using="h_sub_menu")
	private static WebElement subMenu;
	
	@FindBy(how=How.XPATH,using="//a[contains(text(),'WEATHER')]")
	private static WebElement weatherSection;
	
	/* Retrives and verifies titile from home page */
	public static void verifyHomePage() {
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		Assert.assertEquals("Verify Title of page",true, driver.getTitle().contains("NDTV"));
	}

	/* Rejects the alert notification thrown by NDTV home page */
	public static void rejectAlertNotification() {
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		ElementActions.clickElement(alert_RejectButton);
	}
	
	/* Opens sub menu option from the home page */ 
	public static void openSubMenu(){
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		ElementActions.clickElement(subMenu);
	}
	
	/* Opens Weather portal from home page using sub menu option */ 
	public static void openWeatherSection() {
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		ElementActions.clickElement(weatherSection);
	}
	
}
