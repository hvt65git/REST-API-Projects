package sffb9;

import static sffb9.DriverType.CHROME;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverFactory {
	private static final DriverType dt = CHROME;
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<>();
	
	public static WebDriver getWebDriver() {
		return tl.get();
	}
	
	@BeforeMethod
	public void createWebDriver() {
		tl.set(dt.getDriver());
	}
	
	@AfterMethod
	public void releaseWebDriver() {
		getWebDriver().quit();
		tl.remove();
	}

}
