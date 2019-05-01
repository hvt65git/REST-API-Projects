package test8;

public abstract class Glass<X extends Liquid> {
	private X liquid;
	
	public void DrinkIt() {
		System.out.println("Drinking a: " + this.liquid.getClass().getSimpleName() +
				" with name: " + this.liquid.getName());
	}
	
	public void BrowseIt() {
		System.out.println("Browsing a: " + this.liquid.getClass().getSimpleName() +
				" with name: " + this.liquid.getName());
	}
	
	public Glass(X drink) {
		this.liquid = drink;
	}
}
