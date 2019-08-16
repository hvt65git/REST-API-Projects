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
 * TEST CASE ID:		TC_107
 * 
 * OBJECTIVE:			To verify whether Room Type in Select Hotel page is same as Room type selected in search hotel page
 * 
 * EXPECTED RESULTS:	 Room type displayed should be the same as selected in search hotel page
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
						11. Verify that room type reflected is the same as selected in search hotel page
 * 
 * 	
 */

public class TC_107 extends SeleniumTest {
	TestCase tc = new TestCase();

	public TC_107() {
		try {	
			//load the testdata from the Excel spreadsheet into the test case object
			String excelWorksheetName = Utils.getSettingFromFile("TC_TESTDATA_WORKSHEET");
			String excelFilePath = System.getProperty("user.dir") + Utils.getSettingFromFile("TC_107_TESTDATA_FILE");
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
	public void test07() {

		try {
			//launch login page and login
			new LoginPage().login();
			
			//launch Search
			SearchHotelsPage sp = new SearchHotelsPage(tc.getXtd().getTestData());

			//get the selected entry in the room type list
			String rmTypeSelected = sp.getRoomTypeSelection();

			//act - perform search
			sp.search();
			
			//create SelectHotePage object
			SelectHotelPage shp = new SelectHotelPage();

			//get the room type in the SelectHotel page                              
			Assert.assertEquals(shp.getRoomType(), rmTypeSelected);
			
			System.out.println("Success! " +  shp.getRoomType() + " matched: " + 
			"rmTypeSelected = " + rmTypeSelected );
		}
		catch(Exception e) {
			System.out.println(e.getMessage());	
			Assert.fail();
		}
	}
}


