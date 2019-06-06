package collections;

public class PerformanceReview {
	private String name;
	private String boss;
	private String year;
	private Grade grade;
	
	public enum Grade {
		A,B,C,D,F
	}

	public PerformanceReview(String name,  String boss,
			String year,  Grade grade) {
		this.name = name;
		this.boss = boss;
		this.year = year;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}
	public String getBoss() {
		return boss;
	}
	public String getYear() {
		return year;
	}
	public Grade getGrade() {
		return grade;
	}
}
