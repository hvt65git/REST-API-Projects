package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;


public class PayloadGenerator {
//	private static Logger log = LogManager.getLogger(PayloadGenerator.class.getName());

	public static String generatePayLoadStringFromFile(String filePath){
		
	//	log.info("Inside PayloadConverter function");
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} 
		catch (Exception e) {
		//	log.error(e);
			return null;
		}
	}
}
