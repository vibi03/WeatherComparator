package pageObject;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utilities.BrowserFactory;
import utilities.PropertyReader;

public class LaunchURL {

	private static WebDriver driver;
	
	/*
	 * Retrieves NDTV website URL from properties file and launches URL in chrome
	 * browser
	 */
	public static void launchNDTV() throws IOException 
	{	
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, LaunchURL.class);
		driver.get(PropertyReader.readProperty("Configuration", "ndtvWebsiteURL"));
	}

}
