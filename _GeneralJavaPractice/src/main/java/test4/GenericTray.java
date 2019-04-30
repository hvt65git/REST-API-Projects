package test4;

public interface GenericTray {
	public void addGlass(Glass<?> g);
	public Glass<?> removeGlass() throws Exception;
	public void browse();

}
