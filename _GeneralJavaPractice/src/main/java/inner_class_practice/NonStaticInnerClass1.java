package inner_class_practice;

import java.util.ArrayList;
import java.util.List;

import inner_class_practice.OuterClass.InnerClass;

/*
 * 
 * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
 * 
 * The Java Tutorials have been written for JDK 8. Examples and practices described in this page don't take advantage of improvements introduced in later releases.

Nested Classes
The Java programming language allows you to define a class within another class. Such a class is called a nested class and is illustrated here:

class OuterClass {
    ...
    class NestedClass {
        ...
    }
}
Terminology: Nested classes are divided into two categories: static and non-static. Nested classes that are declared static are called static nested classes. Non-static nested classes are called inner classes.
class OuterClass {
    ...
    static class StaticNestedClass {
        ...
    }
    class InnerClass {
        ...
    }
}
A nested class is a member of its enclosing class. Non-static nested classes (inner classes) have access to other members of the enclosing class, even if they are declared private. Static nested classes do not have access to other members of the enclosing class. As a member of the OuterClass, a nested class can be declared private, public, protected, or package private. (Recall that outer classes can only be declared public or package private.)

Why Use Nested Classes?
Compelling reasons for using nested classes include the following:

It is a way of logically grouping classes that are only used in one place: If a class is useful to only one other class, then it is logical to embed it in that class and keep the two together. Nesting such "helper classes" makes their package more streamlined.

It increases encapsulation: Consider two top-level classes, A and B, where B needs access to members of A that would otherwise be declared private. By hiding class B within class A, A's members can be declared private and B can access them. In addition, B itself can be hidden from the outside world.

It can lead to more readable and maintainable code: Nesting small classes within top-level classes places the code closer to where it is used.

Static Nested Classes
As with class methods and variables, a static nested class is associated with its outer class. And like static class methods, a static nested class cannot refer directly to instance variables or methods defined in its enclosing class: it can use them only through an object reference.

Note: A static nested class interacts with the instance members of its outer class (and other classes) just like any other top-level class. In effect, a static nested class is behaviorally a top-level class that has been nested in another top-level class for packaging convenience.
 */

class EmptyTestSuiteException extends Exception {	
	public EmptyTestSuiteException() {
		System.out.println("The test suite has no test case(s).");
	}
}

class OuterClass {
	List<String> list = new ArrayList<>();
	List<InnerClass> list2 = new ArrayList<>();
	String yo = "";

	class InnerClass {
		String inner1;

		InnerClass(String y) {
			OuterClass.this.yo = y;
			OuterClass.this.list.add("hello world");
			OuterClass.this.list2.add(this);
		}    	
	}
}
//CONCLUSION: There is no advantage to encapsulation of test case
//within a test suite object. if we want to remove a test case
//from the suite we have to expose the inner class to the calling program
//because the test case object had two fields
//so doing it this way with encapsulated inner class adds more code to program
//we should keep TestSuite and TestCase classes separate and allow
//developer to choose if they want to use both classes or just the TestCase class
class TestSuite<X> {
	private List<TestCase<X>> ts = new ArrayList<>();
	String tsName;

	public TestSuite(String tsName) {
		this.tsName = tsName;
	}

	public void addTestCase(String tcName, X[][] testdata) {
		TestCase<X> tc = new TestCase<>(tcName, testdata );
		ts.add(tc);
	}

	public X[][] getTestCaseTestData(int index) throws EmptyTestSuiteException {
		if(this.ts.isEmpty()) throw new EmptyTestSuiteException();
		return this.ts.get(index).getTestData();
	}

	public String getTestCaseName(int index) throws EmptyTestSuiteException{
		if(this.ts.isEmpty()) throw new EmptyTestSuiteException();
		return this.ts.get(index).getName();
	}

	//encapsulating inner class TestCase
	//by making it private
	//must declare as static to 
	//prevent warning: The type parameter X is hiding the type X
	//but it would work anyway
	//test suite will use this TestCase data structure (obj)
	private  class TestCase<X> {
		private X[][] testData;
		private String name;

		private X[][] getTestData() {
			return testData;
		}

		private String getName() {
			return name;
		}

		private void nop() {
			//practice accessing outer fields here:
			//no can do this if inner class is declared static
			TestSuite.this.tsName = "mo money";
			System.out.println(TestSuite.this.ts.size());
		}
		
		TestCase(String name, X[][] testData) {
			this.name = name;
			this.testData = testData;
		}
	}
}

public class NonStaticInnerClass1 {

	public static void main(String[] args) {
		OuterClass oc = new OuterClass();
		oc.yo = "youyo";

		InnerClass nsnc = oc.new InnerClass("hi");
		nsnc.inner1 = "yoyoma";

		//create a TestSuite object
		TestSuite<String> ts = new TestSuite<>("test suite #1");
		ts.addTestCase(
				"test case 1", 
				new String[][] {{"seahawks"}, {"storm"}}
				);
		try {
			String name = ts.getTestCaseName(0);
			System.out.println("Printing out test case name: " + name);
			System.out.println("Printing out test case test data:");
			String[][] td =	ts.getTestCaseTestData(0);
			for(String[] current : td) {
				for(String current2 : current) {
					System.out.println(current2);
				}
			}
		}
		catch(EmptyTestSuiteException e) {
			System.out.println(e.getMessage());
		}

	}
}




