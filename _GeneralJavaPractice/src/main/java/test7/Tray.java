package test7;

import java.util.ArrayList;
import java.util.List;

public class Tray {
	List<Glass<?>> tray = new ArrayList<>();

	public void addGlassToTray(Glass<?> glass) {
		this.tray.add(glass);
	}

	public void drinkIt() {
		try {	
			if(this.tray.isEmpty()) {
				throw new  EmptyTrayException("Drinking");
			}
			else {
				this.tray.remove(0).drinkIt();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void browseDrinks() {
		try {	
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException("Browsing");
			}
			else {
				this.tray.forEach(x->System.out.println("Browsing Tray: found a " +
						x.getClass().getSimpleName()));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private class EmptyTrayException extends Exception {
		private static final long serialVersionUID = -2888467426647360532L;

		private EmptyTrayException(String label) {
			super(label + ": Tray is empty.");
		}
	}
}


