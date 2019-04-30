package test6;

import java.util.ArrayList;
import java.util.List;

public class Tray implements GenericTray {
	private List<Glass<?>> tray = new ArrayList<>();

	public void addDrinkToTray(Glass<?> g) {
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
			System.out.println(e.getMessage());
		}
	}

	public void browseTray() {
		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			else {
				this.tray.forEach(x->System.out.println("Browsing Tray: found a " +
						x.getClass().getSimpleName()));
			}
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
	}

	public static class EmptyTrayException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyTrayException() {
			super("The tray is empty.");
		}

		public EmptyTrayException(String message) {
			super(message);
		}
	}

}
