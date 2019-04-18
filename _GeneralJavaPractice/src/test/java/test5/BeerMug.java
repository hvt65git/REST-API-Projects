package test5;

public class BeerMug<X extends Beer> extends Glass<Beer> {
	
	public BeerMug(X drink) {
		super(drink);
	}
}
