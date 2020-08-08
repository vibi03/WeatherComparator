package runnerFiles;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.LaunchURL;
import pageObject.WeatherPortal;
import utilities.BrowserFactory;

public class StepDefinition_WebApp {

	@Before
	public void initialize(Scenario scenario) 
	{
		System.out.println("Scenario name is -- "+scenario.getName());
		
	}
	
	@Given("User able to launch NDTV website")
	public void launchWebsite() throws IOException
	{
		BrowserFactory.setWebDriver();
		LaunchURL.launchNDTV();
		
	}
	
	@And("Navigate to weather section")
	public void navigateWeatherSection()
	{
		HomePage.verifyHomePage();
		HomePage.rejectAlertNotification();
		HomePage.openSubMenu();
		HomePage.openWeatherSection();
	}
	
	@When("User provide location as {string}")
	public void enterLocation(String location)
	{
		WeatherPortal.searchAndSelectCity(location);
		
	}
	
	@Then("Weather details should get displayed")
	public void getWeatherDetails()
	{
		WeatherPortal.retrieveWeatherDetails();
		
	}
	
	@And("Save temperature details in external file")
	public void saveTemperature() throws IOException
	{
		WeatherPortal.saveTemperatureDetails();	
	}
	
	@Then("Close the browser")
	public void closeBrowser()
	{
		BrowserFactory.closeWebDriver();
	}
	
	@After
	  public void afterMethod(Scenario scenario) throws IOException { 
					  
					System.out.println(
					  "-------------------------------------------------------------------------------"
					  ); System.out.println(scenario.getName()+" :-----" + scenario.getStatus());
					  System.out.println(
							  "-------------------------------------------------------------------------------"
							  );
		  
	  }
}
