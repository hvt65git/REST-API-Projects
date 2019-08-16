package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.utils.Conditions;
import MAVENModule._SeleniumAdactinTests.utils.Utils;
/*
 * Adactin Book A Hotel Page - Page Obj Model Page
 */
public class BookAHotelPage {
	private long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));

	private final String XPATH_LNK_LOGOUT			= "//*[text()='Logout']";
	private final String XPATH_LNK_CHANGE_PASSWORD 	= "//*[text()='Change Password']";
	private final String XPATH_LNK_BOOKED_ITINERARY	= "//*[text() = 'Booked Itinerary']";
	private final String XPATH_LNK_SEARCH_HOTEL		= "//*[text() = 'Search Hotel']";

	private final String XPATH_TXT_BOOKED_NUM_ROOMS			= "//*[@id='room_num_dis']";
	private final String XPATH_TXT_BOOKED_ROOM_TYPE 		= "//*[@id='room_type_dis']";
	private final String XPATH_TXT_BOOKED_DAYS 				= "//*[@id='total_days_dis']";
	private final String XPATH_TXT_BOOKED_PRICE_NIGHT  		= "//*[@id='price_night_dis']";
	private final String XPATH_TXT_BOOKED_PRICE_TOTAL 		= "//*[@id='total_price_dis']";
	private final String XPATH_TXT_BOOKED_PRICE_GST  		= "//*[@id='gst_dis']";
	private final String XPATH_TXT_BOOKED_PRICE_FNL_BILLED 	= "//*[@id='final_price_dis']";
	private final String XPATH_BTN_BOOKED_BOOK_NOW			= "//*[@id='book_now']";
	private final String XPATH_BTN_BOOKED_CANCEL			= "//*[@id='cancel']";

	private final String XPATH_TXT_BOOKED_SELECT_HOTEL_NAME 		= "//*[@id='hotel_name_dis']";
	private final String XPATH_TXT_BOOKED_SELECT_HOTEL_LOCATION 	= "//*[@id='location_dis']";
	private final String XPATH_TXT_BOOKED_SELECT_HOTEL_ROOM_TYPE 	= "//*[@id='room_type_dis']";	
	private final String XPATH_TXT_BOOKED_SELECT_HOTEL_DAYS 		= "//*[@id='total_days_dis']";
	private final String XPATH_TXT_BOOKED_SELECT_HOTEL_PRICE_NIGHT = "//*[@id='price_night_dis']";
	
	private final String XPATH_TXT_BOOKED_FIRST_NAME		= "//*[@id='first_name']";
	private final String XPATH_TXT_BOOKED_LAST_NAME			= "//*[@id='last_name']";
	private final String XPATH_TXT_BOOKED_AREA_ADDRESS		= "//*[@id='address']";
	private final String XPATH_TXT_BOOKED_CC_NUM			= "//*[@id='cc_num']";
	private final String XPATH_TXT_BOOKED_CC_CVV_NUM		= "//*[@id='cc_cvv']";
	
	private final String XPATH_LST_BOOKED_CC_TYPE			= "//*[@id='cc_type']";
	private final String XPATH_LST_BOOKED_CC_MNTH 			= "//*[@id='cc_exp_month']";
	private final String XPATH_LST_BOOKED_CC_YR 			= "//*[@id='cc_exp_year']";

	//The value for annotation attribute FindBy.xpath must be a constant expression
	@FindBy(xpath = XPATH_TXT_BOOKED_SELECT_HOTEL_NAME) 
	WebElement hotelName;

	@FindBy(xpath = XPATH_TXT_BOOKED_SELECT_HOTEL_LOCATION)
	WebElement hotelLocation;

	@FindBy(xpath = XPATH_TXT_BOOKED_SELECT_HOTEL_ROOM_TYPE)
	WebElement hotelRoomType;

	@FindBy(xpath = XPATH_TXT_BOOKED_SELECT_HOTEL_DAYS)
	WebElement hotelDays;

	@FindBy(xpath = XPATH_TXT_BOOKED_SELECT_HOTEL_PRICE_NIGHT)
	WebElement hotelPricePerNight;

	@FindBy(xpath = XPATH_TXT_BOOKED_NUM_ROOMS)
	WebElement bookedRooms;

	@FindBy(xpath = XPATH_TXT_BOOKED_ROOM_TYPE)
	WebElement bookedRoomType;

	@FindBy(xpath = XPATH_TXT_BOOKED_DAYS)
	WebElement bookedDays;

	@FindBy(xpath = XPATH_TXT_BOOKED_PRICE_NIGHT)
	WebElement bookedPricePerNight;
	
	@FindBy(xpath = XPATH_TXT_BOOKED_PRICE_TOTAL)
	WebElement bookedTotalPrice;
	
	@FindBy(xpath = XPATH_TXT_BOOKED_PRICE_GST)
	WebElement bookedPriceGST;

	@FindBy(xpath = XPATH_TXT_BOOKED_PRICE_FNL_BILLED)
	WebElement bookedTotalPriceFinalBilled;

	@FindBy(xpath = XPATH_LNK_LOGOUT)
	WebElement lnkLogout;

	@FindBy(xpath = XPATH_TXT_BOOKED_FIRST_NAME)
	WebElement txtFirstName;

	@FindBy(xpath = XPATH_TXT_BOOKED_LAST_NAME)
	WebElement txtLastName;

	@FindBy(xpath = XPATH_TXT_BOOKED_AREA_ADDRESS)
	WebElement txtAddress;
	
	@FindBy(xpath = XPATH_TXT_BOOKED_CC_NUM)
	WebElement txtCCNum;
	
	@FindBy(xpath = XPATH_TXT_BOOKED_CC_CVV_NUM)
	WebElement txt_CC_CVV;

	@FindBy(xpath = XPATH_LST_BOOKED_CC_TYPE)
	WebElement lstCCType;
	
	@FindBy(xpath = XPATH_LST_BOOKED_CC_MNTH)
	WebElement lstCCMonth;
	
	@FindBy(xpath = XPATH_LST_BOOKED_CC_YR)
	WebElement lstCCYear;
	
	@FindBy(xpath = XPATH_BTN_BOOKED_BOOK_NOW)
	WebElement btnBookNow;
	
	private void initPage(String[][] testData) throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();
		Conditions.waitForPageToLoad(driver,  WAIT_TIME_OUT);

		//clear the text controls
		txtFirstName.clear();
		txtLastName.clear();
		txtAddress.clear();
		txtCCNum.clear();
		txt_CC_CVV.clear();
		
		//set the text controls
		txtFirstName.sendKeys(testData[8][0]);
		txtLastName.sendKeys(testData[9][0]);
		txtAddress.sendKeys(testData[10][0]);
		txtCCNum.sendKeys(testData[11][0]);
		txt_CC_CVV.sendKeys(testData[15][0]);
		
		//set the list controls
		new Select(lstCCType).selectByVisibleText(testData[12][0]);
		new Select(lstCCMonth).selectByVisibleText(testData[13][0]);
		new Select(lstCCYear).selectByVisibleText(testData[14][0]);				
	}
	
	public BookAHotelPage(String[][] testData) throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
		initPage(testData);
	}

	public String getRoomType() {
		return hotelRoomType.getAttribute("value");
	}

	public String getNumRooms() {
		return bookedRooms.getAttribute("value");
	}


	public String gethotelName() {
		return hotelName.getAttribute("value");
	}

	public String gethotelLocation() {
		return hotelLocation.getAttribute("value");
	}

	public String gethotelDays() {
		return hotelDays.getAttribute("value");
	}

	public String gethotelPricePerNight() {
		return hotelPricePerNight.getAttribute("value");
	}


	public String getBookedTotalPriceSelection() {
		return bookedTotalPrice.getAttribute("value");
	}
	
	
	public String getBookedRoomGSTPriceSelection()  {
		return bookedPriceGST.getAttribute("value");
	}

	public String getBookedTotalPriceFinalBilledSelection()  {
		return bookedTotalPriceFinalBilled.getAttribute("value");
	}

	public String getCalculateBookedTotalPrice() {
		//no of rooms:
		String rmNumberValue = bookedRooms.getAttribute("value");

		//parse num of rooms to single num
		String rmNumValSub = rmNumberValue.substring(0, 1);

		//Calculate total price = �price per night * no. of nights* no of rooms�.

		// no. of nights:
		String rmHotelDaysValue = bookedDays.getAttribute("value");
		System.out.println("booked hotel days value =" + rmHotelDaysValue +  "...");

		//price per night
		String rmHotelPriceNightValue = bookedPricePerNight.getAttribute("value");
		System.out.println("booked price per night =" + rmHotelPriceNightValue +  "...");

		// total price (excl.GST) 
		String rmHotelTotalPriceValue = bookedTotalPrice.getAttribute("value");
		System.out.println("booked total price =" + rmHotelTotalPriceValue +  "..."); 

		//convert price per night to decimal - double, e.g., 325
		String [] temp = rmHotelPriceNightValue.split(" ");
		String parsed = temp[2];
		Double pricePerNight = new Double(parsed);

		//nights, e.g. = 1
		temp = rmHotelDaysValue.split(" ");
		parsed = temp[0];
		Double numberOfNights = new Double(parsed); //1 Days

		//calc total price and return it
		Double numberOfRooms = new Double(rmNumValSub);
		Double calcTotalPrice = pricePerNight * numberOfNights * numberOfRooms;
		return calcTotalPrice.toString();
	}

	public void bookNow() {
		btnBookNow.click();
	}
	
	public void logout() {
		lnkLogout.click();
	}
}
