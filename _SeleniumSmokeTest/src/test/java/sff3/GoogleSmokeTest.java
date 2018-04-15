package sff3;

import org.openqa.selenium.WebDriver;

interface DriverConfig {
	public WebDriver getWebDriver();
}

enum DriverType implements DriverConfig {
	CHROME{
		public WebDriver getWebDriver() {
			return null;
		}
	},
	FIREFOX{
		public WebDriver getWebDriver() {
			return null;
		}
	},
	EDGE {
		public WebDriver getWebDriver() {
			return null;
		}
	}

}

public class GoogleSmokeTest {

}
