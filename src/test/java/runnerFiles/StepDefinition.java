package runnerFiles;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.LaunchURL;
import pageObject.WeatherPortal;
import utilities.BrowserFactory;

public class StepDefinition {

	@Before
	public void initialize(Scenario scenario) throws IOException
	{
		System.out.println("Scenario name is - "+scenario.getName());
		BrowserFactory.setWebDriver();
	}
	
	@Given("User able to launch NDTV website")
	public void launchWebsite() throws IOException
	{
		LaunchURL.launchNDTV();
		
	}
	
	@Then("Navigate to weather section")
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
		WeatherPortal.retrieveWeatherDetails(location);
	}
	
	@After
	  public void afterMethod(Scenario scenario) throws IOException { 
					  
		//BrowserFactory.closeWebDriver();
					System.out.println(
					  "-------------------------------------------------------------------------------"
					  ); System.out.println(scenario.getName()+" :-----" + scenario.getStatus());
					  System.out.println(
							  "-------------------------------------------------------------------------------"
							  );
		  
	  }
}
