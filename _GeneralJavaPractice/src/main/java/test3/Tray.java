package test3;

import java.util.ArrayList;
import java.util.List;

public class Tray implements GenericTray {
	List<Glass<?>> tray = new ArrayList<>();

	/*
	 * add an item to the list
	 * @see test3.GenericTray#addGlass(test3.Glass)
	 */
	public void addGlass(Glass<?> glass) {
		this.tray.add(glass);
	}

	/*
	 * remove a glass after first checking if the list is empty
	 * @see test3.GenericTray#removeGlass()
	 */
	public Glass<?> removeGlass() throws EmptyTrayException {

		if(this.tray.isEmpty()) {
			throw new EmptyTrayException();
		}
		else {
			return this.tray.remove(0);
		}
	}

	/*
	 * look at the items in the list without removing them
	 * @see test3.GenericTray#peekAtGlasses()
	 */
	public void peekAtGlasses() {

		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			//call the drink() methods on all the items in the list
			for(Glass<?> current : tray) {
				current.drink();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static class EmptyTrayException extends Exception {
		public EmptyTrayException() {
			super("Tray is empty.");
		}
	}
}


