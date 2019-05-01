package test8_unitTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import test8.Beer;

public class BeerTest {
	//global constants
	private final String name = "Japanese Craft Pale Ale!";
	private final String beerDefaultName = "Rainier Beer!";

	@Test()
	public void testBeerGetName() {
		Beer beer = new Beer(name);
		assertEquals(beer.getName(), name);
	}
	
	@Test()
	public void testBeerGetNameDefault() {
		Beer beer = new Beer();
		assertEquals(beer.getName(), beerDefaultName);
	}
}
