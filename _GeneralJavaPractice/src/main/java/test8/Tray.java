package test8;

import java.util.ArrayList;
import java.util.List;

public class Tray {
	private List<Glass<?>> tray = new ArrayList<>();

	public void addGlassToTray(Glass<?> glass) {
		this.tray.add(glass);
	}

	public void getGlassAndDrinkIt() {

		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			//remove next glass from tray and drink it
			this.tray.remove(0).DrinkIt();
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void browseTray() {
		try {
			if(this.tray.isEmpty()) {
				throw new EmptyTrayException();
			}
			//list out all the drink names - minimal code way
			this.tray.forEach(x->x.BrowseIt());
			
			//or can use enhanced for loop
			//for(Glass<?> x : this.tray) {
			//	x.DrinkIt();
			//}
		}
		catch(EmptyTrayException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static class EmptyTrayException extends Exception {
		private static final long serialVersionUID = 7945218525463031335L;
		
		//define our explicit constructor(s)
		public EmptyTrayException() {
			super("The tray is empty.");
		}
	}
}
