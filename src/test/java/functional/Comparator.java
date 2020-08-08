package functional;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilities.FileHandlers;
import utilities.PropertyReader;

public class Comparator {

	private static List<String> data;
	private static Logger log=LoggerFactory.getLogger(Comparator.class);
	
	/*
	 * Compares weather data from open weather API and NDTV website with a variance
	 * mentioned in external properties file
	 */
	public static void compareWeather() throws IOException, NullPointerException
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
				log.info("Temperature from WebApp {}", temperature_webApp);
				log.info("Temperature from API {}", temperature_api);
				Assert.assertEquals("Checking weather difference is within variance range",true,
						temperature_webApp-temperature_api>=-variance && temperature_webApp-temperature_api<=variance);
			}
		}	
	}
}
