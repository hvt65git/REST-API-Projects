package test2;

public class BeerGlass<X extends Beer> extends Glass<Beer> {
	
	public BeerGlass(Beer beer) {
		super(beer);
	}
}

