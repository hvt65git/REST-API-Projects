package polym_runtime_animal_interface;

import static polym_runtime_animal_interface.Variable.*;

interface Somebody {
	public void saySomething();
}

enum Variable implements Somebody {
	X("X is described"){
		public void saySomething() {
			System.out.println("I say: " + this.getDescription());
		}
	}, 
	Y("Y"){
		public void saySomething() {
			
		}	
	}, 
	Z("Z"){
		public void saySomething() {
			
		}
	};
	
	String description;
	
	public String getDescription() {
		return this.description;
	}
	
	private Variable(String description) {
		this.description = description;
	}
}

//What is polymorphism in programming?
//
//POLYMORPHISM is the capability of a METHOD to do different things based on the 
//OBJECT THAT IT IS ACTING UPON.
//
//Method Overriding in Java – This is an example of runtime time (or dynamic polymorphism)
//
class Dog implements Animal {
	@Override
	public void sound() {
		System.out.println("WOOF WOOF!");
	}
}
class Horse implements Animal{
	@Override
	public void sound(){
		System.out.println("Neigh!");
	}
}

class Cat implements Animal{
	@Override
	public void sound(){
		System.out.println("Meow!");
	}
}

public class Main {

	public static void main(String[] args) {
		//play with enum first
		System.out.println(X.getDescription());
		X.saySomething();
		
		//create animals and invoke their sounds
		new Dog().sound();
		new Cat().sound();
		new Horse().sound();

		//create a special dog with a special sound
		Dog scoobyDoo = new Dog() {
			@Override
			public void sound(){
				System.out.println("SCOOBY DOOBY DOOOO!");
			}
		};
		scoobyDoo.sound();

		new Dog() {
			@Override
			public void sound(){
				System.out.println("SCOOBY DOOBY DOOOO TOOOO!");
			}}.sound();
	}
}

//OUTPUT:
//WOOF WOOF!
//Meow!
//Neigh!
//SCOOBY DOOBY DOOOO!
//SCOOBY DOOBY DOOOO TOOOO!

