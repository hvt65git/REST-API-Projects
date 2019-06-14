package drink_i1;

public abstract class Drink implements IDrink {
	private String name;

	public String getName() {
		return this.name;
	}
 
	public Drink(String name) throws Exception {
		//enforce name to be non null, non blank
		if(name == null) { 
			//do this the ptr way! since name.equals(null) will fail! cant deref null ptr
			throw new Exception("name is null");
		}
		else if(name.equals("")) {
			throw new Exception("String is blank");
		}
		else if(name.startsWith(" ") && (name.length()<2)) {
			throw new Exception("String is blank");
		}
		else {
			//right and left trim any blank spaces
			name.trim();
			this.name = name;		
		}
	}
}

