package collections;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import static collections.PerformanceReview.Grade.*
;
public class _Maps {

	public static void main(String[] args) {
		//create PerformanceReview and Employee objects
		Employee johnD = new Employee("John Doe", new PerformanceReview("John Doe", "Boss Hog", "2018", C));
		Employee janeD = new Employee("Jane Doe", new PerformanceReview("Jane Doe", "Boss Hog", "2018", A));
		Employee mrT = new Employee("Mister T", new PerformanceReview("John Doe", "Boss Hog", "2018", F ));

		//(a) HashMap - NO ORDER JUST LIKE A SET
		Map<String, Employee> map = new HashMap<String, Employee>();
		map.put(johnD.getName(), johnD);
		map.put(johnD.getName(), johnD);
		map.put(janeD.getName(), janeD);
		map.put(mrT.getName(), mrT);

		//print out the employee list - iterate in the keySet
		System.out.println("HashMap - NO ORDER JUST LIKE A SET - keySet iteration");
		Set<String> keys = map.keySet();
		keys.forEach(x->System.out.println("Name = " + x + ", "
				+ "Value = " + map.get(x).getPerformanceReview().getGrade().toString()));

		//print out the employee list - iterate on the entrySet
		System.out.println("\r\nHashMap - NO ORDER JUST LIKE A SET - entrySet iteration");
		Set<Entry<String, Employee>> entries = map.entrySet();
		entries.forEach(x->System.out.println("Name = " + x.getKey() 
		+ ", Grade = " + x.getValue().getPerformanceReview().getGrade()));


		//(b) LinkedHashMap - YES to FIFO order


		//print out the employee list


		//(c) Hashtable - YES to sorted order


		//print out the employee list


		//(d) TreeMap - YES to sorted order


		//print out the employee list


	}
}

//OUTPUT:
//Printing out a HashMap (No dupes, no order):
//Employee Name: John Doe, Performance Review Grade: C
//Employee Name: Mister T, Performance Review Grade: F
//Employee Name: Jane Doe, Performance Review Grade: A
//
//Printing out a LinkedHashMap (No dupes, Yes FIFO order):
//Employee Name: John Doe, Performance Review Grade: C
//Employee Name: Jane Doe, Performance Review Grade: A
//Employee Name: Mister T, Performance Review Grade: F
//
//Printing out a Hashtable (No dupes, Yes - SORTED order):
//Employee Name: Jane Doe, Performance Review Grade: A
//Employee Name: John Doe, Performance Review Grade: C
//Employee Name: Mister T, Performance Review Grade: F
//
//Printing out a TreeMap (No dupes, Yes - SORTED order):
//Employee Name: Jane Doe, Performance Review Grade: A
//Employee Name: John Doe, Performance Review Grade: C
//Employee Name: Mister T, Performance Review Grade: F

