package test6;

public interface GenericTray {
	public void addDrinkToTray(Glass<?> g);
	public void getADrink();
	public void browseTray();
}
