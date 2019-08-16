package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_115
 * 
 * OBJECTIVE:			To verify whether the booked itinerary details are not editable.
 * 
 * EXPECTED RESULTS:	Details once accepted should not be editable
 * 
 * STEPS:				
						1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Select location as in test data.
						4. Select Hotel as in test data.
						5. Select room type as in test data.
						6. Select no-of-rooms as in test data.
						7. Enter check-out-date as in test data.
						8. Select No-of-adults as in test data.
						9. Select No-of-children as in test data.
						10. Click on Search button.
						11. Select the hotel and click on continue button
						12. Fill the form and click on Book now button.
						13. Click on My itinerary button
						14. Verify that the details are not editable
						http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						Location: Adelaide
						Hotel: Hotel Cornice
						Room type: standard
						No-of-rooms:2
						Check-in-date: today�s date
						Check-out-date:today+1 date
						No-of-adults:1
						No-of-children: 0
						Details once accepted should not be editable
 * 
 * 	
 */

public class TC_115 extends SeleniumTest {
	private TestCase tc = new TestCase();

	public TC_115() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_115_TESTDATA_FILE");
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
	public void test115() {
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
			bcp.clickMyItineraryButton();

			BookedItineraryPage bip = new BookedItineraryPage(null);
			Boolean bSuccess = false;
			String text = "testing123...";
			try{
				bip.setHotelTxt(text);
				System.out.println("Fail! The following text was successfully entered into a"
						+ " text box that we expected to have been read only: " + text);

			}
			catch(Exception e) {
				bSuccess = true;
				System.out.println("Success! Could not edit the hotel text box.");
			}
			Assert.assertTrue(bSuccess);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


