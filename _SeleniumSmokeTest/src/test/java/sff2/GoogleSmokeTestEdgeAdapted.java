package sff2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static sff2.DriverType.CHROME;
import static sff2.DriverType.EDGE;


interface DriverSetup {
	public WebDriver getDriver();
}

enum DriverType implements DriverSetup {
	CHROME {
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return new ChromeDriver();
		}
	},
	FIREFOX {
		public WebDriver getDriver() {
			System.setProperty("webdriver.gecko.driver", 
					System.getProperty("user.dir") + "\\libs\\geckodriver.exe");
			return new FirefoxDriver();
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
	protected static DriverType dt = EDGE;
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();

	DriverFactory() {
		System.out.println("***** In DriverFactory constructor *****");
	}

	@BeforeMethod 
	//using @BeforeMethod instead of @BeforeTest since we are using multithreaded dataProvider 
	public void createWebDriver() {
		System.out.println("***** @BeforeMethod - In DriverFactory constructor *****");
		tl.set(dt.getDriver());
	}

	@AfterMethod
	//using @AfterMethod instead of @AfterTest since we are using multithreaded dataProvider 
	public void releaseWebDriver() {
		System.out.println("***** @AfterMethod - In DriverFactory constructor *****");
		tl.get().quit();
		tl.remove();
	}

	public static WebDriver getWebDriver() {
		return tl.get();
	}
}


class SeleniumTestCase extends DriverFactory {
	protected final long WAIT_TIMEOUT = 20; //sec

	SeleniumTestCase() {
		System.out.println("***** In SeleniumTestCase constructor *****");
	}

	class TestCase<X> {
		private X[][] testData;
		
		TestCase() {
			System.out.println("***** In TestCase constructor *****");
		}

		public X[][] getTestData() {
			return testData;
		}

		public void setTestData(X[][] testData) {
			this.testData = testData;
		}
	}
}

//Page Object Model implementation for Google main page
//uses:
//PageFactory.initElements()
//@FindBy
//and in the calling program: 

class GoogleMainPage {
	private final long WAIT_TIMEOUT = 20; //sec
	private final String URL = "https://google.com";
	private final String XPATH_SEARCH_TXT_BOX = "//*[@name = 'q']";

	@FindBy(xpath = XPATH_SEARCH_TXT_BOX)
	WebElement txtBox;

	public GoogleMainPage() {

		System.out.println("***** In GoogleMainPage constructor *****");

		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	public void searchGoogle(String term) throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();

		//wait for txtBox to be visible and present
		new WebDriverWait(driver, WAIT_TIMEOUT).
		until(ExpectedConditions.visibilityOf(txtBox));

		//set the txt and submit search
		txtBox.sendKeys(term);
		txtBox.submit();
	}
}



public class GoogleSmokeTestEdgeAdapted extends SeleniumTestCase {
	private final TestCase<String> tc = new TestCase<>();

	public GoogleSmokeTestEdgeAdapted() {
		System.out.println("***** In GoogleSmokeTest constructor *****");
		
		tc.setTestData(new String[][] {
			{"seahawks"},
			{"storm"},
			{"mariners"},
			{"sounders"}
		});
	}

	private void searchGoogle(String term) {

		try {
			System.out.println("*** In searchGoogle - For term: " + term 
					+ ", currentThreadId = " + Thread.currentThread().getId());

			//arrange - create test object
			GoogleMainPage gp = new GoogleMainPage();

			//act - invoke method
			gp.searchGoogle(term);

			//EDGE driver fails here seems to be a timing issue, as  it succeeds
			//in the debugger - yes, that was the problem
			//need to do Thread.sleep for Edge
			String pageTitle = new WebDriverWait(getWebDriver(), WAIT_TIMEOUT).
					until(d->d.getTitle());
			
			//Edge driver bug:
			//known Edge bug, workaround is to use explicit waits
			//https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/14557371/
			
			String driverName = DriverFactory.dt.toString();
			if(driverName.equals("EDGE")) {
				Thread.sleep(1500);
				pageTitle = DriverFactory.getWebDriver().getTitle();
				System.out.println("DEBUG: term = " + term);
				System.out.println("DEBUG: pageTitle = " + pageTitle);
			}

			if(pageTitle.contains(term)) {
				System.out.println("DEBUG: pageTitle contains term");
			}
			else {
				System.out.println("DEBUG: pageTitle does not contain term");
			}
			
			//assert - verify current page contains term in title
			Assert.assertTrue(pageTitle.contains(term));
			System.out.println("DEBUG: searchGoogle was successful for term: " + term);
		}
		catch(Exception e) {
			System.out.println("DEBUG: searchGoogle was NOT successful for term: " + term);
			e.getMessage();
			Assert.fail();
		}

	}

	@Test(dataProvider = "testdataProvider")
	public void googleSmokeTest(String term) {
		searchGoogle(term);
		System.out.println("*** In googleSmokeTest - For term: " + term 
				+ ", currentThreadId = " + Thread.currentThread().getId());
	}

	//Edge driver bug: parallel does not work
	@DataProvider(parallel = false) 
	public String[][] testdataProvider() {
		return tc.getTestData();
	}

}

//OUTPUT FOR EDGE BROWSER:
//Edge browser Page Title has different implementation than Chrome does
//[RemoteTestNG] detected TestNG version 6.14.2
//***** In DriverFactory constructor *****
//***** In DriverFactory constructor *****
//***** In SeleniumTestCase constructor *****
//***** In TestCase constructor *****
//***** In GoogleSmokeTest constructor *****
//***** In DriverFactory constructor *****
//***** In SeleniumTestCase constructor *****
//***** @BeforeMethod - In DriverFactory constructor *****
//[21:24:36.655] - Listening on http://localhost:27523/ 
//
//May 17, 2019 9:24:38 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//*** In searchGoogle - For term: sounders, currentThreadId = 1
//***** In GoogleMainPage constructor *****
//DEBUG: term = sounders
//DEBUG: pageTitle = Google
//DEBUG: pageTitle does not contain term
//***** @AfterMethod - In DriverFactory constructor *****
//[21:24:40.635] - Stopping server.
//
//FAILED: googleSmokeTest("sounders")
//java.lang.AssertionError: expected [true] but found [false]
