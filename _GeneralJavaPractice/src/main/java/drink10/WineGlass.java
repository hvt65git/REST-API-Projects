package drink10;

public class WineGlass<X extends Wine> extends Glass<Drink> {
	public WineGlass(Wine wine) {
		super(wine);
	}
}
