package drink10;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static drink10.DrinkType.BEER;
import static drink10.DrinkType.WINE;


import java.io.PrintStream;


public class TestMain_MultiThread extends BufferFactory {

	@Test(enabled = true, dataProvider = "testDataProvider")
	public void main(DrinkType dt, String name) throws Exception {
		//redirect stdout to new buffer - concurrent System.setOut in BeforeMethod will lose synch
		System.setOut(new PrintStream(getConsoleBuffer()));
		
		Glass<Drink> g = null;

		//create a drink and put in a glass
		switch(dt) {
		case BEER:
			g = new BeerMug(new Beer(name)); 
			break;
		case WINE:
			g = new WineGlass(new Wine(name)); 
			break;
		}

		//drink the drink - writes to console buffer now 
		g.drinkIt();

		String consoleBuffer = getConsoleBuffer().toString(); 

		//verify you drank the correct drink by testing its name in consoleBuffer
		Assert.assertTrue((consoleBuffer.indexOf(name)) != -1);
	}

	@DataProvider(parallel = true)
	public Object[][] testDataProvider() {
		return new Object[][] {
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{WINE, "7 - Coors Lite"}
		};
	}
}