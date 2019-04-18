package test5;

import test5.Tray.EmptyTrayException;

public class Main {


	public static void main(String... s) {

		//create some drinks and glasses
		BeerMug<Beer> bm1 = new BeerMug<>(new Beer());
		BeerMug<Beer> bm2 = new BeerMug<>(new Beer("Rainieeer Beeer!"));
	

		//put the glasses on the tray
		Tray tray = new Tray();
		tray.addGlassToTray(bm1);
		tray.addGlassToTray(bm2);

		//browse the tray
		tray.browseTray();

		//have a drink
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		
		tray.browseTray();


	}

}
