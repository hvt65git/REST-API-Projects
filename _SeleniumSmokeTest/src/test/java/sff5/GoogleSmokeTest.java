package sff5;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static sff5.DriverType.CHROME;

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
					System.getProperty("user.dir") + "\\libs\\MicrosoftEdgeDriver.exe");
			return new ChromeDriver();
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
		getWebDriver().quit();
		tl.remove();
	}
}

class SeleniumBase extends DriverFactory {
	protected static final long WAIT_TIME_OUT = 10; //seconds

	protected class TestCase<X> {
		private X[][] testdata;
		private String name;

		public TestCase(String name, X[][] testdata) {
			this.testdata = testdata;
			this.name = name;
		}

		public X[][] getTestData() {
			return testdata;
		}

		public String getName() {
			return this.name;
		}
	}
}

class GoogleMainPage {
	private static final String URL = "https://google.com";
	private static final String XPATH_SEARCH_BOX = "//*[@name='q']";
	private static final long WAIT_TIME_OUT = 20; //seconds

	@FindBy(xpath=XPATH_SEARCH_BOX)
	WebElement txtSearchBox;

	public GoogleMainPage() {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	public void search(String term) {
		//wait for page to load and element to be visible
		//if timeout is reached an unchecked exception will be thrown
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT).
		until(ExpectedConditions.visibilityOf(txtSearchBox));

		//send the text to the element and enter submit
		txtSearchBox.sendKeys(term);
		txtSearchBox.submit();
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	private final TestCase<String> tc;

	GoogleSmokeTest() {
		String name = "HT Selenium Framework QA Demo";
		String[][] testdata = {
				{"seahawks"}, 
				{"storm"},
				{"sounders"},
				{"super sonics"}
		};
		tc = new TestCase<>(name, testdata);
	}

	@Test(dataProvider = "getTDdata")
	public void htSeleniumQAdemo(String term) {
		try {
			//announce
			System.out.println("Currently executing test: " + tc.getName() + "...");
			
			System.out.println("now searching for: " + term);
			//arrange and act
			new GoogleMainPage().search(term);
			
			//assert
			assertTrue(new WebDriverWait(DriverFactory.getWebDriver(),
					WAIT_TIME_OUT).until(ExpectedConditions.titleContains(term)));
		}
		catch(Exception e) {
			fail(e.getMessage());
		}
	}

	@DataProvider(parallel = true) 
	public String[][] getTDdata() {
		return tc.getTestData();
	}

}
