package test2;

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
