package test3;

public abstract class Drink implements Liquid {
	String taste;
	
	public String getTaste() {
		return this.taste;
	}
	
	public Drink(String taste) {
		this.taste = taste;
	}
}
