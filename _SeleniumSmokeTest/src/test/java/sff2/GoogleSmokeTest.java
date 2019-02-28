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
import static sff2.DriverType.FIREFOX;
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
			return new EdgeDriver();
		}
	}
}

class DriverFactory {
	private DriverType dt = CHROME;
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();

	@BeforeMethod
	public WebDriver createWebDriver() {
		tl.set(dt.getDriver());
		return tl.get();
	}

	@AfterMethod
	public void releaseWebDriver() {
		tl.get().quit();
		tl.remove();
	}

	public static WebDriver getWebDriver() {
		return tl.get();
	}
}


class SeleniumTestCase extends DriverFactory {
	protected final long WAIT_TIMEOUT = 20; //sec

	class TestCase<X> {
		private X[][] testData;

		public X[][] getTestData() {
			return testData;
		}

		public void setTestData(X[][] testData) {
			this.testData = testData;
		}
	}
}

class GoogleMainPage {
	private final long WAIT_TIMEOUT = 20; //sec
	private final String URL = "https://google.com";
	private final String XPATH_SEARCH_TXT_BOX = "//*[@name = 'q']";

	@FindBy(xpath = XPATH_SEARCH_TXT_BOX)
	WebElement txtBox;

	public GoogleMainPage() {
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



public class GoogleSmokeTest extends SeleniumTestCase {
	private final TestCase<String> tc = new TestCase<>();

	public GoogleSmokeTest() {
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

			//assert - verify current page contains term in title
			String pageTitle = new WebDriverWait(getWebDriver(), WAIT_TIMEOUT).
					until(d->d.getTitle());

			Assert.assertTrue(pageTitle.contains(term));

		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	@Test(dataProvider = "testdataProvider")
	public void googleSmokeTest(String term) {
		searchGoogle(term);
		System.out.println("*** In googleSmokeTest - For term: " + term 
				+ ", currentThreadId = " + Thread.currentThread().getId());
	}

	@DataProvider(parallel = true) 
	public String[][] testdataProvider() {
		return tc.getTestData();
	}

}
