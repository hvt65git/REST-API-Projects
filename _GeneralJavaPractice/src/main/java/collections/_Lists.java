package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static collections.PerformanceReview.Grade.*;

public class _Lists {

	public static void main(String[] args) {
		//create PerformanceReview and Employee objects
		Employee johnD = new Employee("John Doe", new PerformanceReview("John Doe", "Boss Hog", "2018", C));
		Employee janeD = new Employee("Jane Doe", new PerformanceReview("Jane Doe", "Boss Hog", "2018", A));
		Employee mrT = new Employee("Mister T", new PerformanceReview("John Doe", "Boss Hog", "2018", F ));
		
		//(a) ArrayList
		List<Employee> employees = new ArrayList<>();
		employees.add(johnD);
		employees.add(janeD);
		employees.add(mrT);
		
		//print out the employee list
		System.out.println("Printing out an ArrayList:");
		employees.forEach(x->System.out.println("Employee Name: " + x.getName()
		+ ", Performance Review Grade: " + x.getPerformanceReview().getGrade()));
		
		//(b) LinkedList
		List<Employee> employees2 = new LinkedList<>();
		employees2.add(johnD);
		employees2.add(janeD);
		employees2.add(mrT);
		
		//print out the employee list
		System.out.println("\r\nPrinting out a LinkedList:");
		employees2.forEach(x->System.out.println("Employee Name: " + x.getName()
		+ ", Performance Review Grade: " + x.getPerformanceReview().getGrade()));

	}

}
