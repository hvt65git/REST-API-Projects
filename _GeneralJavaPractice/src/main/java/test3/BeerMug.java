 package test3;

public class BeerMug<X extends Beer> extends Glass<Beer>{
	
	public BeerMug(Beer beer) {
		super(beer);
	}
}
