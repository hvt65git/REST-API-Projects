package MAVENModule._SeleniumAdactinTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import MAVENModule._SeleniumAdactinTests.system.DriverFactory;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

public class LoginPage {
	private long WAIT_TIME_OUT = Integer.parseInt(Utils.getSettingFromFile("WAIT_TIME_OUT"));
	
	//set the constant expressions to be used in @FindBy args
	private final String XPATH_TXT_USERNAME = "//*[@id='username']";
	private final String XPATH_TXT_PASSWORD = "//*[@id='password']";
	private final String XPATH_BTN_LOGIN = "//*[@id='login']";

	@FindBy(xpath =  "//*[@id='username']")
	WebElement txtUsername;

	@FindBy(xpath = XPATH_TXT_PASSWORD)
	WebElement txtPassword;

	@FindBy(xpath = XPATH_BTN_LOGIN)
	WebElement btnSubmitLogin;

	public LoginPage() throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();
		PageFactory.initElements(driver, this);
		driver.get(Utils.getSettingFromFile("URL"));
		//driver.manage().window().maximize();
	}

	public void login() throws Exception {
		WebDriver driver = DriverFactory.getWebDriver();

		//wait for username txt box to appear - wait by proxy
		new WebDriverWait(driver, WAIT_TIME_OUT)
		.until(ExpectedConditions.visibilityOf(txtUsername));

		//enter the username
		txtUsername.sendKeys(Utils.getSettingFromFile("Username"));

		//enter the password
		txtPassword.sendKeys(Utils.getSettingFromFile("Password"));

		//click the button
		btnSubmitLogin.click();
		System.out.println("Login just executed...");

		//verify login was successful - find the last element on the page - the reset button
		String XpathElement = Utils.getSettingFromFile("XPATH_BTN_RESET");
		new WebDriverWait(driver, WAIT_TIME_OUT)
		.until(d->d.findElement(By.xpath(XpathElement)));
		System.out.println("Login was successful");

	}
}