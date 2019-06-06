package general;

public class MethodChaining {

	public MethodChaining method1() {
		System.out.println("returning from method1...");
		return this;
	}
	
	public MethodChaining method2() {
		System.out.println("returning from method2...");
		return this;
	}
	
	public MethodChaining method3() {
		System.out.println("returning from method3...");
		return this;
	}
	
	public static void main(String[] args) {
		new MethodChaining().method1().method2().method3();

	}
}
//OUTPUT:
//returning from method1...
//returning from method2...
//returning from method3...