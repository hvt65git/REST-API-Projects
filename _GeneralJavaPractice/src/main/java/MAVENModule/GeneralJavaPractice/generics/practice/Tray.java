package MAVENModule.GeneralJavaPractice.generics.practice;


import java.util.ArrayList;
import java.util.List;

interface Liquid {
	public String getDrinkTaste();
	public String getDrinkName();
}

abstract class Drink implements Liquid {
	String name;
	String taste;

	public Drink(String name, String taste) {
		this.name = name;
		this.taste = taste;
	}
	public String getDrinkTaste() {
		return this.taste;

	}
	public String getDrinkName() {
		return this.name;
	}
}

class Juice extends Drink {
	private static String defaultName = "juice";
	private static String defaultTaste = "basic juice taste";

	public Juice() {
		super(defaultName, defaultTaste);
	} 

	public Juice(String name, String taste) {
		super(name, taste);
	} 
}

class Cola extends Drink {
	private static String defaultName = "cola";
	private static String defaultTaste = "basic cola taste";

	public Cola() {
		super(defaultName, defaultTaste);
	} 

	public Cola(String name, String taste) {
		super(name, taste);
	} 
}

class OrangeJuice extends Juice {
	private static String defaultName = "OJ";
	private static String defaultTaste = "orange juice taste";

	public OrangeJuice() {
		super(defaultName, defaultTaste);
	} 
	public OrangeJuice(String name, String taste) {
		super(name, taste);
	} 
}

class Pepsi extends Cola {
	private static String defaultName = "Pepsi";
	private static String defaultTaste = "Pepsi cola taste";

	public Pepsi() {
		super(defaultName, defaultTaste);
	} 
	public Pepsi(String name, String taste) {
		super(name, taste);
	} 
}

class Glass<X extends Drink> {
	X drink;

	public Glass(X drink) {
		this.drink = drink;
	}

	public X getDrink() {
		return drink;
	}
}

class PepsiTray {
	private List<Glass<? super Pepsi>> colaGlasses ;

	public void addGlass(Glass<? super Pepsi> colaGlass) {
		colaGlasses.add(colaGlass);
	}
}

public class Tray {
	List<Glass<?>> glasses = new ArrayList<>() ;

	public void addGlass(Glass<?> glass) {
		glasses.add(glass);
	}

	public void tasteDrinks() {
		for(int i=0; i<glasses.size(); i++) {
			System.out.println("drink name: " + glasses.get(i).getDrink().getDrinkName()
					+ " drink taste: " + glasses.get(i).getDrink().getDrinkTaste());
		}
	}


	public static void main(String[] arg) {
		Pepsi pepH = new Pepsi("henry's pepsi", "pepsi zero taste");
		Pepsi pepJ = new Pepsi("jerry's pepsi", "pepsi classic taste");
		Pepsi pepBasic = new Pepsi();

		System.out.println("pepH name = " + pepH.getDrinkName());
		System.out.println("pepJ name = " + pepJ.getDrinkName());
		System.out.println("pepBasic name = " + pepBasic.getDrinkName());

		Glass<Juice> glassJuice = new Glass<Juice>(new Juice());
		Glass<OrangeJuice> glassOJ =  new Glass<OrangeJuice>(new OrangeJuice());
		Glass<Cola> glassCola = new Glass<Cola>(new Cola("Coca-cola", "Coca-cola taste"));

		Tray tray = new Tray();
		tray.addGlass(glassJuice);
		tray.addGlass(glassOJ);
		tray.addGlass(glassCola);

		tray.addGlass(new Glass<>(pepH));
		tray.addGlass(new Glass<>(pepJ));
		tray.addGlass(new Glass<>(pepBasic));
		
		tray.tasteDrinks();

	}
}
