package drink10;

public abstract class Glass<X extends Drink> {
	X drink;

	public void drinkIt() {
		System.out.println("Now drinking: " + this.drink.getName());
	}

	public Glass(X drink) {
		this.drink = drink;
	}
}
