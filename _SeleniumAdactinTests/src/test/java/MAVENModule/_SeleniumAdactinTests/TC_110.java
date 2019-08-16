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

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.system.SeleniumTest.TestCase.ExcelTestData;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:		TC_110
 * 
 * OBJECTIVE:			To check correct total price is being calculated as
 * 						�price per night*no of days*no of rooms in Book a hotel page
 * 
 * EXPECTED RESULTS:	Total price =125*1*2=250$
 * 
 * STEPS:				1. Launch hotel reservation application using URL as in test data.
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
						12. Verify that total-price is being calculated as (price-per-night*no-of-rooms*no-of-days + 10% GST�)
						URL: http://adactin.com/HotelApp/index.php
						User:{test username}
						Password:{test password}
						Location: Melbourne
						Hotel: Hotel Creek
						Room type: standard
						No-of-rooms:2
						Check-in-date: today�s date
						Check-out-date:today+1 date
						No-of-adults:1
						No-of-children: 0
						Total-price should be calculated as (price-per-night*no-of-rooms*no-of-days
						Total Price= 125*2*1 = 250$
						In book a hotel page



 * 
 * 	
 */

public class TC_110 extends SeleniumTest {
	private final String expectedTotalPrice = Utils.getSettingFromFile("TC110_EXPECTED_TOTAL_PRICE");
	private TestCase tc = new TestCase();

	public TC_110() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_110_TESTDATA_FILE");
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
	public void test110() {
		try {
			//launch login page and login
			new LoginPage().login();
			
			//create SearchHotelsPage object
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());
			
			//get the number of rooms current selection
			String numRoomsSelected = sp.getNumberOfRoomsSelection();

			//perform search
			sp.search();
			
			//create SelectHotePage object
			SelectHotelPage shp = new SelectHotelPage();
			
			//select the radio button
			shp.selectRadioButton();
			
			//select continue button
			shp.selectContinue();
			
			//create BookAHotelPage object
			BookAHotelPage bhp = new BookAHotelPage(tc.getXtd().getTestData());
			
			//assert actual price is same as expected price
			String calculatedTotalPrice = bhp.getCalculateBookedTotalPrice(); //250.0
				
			//assert actual price is same as expected price
			Assert.assertEquals(calculatedTotalPrice, expectedTotalPrice);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


