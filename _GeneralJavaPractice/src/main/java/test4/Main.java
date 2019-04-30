package test4;

public class Main {
	
	public static void main(String[] arg) {
		
		//create some beer
		Beer b1 = new Beer("Coors light!");
		Drink b2 = new Beer("Raineeer beeer!");	
		Liquid b3 = new Beer("polymorphic beer :D");
		
		//add the beer to some glasses
		GenericGlass<Liquid> gg1 = new Glass<>(b1);
		GenericGlass<Liquid> gg2 = new Glass<>(b2);
		GenericGlass<Liquid> gg3 = new Glass<>(b3);
		
		Glass<Liquid> g1 = new Glass<>(b1);
		//Glass<Liquid> g2 = new BeerMug<Beer>(b2);//no can d
		BeerMug<Beer> bm1 = new BeerMug<>(b1); //could not add b2 or b3 error:  Cannot infer type arguments 
		
		//put into tray
		Tray tray = new Tray();
		//tray.addGlass(gg1); //no can do! cannot add a GenericGlass to tray
		tray.addGlass(g1);
		tray.addGlass(bm1);
		tray.browse();
	

	}

}
