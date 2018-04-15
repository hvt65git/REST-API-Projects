package APIAutomation.SampleRestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

import  com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;


//https://developers.google.com/places/web-service/add-place

public class PostXMLRequest {
	

	@Test
	public void verifyResponse() throws IOException{
		
		System.out.println(System.getProperty("user.dir"));
		
		String requestBody = generateString("PostXMLPayload.xml");
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		Response  res = given().
			queryParam("key", "AIzaSyB-ZliaFkPtyfykn7E2nW2yxgBPAvRVUMo").
			body(requestBody).
			when().
				post("/maps/api/place/add/xml"). //using xml format
		 then().assertThat().statusCode(200).
		
		extract().response();
		
		String respose = res.asString();
		System.out.println(respose);
		
		XmlPath xmlResponse = new XmlPath(respose);
		String placeId = xmlResponse.get("PlaceAddResponse.place_id");
		System.out.println("*********************");
		System.out.println(placeId);

	}
	
	public static String generateString(String filename) throws IOException{
		String filePath = System.getProperty("user.dir")+"\\payloads\\"+filename;
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
}

//output
//[RemoteTestNG] detected TestNG version 6.14.2
//C:\Users\nightbeats2\eclipse-workspace\SampleRestAssured-U2
//WARNING: An illegal reflective access operation has occurred
//WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass$3$1 (file:/C:/Users/nightbeats2/.m2/repository/org/codehaus/groovy/groovy/2.4.4/groovy-2.4.4.jar) to method java.lang.Object.finalize()
//WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass$3$1
//WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
//WARNING: All illegal access operations will be denied in a future release
//<?xml version="1.0" encoding="UTF-8"?>
//<PlaceAddResponse>
// <status>OK</status>
// <reference>CkQxAAAARpDdEWOe2b1LAc6FRlchILJ0aNL0yzNbXHuVSOBcPiRJ_cfwE90qI5LWOvgkZAGmtP5o3SeiQjW135a3Twll5hIQOlBEtDPw3ihC8LCYhrao-hoUHxvClR3RdEoFRCzsskVCfCpcCv0</reference>
// <id>80ab4f474dc0789740f5b360719600d842023360</id>
// <place_id>qgYvCi0wMDAwMDBmYzhkNTk3NjQ0OjZiMTJhZTM3YjQ3OjM3ZWJjYTVlMDliNDY5ZGU</place_id>
// <scope>APP</scope>
//</PlaceAddResponse>
//
//*********************
//qgYvCi0wMDAwMDBmYzhkNTk3NjQ0OjZiMTJhZTM3YjQ3OjM3ZWJjYTVlMDliNDY5ZGU
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

