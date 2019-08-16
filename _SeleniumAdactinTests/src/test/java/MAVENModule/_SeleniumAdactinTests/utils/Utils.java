package MAVENModule._SeleniumAdactinTests.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	private static String defaultTimeout = "20"; //sec

	public static String getSettingFromFile(String setting) {
		String ret = null;
		try {
			Properties props = new Properties();
			props.load(new FileInputStream(System.getProperty("user.dir") + "\\settings\\UIMap.properties"));
			ret = props.getProperty(setting);
		}
		catch(Exception e) {
			return defaultTimeout;
		}
		return ret;
	}

	public static String[][] getTestDataFromExcelFile(String excelFilePath, String worksheet) throws Exception {
		int rows = 0, cols = 0;
		String[][] testData = null;


		//load data from excel sheet and get the rows and cols so we can dim our object array
		ExcelReader reader = new ExcelReader(excelFilePath);
		rows = reader.getSheetRows(worksheet);
		cols = reader.getSheetColumns(worksheet);

		//get data - skip first row of headers
		testData = new String[rows-1][cols]; 
		for(int i=0; i<cols; i++){ 
			for(int j=0; j<rows-1; j++){
				testData[j][i] = reader.getCellData(worksheet, i, j+1);
			}
		}	
		return testData;
	}
}
