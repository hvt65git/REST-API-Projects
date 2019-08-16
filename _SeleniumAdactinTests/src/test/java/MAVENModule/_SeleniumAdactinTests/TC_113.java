package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;


import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_113
 * 
 * OBJECTIVE:			To check:
						Hotel Name  	
						Location  	
						Room Type  	
						Number of Rooms  	
						Total Days  	
						Price per Night  	
						Total Price  
 * 						are same in Booking confirmation page as they were selected in previous screen
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

public class TC_113 extends SeleniumTest {

	private TestCase tc = new TestCase();

	public TC_113() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_113_TESTDATA_FILE");
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
	public void test113() {
		try {
			//use soft assertions so that all 7 items can be tested if a test failure occurs
			SoftAssert softAssert = new SoftAssert();
			
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
			BookAHotelPage bhp = new BookAHotelPage(null);

			//get the current selections for the following data:

			//Hotel name: 
			String bhpHotelName = bhp.gethotelName();

			//Location:
			String bhpLocation = bhp.gethotelLocation();

			//room type:
			String bhpRoomType = bhp.getRoomType();
			
			//Number of Rooms  	
			String bhpNumRooms = bhp.getNumRooms();

			//Total Day: 
			String bhpDays = bhp.gethotelDays();

			//Total Price
			String bhpTotalPrice = bhp.getBookedTotalPriceSelection();
			
			//price per night:
			String bhpPricePerNight = bhp.gethotelPricePerNight();

			//compare the values
			
			//Total Day: 
			softAssert.assertEquals(bhpDays, shpDays);
			System.out.println("Testing Days: " + "bhpDays = " + bhpDays +
					", shpDays = " + shpDays);

			//Hotel name: 
			softAssert.assertEquals(bhpHotelName, shpHotelName);
			System.out.println("Testing Hotel Name: " + "bhpHotelName = " + bhpHotelName +
								", shpHotelName = " + shpHotelName);	

			//Location:
			softAssert.assertEquals(bhpLocation, shpLocation);
			System.out.println("Testing Location:" + " bhpLocation = " + bhpLocation +
					", shpLocation = " + shpLocation);	

			//room type:
			softAssert.assertEquals(bhpRoomType, shpRoomType);
			System.out.println("Testing Room Type: " + "bhpRoomType = " + bhpRoomType +
					", shpRoomType = " + shpRoomType);	
			
			//Number of Rooms  		  
			softAssert.assertEquals(bhpNumRooms, shpNumRooms);
			System.out.println("Testing Number of Rooms  : " + "bhpNumRooms = " + bhpNumRooms +
				", shpNumRooms = " + shpNumRooms);	

			//price per night:
			softAssert.assertEquals(bhpPricePerNight, shpPricePerNight);
			System.out.println("Testing Price per Night: " + "bhpPricePerNight = " + bhpPricePerNight +
					", shpPricePerNight = " + shpPricePerNight);	
			
			//Total Price
			softAssert.assertEquals(bhpTotalPrice, shpTotalPrice);
			System.out.println("Testing Total Price: " + "bhpTotalPrice = " + bhpTotalPrice +
					", shpTotalPrice = " + shpTotalPrice);	
			
			//report the failures now
			softAssert.assertAll();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


