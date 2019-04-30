package test6;

public class Main {
	
	public static void main(String[] a) {
		//create some drinks
		Beer b1 = new Beer();
		Beer b2 = new Beer("Coors lite");
		
		Soda s1 = new Soda();
		Drink s2 = new Soda("Coca-Cola");
		Liquid s3 = new Soda("Orange Fanta");
		
		//put them in glasses
		BeerMug<Beer> bm1 = new BeerMug<>(b1);
		BeerMug<Beer> bm2 = new BeerMug<>(b2);
		
		Glass<Soda> sd1 = new Glass<>(s1);
		Glass<Drink> sd2 = new Glass<>(s1);
		Glass<Liquid> sd3 = new Glass<>(s1);
		
		
		//put them on the tray
		Tray tray = new Tray();
		tray.addDrinkToTray(bm1);
		tray.addDrinkToTray(bm2);
		
		tray.addDrinkToTray(sd1);
		tray.addDrinkToTray(sd2);
		tray.addDrinkToTray(sd3);
		
		//browse the tray
		tray.browseTray();
		
		//drink away!
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		tray.getADrink();
		
		//browse the tray
		tray.browseTray();
		
		
	}

}
