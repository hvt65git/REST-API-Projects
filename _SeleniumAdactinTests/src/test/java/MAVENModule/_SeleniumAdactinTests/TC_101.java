package MAVENModule._SeleniumAdactinTests;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MAVENModule._SeleniumAdactinTests.system.SeleniumTest;
import MAVENModule._SeleniumAdactinTests.utils.Conditions;
import MAVENModule._SeleniumAdactinTests.utils.Utils;

/*
 * TEST CASE ID:	TC_101 
 * OBJECTIVE:		Verify valid login
 * STEPS:			1. Launch hotel reservation application using URL as in test data.
 *					2. Login to the application using username and password as in test data.			
 * 
 */
public class TC_101 extends SeleniumTest {

	public TC_101() throws Exception {}

	@Test
	public void tc101_verify_valid_login(){
		try {
			new LoginPage().login();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
}
