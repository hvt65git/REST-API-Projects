package test2;

public abstract class Glass<X extends Liquid> implements GenericGlass<Liquid> {
	private X drink;
	
	public void peek() {
		System.out.println("Peeking at drink..." + drink.getTaste());
	}
	
	public void drink() {
		System.out.println("Drinking..." + drink.getTaste());
	}
	
	public Glass(X drink) {
		this.drink = drink;
	}
}
