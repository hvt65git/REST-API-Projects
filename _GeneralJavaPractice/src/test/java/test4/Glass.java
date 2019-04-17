package test4;

public class Glass<X extends Liquid> implements GenericGlass<Liquid>{
	X liquid;
	
	public void drinkIt() {
		System.out.println("Drinking..." + liquid.getTaste());
	}
	
	public Glass(X liquid) {
		this.liquid = liquid;     
	}

}
