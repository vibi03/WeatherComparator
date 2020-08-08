package utilities;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

protected static  WebDriver driver;
	
	/*
	 * Sets driver to chrome driver instance using Webdriver manager,
	 * Sets chrome options like maximizing at browser startup sets, implicit wait time
	 */
	public static void setWebDriver() 
	{
		
		  WebDriverManager.chromedriver().setup(); 
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("start-maximized");
		  options.setPageLoadStrategy(PageLoadStrategy.NORMAL); 
		  driver = new  ChromeDriver(options);
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	/* Returns driver instance */
	public static WebDriver getWebDriver()
	{
		return driver;
		
	}
	
	/* closes current driver instance */
	public static void closeWebDriver()
	{
		driver.close();
	}

}
