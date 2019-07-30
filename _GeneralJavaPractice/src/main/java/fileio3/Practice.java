package fileio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Practice {
	private final String filename = System.getProperty("user.dir") + "\\dir\\test.txt";

	private void collectionsPractice() {
	}

	private void fileIOtest(final String testdata) {
		try {
			//write the test data to a file
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(testdata);
			bw.newLine();
			bw.close();

			//read the test data from the file and
			//assert the data read from file is as expected
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while(br.ready()) {
				String line = br.readLine();
				System.out.println("Just read: " + line);
				Assert.assertEquals(testdata, line);
			}
			br.close();
		}
		catch(IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(enabled = true, dataProvider = "testDataProvider")
	public void main(String testdata) {
		fileIOtest(testdata);
	}


	@DataProvider(parallel = false)
	public String[][] testDataProvider() {
		return new String[][] {
			{"test data line 111"},
			{"test data line 222"},
			{"test data line 333"}
		};
	}
}
