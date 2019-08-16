package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_103
 * 
 * OBJECTIVE:			To check if error is reported if check-out date field is in the past
 * 
 * EXPECTED RESULTS:	System should report an error message �Enter Valid dates�.
 * 
 * STEPS:				1. Launch hotel
						reservation application
						using URL as in test
						data.
						2. Login to the
						application using
						username and
						password as in test
						data.
						3. Select location as in
						test data.
						4. Select hotel as in test
						data.
						5. Select room type as in
						test data.
						6. Select no-of-rooms as
						in test data.
						7. Enter check-out-date
						as in test data.
						8. Verify that application
						throws error message
 *					
 * TEST DATA MAPPING:				

 */

public class TC_103 extends SeleniumTest {
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat(Utils.getSettingFromFile("TC_DATE_FORMAT"));
	private final String datePickInXpath  = Utils.getSettingFromFile("XPATH_TXT_CHECKIN_DATE");
	private final String datePickOutXpath = Utils.getSettingFromFile("XPATH_TXT_CHECKOUT_DATE");
	private final TestCase tc = new TestCase();

	public TC_103() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_103_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_103_TESTDATA_FILE");
			ExcelTestData xtd = tc.new ExcelTestData(excelFilePath, excelWorksheetName);
			xtd.loadTestDataFromExcelFile();
			tc.setXtd(xtd);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	@Test
	public void tc103(){
		try {
			//launch login page and login
			new LoginPage().login();
			
			//arrange - create test object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//get dates from the Search Hotels forms and compare them:
			String datePickInValue  =  WaitAndGetAttribute(datePickInXpath, "value"); 
			String datePickOutValue =  WaitAndGetAttribute(datePickOutXpath, "value"); 

			//convert the datePickInValue string to a date and log it
			Date datePickInDate = new Date();
			datePickInDate = dateFormatter.parse(datePickInValue);
			System.out.println("Check in date  (dd/MM/yyyy) = " + dateFormatter.format(datePickInDate));

			//convert the datePickInValue string to a date
			Date datePickOutDate = new Date();
			datePickOutDate = dateFormatter.parse(datePickOutValue);
			System.out.println("Check out date (dd/MM/yyyy) = " + dateFormatter.format(datePickOutDate));

			//assert that the check out date and check in date are both earlier than today's date
			Date dateToday = new Date();

			System.out.println("Today's date (dd/MM/yyyy) = " + dateFormatter.format(dateToday));

			Assert.assertTrue(datePickOutDate.before(dateToday),
					"Assert failed - unexpected test data: datePickOutDate was not before dateToday" );

			System.out.println("Check out date is before today's date, "+ "therefore, we expect to see this error text: " 
					+ tc.getXtd().getTestDataElement(5,1));

			//act - perform search
			sp.search();
			System.out.println("The search just executed...");
			
			//get expected error message from test data object
			String xpathExpression = tc.getXtd().getTestDataElement(5,1);

			try {
				//wait for expected error message to display, throw Exception if it is not found
				new WebDriverWait(getWebDriver(), getWaitTimeout())
				.until(d->d.findElement(By.xpath(xpathExpression)));

				System.out.println("Success! We saw expected error text: " + xpathExpression);
			}
			catch(Exception e) {
				System.out.println("Fail. We did not see expected error text: " + xpathExpression);
				throw(e);
			}
		}
		catch(Exception e){
			System.out.println("TC_103 failed, due to: " + e.getMessage());
			Assert.fail();
		}
	}
}
