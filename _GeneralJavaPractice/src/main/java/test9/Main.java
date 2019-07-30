package test9;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static test9.DrinkType.BEER;
import static test9.DrinkType.WINE;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

enum DrinkType {
	BEER, WINE
}

interface Liquid {
	public String getName();
}

abstract class Drink implements Liquid {
	private String name;

	public String getName() {
		return this.name;
	}

	public Drink(String name) {
		this.name = name;
	}
}


class Beer extends Drink {
	public Beer(String name) {
		super(name);
	}
}

class Wine extends Drink {
	public Wine(String name) {
		super(name);
	}
}

abstract class Glass<X extends Drink> {
	X drink;

	public void drinkIt() {
		System.out.println("Now drinking: " + this.drink.getName());
	}

	public Glass(X drink) {
		this.drink = drink;
	}
}


class BeerMug<X extends Beer> extends Glass<Beer>{
	public BeerMug(Beer beer) {
		super(beer);
	}
}

class WineGlass<X extends Wine> extends Glass<Wine> {
	public WineGlass(Wine wine) {
		super(wine);
	}
}

abstract class BufferFactory {
	private static final PrintStream origStdOut = System.out;
	private static final ThreadLocal<ByteArrayOutputStream> tl = new ThreadLocal<>();


	public static ByteArrayOutputStream getConsoleBuffer() {
		return tl.get();
	}

	@BeforeMethod
	public void beforeEachTest() {
		//create the console buffer and set it in the thread mgr
		tl.set(new ByteArrayOutputStream());
	}

	@AfterMethod
	public void afterEachTest() throws Exception {		
		System.setOut(origStdOut);
		//System.out.println("\r\n*** In @AfterMethod: " + tl.get().toString() + "- after System.setOut(origStdOut);->  currentThread.Id = " + Thread.currentThread().getId());
		tl.remove();
	}
}


public class Main extends BufferFactory {
	
	@DataProvider(parallel = true)
	public Object[][] testDataProvider() {
		return new Object[][] {
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{BEER, "4 - Kirin Beer"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{WINE, "7 - Coors Lite"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{BEER, "4 - Kirin Beer"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{WINE, "7 - Coors Lite"},
			{BEER, "1 - Raineeer Beeer"},
			{BEER, "2 - Sapporo Beer"},
			{WINE, "3 - BV Chardonnay"},
			{BEER, "4 - Kirin Beer"},
			{WINE, "5 - BV Cabernet"},
			{WINE, "6 - Vueve Cliquot"},
			{WINE, "7 - Coors Lite"}
		};
	}

	@Test(enabled = true, dataProvider = "testDataProvider")
	public void main(DrinkType dt, String name) throws Exception {
		//redirect stdout to new buffer - concurrent System.setOut in BeforeMethod will lose synch
		System.setOut(new PrintStream(getConsoleBuffer()));
		//System.out.println("\r\n* In Main: " + name + " - after System.setOut ->  currentThread.Id = " + Thread.currentThread().getId());
		
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

}
