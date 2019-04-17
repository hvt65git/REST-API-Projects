package test4;

public class Drink implements Liquid {
	String taste;
	
	public String getTaste() {
		return this.taste;
	}
	
	public Drink(String taste) {
		this.taste = taste;
	}
}
