package sff3;

import org.openqa.selenium.WebDriver;

interface DriverConfig {
	public WebDriver getWebDriver();
}

enum DriverType implements DriverConfig {
	CHROME {
		public WebDriver getWebDriver() {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return null;
		}
	},
	FIREFOX {
		public WebDriver getWebDriver() {
			//not implemented yet
			return null;
		}
	},
	EDGE {
		public WebDriver getWebDriver() {
			//not implemented yet
			return null;
		}
	}

}

public class GoogleSmokeTest {

}
