package test5;

public class Drink implements Liquid {
	private String taste;
	
	public String getTaste() {
		return this.taste;
	}

	public Drink(String taste) {
		this.taste = taste;
	}
}
