package MAVENModule._SeleniumAdactinTests.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import MAVENModule._SeleniumAdactinTests.utils.ExcelReader;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

public class SeleniumTest extends DriverFactory {
	private long WAIT_TIME_OUT;

	protected SeleniumTest()  {
		try {
			WAIT_TIME_OUT = Integer.parseInt(getSettingFromFile("WAIT_TIME_OUT"));
		}
		catch(Exception e) {
			WAIT_TIME_OUT = 20; //set default - seconds
		}
	}
	public static class TestCase {
		private String name;
		private ExcelTestData xtd;

		public ExcelTestData getXtd() {
			return xtd;
		}
		public void setXtd(ExcelTestData xtd) {
			this.xtd = xtd;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public class ExcelTestData {
			private String[][] testData;
			private String excelFilePath;
			private String worksheetName;

			public ExcelTestData(String excelFilePath, String worksheetName) {
				this.excelFilePath = excelFilePath;
				this.worksheetName = worksheetName;
			}
			public String[][] getTestData() {
				return this.testData;
			}

			public String getTestDataElement(int row, int col) {
				return testData[row][col];
			}

			public void loadTestDataFromExcelFile() throws Exception {
				int rows = 0, cols = 0;

				//load data from excel sheet and get the rows and cols so we can dim our object array
				ExcelReader reader = new ExcelReader(excelFilePath);
				rows = reader.getSheetRows(worksheetName);
				cols = reader.getSheetColumns(worksheetName);

				//get data - skip first row of headers
				this.testData = new String[rows-1][cols]; 
				for(int i=0; i<cols; i++){ 
					for(int j=0; j<rows-1; j++){
						this.testData[j][i] = reader.getCellData(worksheetName, i, j+1); //skip first row of headers here
					}
				}
			}
		}
	}

	protected long getWaitTimeout() {
		return WAIT_TIME_OUT;
	}

	protected String WaitAndGetAttribute(String xpathElement, String attribute) {
		WebElement temp  = new WebDriverWait(getWebDriver(), WAIT_TIME_OUT)
		.until(d->d.findElement(By.xpath(xpathElement)));
		return temp.getAttribute(attribute);
	}

	protected String getCurrenttAttributeValue(By by) throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();
		WebElement element = driver.findElement(by);
		return element.getAttribute("value");
	}
	
	protected String getSettingFromFile(String setting)	throws FileNotFoundException, IOException {
		String file = System.getProperty("user.dir") + "\\settings\\UIMap.properties";
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		return props.getProperty(setting);
	}

}
