package sffb9;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleSmokeTest extends SeleniumBase {
	private final TestCase<String> testdata;

	//set the test data in the constructor
	public GoogleSmokeTest() {
		testdata = new TestCase<String>(
				new String[][] {
					{"seattle reign"},
					{"seattle seahawks"}
				}
				);
	}

	@Test(dataProvider = "testdataProvider")
	public void googleSmokeTest(String term) {
		try {
			//arrange test object
			GoogleMainPage gp = new GoogleMainPage();

			//act
			gp.search(term);

			//assert page title contains search term
			String pageTitle =  new WebDriverWait(getWebDriver(), WAIT_TIMEOUT).
					until(x->x.getTitle());

			Assert.assertTrue(pageTitle.contains(term));
		}
		catch(Exception e) {
			Assert.fail();
			System.out.println(e.getMessage());
		}
	}

	@DataProvider(parallel = true)
	public String[][] testdataProvider() {
		return testdata.getTestData();
	}
}