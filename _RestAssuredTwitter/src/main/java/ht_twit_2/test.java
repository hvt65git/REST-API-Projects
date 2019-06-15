package ht_twit_2;

public class test {


	public static void main(String[] a) {
		String entry = "created_at";

		if(entry.equals("created_at")) {
			System.out.println("created_at");
		} 
		else if(entry.equals("text") ) {
			System.out.println("text");
		}
		
		switch(entry) {
		case "created_at":
			System.out.println("hello");
			break;
		case "text":
			System.out.println("hello world");
			break;
		}
	}
}