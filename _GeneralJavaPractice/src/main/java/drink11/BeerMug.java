package drink11;

class BeerMug<X extends Beer> extends Glass<Drink>{
	public BeerMug(Beer beer) {
		super(beer);
	}
}
