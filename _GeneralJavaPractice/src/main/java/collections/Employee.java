package collections;

public class Employee {
	private String name;
	private PerformanceReview pr;
	
	public Employee(String name, PerformanceReview pr) {
		this.name = name;
		this.pr = pr;
	}
	
	public String getName() {
		return name;
	}
	public PerformanceReview getPerformanceReview() {
		return pr;
	}
}
