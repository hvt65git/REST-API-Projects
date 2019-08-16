package MAVENModule._SeleniumAdactinTests.system;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import MAVENModule._SeleniumAdactinTests.utils.Utils;
import static MAVENModule._SeleniumAdactinTests.system.DriverType.*;

public class DriverFactory {
	private DriverType dt = CHROME;
	
	private static ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>(){
		public WebDriver initialize() {
			return null;
		}
	};
	
	private DriverType getDefaultDriverType(String s) {
			switch(s) {
			default:
			case "CHROME": 
				dt = CHROME;
				break;
			case "FIREFOX":
				dt = FIREFOX;
				break;
			}
			return dt;
	}
	
	public static WebDriver getWebDriver() {
		return tl.get();
	}

	private void setWebDriver(WebDriver driver) {
		tl.set(driver);
	}

	@BeforeTest
	@Parameters({"driver-type"})
	public WebDriver createWebDriver(@Optional("CHROME") String driverType) throws Exception {
		//get default driver type from testng.xml file
		dt = getDefaultDriverType(driverType);
		
		//create the driver and set it in the ThreadLocal object
		setWebDriver(dt.getDriver()); 
		return getWebDriver();
	}

	@AfterTest
	public void releaseWebDriver() {	
		getWebDriver().quit();
		tl.remove();
	}
}