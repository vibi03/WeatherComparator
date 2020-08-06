package functional;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import utilities.FileHandlers;
import utilities.PropertyReader;

public class Comparator {

	private static List<String> data;
	
	public static void compareWeather() throws IOException
	{
		try
		{
		data=FileHandlers.readFile();
		for (String listValues : data)
		{
			if(listValues.contains("Temp in Degrees"))
			{
				String[] finalValues=listValues.split(":");
				int temperature_webApp=Integer.parseInt(finalValues[1].trim());
				int variance=Integer.parseInt(PropertyReader.readProperty("Configuration", "variance"));
				int temperature_api=(int) ((RestRequestGenerator.getNodeValue())-273.15);
				System.out.println("Temperature from WebApp- "+temperature_webApp);
				System.out.println("Temperature from API- "+temperature_api);
				Assert.assertEquals("Checking weather difference is within variance range",true,
						temperature_webApp-temperature_api>=-variance && temperature_webApp-temperature_api<=variance);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
