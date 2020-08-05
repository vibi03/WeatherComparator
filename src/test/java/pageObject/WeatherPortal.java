package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserFactory;
import utilities.ElementActions;
import utilities.Waits;

public class WeatherPortal {

	private static WebDriver driver;
	
	@FindBy(how=How.ID,using="searchBox")
	private static WebElement city_SearchBox;
	
	@FindBy(how=How.XPATH, using="//span[@class='heading']/b")
	private static List<WebElement> completeWeatherDetails;
	
	private final static String city_Label= "//label[contains(text(),'City')]";  
	private final static String city_Checkbox="//input[@id='City']";
	private final static String weatherDetailsSection="//div[@title='City']";
	private static List<String> weatherInformation;
	private static String city;
	
	public static void searchAndSelectCity(String location) {
		
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, WeatherPortal.class);
		city=location;
		ElementActions.enterValue(city_SearchBox,location);
		Assert.assertEquals("Check City Label is visible", true, Waits.fluentWaitForElement(city_Label.replace("City", location)));
		System.out.println("Entered location");
		
		ElementActions.clickElement(city_Checkbox.replace("City", location));
		System.out.println("clicked check box");
	}
	
	public static void retrieveWeatherDetails() {
		try {
		driver=BrowserFactory.getWebDriver();
		PageFactory.initElements(driver, WeatherPortal.class);
		ElementActions.clickElement_JSExecutor(weatherDetailsSection.replace("City", city));
		weatherInformation= new ArrayList<>();
		
		for(int i=0;i<completeWeatherDetails.size();i++) {
			weatherInformation.add(ElementActions.getTextFromTextarea(completeWeatherDetails.get(i)));
		}
		System.out.println("Values- "+weatherInformation.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
