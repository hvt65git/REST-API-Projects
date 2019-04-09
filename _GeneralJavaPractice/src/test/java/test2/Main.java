package test2;

import test2.Tray.EmptyTrayException;

public class Main {

	private static void getGlass(Tray tray) {

		try {
			tray.getGlass().drink();
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String...arg) {

		//create some beer
		Beer b1 = new Beer();
		Beer b2 = new Beer("Budddd Weiserrr!");

		Beer rb1 = new RainerBeer();
		Beer rb2 = new RainerBeer("refreshing Rainier Beer taste!");

		//create some pepsi
		Drink pep1 = new Pepsi("Pepsi!");
		Drink pep2 = new Pepsi();

		//create some wine
		Wine wine1 = new Wine();
		Wine wine2 = new Wine("Mumm champgne!");
		Wine wine3 = new BVCabernet();
		Wine wine4 = new BVCabernet("Fresh BV cab!");

		//create beer glasses and add the beers
		Glass<Beer> bg1	= new BeerGlass<Beer>(b1);
		BeerGlass<Beer> bg2	 = new BeerGlass<Beer>(b2);

		Glass<Beer> bg3	= new BeerGlass<Beer>(rb1);
		BeerGlass<Beer> bg4	 = new BeerGlass<Beer>(rb2);

		//		//drink the beer
		//		bg1.drink();
		//		bg2.drink();
		//		bg3.drink();
		//		bg4.drink();

		//create some wine glasses and add the win
		Glass<Wine> wg1	= new WineGlass<Wine>(wine1);
		WineGlass<Wine> wg2	 = new WineGlass<Wine>(wine2);

		Glass<Wine> wg3	= new WineGlass<Wine>(wine3);
		WineGlass<Wine> wg4	 = new WineGlass<Wine>(wine4);

		//		//drink the wine
		//		wg1.drink();
		//		wg2.drink();
		//		wg3.drink();
		//		wg4.drink();

		//create a tray
		Tray tray = new Tray();

		//test the peek method - success
		//tray.peekAtAllDrinks();

		//add all the glasses to the tray
		tray.addGlass(bg1);
		tray.addGlass(bg2);
		tray.addGlass(bg3);
		tray.addGlass(bg4);

		tray.addGlass(wg1);
		tray.addGlass(wg2);
		tray.addGlass(wg3);
		tray.addGlass(wg4);

		//test the peek method - success
		//tray.peekAtAllDrinks();

		getGlass(tray);
		getGlass(tray);
		getGlass(tray);
		getGlass(tray);
		tray.peekAtAllDrinks();

		//success!
	}
}

//key method to remember 
//tricky point: a method that returns an object or throws an exception for a call
//public Glass<?> getGlass() throws EmptyTrayException {
//
//  //throw an exception 
//	if(this.tray.isEmpty()) {
//		throw new EmptyTrayException();
//	}

	//or return an object
//	return this.tray.remove(0);
//}
//or use if else return:
//public Glass<?> getGlass() throws EmptyTrayException {
//
//	if(this.tray.isEmpty()) {
//		throw new EmptyTrayException();
//	}
//	else {
//		return this.tray.remove(0);
//	}
//}


