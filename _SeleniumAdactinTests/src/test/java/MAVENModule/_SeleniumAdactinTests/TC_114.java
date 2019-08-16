package MAVENModule._SeleniumAdactinTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_114
 * 
 * OBJECTIVE:			Verify Order number is generated in booking confirmation page
 * 
 * EXPECTED RESULTS:	Data should be same as selected in previous screen
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
						12. Verify Hotel name, Location, room type, Total Day, price per night are
						    same in Booking confirmation page as they were selected in previous screen
						URL: http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						Location: Sydney
						Hotel: hotel Creek
						Room type: standard
						No-of-rooms:2
						Check-in-date: today�s date
						Check-out-date:today+1 date
						No-of-adults:1
						No-of-children: 0
 * 
 * 	
 */

public class TC_114 extends SeleniumTest {
	private TestCase tc = new TestCase();

	public TC_114() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_114_TESTDATA_FILE");
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
	public void test114() {
		try {
			//launch login page and login
			new LoginPage().login();

			//create SearchHotelsPage object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//perform search
			sp.search();

			//create SelectHotePage object
			SelectHotelPage shp = new SelectHotelPage();

			//get the current selections for the following data:

			//Hotel name: 
			String shpHotelName = shp.gethotelName();

			//Location:
			String shpLocation = shp.gethotelLocation();

			//room type:
			String shpRoomType = shp.getRoomType();
			
			//Number of Rooms  	
			String shpNumRooms = shp.getNumRooms();

			//Total Day: 
			String shpDays = shp.gethotelDays();

			//price per night:
			String shpPricePerNight = shp.gethotelPricePerNight();
			
			//Total Price
			String shpTotalPrice = shp.gethotelTotalPrice();
			
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
			
			//get the Order No. and verify not null or blank
			String orderNo = bcp.getOrderNo();
			Assert.assertNotEquals(orderNo, null);
			Assert.assertNotEquals(orderNo, "");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


