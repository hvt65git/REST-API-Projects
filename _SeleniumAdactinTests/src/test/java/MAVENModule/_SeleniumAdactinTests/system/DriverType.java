package MAVENModule._SeleniumAdactinTests.system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum DriverType implements Driver {
	CHROME{
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return new ChromeDriver();

		}
	},
	FIREFOX{
		public WebDriver getDriver() {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\libs\\geckodriver.exe");
			return new FirefoxDriver();

		}	
	}
}