package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.utils.Utils;


/*
 * TEST CASE ID:	TC_102 
 * 
 * OBJECTIVE:		Verify correct error message displayed when arrival date is
 * 
 * EXPECTED RESULTS:
 * 
 * 					later than departure date for the reservation
 * 
 * STEPS:			1. Launch hotel reservation application using URL as in test data.
					2. Login to the application using username and password as in test data.
					3. Select location as in test data.
					4. Select hotel as in test data.
					5. Select room type as in test data.
					6. Select no-of-rooms as in test data.
					7. Enter check-in-date later than the check-out-date field as in test data.
					8. Verify that system gives an error saying �check-in-date should not be later than check-out-date�.

					Test data mapping for Search Hotels page object:
					testData[0][0]  = Sydney
					testData[1][0]  = Hotel Creek
					testData[2][0]  = Standard
					testData[3][0]  = 1 - One
					testData[4][0]  = 7
					testData[5][0]  = 5
					testData[6][0]  =  1 - One
					testData[7][0]  =  1 - One
					testData[8][0]  = END

					Test data needed for this file
					testData[4][1]  = Check-In Date shall be before than Check-Out Date
					testData[5][1]  = Check-Out Date shall be after than Check-In Date
 */

public class TC_102 extends SeleniumTest {
	private SimpleDateFormat dateFormatter; 
	private String datePickInXpath;
	private String datePickOutXpath;
	private TestCase tc = new TestCase();

	public TC_102() {
		try {	
			dateFormatter = new SimpleDateFormat(getSettingFromFile("TC_DATE_FORMAT"));	
			datePickInXpath  = getSettingFromFile("XPATH_TXT_CHECKIN_DATE");
			datePickOutXpath = getSettingFromFile("XPATH_TXT_CHECKOUT_DATE");

			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_102_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_102_TESTDATA_FILE");
			ExcelTestData xtd = tc. new ExcelTestData(excelFilePath, excelWorksheetName);
			xtd.loadTestDataFromExcelFile();
			tc.setXtd(xtd);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	@Test
	public void tc102_verify_date_error_msgs(){
		try {
			//launch login page and login
			new LoginPage().login();

			//launch and init Search Hotels page
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//get dates from the Search Hotels forms and compare them:
			String datePickInValue =  WaitAndGetAttribute(datePickInXpath, "value"); 
			String datePickOutValue =  WaitAndGetAttribute(datePickOutXpath, "value"); 

			//convert the datePickInValue string to a date and log it
			Date datePickInDate = new Date();
			datePickInDate = dateFormatter.parse(datePickInValue);
			System.out.println("Check in date  (dd/MM/yyyy) = " + dateFormatter.format(datePickInDate));

			//convert the datePickInValue string to a date
			Date datePickOutDate = new Date();
			datePickOutDate = dateFormatter.parse(datePickOutValue);
			System.out.println("Check out date (dd/MM/yyyy) = " + dateFormatter.format(datePickOutDate));

			//Assert date expectations
			//for this test case TC_102 the check out date must be earlier than the check out date
			//or else the test data was not set properly
			Assert.assertTrue(datePickOutDate.before(datePickInDate));
			System.out.println("Check out date is before the check-in date, "
					+ "so we expect to find error messages stating this after the search occurs...");

			//act - perform search
			sp.search();
			System.out.println("The search just executed...");

			//verify check-IN date error message exists
			new WebDriverWait(getWebDriver(), getWaitTimeout())
			.until(d->d.findElement(By.xpath("//*[text() = '" + tc.getXtd().getTestDataElement(4,1) + "']")));
			System.out.println(tc.getXtd().getTestDataElement(4,1) + " was found...");

			//verify check-out date error message exists
			new WebDriverWait(getWebDriver(), getWaitTimeout())
			.until(d->d.findElement(By.xpath("//*[text() = '" + tc.getXtd().getTestDataElement(5,1) + "']")));
			System.out.println(tc.getXtd().getTestDataElement(5,1) + " was found...");

			System.out.println("Both date error messages were found!");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
}


