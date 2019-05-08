package test2_unitTest;

import static org.testng.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import test2.Glass;
import test2.Wine;
import test2.WineGlass;

/*
need to test following methods:

public class WineGlass<X extends Wine> extends Glass<Wine> {

	public WineGlass(Wine wine) {
		super(wine);
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

public class Wine extends Drink {
	//since an implcit super constructor is not defined for the default constructor of Wine,
	//we must define an explicit constructor for Wine class
	public Wine() {
		this("stale wine taste, blah");
	}
	
	public Wine(String taste) {
		super(taste);
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
}


//test these two methods:
 * 
 * class: WineGlass
 * 	
	public void peek() {
		System.out.println("Peeking at drink..." + drink.getTaste());
	}
	
	public void drink() {
		System.out.println("Drinking..." + drink.getTaste());
	}
	
	
	for a wine object with these two Wine methods:
	
	class: Wine

	public Wine() {
		this("stale wine taste, blah");
	}
	
	public Wine(String taste) {
		super(taste);
	}
	
	The essential test cases (test methods) are thus:
	
	(1) WineGlass -> peek() - Wine -> using the no arg Wine constuctor
	
	(2) WineGlass -> peek() - Wine -> using the arg Wine constuctor 
	
	(3) WineGlass -> drink() - Wine -> using the no arg Wine constuctor 
	
	(4) WineGlass -> drink() - Wine -> using the arg Wine constuctor 
	
	Test data needed:

	(a) Wine object created using the no arg constructor: 
		expected default name: "stale wine taste, blah"
		
	(b) Wine object created using the arg constructor

	(c) Wine Glass object that contains (a)
	
	(d) Wine Glass object that contains (b)


 */

public class WineGlassTest {
	
	//test data
	//(a) Wine object created using the no arg constructor: 
	//	  expected default name: "stale wine taste, blah"
	private final String defWinTaste = "stale wine taste, blah";
	
	//(b) Wine object created using the arg constructor
	private final String varWineTaste = "nice chardonnay!";
	
	//peek base string
	private final String lbl_peek = "Peeking at drink...";
	
	//drink base string
	private final String lbl_drink = "Drinking...";
	
	//Wine with (a) string for constructor
	private final Wine wine_a = new Wine();
	
	//Wine with (b) string for constructor
	private final Wine wine_b = new Wine(varWineTaste);

	//Wine Glass object that contains wine_a
	private final Glass<Wine> wg1 = new WineGlass<>(wine_a);
	
	//Wine Glass object that contains wine_b
	private final WineGlass<Wine> wg2 = new WineGlass<>(wine_b);
	
	//reassign standard out to a buffer that we can do asserts on
	private final PrintStream origStdOut = System.out;
	
	//create the console stream buffer
	private ByteArrayOutputStream consoleBuf = new ByteArrayOutputStream();
	
	
	@BeforeMethod
	public void beforeTest() {
		//redirect std out into the console buffer that we can assert on 
		System.setOut(new PrintStream(consoleBuf));
	}
	
	@Test
	public void test01_WineGlass_wg1_peek_def_Wine() {
		//peek and verify strings lbl_peek and defWinTaste
		wg1.peek();
		assertTrue(consoleBuf.toString().indexOf(lbl_peek)!=-1);
		assertTrue(consoleBuf.toString().indexOf(defWinTaste )!=-1);
	}	

	@Test
	public void test02_WineGlass_wg2_peek_var_Wine() {
		//peek and verify strings lbl_peek and varWineTaste
		wg2.peek();
		assertTrue(consoleBuf.toString().indexOf(lbl_peek)!=-1);
		assertTrue(consoleBuf.toString().indexOf(varWineTaste )!=-1);
	}	

	@Test
	public void test03_WineGlass_wg1_drink_def_Wine() {
		//peek and verify strings lbl_drink and defWinTaste
		wg1.drink();
		assertTrue(consoleBuf.toString().indexOf(lbl_drink)!=-1);
		assertTrue(consoleBuf.toString().indexOf(defWinTaste )!=-1);
	}	

	@Test
	public void test04_WineGlass_wg2_drink_var_Wine() {
		//peek and verify strings lbl_drink and varWineTaste
		wg2.drink();
		assertTrue(consoleBuf.toString().indexOf(lbl_drink)!=-1);
		assertTrue(consoleBuf.toString().indexOf(varWineTaste )!=-1);
	}
	
	@AfterMethod
	public void afterTest() {
		//restore std out
		System.setOut(origStdOut);
		
		//print out the console buffer
		System.out.println(consoleBuf.toString());
		
		//reset the console buffer
		consoleBuf = new ByteArrayOutputStream();
	}
}

//  OUTPUT:
//	[RemoteTestNG] detected TestNG version 6.14.2
//	Peeking at drink...stale wine taste, blah
//
//	Peeking at drink...nice chardonnay!
//
//	Drinking...stale wine taste, blah
//
//	Drinking...nice chardonnay!
//
//	PASSED: test01_WineGlass_wg1_peek_def_Wine
//	PASSED: test02_WineGlass_wg2_peek_var_Wine
//	PASSED: test03_WineGlass_wg1_drink_def_Wine
//	PASSED: test04_WineGlass_wg2_drink_var_Wine
//
//	===============================================
//	    Default test
//	    Tests run: 4, Failures: 0, Skips: 0