package test3;

public interface GenericTray {
	public void addGlass(Glass<?> glass);
	public Glass<?> removeGlass() throws Exception;
	public void peekAtGlasses();
}
