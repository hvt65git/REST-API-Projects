package test7;

public abstract class Drink implements Liquid {
	 String name;
	 
	 public String getName() {
		 return this.name;
	 }
	 
	 public Drink(String name) {
		 this.name = name;
	 }
}
