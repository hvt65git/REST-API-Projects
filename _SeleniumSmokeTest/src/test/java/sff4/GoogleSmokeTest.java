package sff4;

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

import static sff4.DriverType.CHROME;

interface Driver {
	public WebDriver getDriver();
}

enum DriverType implements Driver {
	CHROME{
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return new ChromeDriver();
		}
	},
	EDGE {
		public WebDriver getDriver() {
			System.setProperty("webdriver.edge.driver", 
					System.getProperty("user.dir") + "\\libs\\edgedriver.exe");
			return new EdgeDriver();
		}
	}
}

class DriverFactory {
	private static final DriverType dt = CHROME;
	private static final ThreadLocal<WebDriver> tl = new ThreadLocal<>();

	public static WebDriver getWebDriver() {
		return tl.get();
	}

	@BeforeMethod
	protected void createWebDriver() {
		tl.set(dt.getDriver());
	}

	@AfterMethod
	protected void releaseWebDriver() {
		getWebDriver().quit();
		tl.remove();
	}
}




class SeleniumBase extends DriverFactory {
	protected final long WAIT_TIME_OUT = 10; //seconds

	class TestCase<X> {
		private X[][] testdata;
		private String name;

		public TestCase(X[][] testdata, String name) {
			this.testdata = testdata;
			this.name = name;
		}

		public X[][] getTestData() {
			return this.testdata;
		}

		public String getName() {
			return this.name;
		}
	}
}

class GoogleMainPage {
	private int testCount = 0;
	private static final long WAIT_TIME_OUT = 10; //seconds
	private static final String URL = "https://google.com";
	private static final String XPATH_SEARCHBOX = "//*[@name='q']";

	@FindBy(xpath = XPATH_SEARCHBOX)
	WebElement txtSearchBox;

	public GoogleMainPage() {
		//get webdriver
		WebDriver driver = DriverFactory.getWebDriver();

		//init page
		PageFactory.initElements(driver,this);

		//launch webpage
		driver.get(URL);
	}

	//exported method
	public void searchGoogle(String term) {
		//get webdriver
		WebDriver driver = DriverFactory.getWebDriver();

		//wait for page to load
		new WebDriverWait(driver,WAIT_TIME_OUT).
		until(ExpectedConditions.visibilityOf(txtSearchBox));

		//set the search term and submit
		txtSearchBox.sendKeys(term);
		txtSearchBox.submit();
	}
}

//note: class SeleniumBase extends the HT Selenium testing framework
public class GoogleSmokeTest extends SeleniumBase {
	private final TestCase<String> tc;

	GoogleSmokeTest() {
		String name = "HT Selenium framework example";
		String[][] testdata = {
				{"seahawks"},
				{"mariners"}
		};
		tc = new TestCase<String>(testdata, name);
	}

	@Test(dataProvider="testdataProvider")
	public void googleSmokeTest(String term) {
		try {
			//announce 
			System.out.println("Test: " + tc.getName() + " in progress...");
			System.out.println("for test data: " + term + ".");
			
			//arrange
			GoogleMainPage gp = new GoogleMainPage();

			//act
			gp.searchGoogle(term);

			//assert

			//wait for the page title of the current web page to
			//contain the expected value
			Assert.assertTrue(new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
					.until(ExpectedConditions.titleContains(term))
					);

		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@DataProvider(parallel = false)
	public String[][] testdataProvider() {
		return this.tc.getTestData();
	}
}
