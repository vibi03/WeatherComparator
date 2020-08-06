package runnerFiles;

import java.io.IOException;

import functional.Comparator;
import functional.RestRequestGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition_API {

	@Given("User set URI for {string} service with protocol as {string}")
	public void setEndPoint(String serviceLabel, String protocol) throws IOException
	{
		RestRequestGenerator.setServiceName(serviceLabel);
		RestRequestGenerator.setBaseURI(protocol);
		RestRequestGenerator.setBasePath();
	}
	
	@When("User hit the get request with city as {string}")
	public void hitRequest(String city) throws IOException
	{
		RestRequestGenerator.sendGetRequest(city);
	}
	
	@Then("Service should return status code {string}")
	public void checkStatusCode(String statusCode)
	{
		RestRequestGenerator.verifyStatus(statusCode);
	}
	
	@And("Check whether response has the node {string}")
	public void verifyNodes(String node) throws NullPointerException
	{
		RestRequestGenerator.checkNode(node);
	}
	
	@Then("Validate response with schema")
	public void validateSchema() throws NullPointerException
	{
		RestRequestGenerator.validateResponce();
	}
	
	@Then("Verify weather against weather from NDTV weather data")
	public void weatherComparator() throws IOException,NumberFormatException
	{
		Comparator.compareWeather();
	}
}
