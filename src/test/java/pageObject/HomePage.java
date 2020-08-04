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
	
	public static void verifyHomePage() {
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		Assert.assertEquals("Verify Title of page",true, driver.getTitle().contains("NDTV"));
	}

	public static void rejectAlertNotification() {
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		ElementActions.clickElement(alert_RejectButton);
	}
	
	public static void openSubMenu(){
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		ElementActions.clickElement(subMenu);
	}
	
	public static void openWeatherSection() {
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, HomePage.class);
		ElementActions.clickElement(weatherSection);
	}
	
}
