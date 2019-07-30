package fileio_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Practice {
	
	@BeforeMethod
	public void beforeEachTest() {
		
	}

	@Test
	public void main() {
		String filename = System.getProperty("user.dir") + "\\dir\\test.txt";

		// practice i/o output to a file
		try {
			//write to file:
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write("If a man does not keep pace with his companions...\r\n");
			bw.write("If a man does not keep pace with his companions...\r\n");
			bw.write("If a man does not keep pace with his companions...\r\n");
			bw.write("perhaps it is because he hears a different drummer\r\n");
			bw.write("perhaps it is because he hears a different drummer\r\n");
			bw.write("perhaps it is because he hears a different drummer\r\n");
			bw.close();
			
			//read from file
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while(br.ready()) {
				System.out.println(br.readLine());
			}
			br.close();
		}
		catch(IOException e) {

		}
		catch(Exception e) {

		}

	}

}
