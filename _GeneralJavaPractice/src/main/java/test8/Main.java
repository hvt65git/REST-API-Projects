package test8;

public class Main {
	
	public static void main(String[] arg) {
		//create some drinks
		Beer b1 = new Beer();
		Beer b2 = new Beer("Japanese Craft Pale Ale!");
		
		Wine w1 = new Wine();
		Wine w2 = new Wine("BV cabernet");
		
		//put them all in glasses
		Glass<Beer> gb1 = new BeerMug<>(b1);
		BeerMug<Beer> gb2 = new BeerMug<>(b2);
		
		Glass<Wine> wg1 = new WineGlass<>(w1);
		WineGlass<Wine> wg2 = new WineGlass<>(w2);
		
		//put the glasses in the tray
		Tray tray = new Tray();
		tray.addGlassToTray(gb1);
		tray.addGlassToTray(gb2);
		tray.addGlassToTray(wg1);
		tray.addGlassToTray(wg2);
			
		//browse the tray
		//tray.browseTray();
		
		//drink all the drinks
		tray.getGlassAndDrinkIt();
		tray.getGlassAndDrinkIt();
		tray.getGlassAndDrinkIt();
		tray.getGlassAndDrinkIt();		
		tray.getGlassAndDrinkIt();

		
		//tray.browseTray();
	}

}
