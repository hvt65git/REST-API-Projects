package drink10;

public abstract class Drink implements Liquid {
	private String name;

	public String getName() {
		return this.name;
	}

	public Drink(String name) {
		this.name = name;
	}
}
