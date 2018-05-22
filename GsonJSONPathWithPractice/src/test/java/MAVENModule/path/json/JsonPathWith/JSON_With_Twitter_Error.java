package MAVENModule.path.json.JsonPathWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.path.json.JsonPath;


/*
 * create Java object, serialize it with Gson and then use JsonPath object 
 * to parse it to get the data
 * 
 * JSON data to create and parse:
 */
//{ 
//"errors":[
//	  {
//	  "code":215,
//	  "message":"Bad Authentication data."
//	  }
//	]
//}


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

public class JSON_With_Twitter_Error {
	
	@Test
	public static void createAndParseJsonErrorData() {
		
		//create an Error object
		Error err = new Error(215, "Bad Authentication data.");
		
		//create JsonErrors object
		JsonErrors jerrs = new JsonErrors(err);
		
		//instantiate a GsonBuilder object 
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		//(A) - convert java object to Json 
		String stringJson = gs.toJson(jerrs);
		System.out.println(stringJson);
			
		//(B) parse the stringJson with JsonPath
		
		//message
		String path = "errors[0].message";
		String message = JsonPath.with(stringJson).get(path);
		System.out.println("JSON path: " + path + " = " + message );
		
		//code
		path = "errors[0].code";
		Integer code = JsonPath.with(stringJson).get(path);
		System.out.println("JSON path: " + path + " = " + code ); 
	}
}

