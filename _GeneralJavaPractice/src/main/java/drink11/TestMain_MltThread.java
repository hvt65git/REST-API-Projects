package drink11;

import static drink11.DrinkType.BEER;
import static drink11.DrinkType.WINE;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*bugbug threading behavior buggy not deterministic 
 * when classes are all in separate files
 * in test9 dir all classes are in same file and it works 
 * perfectly for a hundred threads
 * BUG fixed - fixed code in BufferFactory with global variable:
 * private ByteArrayOutputStream consoleBuffer = new ByteArrayOutputStream();
 */
public class TestMain_MltThread extends BufferFactory {

	@Test(enabled = true, dataProvider = "testDataProvider")
	public void main(DrinkType dt, String name) throws Exception {
		//redirect stdout to new buffer - concurrent System.setOut in BeforeMethod will lose synch
		//System.setOut(new PrintStream(getConsoleBuffer()));
		
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
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{WINE, "7 - Coors Lite"}
		};
	}
}