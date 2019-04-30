package test8_unitTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import test8.Beer;
import test8.BeerMug;

public class testBeerMugClass {
	
	//create test data for the BeerMug class constructor
	private final String beerDefaultName = "Rainier Beer!";	
	private final String beerName = "Japanese Craft Pale Ale!";

	private final Beer beerNoParamConstructor = new Beer();
	private final Beer beerSingleParamConstructor = new Beer(beerName);
	
	// Store the original standard out before changing it.
	private final PrintStream originalStdOut = System.out;
	private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
	

	@BeforeMethod
	public void beforeTest() {
		// Redirect all System.out to consoleContent.
		System.setOut(new PrintStream(this.consoleContent));
	}

	@Test
	public void beerNoParamConstructor() {
		
		//create a BeerMug for beerNoParamConstructor
		BeerMug<Beer> bm = new BeerMug<>(beerNoParamConstructor);
		
		//drink the beer and verify string appears in console
		bm.DrinkIt();
		assertTrue(this.consoleContent.toString().indexOf(beerDefaultName)!=-1);
	}
	

	@Test
	public void beerSingleParamConstructor() {
		
		//create a BeerMug for beerNoParamConstructor
		BeerMug<Beer> bm = new BeerMug<>(beerSingleParamConstructor);
		
		//drink the beer and verify string appears in console
		bm.DrinkIt();
		assertTrue(this.consoleContent.toString().indexOf(beerName)!=-1);
	}
	
	@AfterMethod
	public void afterTest() {
		// Put back the standard out.
		System.setOut(this.originalStdOut);

		// Print what has been captured.
		System.out.println(this.consoleContent.toString());

		// Clear the consoleContent.
		this.consoleContent = new ByteArrayOutputStream();    
	}
}
