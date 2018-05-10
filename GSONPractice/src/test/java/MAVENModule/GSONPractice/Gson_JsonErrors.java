package MAVENModule.GSONPractice;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.path.json.JsonPath;
import static io.restassured.path.json.JsonPath.with;

/*
 * create Java object, serialize it with Gson and then use JsonPath object 
 * to parse it to get the data
 * { "errors":[
 * {
 * "code":215,
 * "message":"Bad Authentication data."
 * }
 * ]}
 */


class JsonErrors {
	List<Error> errors = new ArrayList<>();
	
	public JsonErrors(Error e) {
		errors.add(e);
	}
}

class Error {
	int code;
	String message;
	
	public Error(int code, String message) {
		this.code = code;
		this.message = message;
	}
}

public class Gson_JsonErrors {
	
	public static void main(String...arg) {
		
		//create an Error object
		Error err = new Error(215, "Bad Authentication data.");
		
		//create JsonErrors object
		JsonErrors jerrs = new JsonErrors(err);
		
		//instantiate a GsonBuilder object 
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		//(A) - convert to Json and print it out and compare to expected
		String stringJson = gs.toJson(jerrs);
		System.out.println("\r\n" +stringJson);
		
//		{ 
//			"errors":[
//				          {
//				        	  "code":215,
//				        	  "message":"Bad Authentication data."
//				          }
//			          ]
//			}
				
		//(B) parse the stringJson with JsonPath
		//fail
		String path = "errors[0].message";
		
		//String message = new JsonPath(stringJson).getString("path");
		String message = with(stringJson).get(path);
		
		System.out.println("\r\nerrors[0].message = " + message );
	}

}


//output