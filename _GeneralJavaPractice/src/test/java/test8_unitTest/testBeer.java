package test8_unitTest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import test8.Beer;

public class testBeer {
	private static final String name = "Japanese Craft Pale Ale!";
	private static final String defaultName = "Rainier Beer!";

	@Test
	public void testBeerGetName() {
		Beer beer = new Beer(name);
		assertEquals(name, beer.getName());
	}
	
	@Test
	public void testBeerGetNameDefault() {
		Beer beer = new Beer();
		assertEquals(defaultName, beer.getName());
	}

}
