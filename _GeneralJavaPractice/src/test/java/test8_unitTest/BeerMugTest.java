package test8_unitTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import test8.Beer;
import test8.BeerMug;

public class BeerMugTest {
	
	//test data
	private final String beerDefaultName = "Rainier Beer!";	
	private final String beerName = "Japanese Craft Pale Ale!";

	private final Beer beerNoParamConstructor = new Beer();
	private final Beer beerSingleParamConstructor = new Beer(beerName);
	


	@Test
	public void beerNoParamConstructor() {
		
		//create a BeerMug for beerNoParamConstructor
		BeerMug<Beer> bm = new BeerMug<>(beerNoParamConstructor);
		
		//drink the beer and verify string appears in console
		bm.DrinkIt();
		
		//assert that the expected string appears in the console buffer
		
	
	}
	

	@Test
	public void beerSingleParamConstructor() {
		
		//create a BeerMug for beerNoParamConstructor
		BeerMug<Beer> bm = new BeerMug<>(beerSingleParamConstructor);
		
		//drink the beer and verify string appears in console
		bm.DrinkIt();
		

		//assert that the expected string appears in the console buffer
		

	}
	
	@AfterMethod
	public void afterTest() {
		//Restore the standard out.
		
		//print out the console buffer to standard out
		
		
		//reset the console buffer
		

	}
}
