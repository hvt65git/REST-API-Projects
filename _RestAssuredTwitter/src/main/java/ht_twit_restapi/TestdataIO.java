package ht_twit_restapi;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestdataIO {
	BufferedWriter bw = null;

	public void createFile(String file) {
		try {
			// If the file doesn't exist, create it
			// If the file exists, truncate (remove all content)
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
	
}
