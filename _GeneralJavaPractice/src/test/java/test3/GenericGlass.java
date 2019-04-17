package test3;
//public interface GenericGlass<Liquid> 
//The type parameter Liquid is hiding the type Liquid
public interface GenericGlass<X extends Liquid> {
	public void drink();
}
