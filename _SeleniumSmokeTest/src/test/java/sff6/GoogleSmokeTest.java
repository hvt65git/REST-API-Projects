package sff6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sff6.SeleniumBase.TestCase;

import static sff6.DriverType.CHROME;




interface Driver {
	public WebDriver getDriver();
}

enum DriverType implements Driver {
	CHROME {
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return new ChromeDriver();
		}
	},
	EDGE {
		public WebDriver getDriver() {
			System.setProperty("webdriver.edge.driver", 
					System.getProperty("user.dir") + "\\libs\\MicrosoftWebDriver.exe");
			return new EdgeDriver();
		}	
	}
}

class DriverFactory {
	private static final DriverType dt = CHROME;
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<>();

	@BeforeMethod
	public void beforeEachTest() {
		//create and set the web driver into thread manager and launch browser window
		tl.set(dt.getDriver());
	}

	public static WebDriver getWebDriver() {
		return tl.get();
	}

	@AfterMethod
	public void afterEachTest() {
		//close browser window and clean up thread
		getWebDriver().quit();
		tl.remove();
	}
}

class SeleniumBase extends DriverFactory {
	protected static final long WAIT_TIME_OUT = 10; //seconds

	  class TestCase<X> {
		private String name;
		private X[][] testdata;

		protected TestCase(String name, X[][] testdata) {
			this.name = name;
			this.testdata = testdata;
		}

		protected String getName() {
			return name;
		}

		protected X[][] getTestdata() {
			return testdata;
		}
	}
}

/*
 * GoogleMainPage - implements Selenium Page Object Model
 */
class GoogleMainPage {
	private final long WAIT_TIMEOUT = 10; //secs
	private final String URL = "https://google.com";
	private static final String XPATH_TXT_BOX = "//*[@name='q']";

	@FindBy(xpath = XPATH_TXT_BOX)
	private WebElement search_box;

	public GoogleMainPage() {
		//get driver for current browser window
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	public void search(String term) {
		//get driver for current browser window
		WebDriver driver = DriverFactory.getWebDriver();

		//wait for the search_box to appear
		new WebDriverWait(driver, WAIT_TIMEOUT).
		until(ExpectedConditions.visibilityOf(search_box));

		//enter the search term in the search_box 
		search_box.sendKeys(term);
		search_box.submit();
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	private final TestCase<String> tc;
	

	public GoogleSmokeTest() {
		//create some test data
		tc = new TestCase<>("ht selenium framework demo", 
				new String[][] {{"seahawks"}, {"sounders"},{"storm"}, {"lakers"}
		} );
	}

	@DataProvider(parallel = true)
	public String[][] testDataProvider() {
		return tc.getTestdata();
	}
	
	@Test(dataProvider = "testDataProvider")
	public void googleSmokeTest(String term) {
		try {
			//arrange
			GoogleMainPage gp = new GoogleMainPage();
			
			//act
			gp.search(term);
			
			//assert that we expect to find search term in the page title
			Assert.assertTrue( new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT )
					.until(ExpectedConditions.titleContains(term))
				);
		}
		catch(Exception e) {
			Assert.fail();
			System.out.println(e.getMessage());
		}
	}
}




class Testing123 {
	SeleniumBase.TestCase<String> tc2; 
	
	public static void main(String... arg) {
		//create a TestCase object
		SeleniumBase sb = new SeleniumBase();
		TestCase<String> tc2 = sb.new TestCase<>(null, null);
	}
}
