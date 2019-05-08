package test2_unitTest;

import test2.Drink;
import test2.GenericGlass;
import test2.Glass;
import test2.Liquid;

/*
 * need to test classes:
 * 
 *public class AbstractGlassStub<X extends Liquid> extends Glass<Liquid> {

	public AbstractGlassStub(X drink) {
		super(drink);
	}
}


public abstract class Glass<X extends Liquid> implements GenericGlass<Liquid> {
	private X drink;

	public void peek() {
		System.out.println("Peeking at drink..." + drink.getTaste());
	}

	public void drink() {
		System.out.println("Drinking..." + drink.getTaste());
	}

	public Glass(X drink) {
		this.drink = drink;
	}
}


public abstract class Drink implements Liquid {
	private String taste;

	public String getTaste() {
		return this.taste;
	}

	public Drink(String taste) {
		this.taste = taste;
	}

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

}

TEST CASES:
	(I.) Invoke AbstractGlassStub.peek(), verify strings in System.out:
		(a.) "Peeking at drink..."
		(b.) expected string value returned by drink.getTaste()
			(i.) for no param Pepsi constructor, 
				we expect string: "flat expired taste with no fizz, blah!"
			(ii.) for single param Pepsi constructor, 
				we expect the variable string
				
	(II.) Invoke AbstractGlassStub.drink(), verify strings in System.out:
		(a.) "Drinking..."
		(b.) expected string value returned by drink.getTaste()
			(i.) for no param Pepsi constructor, 
				we expect string: "flat expired taste with no fizz, blah!"
			(ii.) for single param Pepsi constructor, 
				we expect the variable string

	So we have 4 test cases:
	(1) Invoke AbstractGlassStub.peek() for Pepsi with default getTaste() string
	(2) Invoke AbstractGlassStub.peek() for Pepsi with passed in getTaste() string
	(3) Invoke AbstractGlassStub.drink() for Pepsi with default getTaste() string
	(4) Invoke AbstractGlassStub.drink() for Pepsi with passed in getTaste() string

 */
public class AbstractGlassTest {

}
