package good_code;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
 
 
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
 
import static org.testng.Assert.assertTrue;
 
/**
 * Show how to unit test standard output.
 */
public class StandardOutputTest
{
  // Variables
  Random random = new Random();
  final int MAX = 100; // Milliseconds.
 
  // Store the original standard out before changing it.
  private final PrintStream originalStdOut = System.out;
  private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
 
 
  @BeforeMethod
  public void beforeTest()
  {
    // Redirect all System.out to consoleContent.
    System.setOut(new PrintStream(this.consoleContent));
  }
 
  @Test(description="Test method 1", invocationCount=3)
  public void testMethod1()
  {
    final int iRnd = this.random.nextInt(MAX*10);
    this.pause(iRnd);
    System.out.print(String.format("Test method 1 (Work Time = %d ms).", iRnd));
 
    //ht added - this appears to be what i need to test my BeerMug class
    assertTrue(this.consoleContent.toString().indexOf("Test method zzzz")!=-1);
  }
 
  @Test(description="Test method 2", invocationCount=3)
  public void testMethod2()
  {
    final int iRnd = this.random.nextInt(MAX);
    this.pause(iRnd);
    System.out.print(String.format("Test method 2 (Work Time = %d ms).", iRnd));
 
    assertTrue(this.consoleContent.toString().indexOf("Test method 2")!=-1);
  }  
 
  @AfterMethod
  public void afterTest()
  {
    // Put back the standard out.
    System.setOut(this.originalStdOut);
 
    // Print what has been captured.
    System.out.println(this.consoleContent.toString());
    System.out.println(String.format("              ====>Captured Console length=%d", 
    		this.consoleContent.toString().length()));
 
    // Clear the consoleContent.
    this.consoleContent = new ByteArrayOutputStream();    
  }
 
  /***********
   * Helpers
   ************/
  // Simulate some processing time by pausing.
  private void pause(long lPauseInMillisSec)
  {
    try
    {
      Thread.sleep(lPauseInMillisSec);
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
 
  }  
}