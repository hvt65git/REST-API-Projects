package test7;

public interface GenericTray {
	public void addGlassToTray(Glass<?> glass);
	public void drinkIt();
	public void browseDrinks();
}
