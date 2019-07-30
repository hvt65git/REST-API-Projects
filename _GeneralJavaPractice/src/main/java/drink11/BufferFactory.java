package drink11;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

abstract class BufferFactory {
	private static final PrintStream origStdOut = System.out;
	private static final ThreadLocal<ByteArrayOutputStream> tl = new ThreadLocal<>();
	private ByteArrayOutputStream consoleBuffer = new ByteArrayOutputStream();

	public static ByteArrayOutputStream getConsoleBuffer() {
		return tl.get();
	}

	@BeforeMethod
	public void beforeEachTest() {
		//create the console buffer and set it in the thread mgr
		//do NOT do the System.setOut here for parallel = true DataProvider
		tl.set(consoleBuffer);
		
	}

	@AfterMethod
	public void afterEachTest() throws Exception {		
		System.setOut(origStdOut);
		tl.remove();
		consoleBuffer = new ByteArrayOutputStream();
	}
}
