package functional;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyReader;
import utilities.ProtocolEnum;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class RestRequestGenerator {
	
	private static RequestSpecification request;
	private static Response response;
	private static String serviceName;
	protected static double nodeValue;
	private static Logger log=LoggerFactory.getLogger(RestRequestGenerator.class);
	
	/* To set service name to retrieve properties */  
	public static void setServiceName(String serviceLabel) {
		
		serviceName=serviceLabel;
		
	}
	
	/* To retrieve base URI from properties file based on protocol and set in request */
	public static void setBaseURI(String protocol) throws IOException
	{
		
		if(protocol.equalsIgnoreCase("secured"))
		{
			RestAssured.baseURI = ProtocolEnum.SECURED.getProtocol() + "://"+ PropertyReader.readProperty("Configuration", serviceName+"_BaseURI");
		}
		else
		{
			RestAssured.baseURI = ProtocolEnum.NON_SECURED.getProtocol() + "://"+ PropertyReader.readProperty("Configuration", serviceName+"_BaseURI");
		} 
		  
	}
	
	/* To retrieve base path from properties file and set it to request */ 
	public static void setBasePath() throws IOException
	{
		RestAssured.basePath=PropertyReader.readProperty("Configuration", serviceName+"_BasePath");
	}
	
	/* Hits get request with the query parameters retrieved from properties file */
	public static void sendGetRequest(String city) throws IOException
	{
		request = RestAssured.given(); 
		request.log().all();
	    response=request.queryParam("q", city) 
                .queryParam("appid", PropertyReader.readProperty("Configuration", serviceName+"_Key")) 
                .get(PropertyReader.readProperty("Configuration", serviceName+"_EndPoint"));
		log.info("Response received is {}"+ response.getBody().asString());
	   
	}

	/* Retrives particular node value from json response */
	public static void checkNode(String node) throws NumberFormatException
	{		
			nodeValue=response.jsonPath().getDouble("main."+node);
			System.out.println("Node value - "+nodeValue);	
	}
	
	/* Returns Node value saved already */
	public static int getNodeValue() 
	{
		return (int)nodeValue;
	}

	/* Verifies status code in the response */
	public static void verifyStatus(String statusCode) 
	{
		Assert.assertEquals(Integer.parseInt(statusCode),response.getStatusCode());
				
	}

	/*
	 * Asserts response from service is matching with schema available in resource
	 * package
	 */ 
	public static void validateResponce()
	{
		try
		{
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
			      .setValidationConfiguration(
			        ValidationConfiguration.newBuilder()
			          .setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
			            .freeze();
			    response.then().assertThat()
			      .body(matchesJsonSchemaInClasspath("WeatherResponseSchema.json")
			        .using(jsonSchemaFactory));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
