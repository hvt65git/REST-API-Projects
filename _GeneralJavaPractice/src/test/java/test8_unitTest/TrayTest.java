package test8_unitTest;

import static org.testng.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test8.Beer;
import test8.BeerMug;
import test8.Glass;
import test8.Tray;
import test8.Wine;
import test8.WineGlass;

public class TrayTest {
	
	//test data setup
	private final String beerName = "Japanese Craft Pale Ale";
	private final String beerDefaultName = "Rainier Beer";
	
	private final String wineName = "BV cabernet";
	private final String wineDefaultName = "basic chardonnay";
	
	private final String emptyTray = "The tray is empty";
	
	private final Beer b1 = new Beer();
	private final Beer b2 = new Beer(beerName);
	
	private final Wine w1 = new Wine();
	private final Wine w2 = new Wine(wineName);
	
	private final Glass<Beer> gb1 = new BeerMug<>(b1);
	private final BeerMug<Beer> gb2 = new BeerMug<>(b2);
	
	private final Glass<Wine> wg1 = new WineGlass<>(w1);
	private final WineGlass<Wine> wg2 = new WineGlass<>(w2);
	
	// Store the original standard out before changing it.
	private final PrintStream originalStdOut = System.out;
	private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();
	

	@BeforeTest
	public void beforeMethod() {
		// Redirect all System.out to consoleContent.
		System.setOut(new PrintStream(this.consoleContent));
	}

	@Test(enabled = true)
	public void addGlassToTray() {
		final Tray tray = new Tray();
		
		//add glass wg1 to tray
		tray.addGlassToTray(wg1);
		
		//browse the tray
		tray.browseTray();
		
		//look for wineDefaultName in console output
		assertTrue(this.consoleContent.toString().indexOf(wineDefaultName)!=-1);
	}
	
	

	@Test(enabled = false)
	public void getGlassAndDrinkIt() {
		//create a local tray object - declared in body of this method
		
		final String expectedAfterAddingGlassGb2 = "Browsing a: Beer with name: Japanese Craft Pale Ale";
		final String expectedAfterRemovingGlassGb2 = "Drinking a: Beer with name: Japanese Craft Pale Ale";
				
		Tray tray = new Tray();
		
		//add a glass with name beerName to the tray
		tray.addGlassToTray(gb2);
		
		//browse the tray and make sure glass is there
		tray.browseTray();
		
		//console output:
		//Browsing a: Beer with name: Japanese Craft Pale Ale
		
		//look for wineDefaultName in console output
		assertTrue(this.consoleContent.toString().indexOf(expectedAfterAddingGlassGb2)!=-1);
		
		//remove the glass and drink it
		tray.getGlassAndDrinkIt();
		
		//console output:
		//Drinking a: Beer with name: Japanese Craft Pale Ale
		
		//look for wineDefaultName in console output, a fail if it is there
		assertTrue(this.consoleContent.toString().indexOf(expectedAfterRemovingGlassGb2)!=-1);
	}

		
	@Test(enabled = false)
	public void browseTrayAfterAddingGlass() {
		final Tray tray = new Tray();
		
		//add glass wg2 to tray
		tray.addGlassToTray(wg2);
		
		//browse the tray
		tray.browseTray();
		
		//look for wineName in console output
		assertTrue(this.consoleContent.toString().indexOf(wineName)!=-1);
	}
	
	@Test(enabled = true)
	public void browseTrayAfterRemovingGlass() {
		final Tray tray = new Tray();
		
		//add glass gb1 to tray
		tray.addGlassToTray(gb1);
		
		//browse the tray
		tray.browseTray(); 
		
		//console output:
		//Browsing a: Beer with name: Rainier Beer!
		
		//look for beerDefaultName in console output
		assertTrue(this.consoleContent.toString().indexOf(beerDefaultName)!=-1);
		
		//drink it
		tray.getGlassAndDrinkIt();
		
		//console output:
		//Drinking a: Beer with name: Rainier Beer!		
		
		//browse the tray 
		tray.browseTray();
		
		//console output:
		//The tray is empty.
		
		//assert that we receive empty tray message
		assertTrue(this.consoleContent.toString().indexOf(emptyTray)!=-1);
		
		//console output
		//[RemoteTestNG] detected TestNG version 6.14.2
		//Browsing a: Beer with name: Rainier Beer!
		//Drinking a: Beer with name: Rainier Beer!
		//The tray is empty.
	}
	

	@AfterTest
	public void afterMethod() {
		// Put back the standard out.
		System.setOut(this.originalStdOut);

		// Print what has been captured.
		System.out.println(this.consoleContent.toString());

		// Clear the consoleContent.
		this.consoleContent = new ByteArrayOutputStream();    
	}
}

