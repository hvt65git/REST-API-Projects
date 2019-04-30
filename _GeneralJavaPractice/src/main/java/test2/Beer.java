package test2;

public class Beer extends Drink {
	//note, since we are extending Drink
	//and implicit super constructor Drink() is undefined for default constructor
	//we must define an explicit constructor for Beer
	//the error message is:
	//Implicit super constructor Drink() is undefined for default constructor. Must define an explicit constructor
	public Beer() {
		super("stale old beer taste, blah1");
	}
	
	public Beer(String taste) {
		super(taste);
	}

}
