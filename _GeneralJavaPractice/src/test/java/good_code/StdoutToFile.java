package good_code;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.testng.Assert.assertTrue;

public class StdoutToFile {
	private final String filename = "C:\\Users\\nightbeats2\\eclipse-workspace\\testoutput.txt";
	private final String testname = "Japanese Craft Beer - Pale Ale";

	// Store the original standard out before changing it.
	private final PrintStream originalStdOut = System.out;
	private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();

	@BeforeMethod
	public void beforeTest() {
		// Redirect all System.out to consoleContent.
		System.setOut(new PrintStream(this.consoleContent));
	}

	@Test(description="Test method 1")
	public void testMethod1() {
		System.out.print(testname);

		//ht added - this appears to be what i need to test my BeerMug class-yes success!
		assertTrue(this.consoleContent.toString().indexOf(testname)!=-1);
	}


	@AfterMethod
	public void afterTest() {

		//write console output to file
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "utf-8")) ;

			System.setOut(new PrintStream(new FileOutputStream(filename)));

			System.out.println(this.consoleContent.toString());

			System.out.println(
					String.format("===>Captured Console length=%d", 
							this.consoleContent.toString().length()));

			writer.close();
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Put back the standard out and output to console
		System.setOut(this.originalStdOut);

		// Print what has been captured.
		System.out.println(this.consoleContent.toString());

		System.out.println(
				String.format("===>Captured Console length=%d", 
						this.consoleContent.toString().length()));

		// Clear the consoleContent.
		this.consoleContent = new ByteArrayOutputStream();    
	}
}
