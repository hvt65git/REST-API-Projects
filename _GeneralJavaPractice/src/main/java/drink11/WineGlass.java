package drink11;

class WineGlass<X extends Wine> extends Glass<Drink> {
	public WineGlass(Wine wine) {
		super(wine);
	}
}