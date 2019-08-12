package sff10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import static sff10.DriverType.*;


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
			return new ChromeDriver();
		}
	}
}

class GoogleMainPage {
	private final String URL = "https://google.com";
	private final String XPATH_TXTBOX = "//*[@name='q']";

	@FindBy(xpath = XPATH_TXTBOX)
	WebElement txtbox;

	public GoogleMainPage() {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(URL);
	}

	public void search(String term) {
		//wait for the txt box to appear
		WebDriver driver = DriverFactory.getWebDriver();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(txtbox));

		//set the text in the txt box and submit search
		txtbox.sendKeys(term);
		txtbox.submit();
	}
}

class DriverFactory {
	DriverType dt = CHROME;
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
		getWebDriver().quit();
		tl.remove();
		tl = new ThreadLocal<>();
	}
}

class SeleniumBase extends DriverFactory {
	protected final long WAIT_TIMEOUT = 10; //seconds

	protected static class TestCase<X> {
		private X[][] testData;

		public TestCase(X[][] testData) {
			this.testData =  testData;
		}

		public X[][] getTestData() {
			return this.testData;
		}
	}
}

public class GoogleSmokeTest extends SeleniumBase {
	private final TestCase<String> tc;

	public GoogleSmokeTest() {
		//set the test data
		tc = new TestCase(new String[][]{
			{"seattle storm"}, 
			{"seattle sounders"}, 
			{"seattle seahawks"}
		});
	}

	@Test(dataProvider = "testDataProvider")
	public void googleSmokeTest(String term){
		//arrange
		GoogleMainPage gp = new GoogleMainPage();

		//act
		gp.search(term);

		//assert
		Assert.assertTrue(new WebDriverWait(getWebDriver(), 10).
				until(ExpectedConditions.titleContains(term)));
	}

	@DataProvider(parallel = false)
	public String[][] testDataProvider() {
		return tc.getTestData();
	}
}
