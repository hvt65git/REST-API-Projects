package test7;

public class BeerMug<X extends Beer> extends Glass<Beer> {
	
	public BeerMug(X drink) {
		super(drink);
	}
}
