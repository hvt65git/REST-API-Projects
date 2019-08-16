package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_119
 * 
 * OBJECTIVE:			To check whether �search order id� query is working for nonexistent Order Id:

 * EXPECTED RESULTS:	Search Order ID query should display no records and the "0 result(s) found." text near the top of the page
 * 
 * STEPS:				
						1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Click on booked itinerary link.
						4. Enter a fake order id.
						5. Verify that no details are displayed and the  "0 result(s) found." is visible
						http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						
 * 
 * 	
 */

public class TC_119 extends SeleniumTest {
	private final TestCase tc = new TestCase();
	private final String fakeOrderID = "fakeID";
	private final String EXPECTED_SEARCH_RESULT_ERROR_TEXT = Utils.getSettingFromFile("TC119_EXPECTED_SEARCH_RESULT_ERROR_TEXT");

	public TC_119() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_119_TESTDATA_FILE");
			ExcelTestData xtd = tc. new ExcelTestData(excelFilePath, excelWorksheetName);
			xtd.loadTestDataFromExcelFile();
			tc.setXtd(xtd);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail(); 
		}
	}

	@Test
	public void test119() {
		try {
			//launch login page and login
			new LoginPage().login();

			//create SearchHotelsPage object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//perform search
			sp.search();

			//create SelectHotePage object
			SelectHotelPage shp = new SelectHotelPage();

			//advance to the Hotel Booking page

			//select the radio button
			shp.selectRadioButton();

			//select continue button
			shp.selectContinue();

			//create BookAHotelPage object
			BookAHotelPage bhp = new BookAHotelPage(tc.getXtd().getTestData());

			//book it!
			bhp.bookNow();

			//create BookingConfirmationPage object
			BookingConfirmationPage bcp = new BookingConfirmationPage(null);

			//before clicking the My Itinerary button and advancing to next window,
			//save the main current fields settings for comparison to the field settings in the next page
			String bcpOrderNo 		=  bcp.getOrderNo();
			String bcpHotelName 	=  bcp.getHotelName(); 
			String bcpLocation 		=  bcp.getLocation();
			String bcpTotalRooms 	=  bcp.getTotalRooms();
			String bcpArrivalDate 	=  bcp.getArrivalDate();
			String bcpDepartureDate =  bcp.getDepartureDate();
			String bcpFinalDrive	=  bcp.getFinalPrice();

			//click My Itinerary button
			bcp.clickMyItineraryButton();

			//create BookedItineraryPage object 
			BookedItineraryPage bip = new BookedItineraryPage(bcpOrderNo);
			
			//search for order id using search text box and go button
			bip.searchOrderID(fakeOrderID);
			
			//get search error string and compare to expected value
			String actualErrorText = bip.getSearchResultErrorText();
			assertEquals(actualErrorText, EXPECTED_SEARCH_RESULT_ERROR_TEXT);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


