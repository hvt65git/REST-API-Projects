package test6;

public class BeerMug<X extends Beer> extends Glass<Beer> {
	
	public BeerMug(X beer) {
		super(beer);
	}
}
