package interview_practice_drink_i1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drink_i1.Beer;

public class TestDrink {
	//declare constants
	private static final PrintStream origStdOut = System.out;
	private ByteArrayOutputStream consoleBuffer = new ByteArrayOutputStream();

	//declare constants for test data
	private static final String failedMessage = "Expected object to not be created.";
	private static final String badNameBlank = "";
	private static final String badNameNull = null;
	private static final String badNameBlankSpace = " ";
	private static final String goodBeerName = " Great Rainier Beer!  ";


	@BeforeMethod(enabled = true)
	public void beforeEachTest() {
		//redirect sysout
		System.setOut(new PrintStream(consoleBuffer));
	}

	@Test(enabled = false)
	//(1) test Beer() blank name in constructor - verify object rejection using sysout response	
	public void testDrink_badNameBlank() {
		final String testExpectedValue = "String is blank";
		try {
			Beer beer = new Beer(badNameBlank);
			Assert.fail(failedMessage);
		}
		catch(Exception e) {
			//print error message to consoleBuffer
			System.out.println(e.getMessage());

			//assert we receive expected value:
			Assert.assertTrue(consoleBuffer.toString().indexOf(testExpectedValue) != -1 );
		}

	}

	@Test(enabled = false)
	//(2) test Wine() null name in constructor - verify object rejection using sysout response	}
	public void testDrink_badNameNull() {
		final String testExpectedValue = "name is null";
		try {
			Beer beer = new Beer(badNameNull);
			Assert.fail(failedMessage);
		}
		catch(Exception e) {
			//print out error to consoleBuffer
			System.out.println(e.getMessage());

			int i = consoleBuffer.toString().indexOf(testExpectedValue);

			//assert we received expected error message
			Assert.assertTrue(consoleBuffer.toString().indexOf(testExpectedValue) != -1);
		}
	}

	@Test(enabled = false)
	//(3) test Beer() one blank space name in constructor - verify object created using sysout response
	public void testDrink_badNameBlankSpace() {

	}

	@Test(enabled = false)
	//(4) test Wine() non blank name by calling getName() method - verify using sysout response
	public void testDrink_() {

	}

	@Test(enabled = true)
	//(5) test Beer() goodBeerName name in getName call 
	public void testDrink_goodBeerName() {
		//final String testExpectedValue = goodBeerName;
		try {
			//arrange - create beer object
			Beer beer = new Beer(goodBeerName);

			//act - call getName method
			System.out.println("Drinking: " + beer.getName());

			//assert we receive goodBeerName in out buffer
			Assert.assertTrue(consoleBuffer.toString().indexOf("dd") != -1);			
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}



	@AfterMethod(enabled = true)
	public void afterEachTest() {
		//restore sysout
		System.setOut(origStdOut);

		//print out PrintStream buffer to std out
		System.out.println(consoleBuffer);

		//reset consoleBuffer
		consoleBuffer = new ByteArrayOutputStream();
	}


}
