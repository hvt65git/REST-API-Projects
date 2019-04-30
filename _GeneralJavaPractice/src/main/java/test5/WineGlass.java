package test5;

public class WineGlass<X extends Wine> extends  Glass<Wine> {
	
	public WineGlass(X drink) {
		super(drink);
	}
}


