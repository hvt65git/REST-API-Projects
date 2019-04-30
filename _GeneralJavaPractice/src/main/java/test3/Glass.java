package test3;

public class Glass<X extends Liquid> implements GenericGlass<Liquid> {
	X liquid;
	
	public void drink() {
		System.out.println("Drinking... " + this.liquid.getTaste());
	}
	
	public Glass(X liquid) {
		this.liquid = liquid;
	}
}



