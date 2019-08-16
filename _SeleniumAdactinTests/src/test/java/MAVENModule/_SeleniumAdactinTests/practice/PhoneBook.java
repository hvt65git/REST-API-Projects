package MAVENModule._SeleniumAdactinTests.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class PhoneBook {
	private static List<String> crystals = Arrays.asList("amethyst", 
			"clear quartz", "blue lace agate", "citrine", "carnelian", "jasper");

	Set<Double> hs = new HashSet<>();
	Set<Double> lhs = new LinkedHashSet<>();
	Set<Double> ts = new HashSet<>();

	private static Map<String, String> phoneBook1 = new HashMap();
	private static Map<String, String> phoneBook2 = new LinkedHashMap();


	public static void main(String[] arg) {

		//print the crystals list - v1
		System.out.println("printing out List<String> crystals - for each loop with lambda:... ");
		crystals.forEach(current->System.out.println("foreach: current crystal - " + current));

		//print the crystals list - v2
		System.out.println("\nprinting out List<String> crystals - enhanced for loop:... ");
		for(String current : crystals) {
			System.out.println("current crystal - " + current);
		}

		//init phonebook
		phoneBook1.put("206.766.1114", "john smith");
		phoneBook1.put("206.711.2224", "mary smith");
		phoneBook1.put("206.722.3334", "george thomas");
		phoneBook1.put("206.733.4444", "karen thomas");
		phoneBook1.put("206.744.1534", "abe johnson");
		phoneBook1.put("206.755.1734", "zeke zeke");

		//print out the phone book

		//get the keys
		Set<String> keys = phoneBook1.keySet();

		//print out the keys for fun
		System.out.println("\nprinting out Map<String, String> phoneBook1 - enhanced for loop:... ");
		for(String current : keys) {
			System.out.println("key: " + current + ", value: " + phoneBook1.get(current));
		}

		//print out the values
		System.out.println("\nprinting out Map<String, String> phoneBook1 - for each:... ");
		keys.forEach(current->System.out.println("key: " + current + ", value: " + phoneBook1.get(current)));


		phoneBook2 = phoneBook1;
		//print out the values
		System.out.println("\nprinting out Map<String, String> phoneBook2 - for each:... ");
		keys.forEach(current->System.out.println("key: " + current + ", value: " + phoneBook2.get(current)));
	}


}
//note the order is not preserved for the hashmap, the data needs to be put into a LinkedHashSet
//cant just ref your way into one:  phonebook2 still has on ordered map  because data was put into a HashMap
//printing out Map<String, String> phoneBook1 - for each:... 
//key: 206.766.1114, value: john smith
//key: 206.711.2224, value: mary smith
//key: 206.722.3334, value: george thomas
//key: 206.755.1734, value: zeke zeke
//key: 206.733.4444, value: karen thomas
//key: 206.744.1534, value: abe johnson
//
//printing out Map<String, String> phoneBook2 - for each:... 
//key: 206.766.1114, value: john smith
//key: 206.711.2224, value: mary smith
//key: 206.722.3334, value: george thomas
//key: 206.755.1734, value: zeke zeke
//key: 206.733.4444, value: karen thomas
//key: 206.744.1534, value: abe johnson
