package sffb9;

public class SeleniumBase extends DriverFactory {
	public final long WAIT_TIMEOUT = 10; //secs
	
	public static class TestCase<X> {
		X[][] testdata;
		
		public TestCase(X[][] testdata) {
			this.testdata = testdata;
		}
		
		public X[][] getTestData() {
			return this.testdata;
		}
	}
}
