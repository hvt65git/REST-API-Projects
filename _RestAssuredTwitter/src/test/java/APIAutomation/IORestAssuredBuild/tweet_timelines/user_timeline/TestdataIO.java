package APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestdataIO {
	BufferedWriter bw = null;

	public void createFile(String file) {
		// If the file doesn't exist, create it
		// If the file exists, truncate (remove all content)
		try {
			bw = new BufferedWriter(new FileWriter(file));
		} 
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	public void write(String text) {
		try {
			bw.write(text);
		} 
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	public void close() {
		try {
			bw.close();
		} 
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
	
//	public static void main(String[] arg) {	
//		String filename = System.getProperty("user.dir") + "\\" + "test123.txt";
//		TestdataIO td = new TestdataIO();
//		td.createFile(filename);
//		td.write("testing 123...test test test\r\n");
//		td.close();
//	}
}
