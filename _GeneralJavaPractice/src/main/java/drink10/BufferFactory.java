package drink10;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BufferFactory {
	private static final PrintStream origStdOut = System.out;
	private static final ThreadLocal<ByteArrayOutputStream> tl = new ThreadLocal<>();


	public static ByteArrayOutputStream getConsoleBuffer() {
		return tl.get();
	}

	@BeforeMethod
	public void beforeEachTest() {
		//create the console buffer and set it in the thread mgr
		tl.set(new ByteArrayOutputStream());
	}

	@AfterMethod
	public void afterEachTest() throws Exception {		
		System.setOut(origStdOut);
		//System.out.println("\r\n*** In @AfterMethod: " + tl.get().toString() + "- after System.setOut(origStdOut);->  currentThread.Id = " + Thread.currentThread().getId());
		tl.remove();
	}
}
