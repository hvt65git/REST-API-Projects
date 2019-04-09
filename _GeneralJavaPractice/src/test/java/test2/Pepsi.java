package test2;

public class Pepsi extends Drink {
	//implicit super constructor Drink() is undefined for default constructor
	//so we must define an explicit constructor
	public Pepsi() {
		//Implicit super constructor Drink() is undefined. Must explicitly invoke another constructor
		//so we need a super call to the defined super constructor
		//not this: super();
		super("flat expired taste with no fizz, blah!");
	}
	
	public Pepsi(String taste) {
		super(taste);
	}

}
