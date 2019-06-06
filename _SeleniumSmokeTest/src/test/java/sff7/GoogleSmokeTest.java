package sff7;

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

import static sff7.DriverType.CHROME;
import static sff7.DriverType.EDGE;


interface DriverConfig {
	public WebDriver getDriver();
}

enum DriverType implements DriverConfig {
	CHROME{
		public WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "\\libs\\chromedriver.exe" );
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
	protected static final long WAIT_TIME_OUT = 10; //seconds

	protected static class TestCase<X> {
		private String name;
		private X[][] testdata;

		protected TestCase(String name, X[][] testdata) {
			this.testdata = testdata;
			this.name = name;
		}

		protected X[][] getTestData() {
			return this.testdata;
		}

		protected String getName() {
			return this.name;
		}
	}
}
/*
 * implement GoogleMainPage using Page Object Model  - 
 * @FindBy annotation and PageFactory.initElements(...)
 */
class GoogleMainPage {
	//declare constants
	private static final long WAIT_TIME_OUT = 10; //seconds
	private static final String URL = "https://google.com";
	private static final String XPATH_SEARCHBOX = "//*[@name='q']";

	@FindBy(xpath = XPATH_SEARCHBOX)
	private WebElement txtSearchBox;

	public GoogleMainPage() {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	//exported method:
	public void search(String term) {
		//wait for web element txtSearchBox to appear
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT).
		until(ExpectedConditions.visibilityOf(txtSearchBox));

		//send the search term to the txtSearchBox and press submit
		txtSearchBox.sendKeys(term);
		txtSearchBox.submit();
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	//set the constant test data
	private static final TestCase<String> tc = new TestCase<String>(
			"selenium practice",
			new String[][] { 
				{"seahawks"},
				{"storm"},
				{"sounders"}
			} );

	@DataProvider(parallel = true)
	public String[][] testDataProvider() {
		return tc.getTestData();
	}

	@Test(dataProvider = "testDataProvider")
	public void googleSmokeTest(String term) {
		try {
			//arrange
			GoogleMainPage gp = new GoogleMainPage();

			//act
			gp.search(term);

			//assert

			//wait for page title to contain search term
			Assert.assertTrue(
					new WebDriverWait(getWebDriver(),WAIT_TIME_OUT).
					until(ExpectedConditions.titleContains(term))
					);
			System.out.println("Test was successful!");
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}

	}

}
