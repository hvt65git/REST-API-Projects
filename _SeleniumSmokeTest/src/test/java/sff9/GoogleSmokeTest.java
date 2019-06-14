package sff9;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

interface Driver {
	public WebDriver getDriver();
}

enum DriverType implements Driver {	
	CHROME("Driver for Chrome Browser"){
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir" + "\\libs\\chromedriver.exe"));
			return new ChromeDriver();
		}
	},

	EDGE("Driver for Microsoft Edge Browser"){
		public WebDriver getDriver() {
			System.setProperty("webdriver.edge.driver", 
					System.getProperty("user.dir" + "\\libs\\MicrosoftWebDriver.exe"));
			return new EdgeDriver();
		}
	};

	private String description;

	public String getDescription() {
		return this.description;
	}

	private DriverType(String description) {
		this.description = description;
	}
}

class DriverFactory {
	private static final DriverType dt = DriverType.CHROME;
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
		tl.get().quit();
		tl.remove();
	}
}

class SeleniumBase extends DriverFactory {
	protected static long WAIT_TIME_OUT = 10; //secs

	protected static class TestCase<X> {
		private X[][] testdata;

		protected X[][] getTestData() {
			return this.testdata;
		}

		public TestCase(X[][] testdata) {
			this.testdata = testdata;
		}
	}
}

/*
 * Page Object Model implementation for GoogleMainPage
 */

class GoogleMainPage {
	//declare constants
	private static final long WAIT_TIME_OUT = 10; //secs
	private static final String XPATH = "//*[@name='q']";
	private static final String URL = "https://google.com";

	@FindBy(xpath = XPATH)
	WebElement txtSearchBox;

	public GoogleMainPage() {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	public void search(String term) {
		//wait for search box element to be present
		WebDriver driver = DriverFactory.getWebDriver();
		new WebDriverWait(driver, WAIT_TIME_OUT).
		until(ExpectedConditions.visibilityOf(txtSearchBox));

		//set the text and submit the search
		txtSearchBox.sendKeys(term);
		txtSearchBox.submit();
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	//declare globals
	private static TestCase<String> tc;
	
	public GoogleSmokeTest() {
		//create some test data and set it
		tc = new TestCase(new String[][] { 
			{"Seahawks"}, {"Storm"}, {"Sonics"}
			});
	}
	
	@Test(enabled = true, dataProvider = "testDataProvider")
	public void googleSmokeTest(String term) {
		//arrange - create test object
		
		//act - call the search method
		
		//assert - assert page title contains search term
		
	}

}




