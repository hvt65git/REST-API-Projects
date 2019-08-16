package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_116
 * 
 * OBJECTIVE:			To check whether the booked itinerary reflects the correct information in line with the booking.
 * 						Do this for the following fields:
						 * Order No
						 * Hotel Name
						 * Location
						 * Number of Rooms
						 * Arrival Date
						 * Departure Date
						 * Final Billed Price
 * 
 * EXPECTED RESULTS:	Itinerary should reflect the correct information in line with the booking.
 * 
 * STEPS:				
						1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Select location as in test data.
						4. Select hotel as in test data.
						5. Select room type as in test data.
						6. Select no-of-rooms as in test data.
						7. Enter check-out-date as in test data.
						8. Select No-of-adults as in test data.
						9. Select No-of-children as in test data.
						10. Click on Search button.
						11. Select the hotel and click on continue button
						12. Fill the form and click on Book now button.
						13. Click on My itinerary button
						14. Verify that the details are reflected correctly as per the booking
						http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						Location: Sydney
						Hotel: Hotel Creek
						Room type: standard
						No-of-rooms:2
						Check-in-date: today�s date
						Check-out-date:today+1 date
						No-of-adults:1
						No-of-children: 0
 * 
 * 	
 */

public class TC_116 extends SeleniumTest {
	private TestCase tc = new TestCase();

	public TC_116() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_116_TESTDATA_FILE");
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
	public void test116() {
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

			//save the main current fields settings for comparison to the field settings in the next page
			String bipOrderNo 		=  bip.getOrderNo();
			String bipHotelName 	=  bip.getHotelName(); 
			String bipLocation 		=  bip.getLocation();
			String bipTotalRooms 	=  bip.getTotalRooms();
			String bipArrivalDate 	=  bip.getArrivalDate();
			String bipDepartureDate =  bip.getDepartureDate();
			String bipFinalDrive	=  bip.getFinalPrice();

			//clean up
			bip.cancelSelectedAll();

			//softAssert each field 
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.assertEquals(bcpOrderNo, bipOrderNo );
			System.out.println("softAssert.assertEquals: bcpOrderNo = " + bcpOrderNo + "\tbipOrderNo = " + bipOrderNo );
			
			softAssert.assertEquals(bcpHotelName , bipHotelName);
			System.out.println("softAssert.assertEquals: bcpHotelName = " + bcpHotelName + "\tbipHotelName = " + bipHotelName);
			
			softAssert.assertEquals(bcpLocation , bipLocation);
			System.out.println("softAssert.assertEquals: bcpLocation = " + bcpLocation + "\t\tbipLocation = " + bipLocation);
			
			softAssert.assertEquals(bcpTotalRooms , bipTotalRooms);
			System.out.println("softAssert.assertEquals: bcpTotalRooms = " + bcpTotalRooms + "\tbipTotalRooms = " + bipTotalRooms);
					
			softAssert.assertEquals(bcpArrivalDate , bipArrivalDate);
			System.out.println("softAssert.assertEquals: bcpArrivalDate = " + bcpArrivalDate + "\tbipArrivalDate = " + bipArrivalDate);
			
			softAssert.assertEquals(bcpDepartureDate , bipDepartureDate);
			System.out.println("softAssert.assertEquals: bcpDepartureDate = " + bcpDepartureDate + "\tbipDepartureDate = " + bipDepartureDate);
			
			softAssert.assertEquals(bcpFinalDrive, bipFinalDrive);
			System.out.println("softAssert.assertEquals: bcpFinalDrive = " + bcpFinalDrive + "\tbipFinalDrive = " + bipFinalDrive);

			softAssert.assertAll();

		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


