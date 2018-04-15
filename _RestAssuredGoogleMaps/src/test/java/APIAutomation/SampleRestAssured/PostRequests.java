package APIAutomation.SampleRestAssured;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import  com.jayway.restassured.RestAssured;

public class PostRequests {
	private final String baseURI = "https://maps.googleapis.com"; //note, no slash on end
	private final String myGooglePlacesKey = "AIzaSyD-Ij7UTEjtRnSeUDhfP26ViC6eDfJ2vIQ";

	@Test
	public void verifyResponse(){

		given().
		queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
		body("{"+
				"\"location\": { "+
				"\"lat\": -33.766971123445,"+
				"\"lng\": 151.1958750"+
				"},"+
				"\"accuracy\": 50,"+
				"\"name\": \"Google Shoes2!\","+
				"\"phone_number\": \"(02) 9374 4000\","+
				"\"address\": \"485 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""+
				"}").
		when().
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).and().
		body("status", equalTo("OK")); //pass the JSON node
		System.out.println("Request is executed successfully");
		
		//OUTPUT
//		[RemoteTestNG] detected TestNG version 6.14.2
//		WARNING: An illegal reflective access operation has occurred
//		WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass$3$1 (file:/C:/Users/nightbeats2/.m2/repository/org/codehaus/groovy/groovy/2.4.4/groovy-2.4.4.jar) to method java.lang.Object.finalize()
//		WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass$3$1
//		WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
//		WARNING: All illegal access operations will be denied in a future release
//		Request is executed successfully
//		PASSED: verifyResponse
		
	}


}
