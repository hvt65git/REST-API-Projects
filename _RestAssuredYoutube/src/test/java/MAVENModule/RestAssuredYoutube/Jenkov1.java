package MAVENModule.RestAssuredYoutube;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.testng.annotations.Test;

public class Jenkov1 {

	public static void main (String... a) {

		try {
			InputStream inputStream       = new FileInputStream("c:\\data\\input.txt");
			Reader      inputStreamReader = new InputStreamReader(inputStream);

			int data = inputStreamReader.read();
			while(data != -1){
			    char theChar = (char) data;
			    System.out.println("char = " + theChar);
			    data = inputStreamReader.read();
			}

			inputStreamReader.close();
		}
		catch(Exception e) {

		}
	}

}
//success!
//output
//char = l
//char = i
//char = n
//char = e
//char = 1
//char = 
//
//char = 
//
//char = l
//char = i
//char = n
//char = e
//char = 2
//char = 
//
//char = 
//
//char = l
//char = i
//char = n
//char = e
//char = 3
//char = 
//
//char = 
//
//char = e
//char = o
//char = f
