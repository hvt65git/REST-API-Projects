package collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import static collections.PerformanceReview.Grade.*;

public class _Sets {
	public static void main(String[] args) {
		//create PerformanceReview and Employee objects
		Employee johnD = new Employee("John Doe", new PerformanceReview("John Doe", "Boss Hog", "2018", C));
		Employee janeD = new Employee("Jane Doe", new PerformanceReview("Jane Doe", "Boss Hog", "2018", A));
		Employee mrT = new Employee("Mister T", new PerformanceReview("John Doe", "Boss Hog", "2018", F ));
		
		//(a) HashSet
		Set<Employee> employees = new HashSet<>();
		employees.add(johnD);
		employees.add(johnD);
		employees.add(janeD);
		employees.add(mrT);
		
		//print out the employee list
		System.out.println("Printing out an HashSet (No dupes, no order):");
		employees.forEach(x->System.out.println("Employee Name: " + x.getName()
		+ ", Performance Review Grade: " + x.getPerformanceReview().getGrade()));
		
		//(b) LinkedHashSet
		Set<Employee> employees2 = new LinkedHashSet<>();
		employees2.add(johnD);
		employees2.add(johnD);
		employees2.add(janeD);
		employees2.add(mrT);
		
		//print out the employee list
		System.out.println("\r\nPrinting out a LinkedHashSet (No dupes, Yes order):");
		employees2.forEach(x->System.out.println("Employee Name: " + x.getName()
		+ ", Performance Review Grade: " + x.getPerformanceReview().getGrade()));

	}

}
