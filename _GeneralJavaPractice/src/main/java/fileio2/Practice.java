package fileio2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class Practice {
	//declare constants
	private static final String filename = System.getProperty("user.dir") + 
			"\\dir\\test.txt";
	private static final String testdata = "testing 123";


	/*
	 * practicing BufferedWriter, FileWriter, BufferedReader, FileReader
	 */
	@Test
	public void test01() {
		try {
			//write the test data to the file
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write(testdata);
			bw.newLine();
			bw.write(testdata);
			bw.newLine();
			bw.write(testdata);
			bw.newLine();
			bw.close();
			
			//read the file and verify it contains the line of text that was written
			
			String temp = null;
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while(br.ready() ) {
				temp =  br.readLine();
				System.out.println(temp);
				Assert.assertEquals(testdata, temp);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}



	}


}
