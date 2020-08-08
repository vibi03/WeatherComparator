package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	private static Properties prop = new Properties();
	
	/* Returns value mentioned in properties file for particular property */
	public static String readProperty(String fileName, String propertyName) throws IOException
	{
		InputStream input = new FileInputStream("src/test/resources/properties/"+fileName+".properties");
		prop.load(input);		
		if(prop.getProperty(propertyName)!= null) return prop.getProperty(propertyName);
		else throw new RuntimeException("Property key - " + propertyName+" not found in properties file - "+fileName);		
	
	}
	
}
