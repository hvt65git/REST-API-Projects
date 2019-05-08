package classmethodtest;

/*
 * notes: 
 * 
 * (1) a static method can only access static variables and static methods
 * error message is: Cannot make a static reference to the non-static field id
 * said in other away: a class method can only access class variables
 * 
 * (2) a static method cannot access a static variable with the this reference!
 * error message is: Cannot use this in a static context
 */
public class Product {
	private static int id;
	
	public int getIdNonStaticMethod() {
		//warning: The static field Product.id should be accessed in a static way
		return this.id;
	}
	
	public static int getId() {
		return id;
	}

	public static void main(String... arg) {
		
		id =1233;
		System.out.println(getId());
		System.out.println(Product.getId());
		
		Product p = new Product();
		System.out.println(p.getIdNonStaticMethod());

		id = 999;
		System.out.println(getId());
		System.out.println(Product.getId());
		System.out.println(p.getIdNonStaticMethod());
		
	}
}

//output:
//1233
//1233
//1233
//999
//999
//999
