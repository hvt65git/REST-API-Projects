package polym_runtime_animal_interface;

/*
What is polymorphism in programming?
Polymorphism is the capability of a method to do different things based on the object that it is acting upon.
In other words, polymorphism allows you define one interface and have multiple implementations. 

As we have seen in the above example that we have defined the method sound() 
and have the multiple implementations of it in the different-2 sub classes.
Which sound() method will be called is determined at runtime so the example
 we gave above is a runtime polymorphism example.

Types of polymorphism and method overloading & overriding are covered in the separate tutorials. You can refer them here:
1. Method Overloading in Java – This is an example of compile time (or static polymorphism)
2. Method Overriding in Java – This is an example of runtime time (or dynamic polymorphism)
3. Types of Polymorphism – Runtime and compile time – This is our next tutorial where we have covered the types of polymorphism in detail. I would recommend you to go though method overloading and overriding before going though this topic.
 */
public interface Animal{ //runtime polym
	default void sound(){
		System.out.println("Animal is making a sound");   
	}
}

class Horse implements Animal{
	@Override
	public void sound(){
		System.out.println("Neigh");
	}
}

class Cat implements Animal{
	@Override
	public void sound(){
		System.out.println("Meow");
	}
}