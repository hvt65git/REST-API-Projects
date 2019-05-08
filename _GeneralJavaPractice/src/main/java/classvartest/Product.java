package classvartest;
/*
 * 
They both are member variables, meaning that both are associated with a class. Now of course, there are differences between the two:

Instance variables:

These variables belong to the instance of a class, thus an object. And every instance of that class (object) has it's own copy of that variable. Changes made to the variable don't reflect in other instances of that class.

public class Product {
    public int Barcode;
}
Class variables:

These are also known as static member variables and there's only one copy of that variable that is shared with all instances of that class. If changes are made to that variable, all other instances will see the effect of the changes.

public class Product {
    public static int Barcode;
}
 */

public class Product {
	private int Barcode;

	public static void main(String[] args) {

		Product prod1 = new Product();

		//warning The static field Product.Barcode should be accessed in a static way
		prod1.Barcode = 123456; 

		Product prod2 = new Product();

		//warning The static field Product.Barcode should be accessed in a static way
		prod2.Barcode = 987654;

		//warning The static field Product.Barcode should be accessed in a static way
		System.out.println(prod1.Barcode);

		//warning The static field Product.Barcode should be accessed in a static way
		System.out.println(prod2.Barcode);
	}

}

//output:
//987654
//987654

