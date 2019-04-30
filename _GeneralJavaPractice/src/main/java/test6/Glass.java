package test6;

public class Glass<X extends Liquid> {
	X drink;

	public void drinkIt() {
		System.out.println("Drinking a " + 
				this.drink.getClass().getSimpleName() +
				": " + this.drink.getName());
	}

	public Glass(X drink) {
		this.drink = drink;
	}
}
