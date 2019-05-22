package sff3_yay_ht_back_on_track_2019;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static sff3_yay_ht_back_on_track_2019.DriverType.CHROME;

import java.util.List;

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

abstract class DriverFactory {
	private DriverType dt = CHROME;
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<>();

	public static WebDriver getWebDriver() {
		return tl.get();
	}

	@BeforeMethod
	protected void createWeDriver() {
		tl.set(dt.getDriver());
	}

	@AfterMethod
	protected void releaseWebDriver() {
		getWebDriver().quit();
		tl.remove();
	}
}

abstract class SeleniumBase extends DriverFactory {
	protected static final long WAIT_TIME_OUT = 10; //seconds

	protected static class TestCase<X> {
		private X[][] tc;
		private String description;

		public TestCase(String description) {
			this.description = description;
		}

		public String getDescription() {
			return this.description;
		}

		protected void setTestCase(X[][] tc) {
			this.tc = tc;
		}

		protected X[][] getTestCase() {
			return this.tc;
		}
	}

	protected static class TestSuite<X> {
		private List<TestCase<X>> testSuite;

		protected TestCase<X> getTestCase() {
			if(this.testSuite.isEmpty()) {
				System.out.println("There are no tests specified for this test suite");
				return null;
			}
			else {
				return this.testSuite.get(0);
			}
		}

		protected void addTestCaseToSuite(TestCase<X> tc) {
			this.testSuite.add(tc);
		}
	}
}
/*
 * Create a class for the Google main page and use the
 * Page Object Model with PageFactory.initElements()
 * and @FindBy(xpath) annotation
 */
class GoogleMainPage {
	private static final String URL = "https://google.com";
	private static final long WAIT_TIME_OUT = 10; //seconds

	@FindBy(xpath = "//*[@name='q']")
	private WebElement searchBoxElement;

	public GoogleMainPage() {
		//init the page object
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);

		//launch web page
		driver.get(URL);
	}

	/*
	 * This is the exported method for searching the page
	 * for the desired search term
	 */
	public void searchGoogle(String term) {
		//wait for the visibility of what we need
		//to do the search, which is the searchBox web element
		WebDriver driver = DriverFactory.getWebDriver();
		new WebDriverWait(driver, WAIT_TIME_OUT).
		until(ExpectedConditions.visibilityOf(searchBoxElement));

		//send the search term to the web element search box
		searchBoxElement.sendKeys(term);

		//submit the search
		searchBoxElement.submit();
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	private static TestCase<String> tc;

	public GoogleSmokeTest() {
		//set some test data;
		tc = new TestCase<>("google search test");
		tc.setTestCase(new String[][] {
			{"seattle seahawks"}, 
			{"yoga"}, 
			{"guru"}
		});
	}

	@Test(dataProvider = "getTestData")
	public void googleSearchTest(String term){
		try {
			//note: our webdriver has been set in the ThreadLocal object
			//in the @BeforeMethod call that happens for this test

			//arrange - create a GoogleMainPage object

			//launch GoogleMainPage
			GoogleMainPage gp = new GoogleMainPage();

			//act - call GoogleMainPage object exported search method
			gp.searchGoogle(term);

			//wait for the search results page to appear - wait until
			//we can get the page title
			//note: For EdgeDriver we will need to do an explicit wait
			String pageTitle = 
					new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
					.until(d->d.getTitle());
			
			//assert - verify search term can be found on current web page
			assertTrue(pageTitle.contains(term));
			
			//note: in the subsequent @AfterMethod call, the current web page
			//will be closed and its driver will be freed in the ThreadLocal object
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			fail();
		}
	} 

	@DataProvider(parallel = false)
	public String[][] getTestData() {
		return tc.getTestCase();
	}
}
