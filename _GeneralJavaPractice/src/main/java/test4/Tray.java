package test4;

import java.util.ArrayList;
import java.util.List;

public class Tray implements GenericTray {
	List<Glass<?>> tray = new ArrayList<>();

	public void addGlass(Glass<?> g) {
		this.tray.add(g);
	}

	public Glass<?> removeGlass() throws EmptyTrayException {

			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			else {
				return this.tray.remove(0);
			}
	}

	public void browse() {
		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			else {
				for(Glass<?> current : this.tray) {
					current.drinkIt();
				}
			}
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
	}

	public static class EmptyTrayException extends Exception {

		public EmptyTrayException( ) {
			super("The tray is empty.");
		}

		public EmptyTrayException(String message) {
			super(message);
		}
	}
}
