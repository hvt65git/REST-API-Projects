package test7;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Main {

	//back up stdout
	PrintStream origStdOut = System.out;

	//create the buffer for the redirected std out
	ByteArrayOutputStream consoleBuffer = new ByteArrayOutputStream();

	@BeforeMethod(enabled = true)
	public void beforeTest() {
		//redirect std out
		System.setOut(new PrintStream(consoleBuffer));
	}

	@Test()
	public void main() {

		//create some drinks
		Beer b1 = new Beer();
		Beer b2 = new Beer("Coors lite.");

		Wine w1 = new Wine();
		Wine w2 = new Wine("BV cabernet.");

		//add them to glasses
		BeerMug<Beer> bm1 = new BeerMug<>(b1);
		BeerMug<Beer> bm2 = new BeerMug<>(b2);

		WineGlass<Wine> wg1 =  new WineGlass<>(w1);
		WineGlass<Wine> wg2 =  new WineGlass<>(w2);

		//add to tray
		Tray tray = new Tray();
		tray.addGlassToTray(bm1);
		tray.addGlassToTray(wg1);

		//verify std out displays:
		//Browsing Tray: found a BeerMug
		//Browsing Tray: found a WineGlass

		//browse the tray
		tray.browseDrinks();

		//assert the buffer does contain desired strings
		Assert.assertTrue(consoleBuffer.toString().indexOf("Browsing Tray: found a BeerMug") != -1);
		Assert.assertTrue(consoleBuffer.toString().indexOf("Browsing Tray: found a WineGlass") != -1);
	}
	@AfterMethod(enabled = true)
	public void afterTest() {
		//restore std out
		System.setOut(origStdOut);

		//display the buffer to std out
		System.out.println("console buffer: " + consoleBuffer.toString());

	}

}
