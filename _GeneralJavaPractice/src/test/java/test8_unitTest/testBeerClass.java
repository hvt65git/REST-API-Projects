package test8_unitTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import test8.Beer;

public class testBeerClass {
	//create test string for single param constructor
	private static final String name = "Japanese Craft Pale Ale!";
	private static final String beerDefaultName = "Rainier Beer!";

	@Test(description="Testing Single Param Constructor")
	public void testBeerGetName() {
		Beer beer = new Beer(name);
		assertEquals(name, beer.getName());
	}
	
	@Test(description="Testing No Param Constructor")
	public void testBeerGetNameDefault() {
		Beer beer = new Beer();
		assertEquals(beerDefaultName, beer.getName());
	}
}
