package test5;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test5.Tray.EmptyTrayException;

public class Main {
	private ByteArrayOutputStream consoleBuffer;
	private final PrintStream origSysOut = System.out;
	
	@BeforeMethod
	public void beforeEachTest() {
		//redirect System.out
		consoleBuffer = new ByteArrayOutputStream();
		System.setOut(new PrintStream(consoleBuffer));
	}

	@Test
	public void main() {

		//create some drinks and glasses
		BeerMug<Beer> bm1 = new BeerMug<>(new Beer());
		BeerMug<Beer> bm2 = new BeerMug<>(new Beer("Rainieeer Beeer!"));
		
		WineGlass<Wine> wg1 = new WineGlass<>(new Wine("Chateau D'yquem!!!"));
		WineGlass<Wine> wg2 = new WineGlass<>(new Wine("BV Chardonnay!"));

		//put the glasses on the tray
		Tray tray = new Tray();
		
		tray.addGlassToTray(bm1);
		tray.addGlassToTray(bm2);
		
		tray.addGlassToTray(wg1);
		tray.addGlassToTray(wg2);

		//browse the tray
		//tray.browseTray();

		//have a drink
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		//tray.getADrink();
		
		//EXPECTED console output strings:
		//Drinking... a Beer with taste: basic beer flavor.
		//Drinking... a Beer with taste: Rainieeer Beeer!
		//Drinking... a Wine with taste: Chateau D'yquem!!!
		//Drinking... a Wine with taste: BV Chardonnay!
		Assert.assertTrue(consoleBuffer.toString().indexOf("Drinking... a Beer with taste: basic beer flavor.") != -1);
		Assert.assertTrue(consoleBuffer.toString().indexOf("Drinking... a Beer with taste: Rainieeer Beeer!") != -1);
		Assert.assertTrue(consoleBuffer.toString().indexOf("Drinking... a Wine with taste: Chateau D'yquem!!!") != -1);
		Assert.assertTrue(consoleBuffer.toString().indexOf("Drinking... a Wine with taste: BV Chardonnay!") != -1);

	}
	
	@AfterMethod
	public void afterEachTest() {
		//restore System.out
		System.setOut(origSysOut);
		
		//print out consoleBuffer to std out
		System.out.println(consoleBuffer.toString());
	}

}
