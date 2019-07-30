package drink10;

public class BeerMug<X extends Beer> extends Glass<Drink>{
	public BeerMug(Beer beer) {
		super(beer);
	}
}
