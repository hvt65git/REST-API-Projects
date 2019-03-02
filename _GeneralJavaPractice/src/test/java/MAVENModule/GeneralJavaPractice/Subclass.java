package MAVENModule.GeneralJavaPractice;

/*
 * Accessing Superclass Members
If your method overrides one of its superclass's methods,
 you can invoke the overridden method through the use of the keyword super. 
 You can also use super to refer to a hidden field (although hiding fields is discouraged). 
 
Consider this class, called Subclass, that overrides Superclass printMethod():
 */

public class Subclass extends Superclass {
	//since we extended a class that did not have the default no arg constructor
	//we receive this error message:
	//Implicit super constructor Superclass() is undefined. Must explicitly invoke another constructor
	//we must match the constructor(s) in the superclass:
	public Subclass() {
		//note:
		//constructor call must be the first statement in a constructor
		this(""); //calls this class' constructor
		//or:
		//super("default label"); //calls the super class constructor and passes the string "default label"
		System.out.println("*** 2b - Subclass constructor called - no arg ***");
	}

	public Subclass(String label) {
		super(label);
		System.out.println("*** 2a - Subclass constructor called - with label: " + label + " ***");
	}
	
    // overrides printMethod in Superclass
    public void printMethod(String message) {
        super.printMethod();
        System.out.println( "Printed in Subclass: " + message);
        System.out.println();
    }
    
    public static void main(String[] args) {
        Subclass s1 = new Subclass();
        s1.printMethod("s1");    
        
        String label = "holayyy";
        Subclass s2 = new Subclass(label);
        s2.printMethod("s2 + " + label);  
    }
}

//OUTPUT
//
//*** 1 - Superclass constructor ***
//*** 2a - Subclass constructor called - with label:  ***
//*** 2b - Subclass constructor called - no arg ***
//Printed in Superclass.
//Printed in Subclass: s1
//
//*** 1 - Superclass constructor holayyy***
//*** 2a - Subclass constructor called - with label: holayyy ***
//Printed in Superclass.
//Printed in Subclass: s2 + holayyy


