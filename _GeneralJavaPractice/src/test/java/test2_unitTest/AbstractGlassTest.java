package test2_unitTest;

import static org.testng.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test2.Pepsi;

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
	(1) Invoke AbstractGlassStub.peek()->drink.getTaste()) for:
		Pepsi object using no param constructor.
		Expected strings are:
		(a) "Peeking at drink..."
		(b) "flat expired taste with no fizz, blah!"

	(2) Invoke AbstractGlassStub.peek()->drink.getTaste() for:
		Pepsi object using single param constructor.
		Expected strings are:
		(a) "Peeking at drink..."
		(b) constructor argument string value


	(3) Invoke AbstractGlassStub.drink()->drink.getTaste()) for:
		Pepsi object using no param constructor.
		Expected strings are:
		(a) "Drinking..."
		(b) "flat expired taste with no fizz, blah!"

	(4) Invoke AbstractGlassStub.drink()->drink.getTaste() for:
		Pepsi object using single param constructor.
		Expected strings are:
		(a) "Drinking..."
		(b) constructor argument string value

TEST DATA:
	(1) Pepsi object created with no param constructor
	(2) Pepsi object created with single param constructor
	(3) AbstractGlassStub object that contains Pepsi object 1
	(4) AbstractGlassStub object that contains Pepsi object 2
	(5) String: Expected Label =  "Peeking at drink..."
	(6) String: Expected Label =  "Drinking..."
	(7) String: Default Drink Taste = "flat expired taste with no fizz, blah!"
	(8) String: Drink Taste

 */
public class AbstractGlassTest {
	//test data (global constants)

	//String: Expected Label =  "Peeking at drink..."
	private final String peek = "Peeking at drink...";

	//String: Expected Label =  "Drinking..."
	private final String drink = "Drinking...";

	//String: Default Drink Taste = "flat expired taste with no fizz, blah!"
	private final String defaultPepsiTaste = "flat expired taste with no fizz, blah!";

	//String: Drink Taste
	private final String pepsiTaste = "fresh Pepsi Vanilla flavor";

	//Pepsi object created with no param constructor
	private final Pepsi pep1 = new Pepsi();

	//Pepsi object created with single param constructor
	private final Pepsi pep2 = new Pepsi(pepsiTaste);

	//(3) AbstractGlassStub object that contains Pepsi object 1
	AbstractGlassStub glass1 = new AbstractGlassStub(pep1);

	//(4) AbstractGlassStub object that contains Pepsi object 2
	AbstractGlassStub glass2 = new AbstractGlassStub(pep2);

	//need a PrintStream object to restore std out (System.out)
	PrintStream origStdOut = System.out;

	//need a ByteArrayOutputStream object for a console buffer
	ByteArrayOutputStream consoleBuffer = new ByteArrayOutputStream();

	@BeforeMethod
	public void beforeEachTest() {
		//redirect std out
		System.setOut(new PrintStream(consoleBuffer));
	}

	/*
	 * 	(1) Invoke AbstractGlassStub.peek()->drink.getTaste()) for:
		Pepsi object using no param constructor.
		Expected strings are:
		(a) "Peeking at drink..."
		(b) "flat expired taste with no fizz, blah!"
	 */
	@Test(enabled = false)
	public void peekGetTastePepsiDefaultTaste() {
		glass1.peek();
		assertTrue(consoleBuffer.toString().indexOf(peek) != -1);
		assertTrue(consoleBuffer.toString().indexOf(defaultPepsiTaste) != -1);
	}


	/*
	 * 
	 * 	(2) Invoke AbstractGlassStub.peek()->drink.getTaste() for:
		Pepsi object using single param constructor.
		Expected strings are:
		(a) "Peeking at drink..." - peek
		(b) pepsiTaste - glass2

	 */
	@Test(enabled = true)
	public void peekGetTastePepsiTaste() {
		//invoke method
		glass2.peek();
		
		//verify actual value matches expected value
		assertTrue(consoleBuffer.toString().indexOf(peek) != -1);
		assertTrue(consoleBuffer.toString().indexOf(pepsiTaste) != -1);
		
	}


	/*
	 *
	 *	
		(3) Invoke AbstractGlassStub.drink()->drink.getTaste()) for:
		Pepsi object using no param constructor.
		Expected strings are:
		(a) "Drinking..."
		(b) "flat expired taste with no fizz, blah!"
	 */
	@Test(enabled = false)
	public void drinkGetTastePepsiDefaultTaste() {
		
	}
	
	/*
	 * 
		(4) Invoke AbstractGlassStub.drink()->drink.getTaste() for:
		Pepsi object using single param constructor.
		Expected strings are:
		(a) "Drinking..."
		(b) constructor argument string value
	 */
	@Test(enabled = false)
	public void drinkGetTastePepsiTaste() {
		
	}

	@AfterMethod
	public void afterEachTest() {
		//restore std out
		System.setOut(origStdOut);

		//print out console buffer
		System.out.println(consoleBuffer.toString());

		//reset the console buffer
		consoleBuffer = new ByteArrayOutputStream();
	}
}
