package MAVENModule.RestAssuredYoutube;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

//the current working directory is used as classpath.
//which is: C:\Users\nightbeats2\eclipse-workspace\_RestAssuredYoutube

public class Main {

	public static void main(String[] args) throws Exception {
		String val = "";

		try {

			InputStream i = Main.class.getResourceAsStream("/hello1.txt");
			BufferedReader r = new BufferedReader(new InputStreamReader(i));

			// reads each line
			String l;
			while ((l = r.readLine()) != null) {
				val = val + l;
			}
			i.close();
		}
		catch(Exception e) {
			System.out.println("bugbug: " + e.getMessage());
		}
	}
}