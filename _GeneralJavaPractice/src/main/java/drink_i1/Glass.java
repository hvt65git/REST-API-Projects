package drink_i1;

public abstract class Glass<X extends IDrink> implements IGlass<IDrink> {
	X drink;
	
	public void drinkIt() {
		System.out.println("Drinking: " + drink.getName());
	}
	
	public Glass(X drink) {
		this.drink = drink;
	}
}
