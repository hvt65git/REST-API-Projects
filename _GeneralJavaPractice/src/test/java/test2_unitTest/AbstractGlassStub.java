package test2_unitTest;

import test2.GenericGlass;
import test2.Glass;
import test2.Liquid;

/*
 * Description: AbstractGlassStub extends Glass so that we can test its 
 * public methods
 * 
 * package test2;

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

 */
public class AbstractGlassStub<X extends Liquid> extends Glass<Liquid> {

	public AbstractGlassStub(X drink) {
		super(drink);
	}
}
