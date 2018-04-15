package APIAutomation.SampleRestAssured;

import org.testng.annotations.Test;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class EndToEndTestCasePostADeletion {

	@Test
	public void verifyResponse(){

		String requestBody = "{"+
				"\"location\": { "+
				"\"lat\": -33.866971123445,"+
				"\"lng\": 151.1958750"+
				"},"+
				"\"accuracy\": 50,"+
				"\"name\": \"Google Shoes!\","+
				"\"phone_number\": \"(02) 9374 4000\","+
				"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""+
				"}";

		//add a place to google maps
		baseURI = "https://maps.googleapis.com";
		Response  res = given().
				queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
				body(requestBody).
				when().
				post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).

				extract().response();

		//extract the place_id value
		String respose = res.asString();
		System.out.println(respose);

		JsonPath jsonResponse = new JsonPath(respose);
		String placeId = jsonResponse.get("place_id");
		System.out.println(placeId);


		//now delete the place  from google maps using the placeID and verify
		//status code is as expected and also verify
		//status key has value OK

		given().
		queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
		body("{"+
				"\"place_id\": \""+ placeId +"\"" +
				"}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().body("status", equalTo("OK"));

	}
}
//output:
//[RemoteTestNG] detected TestNG version 6.14.2
//WARNING: An illegal reflective access operation has occurred
//WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass$3$1 (file:/C:/Users/nightbeats2/.m2/repository/org/codehaus/groovy/groovy/2.4.4/groovy-2.4.4.jar) to method java.lang.Object.finalize()
//WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass$3$1
//WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
//WARNING: All illegal access operations will be denied in a future release
//{
//   "id" : "beb8badab39eb45128cc7ad3965f5fd67aa19f6e",
//   "place_id" : "qgYvCi0wMDAwMDBmYzhkNTk3NjQ0OjZiMTJhZTM3YjQ3OjI3Zjc5Y2Q5NTMwODkxOTE",
//   "reference" : "CkQxAAAANHR1OsboVbqyavWsWuauDfnnSCEsL-Tfr1dsQWzCFHvH9owH7bz7JMqQbVIppo1UkOeknfJ7TXkS0eSqekUtjBIQ3okPbuG8imFoxwcufXChahoUYIofWwK3_k4sXuU_htSK-NHSYPo",
//   "scope" : "APP",
//   "status" : "OK"
//}
//
//qgYvCi0wMDAwMDBmYzhkNTk3NjQ0OjZiMTJhZTM3YjQ3OjI3Zjc5Y2Q5NTMwODkxOTE
//PASSED: verifyResponse
//
//===============================================
//    Default test
//    Tests run: 1, Failures: 0, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 1, Failures: 0, Skips: 0
//===============================================

