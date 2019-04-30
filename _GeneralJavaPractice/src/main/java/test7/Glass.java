package test7;

public class Glass<X extends Liquid> implements GenericGlass<Liquid> {
	private X drink;

	public void drinkIt() {
		System.out.println("Drinking a " + 
				this.drink.getClass().getSimpleName() +
				" named: " + this.drink.getName());
	}

	public Glass(X drink) {
		this.drink = drink;
	}
}
