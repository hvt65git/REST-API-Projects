package test2;

public interface GenericTray {
	void addGlass();
	Glass<?> getGlass();
	void peekAtAllDrinks();
}
