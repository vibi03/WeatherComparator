package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileHandlers 
{

	private static File file;
	private static String filePath;
	
	public static void writeToFile(List<String> weatherInformation) throws IOException
	{
		filePath=System.getProperty("user.dir")+PropertyReader.readProperty("Configuration", "filepath")+"weatherDetails.txt";
		System.out.println("File Pathe- "+filePath); 
		file = new File(filePath);
	        if (!file.exists()) {
	            file.createNewFile();
	        } 
		FileWriter writer = new FileWriter(filePath); 
		for(String str: weatherInformation) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
	}	
	
	public static List<String> readFile() throws IOException
	{
	
		List<String> fileData = Collections.emptyList();  
		filePath=System.getProperty("user.dir")+PropertyReader.readProperty("Configuration", "filepath")+"weatherDetails.txt";
	    fileData =  Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);  
	    return fileData; 
	}
}
