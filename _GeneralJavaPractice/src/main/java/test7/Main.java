package test7;

public class Main {
	
	
	public static void main(String...strings) {
		
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
		tray.addGlassToTray(bm2);
		tray.addGlassToTray(wg1);
		tray.addGlassToTray(wg2);
		
		//browse the tray
		tray.browseDrinks();
		
		//drink the drinks
		tray.drinkIt();
		tray.drinkIt();
		tray.drinkIt();
		tray.drinkIt();
		tray.drinkIt();
		tray.drinkIt();
		
		
		//browse the tray
		tray.browseDrinks();
	}

}
