package test2;

import java.util.ArrayList;
import java.util.List;

/*
 * provides concrete implementation of GenericTray interface
 */

public class Tray {
	private List<Glass<?>> tray = new ArrayList<>();

	public void addGlass(Glass<?> glass) {
		this.tray.add(glass);
	}

	public Glass<?> getGlass() throws EmptyTrayException {

		if(this.tray.isEmpty()) {
			throw new EmptyTrayException();
			//return null; //note: any code here would be Unreachable code
		}
		else {
			return this.tray.remove(0);
		}
	}

	public void peekAtAllDrinks() {
		
		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			//look at all the drinks without removing from tray
			for(Glass<?> current : tray ) {
				current.peek();
			}
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
	}

	public static class EmptyTrayException extends Exception {
		static final long serialVersionUID = 0;

		public EmptyTrayException() {
			super("The tray is empty.");
		}

		public EmptyTrayException(String message) {
			super(message);
		}
	}
}
