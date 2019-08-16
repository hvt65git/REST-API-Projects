package MAVENModule._SeleniumAdactinTests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.utils.Conditions;
import MAVENModule._SeleniumAdactinTests.utils.Utils;
import static  MAVENModule._SeleniumAdactinTests.utils.Utils.*;



/*
 * Adactin Search Hotel Page - Page Obj Model Page
 */
public class SearchHotelsPage {
	private long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));
	
	//set the constant expressions to be used in @FindBy args
	private final String XPATH_LST_LOCATION			= "//*[@id='location']";
	private final String XPATH_LST_HOTELS  			= "//*[@id='hotels']";
	private final String XPATH_LST_ROOM_TYPE 		= "//*[@id='room_type']";
	private final String XPATH_LST_NUM_ROOMS 		= "//*[@id='room_nos']";
	private final String XPATH_TXT_CHECKIN_DATE 	= "//*[@id='datepick_in']";
	private final String XPATH_TXT_CHECKOUT_DATE	= "//*[@id='datepick_out']";
	private final String XPATH_BTN_SEARCH 			= "//*[@id='Submit']";
	private final String XPATH_BTN_RESET 			= "//*[@id='Reset']";
	private final String XPATH_LST_ADULT_PER_ROOM 	= "//*[@id='adult_room']";
	private final String XPATH_LST_CHILD_PER_ROOM 	= "//*[@id='child_room']";

	@FindBy(xpath = XPATH_LST_LOCATION)
	WebElement lstLocation;

	@FindBy(xpath = XPATH_LST_HOTELS)
	WebElement lstHotels;

	@FindBy(xpath = XPATH_LST_ROOM_TYPE)
	WebElement lstRoomType;

	@FindBy(xpath = XPATH_LST_NUM_ROOMS)
	WebElement lstNumRows;

	@FindBy(xpath = XPATH_TXT_CHECKIN_DATE)
	WebElement txtcheckInDate;

	@FindBy(xpath = XPATH_TXT_CHECKOUT_DATE)
	WebElement txtcheckOutDate;

	@FindBy(xpath = XPATH_LST_ADULT_PER_ROOM)
	WebElement lstAdultRm;

	@FindBy(xpath = XPATH_LST_CHILD_PER_ROOM)
	WebElement lstChildRm;

	@FindBy(xpath = XPATH_BTN_SEARCH)
	WebElement btnSearch;

	@FindBy(xpath = XPATH_BTN_RESET)
	WebElement btnReset;

	private static Date getDayAdjustedDate(Date today, int daysAdjusted) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, daysAdjusted);
		dt = c.getTime();
		return dt;
	}

	private void initPage(String[][] testData) throws Exception {
		//set the list controls
		String selection = null;
		
		WebDriver driver = DriverFactory.getWebDriver();
		Boolean ret = Conditions.waitForPageToLoad(driver,  WAIT_TIME_OUT);
		
		selection = testData[0][0];
		new Select(lstLocation).selectByVisibleText(selection);		

		selection = testData[1][0];
		new Select(lstHotels).selectByVisibleText(selection);		

		selection = testData[2][0];
		new Select(lstRoomType).selectByVisibleText(selection);		

		selection = testData[3][0];
		new Select(lstNumRows).selectByVisibleText(selection);	

		txtcheckInDate.clear();
		txtcheckOutDate.clear();

		Date dateToday = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(Utils.getSettingFromFile("TC_DATE_FORMAT"));

		//get check-in date = x days from today
		Date dateCheckIn = getDayAdjustedDate(dateToday, Integer.parseInt(testData[4][0]));
		txtcheckInDate.sendKeys(formatter.format(dateCheckIn));

		//get check-out date = y days from today
		Date dateCheckOut = getDayAdjustedDate(dateToday, Integer.parseInt(testData[5][0]));
		txtcheckOutDate.sendKeys(formatter.format(dateCheckOut));

		//set the adult rms list
		//testData[6][0]  = 0 	->adults
		new Select(lstAdultRm).selectByVisibleText(testData[6][0]);

		//testData[7][0]  = 1  	->children
		//set the child rms selection
		new Select(lstChildRm).selectByVisibleText(testData[7][0]);
	}

	public SearchHotelsPage(String[][] testData) throws Exception {
		//init the elements of this page
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();;
		
		//init this page with the loaded test data
		initPage(testData);
	}
	
	public String getRoomTypeSelection() throws Exception {
		return new Select(lstRoomType).getFirstSelectedOption().getText();
	}
	
	public String getNumberOfRoomsSelection() throws Exception {
		return new Select(lstNumRows).getFirstSelectedOption().getText();
	}


	public void search() throws Exception {
		btnSearch.submit();
	}
}
