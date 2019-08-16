package MAVENModule._SeleniumAdactinTests.utils;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Conditions {

	public static Boolean waitForPageToLoad(WebDriver driver, long WAIT_TIME_OUT) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT);

		// wait for jQuery to finish loading
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				try {
					return ((Long)js.executeScript("return jQuery.active") == 0);
				}
				catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to finish loading
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor)driver;
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	public static Function<WebElement, WebElement> visibilityOf()  {	
		return new Function<WebElement,WebElement>() {
			@Override
			public WebElement apply(WebElement element) {
				return (element.isDisplayed()) ? element : null;
			}
		};
	}

	public static ExpectedCondition<Boolean> pageTitleContains(String term)  {	
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getTitle().contains(term);
			}
		};
	}

	public static ExpectedCondition<WebElement> presenceOfElementLocated(By by)  {	
		return new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		};
	}
	
	
	

}