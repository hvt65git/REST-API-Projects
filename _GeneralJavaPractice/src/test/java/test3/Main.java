package test3;

import test3.Tray.EmptyTrayException;

public class Main {
	
	
	private static void getGlassAndDrinkIt(Tray tray) {
		
		try {
			tray.removeGlass().drink();
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		
		// create some beer
		Beer rb = new RainierBeer("Rainieeeer Beeer!");
		
		//generic beers
		Beer b1 = new Beer();
		Beer b2 = new Beer("Coors light!");
		
		//put the beers in beer mugs
		BeerMug bm1 = new BeerMug(rb);
		BeerMug bm2 = new BeerMug(b1);
		BeerMug bm3 = new BeerMug(b2);
			
		//create a pepsi
		Pepsi pep1 = new Pepsi();
		Pepsi pep2 = new Pepsi("Fresh Pepsi!");
		
		//put pepsi in glasses
		Glass<Drink> pg1 = new Glass<>(pep1);
		Glass<Pepsi> pg2 = new Glass<>(pep2);
			
		//create some wine
		Wine w1 = new Wine();
		Wine w2 = new Wine("BV chardonnay!");
		
		//put the wine in a wine glass
		Glass<Wine> gw1 = new Glass<>(w1);
		WineGlass<Wine> wg1 = new WineGlass(w2);

		//add all the glasses to the tray
		Tray tray = new Tray();
		tray.addGlass(pg1);
		tray.addGlass(pg2);
		tray.addGlass(gw1);
		tray.addGlass(wg1);		
		tray.addGlass(bm1);
		tray.addGlass(bm2); 
		tray.addGlass(bm3);
		

		//remove each glass and drink it
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);
		getGlassAndDrinkIt(tray);

		
		//peek at tray
		tray.peekAtGlasses();
	}

}
