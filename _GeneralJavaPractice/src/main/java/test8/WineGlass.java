package test8;

public class WineGlass<X extends Wine> extends Glass<Wine> {
	
	//define explicit constructor
	public WineGlass(Wine wine) {
		super(wine);
	}
}
