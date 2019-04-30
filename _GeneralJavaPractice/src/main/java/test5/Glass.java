package test5;

public abstract class Glass<X extends Liquid> {
	X drink;

	public void drinkIt() {
		System.out.println("Drinking... " + "a " +
				drink.getClass().getSimpleName() + " with taste: "
				+ this.drink.getTaste());
	}
	
	public Glass(X drink) {
		this.drink = drink;
	}
}
