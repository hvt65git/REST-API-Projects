package test8_unitTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import static org.testng.Assert.assertTrue;

public class testBeerMug {
	// Variables
	private static final String testname = "Japanese Craft Beer - Pale Ale";

	// Store the original standard out before changing it.
	private final PrintStream originalStdOut = System.out;
	private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();

	@BeforeMethod
	public void beforeTest()
	{
		// Redirect all System.out to consoleContent.
		System.setOut(new PrintStream(this.consoleContent));
	}
	
	@Test(description="Test method 1", invocationCount=1)
	public void testMethod1()
	{
		System.out.print(testname);

		//ht added - this appears to be what i need to test my BeerMug class
		assertTrue(this.consoleContent.toString().indexOf(testname)!=-1);
	}
	
	
	@AfterMethod
	public void afterTest()
	{
		// Put back the standard out.
		System.setOut(this.originalStdOut);

		// Print what has been captured.
		System.out.println(this.consoleContent.toString());
		
		System.out.println(String.format("====>Captured Console length=%d", 
				this.consoleContent.toString().length()));

		// Clear the consoleContent.
		this.consoleContent = new ByteArrayOutputStream();    
	}

}

