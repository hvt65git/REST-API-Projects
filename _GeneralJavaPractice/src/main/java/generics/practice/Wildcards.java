package generics.practice;

import java.util.ArrayList;
import java.util.List;


//These guidelines do not apply to a method's return type. Using a wildcard as a return type should be avoided because it forces programmers using the code to deal with wildcards.
//
//A list defined by List<? extends ...> can be informally thought of as read-only, but that is not a strict guarantee. Suppose you have the following two classes:

class NaturalNumber {

	private int i;

	public NaturalNumber(int i) { this.i = i; }
	// ...
}

class EvenNumber extends NaturalNumber {

	public EvenNumber(int i) { super(i); }
	// ...
}
////Consider the following code:
//
//List<EvenNumber> le = new ArrayList<>();
//List<? extends NaturalNumber> ln = le;
//ln.add(new NaturalNumber(35));  // compile-time error
//Because List<EvenNumber> is a subtype of List<? extends NaturalNumber>,
//you can assign le to ln. But you cannot use ln to add a natural number to a list of even numbers.
//The following operations on the list are possible:
//
//You can add null.
//You can invoke clear.
//You can get the iterator and invoke remove.
//You can capture the wildcard and write elements that you've read from the list.
//You can see that the list defined by List<? extends NaturalNumber> 
//is not read-only in the strictest sense of the word,
//but you might think of it that way because you cannot store a new element or change an existing element in the list.

public class Wildcards {

	public static void main() {

		List<EvenNumber> le = new ArrayList<>();
		List<? extends NaturalNumber> ln = le;
		//ln.add(new EvenNumber(35));  // compile-time error

	}
	
//	Lower Bounded Wildcards
//	The Upper Bounded Wildcards section shows that an upper bounded wildcard restricts the unknown type to be a specific type or a subtype of that type and is represented using the extends keyword. In a similar way, a lower bounded wildcard restricts the unknown type to be a specific type or a super type of that type.
//
//	A lower bounded wildcard is expressed using the wildcard character ('?'), following by the super keyword, followed by its lower bound: <? super A>.
//
//	Note: You can specify an upper bound for a wildcard, or you can specify a lower bound, but you cannot specify both.
//	Say you want to write a method that puts Integer objects into a list. 
//	To maximize flexibility, you would like the method to work on List<Integer>, 
//	List<Number>, and List<Object> — anything that can hold Integer values.
//
//	To write the method that works on lists of Integer and the supertypes of Integer, such as Integer, Number, and Object, you would specify List<? super Integer>. The term List<Integer> is more restrictive than List<? super Integer> because the former matches a list of type Integer only, whereas the latter matches a list of any type that is a supertype of Integer.
//
//	The following code adds the numbers 1 through 10 to the end of a list:

	public static void addNumbers(List<? super Integer> list) {
	    for (int i = 1; i <= 10; i++) {
	        list.add(i);
	    }
	}

}
