package drink_i1;

public class WineGlass<X extends Wine> extends Glass<Wine> {
	
	public WineGlass(X wine) {
		super(wine);
	}
}
