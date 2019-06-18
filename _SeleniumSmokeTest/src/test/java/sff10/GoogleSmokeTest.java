package sff10;

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

/*
 * 
 */
interface Driver {
	public WebDriver getDriver();
}

/*
 * 
 */
enum DriverType implements Driver {
	CHROME{
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return new ChromeDriver();
		}
	},
	EDGE{
		public WebDriver getDriver() {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\libs\\MicrosoftWebDriver.exe");
			return new ChromeDriver();
		}
	}
}

/*
 * 
 */
class DriverFactory {
	private static DriverType dt = DriverType.CHROME;
	private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();

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

/*
 * 
 */
class SeleniumBase extends DriverFactory {
	protected final int WAIT_TIME_OUT = 10; //seconds

	protected static class TestCase<X> {
		private X[][] testData;

		public TestCase(X[][] testData) {
			this.testData = testData;
		}

		public X[][] getTestData() {
			return this.testData;
		}
	}
}


/*
 * Page Object Model - GoogleMainPage
 */

class GoogleMainPage {
	//declare constants
	private static final int WAIT_TIME_OUT = 10; //seconds
	private static final String XPATH = "//*[@name = 'q']";
	private static final String URL = "https://google.com";

	@FindBy(xpath = XPATH)
	WebElement txtSearchBox;

	public GoogleMainPage() {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	public void search(String term) {
		//wait for the element to appear
		WebDriver driver = DriverFactory.getWebDriver();
		new WebDriverWait(driver, WAIT_TIME_OUT )
		.until(ExpectedConditions.visibilityOf(txtSearchBox));

		//send the text and submit the search
		txtSearchBox.sendKeys(term);
		txtSearchBox.submit();
	}
}


public class GoogleSmokeTest extends SeleniumBase {
	TestCase<String> tc;

	public GoogleSmokeTest() {
		//set the test data for this test
		tc = new TestCase<>(new String[][] {
			{"seattle seahawks"}, 
			{"seattle storm"}, 
			{"seattle sounders"}
			});

	}

	@Test(dataProvider = "getTestData", enabled = true)
	public void googleSmokeTest(String term) {
		try {
			//arrange testing object(s)
			GoogleMainPage gp = new GoogleMainPage();
			
			//act - search
			gp.search(term);
			
			//assert - verify current page contains search term in the page title
			Assert.assertTrue(new WebDriverWait(getWebDriver(), WAIT_TIME_OUT)
					.until(ExpectedConditions.titleContains(term)));
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@DataProvider(parallel = true)
	public String[][] getTestData() {
		return tc.getTestData();
	}
}












