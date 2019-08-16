package MAVENModule._SeleniumAdactinTests;

import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.utils.Conditions;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

public class BookedItineraryPage {
	private final String curOrderID;
	private long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));
	
	private final String XPATH_LBL_SEARCH_RESULT_ERROR 	=	"//*[contains(text(), '0 result(s) found')]";
	private final String XPATH_TXT_SEARCH_ORDER_ID		= 	"//*[@id='order_id_text']";
	private final String XPATH_BTN_SEARCH_GO 			= 	"//*[@id='search_hotel_id']";
	//private final String XPATH_TXT_ORDER_ID 			=   "//*[starts-with(@id, 'order_id_') AND @class='select_text']"; //proxy find failed
	private final String XPATH_TXT_ORDER_ID 			=   "//*[starts-with(@id, 'order_id_')]";
	private final String XPATH_TXT_HOTEL_NAME 			=	"//*[starts-with(@id, 'hotel_name_')]";
	private final String XPATH_TXT_LOCATION				= 	"//*[starts-with(@id, 'location_')]";
	private final String XPATH_TXT_TOTAL_ROOMS 			=	"//*[starts-with(@id, 'rooms_')]";
	private final String XPATH_TXT_ARRIVAL_DATE 		= 	"//*[starts-with(@id, 'arr_date_')]";
	private final String XPATH_TXT_DEPARTURE_DATE 		= 	"//*[starts-with(@id, 'dep_date_')]";
	private final String XPATH_TXT_FINAL_PRICE 			= 	"//*[starts-with(@id, 'total_price_')]";
	
	private final String XPATH_BTN_SUB_CANCEL_ITEM		=	"//*[@value='Cancel Selected']";
	private final String XPATH_CBX_ALL					=	"//*[@id='check_all']";

	@FindBy(xpath = XPATH_LBL_SEARCH_RESULT_ERROR)
	private WebElement searchResultErrorLabel;
	
	@FindBy(xpath = XPATH_TXT_SEARCH_ORDER_ID)
	private WebElement searchOrderIdTxt;

	@FindBy(xpath = XPATH_BTN_SEARCH_GO)
	private WebElement searchOrderIdGo;

	@FindBy(xpath = XPATH_CBX_ALL)
	private WebElement checkAll;

	@FindBy(xpath = XPATH_BTN_SUB_CANCEL_ITEM)
	private WebElement cancelItem;

	@FindBy(xpath = XPATH_TXT_HOTEL_NAME)
	private WebElement hotelName;

	@FindBy(xpath = XPATH_TXT_LOCATION)
	private WebElement location;

	@FindBy(xpath = XPATH_TXT_TOTAL_ROOMS)
	private WebElement totalRooms;

	@FindBy(xpath = XPATH_TXT_ARRIVAL_DATE)
	private WebElement arrivalDate;

	@FindBy(xpath = XPATH_TXT_DEPARTURE_DATE)
	private WebElement departureDate;

	@FindBy(xpath = XPATH_TXT_FINAL_PRICE)
	private WebElement finalPrice;

	public BookedItineraryPage(String curOrderID) {
		this.curOrderID = curOrderID;
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		Conditions.waitForPageToLoad(driver, WAIT_TIME_OUT);
		driver.manage().window().maximize();
	}

	public void setHotelTxt(String text) throws Exception {
		hotelName.sendKeys(text);	
	}

	public String getHotelName() throws Exception {
		return hotelName.getAttribute("value");
	}

	public String getLocation() throws Exception {
		return location.getAttribute("value");
	}

	public String getTotalRooms() throws Exception {
		return totalRooms.getAttribute("value");
	}

	public String getArrivalDate() throws Exception {
		return arrivalDate.getAttribute("value");
	}

	public String getDepartureDate() throws Exception {
		return departureDate.getAttribute("value");
	}

	public String getFinalPrice() throws Exception {
		return finalPrice.getAttribute("value");
	}

	public String getOrderNo()  throws Exception {
		try {
			String attribute = "class";
			String val = "select_text";
			String temp = null;

			//get all the orderIds
			List<WebElement> orderIds = new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
			.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_TXT_ORDER_ID)));

			for(WebElement current : orderIds) {	
				temp = current.getAttribute(attribute);
				if(temp.equals(val)) {
					//test for match
					String orderID = current.getAttribute("value");
					if(orderID.equals(curOrderID)) {
						return orderID;
					}
				}	
			}
			return null;
		}
		catch(Exception e) {
			return null;
		}
	}

	public void cancelItem()  throws Exception {

	}

	public void cancelSelectedAll()  throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();
		new WebDriverWait(driver, WAIT_TIME_OUT)
		.until(ExpectedConditions.visibilityOf(checkAll));
		checkAll.click(); //success

		new WebDriverWait(driver, WAIT_TIME_OUT)
		.until(ExpectedConditions.visibilityOf(cancelItem));
		cancelItem.click();

		Alert alert = new WebDriverWait(driver, WAIT_TIME_OUT)
		.until(ExpectedConditions.alertIsPresent());

		alert = driver.switchTo().alert();
		alert.accept();

	}

	public void searchOrderID(String desiredOrderID) throws NoSuchElementException, Exception {	
		new WebDriverWait(DriverFactory.getWebDriver(),  WAIT_TIME_OUT)
		.until(visibilityOf(searchOrderIdTxt)).sendKeys(desiredOrderID);
		
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
		.until(visibilityOf(searchOrderIdGo)).click();
	}
	
	public String getSearchResultErrorText() throws NoSuchElementException, Exception {
		new WebDriverWait(DriverFactory.getWebDriver(), WAIT_TIME_OUT)
			.until(ExpectedConditions.visibilityOf(searchResultErrorLabel));
		return searchResultErrorLabel.getText();
	}
}


