package test2;

public class SodaGlass<X extends Liquid> extends Glass<Liquid> {
	
	public SodaGlass(Liquid liquid) {
		super(liquid);
	}

}
