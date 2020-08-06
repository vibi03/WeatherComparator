package functional;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyReader;
import utilities.ProtocolEnum;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;
import org.junit.Assert;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class RestRequestGenerator {
	
	private static RequestSpecification request;
	private static Response response;
	private static String serviceName;
	protected static double nodeValue;
	
	public static void setServiceName(String serviceLabel) {
		
		serviceName=serviceLabel;
		
	}
	
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
	
	public static void setBasePath() throws IOException
	{
		RestAssured.basePath=PropertyReader.readProperty("Configuration", serviceName+"_BasePath");
	}
	
	public static void sendGetRequest(String city) throws IOException
	{
		request = RestAssured.given(); 
		System.out.println(request.log().all());
	    response=request.queryParam("q", city) 
                .queryParam("appid", PropertyReader.readProperty("Configuration", serviceName+"_Key")) 
                .get(PropertyReader.readProperty("Configuration", serviceName+"_EndPoint"));
		System.out.println("Response received is -"+ response.getBody().asString());
	   
	}

	public static void checkNode(String node) 
	{		
		
		try
		{

			nodeValue=response.jsonPath().getDouble("main."+node);
			System.out.println("Node value - "+nodeValue);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public static int getNodeValue()
	{
		return (int)nodeValue;
	}

	public static void verifyStatus(String statusCode) 
	{
		Assert.assertEquals(Integer.parseInt(statusCode),response.getStatusCode());
				
	}

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
