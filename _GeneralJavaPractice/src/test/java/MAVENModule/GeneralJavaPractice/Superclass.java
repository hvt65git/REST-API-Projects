package MAVENModule.GeneralJavaPractice;

//note:staitc modifier = Illegal modifier for the class Superclass;
//only public, abstract & final are permitted
//The class Superclass can be either abstract or final, not both

//if abstract this class cannot be instantiated
//if final this class cannot be extended (inherited)

 class Superclass {
	
	public Superclass(String label) {
		//System.out.println("*** 1 - Superclass constructor called ***");
		System.out.println("*** 1 - Superclass constructor " + label +  "***");
	}

    public void printMethod() {
        System.out.println("Printed in Superclass.");
    }
}