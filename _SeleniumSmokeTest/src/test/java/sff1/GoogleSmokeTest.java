package sff1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertTrue;

import sff1.SeleniumTest.TestCase;

import static sff1.DriverType.CHROME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface DriverConfig {
	public WebDriver getDriver() throws Exception;
}

enum DriverType implements DriverConfig {
	CHROME {
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + 
					"\\libs\\chromedriver.exe");
			return new ChromeDriver();
		}
	},
	FIREFOX {//not supported yet by current version of Selenium
		public WebDriver getDriver() {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + 
					"\\libs\\geckodriver.exe");
			return new ChromeDriver();
		}
	},
	EDGE {
		public WebDriver getDriver() throws Exception {
			new Exception("Edge webdriver not implemented yet!");
			return null;
		}
	};
}

class DriverFactory {
	//must init our static final ThreadLocal var here, cannot do it in a constructor
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>() ; 

	public static WebDriver getWebDriver() {
		return tl.get();
	}

	@BeforeMethod()
	public void createWebDriver() {
		try {
			long id = Thread.currentThread().getId();
			System.out.println("@BeforeMethod->createWebDriver->Current thread #: " + id);
			tl.set(DriverType.values()[0].getDriver());
		}
		catch(Exception e) {
			System.out.println("Could not create the WebDriver... " + e.getMessage());
			System.exit(0);
		}
	}

	@AfterMethod
	public void releaseWebDriver() {
		long id = Thread.currentThread().getId();
		System.out.println("@AfterMethod->releaseWebDriver->Current thread #: " + id);

		getWebDriver().quit();
		tl.remove(); //may not be necessary
	}

}

class SeleniumTest extends DriverFactory {
	protected static final long WAIT_TIMEOUT = 20; //secs
	
	protected class TestCase<X> {
		private X[][] testData;

		public X[][] getTestData() {
			return this.testData;
		}

		public void setTestData(X[][] testData) {
			this.testData = testData;
		}
	}
}

class GoogleMainPage{
	private static final String URL = "https://google.com";
	private static final String XPATH_SEARCHBOX = "//*[@name='q']";
	private static final long WAIT_TIMEOUT = 20; //secs

	@FindBy(xpath = XPATH_SEARCHBOX)
	WebElement searchBox;

	public GoogleMainPage(String term) {
		long id = Thread.currentThread().getId();
		System.out.println("GoogleMainPage->Search term: - " + term + " - Current thread #: " + id);

		//init elements
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);

		//launch google main page
		driver.get(URL);
	}

	public GoogleMainPage() {
		long id = Thread.currentThread().getId();
		System.out.println("GoogleMainPage->Current thread #: " + id);

		//init elements
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);

		//launch google main page
		driver.get(URL);
	}

	public void searchGoogleFor(String term) throws Exception {
		long id = Thread.currentThread().getId();
		System.out.println("searchGoogleFor->Search term: - " + term + " - Current thread #: " + id);

		//get webdriver for current thread
		WebDriver driver = DriverFactory.getWebDriver();

		//wait until google main page has been loaded
		new WebDriverWait(driver, WAIT_TIMEOUT).
		until(ExpectedConditions.visibilityOf(searchBox));

		//enter search term
		searchBox.sendKeys(term);
		searchBox.submit();
	}


}

public class GoogleSmokeTest extends SeleniumTest {
	private int n = 0;
	private final TestCase<String> tc = new TestCase<String>();

	public GoogleSmokeTest() {
		//set the test data for this test case
		tc.setTestData(new String[][] {
			{"seattle seahawks"},
			{"green bay packers"},
			{"seattle storm"}});
	}

	private void searchGoogleSmokeTest(String term) {
		try {
			long id = Thread.currentThread().getId();
			System.out.println("searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - " + term + " - Current thread #: " + id);

			//create page object - arrange
			GoogleMainPage g = new GoogleMainPage(term);

			//search for term - act
			g.searchGoogleFor(term);

			//verify result - assert

			//wait for search results page to appear then get the page title
			//maven project error: 
			//Lambda expressions are allowed only at source level 1.8 or above
			//i fixed the error added right JDK version section to pom.xml
			String pageTitle = new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIMEOUT).
					until(d->d.getTitle());

			//look for search term in the page title
			assertTrue(pageTitle.contains(term));

			id = Thread.currentThread().getId();
			System.out.println("searchGoogleSmokeTest after Assert ->Search term: - " + term + " - Current thread #: " + id);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	}


	@Test(dataProvider = "getTestData")
	public void test01(String s) {
		long id = Thread.currentThread().getId();
		System.out.println("\r\nCurrently executing a test for test data: - " + s + " - in thread#: " + id);
		searchGoogleSmokeTest(s);
	}

	@DataProvider(parallel = false ) 
	public String[][] getTestData() {
		return tc.getTestData();
	}

}

//OUTPUT - parallel = true
//[RemoteTestNG] detected TestNG version 6.14.2
//@BeforeMethod->createWebDriver->Current thread #: 13
//@BeforeMethod->createWebDriver->Current thread #: 14
//@BeforeMethod->createWebDriver->Current thread #: 15
//Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 26198
//Only local connections are allowed.
//Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 38216
//Only local connections are allowed.
//Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 29235
//Only local connections are allowed.
//Mar 21, 2018 11:09:08 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//Mar 21, 2018 11:09:08 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//Mar 21, 2018 11:09:08 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//
//Currently executing a test for test data: - seattle storm - in thread#: 15
//searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - seattle storm - Current thread #: 15
//GoogleMainPage->Search term: - seattle storm - Current thread #: 15
//
//Currently executing a test for test data: - seattle seahawks - in thread#: 13
//searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - seattle seahawks - Current thread #: 13
//GoogleMainPage->Search term: - seattle seahawks - Current thread #: 13
//
//Currently executing a test for test data: - green bay packers - in thread#: 14
//searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - green bay packers - Current thread #: 14
//GoogleMainPage->Search term: - green bay packers - Current thread #: 14
//searchGoogleFor->Search term: - seattle storm - Current thread #: 15
//searchGoogleFor->Search term: - green bay packers - Current thread #: 14
//searchGoogleFor->Search term: - seattle seahawks - Current thread #: 13
//searchGoogleSmokeTest after Assert ->Search term: - green bay packers - Current thread #: 14
//@@AfterMethod->releaseWebDriver->Current thread #: 14
//searchGoogleSmokeTest after Assert ->Search term: - seattle seahawks - Current thread #: 13
//@@AfterMethod->releaseWebDriver->Current thread #: 13
//searchGoogleSmokeTest after Assert ->Search term: - seattle storm - Current thread #: 15
//@@AfterMethod->releaseWebDriver->Current thread #: 15
//PASSED: test01("green bay packers")
//PASSED: test01("seattle seahawks")
//PASSED: test01("seattle storm")
//
//===============================================
//    Default test
//    Tests run: 3, Failures: 0, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 3, Failures: 0, Skips: 0

//OUTPUT parallel = false
//[RemoteTestNG] detected TestNG version 6.14.2
//@BeforeMethod->createWebDriver->Current thread #: 1
//Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 41400
//Only local connections are allowed.
//Mar 21, 2018 11:11:25 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//
//Currently executing a test for test data: - seattle seahawks - in thread#: 1
//searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - seattle seahawks - Current thread #: 1
//GoogleMainPage->Search term: - seattle seahawks - Current thread #: 1
//searchGoogleFor->Search term: - seattle seahawks - Current thread #: 1
//searchGoogleSmokeTest after Assert ->Search term: - seattle seahawks - Current thread #: 1
//@@AfterMethod->releaseWebDriver->Current thread #: 1
//@BeforeMethod->createWebDriver->Current thread #: 1
//Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 6970
//Only local connections are allowed.
//Mar 21, 2018 11:11:34 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//
//Currently executing a test for test data: - green bay packers - in thread#: 1
//searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - green bay packers - Current thread #: 1
//GoogleMainPage->Search term: - green bay packers - Current thread #: 1
//searchGoogleFor->Search term: - green bay packers - Current thread #: 1
//searchGoogleSmokeTest after Assert ->Search term: - green bay packers - Current thread #: 1
//@@AfterMethod->releaseWebDriver->Current thread #: 1
//@BeforeMethod->createWebDriver->Current thread #: 1
//Starting ChromeDriver 2.35.528161 (5b82f2d2aae0ca24b877009200ced9065a772e73) on port 7289
//Only local connections are allowed.
//Mar 21, 2018 11:11:43 PM org.openqa.selenium.remote.ProtocolHandshake createSession
//INFO: Detected dialect: OSS
//
//Currently executing a test for test data: - seattle storm - in thread#: 1
//searchGoogleSmokeTest before GoogleMainPage instantiation->Search term: - seattle storm - Current thread #: 1
//GoogleMainPage->Search term: - seattle storm - Current thread #: 1
//searchGoogleFor->Search term: - seattle storm - Current thread #: 1
//searchGoogleSmokeTest after Assert ->Search term: - seattle storm - Current thread #: 1
//@@AfterMethod->releaseWebDriver->Current thread #: 1
//PASSED: test01("seattle seahawks")
//PASSED: test01("green bay packers")
//PASSED: test01("seattle storm")
//
//===============================================
//    Default test
//    Tests run: 3, Failures: 0, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 3, Failures: 0, Skips: 0
//===============================================

