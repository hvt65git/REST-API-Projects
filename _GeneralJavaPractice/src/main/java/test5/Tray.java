package test5;

import java.util.ArrayList;
import java.util.List;

import test5.Tray.EmptyTrayException;

public class Tray implements GenericTray {
	List<Glass<?>> tray = new ArrayList<>();

	public void addGlassToTray(Glass<?> g) {
		this.tray.add(g);
	}

	public void getADrink() {
		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			else {
				this.tray.remove(0).drinkIt();
			}
		}
		catch(EmptyTrayException e) {
			System.out.println("Drinking: " + e.getMessage());
		}
	}


	public void browseTray() {
		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			else {			
				this.tray.forEach(g->System.out.println("Browsing... found a " + 
						g.getClass().getSimpleName())
						);
			}
		}
		catch(EmptyTrayException e) {
			System.out.println("Browsing: " + e.getMessage());
		}
	}

	public static class EmptyTrayException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyTrayException() {
			super("The tray is empty.");
		}

		public EmptyTrayException(String m) {
			super(m);
		}
	}
}
