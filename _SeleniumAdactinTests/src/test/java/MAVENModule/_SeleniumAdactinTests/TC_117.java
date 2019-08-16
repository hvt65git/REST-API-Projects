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
 * TEST CASE ID:		TC_117
 * 
 * OBJECTIVE:			To check whether �search order id� query is working and displaying the relevant details:
						 * Order No
						 * Hotel Name
						 * Location
						 * Number of Rooms
						 * Arrival Date
						 * Departure Date
						 * Final Billed Price
 * 
 * EXPECTED RESULTS:	Search Order ID query should display the relevant details for Order ID
 * 
 * STEPS:				
						1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Click on booked itinerary link.
						4. Enter the order id.
						5. Verify that the relevant details are displayed
						http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						Order id :pick existing order id
 * 
 * 	
 */

public class TC_117 extends SeleniumTest {
	private TestCase tc = new TestCase();

	public TC_117() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_117_TESTDATA_FILE");
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
	public void test117() {
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
			bip.searchOrderID(bcpOrderNo);
			
			//save the main current fields settings for comparison to the field settings in the next page
			String bipOrderNo = bip.getOrderNo();
			String bipHotelName 	=  bip.getHotelName(); 
			String bipLocation 		=  bip.getLocation();
			String bipTotalRooms 	=  bip.getTotalRooms();
			String bipArrivalDate 	=  bip.getArrivalDate();
			String bipDepartureDate =  bip.getDepartureDate();
			String bipFinalDrive	=  bip.getFinalPrice();

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


