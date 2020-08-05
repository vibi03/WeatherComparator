package functional;


import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.PropertyReader;
import utilities.ProtocolEnum;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Assert;

public class RestRequestGenerator {
	
	private static RequestSpecification request;
	private static Response response;
	private static String serviceName;
	
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

	public static void checkNodes(DataTable value) 
	{		
		List<Map<String, String>> data = value.asMaps(String.class, String.class);
		for(Map<String, String>map:data)
		{
			if(map.get("status").equalsIgnoreCase("NotNull"))
			{
				Assert.assertEquals(true, !(response.jsonPath().get("nlp_result").equals(null)));
			}
		}
	}

	public static void verifyStatus(String statusCode) 
	{
		Assert.assertEquals(Integer.parseInt(statusCode),response.getStatusCode());
				
	}

	
	

}
