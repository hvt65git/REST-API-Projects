package test2;

public class WineGlass<X extends Wine> extends Glass<Wine> {

	public WineGlass(Wine wine) {
		super(wine);
	}
}
