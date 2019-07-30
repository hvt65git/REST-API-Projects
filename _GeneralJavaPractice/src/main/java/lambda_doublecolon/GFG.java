package lambda_doublecolon;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Java code to print the elements of Stream 
//using double colon operator 

import java.util.stream.*; 

class GFG { 
	public static void main(String[] args) { 
		//date time practice
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));  
		System.out.println("NOW: " + LocalDateTime.now().toString());  

		// Get the stream 
		Stream<String> stream 
		= Stream.of("Geeks", "For", 
				"Geeks", "A", 
				"Computer", 
				"Portal"); 

		// Print the stream using double colon operator 
		// replaces this call: 
		//stream.forEach(s -> System.out.println(s));
		stream.forEach(System.out::println); 
	} 
} 
