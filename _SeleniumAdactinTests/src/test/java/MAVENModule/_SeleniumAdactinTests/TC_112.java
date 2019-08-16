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
 * TEST CASE ID:		TC_112
 * 
 * OBJECTIVE:			To check correct Final billed price is Total Price + 10% Total price in Book a Hotel page
 * 
 * EXPECTED RESULTS:	Final billed Price=125+12.5=137.5 in Book a Hotel page
 * 
 * STEPS:				1. Launch hotel reservation application using URL as in test data.
						2. Login to the application using username and password as in test data.
						3. Select location as in test data. Select Hotel as in test data.
						4. Select room type as in test data.
						5. Select no-of-rooms as in test data.
						6. Enter check-out-date as in test data.
						7. Select No-of-adults as in test data.
						8. Select No-of-children as in test data.
						9. Click on Search button.
						10. Select the hotel and click on continue button
						11. Verify that Final Billed Price is being calculated as (price-per-night*no-of-rooms*no-of-days
						URL: http://adactin.com/HotelApp/index.php
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
						Final billed Price=125+12.5=137.5 in Book a Hotel page
 * 
 * 	
 */

public class TC_112 extends SeleniumTest {
	private final String expectedTotalPrice = Utils.getSettingFromFile("TC112_EXPECTED_TOTAL_PRICE");
	private TestCase tc = new TestCase();

	public TC_112() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_112_TESTDATA_FILE");
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
	public void test112() {
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
			BookAHotelPage bhp = new BookAHotelPage(null);

			//get total price from Book a Hotel Page
			String totalPrice = bhp.getBookedTotalPriceSelection();//whoopsie problem here!
			System.out.println("totalPrice = " + totalPrice);

			//get final billed price from Book a Hotel Page
			String finalBilledPrice = bhp.getBookedTotalPriceFinalBilledSelection();
			System.out.println("finalBilledPrice = " + finalBilledPrice);

			//assert  final billed price is total price + 10% total price in Book a Hotel page
			Double dTotalPrice = new Double(convertPriceToString(totalPrice));
			Double dFinalBilledPrice = new Double(convertPriceToString(finalBilledPrice));

			//calculate expected final billing price and compare it to actual billing price
			Double dCalculatedTotalPrice = 1.1 * dTotalPrice;	
			Assert.assertEquals(dCalculatedTotalPrice, dFinalBilledPrice);
			System.out.println("Assert passed! final billed price is total price + 10% total price in Book a Hotel page");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}

	private String convertPriceToString(String s) {
		String [] temp = s.split(" ");
		String parsed = temp[2];
		return parsed;
	}
}


