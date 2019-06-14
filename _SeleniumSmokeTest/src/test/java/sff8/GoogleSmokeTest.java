package sff8;

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

import static sff8.DriverType.CHROME;

interface Driver {
	public WebDriver getDriver();
}

enum DriverType implements Driver {
	CHROME("Implements Selenium ChromeDriver"){
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", //wcd
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe");
			return new ChromeDriver();
		}
	},
	EDGE("Implements Selenium EdgeDriver"){
		public WebDriver getDriver() {
			System.setProperty("webdriver.edge.driver", //wcd
					System.getProperty("user.dir") + "\\libs\\MicrosoftWebDriver.exe");
			return new EdgeDriver();
		}
	};

	private String description;

	public String getDescription() {
		return this.description;
	}

	private DriverType(String description) {
		this.description  = description;
	}
}

abstract class DriverFactory {
	private final DriverType dt = CHROME;
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
	protected static long WAIT_TIME_OUT = 10; //seconds

	protected static class TestCase<X> {
		private X[][] testdata;

		public X[][] getTestdata() {
			return this.testdata;
		}

		public TestCase(X[][] testdata) {
			this.testdata = testdata;
		}
	}
}

//Page Object Model implementation
class GoogleMainPage {
	//declare constants
	private static final String URL = "https://google.com";
	private static final String XPATH = "//*[@name = 'q']";
	private static final long WAIT_TIME_OUT = 10; //seconds

	@FindBy(xpath = XPATH)
	WebElement searchBox;

	public GoogleMainPage() {
		//get driver
		WebDriver driver = DriverFactory.getWebDriver();

		//init Page Object
		PageFactory.initElements(driver, this);

		//launch webpage
		driver.get(URL);
	}

	//declare exported search method
	public void search(String term) {
		//get driver
		WebDriver driver = DriverFactory.getWebDriver();

		//wait for search box to be visible
		new WebDriverWait(driver, WAIT_TIME_OUT).
		until(ExpectedConditions.visibilityOf(searchBox));

		//set the text and submit the search
		searchBox.sendKeys(term);
		searchBox.submit();
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	//declare and init constants
	private static final TestCase<String> testCase = new TestCase<String>(new String[][] {
		{"seahawks"}, {"storm"}});

	@Test(dataProvider = "testDataProvider")
	public void googleSmokeTest(String term) {
		try {
			//arrange
			GoogleMainPage gp = new GoogleMainPage();

			//act
			gp.search(term);

			//assert the page title contains the search term
			Assert.assertTrue(
					new WebDriverWait(getWebDriver(), WAIT_TIME_OUT).
					until(ExpectedConditions.titleContains(term))
					);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@DataProvider(parallel = false)
	public String[][] testDataProvider() {
		return testCase.getTestdata();
	}
}




