package APIAutomation.SampleRestAssured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static APIAutomation.SampleRestAssured.StatusCode.*;


import org.testng.annotations.Test;

//good sample GET requests from previous Postman session with guided tutorial udemy lecture 2
//
//openweathermap
//api.openweathermap.org/data/2.5/weather?q=Gardena&appid=545c707006c049596a5b2e0e8c05f1ab
//
//googlemaps
//https://maps.googleapis.com/maps/api/place/nearbysearch/json?
//	location=-33.8670522,151.1957362&radius=500&type=restaurant&key=AIzaSyD-Ij7UTEjtRnSeUDhfP26ViC6eDfJ2vIQ

////VerifyStatusCodeAndVerifyNameField
public class GetRequests {
	private final String baseURI = "https://maps.googleapis.com"; //note, no slash on end
	private final String myGooglePlacesKey = "AIzaSyD-Ij7UTEjtRnSeUDhfP26ViC6eDfJ2vIQ";
	private final StatusCode statusCode = OK;

	@Test(enabled = false)
	public void whenGetVerifyFieldsContentTypeAndStatusCode() {
		
		RestAssured.baseURI = baseURI;
		ValidatableResponse response = given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "500").
				param("type", "restaurant").
				param("key", myGooglePlacesKey).
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(statusCode.getValue()).and().
				contentType(ContentType.JSON).and().
				body("results[0].name", equalTo("The Little Snail Restaurantaaa") );

		System.out.println("Request is executed successfully");
		System.out.println("Response = " + response.extract().asString());
	}

	@Test(enabled = true)
	public void verifyHeaders(){
		
		//expected headervalues(from Postman client):
		//Content-Encoding →gzip
		//Server →scaffolding on HTTPServer2

		RestAssured.baseURI = "https://maps.googleapis.com";
		ValidatableResponse response = given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "500").
				param("type", "restaurant").
				param("key", myGooglePlacesKey).
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(statusCode.getValue()).and().
				contentType(ContentType.JSON).and().
				body("results[0].name", equalTo("The Little Snail Restaurant")).and().
				body("results[13].name", equalTo("Harvest Buffet")).and().
				body("results[5].vicinity", equalTo("80 Pyrmont Street, Pyrmont")).and().
				header("content-encoding", "gzip").and().
				header("server", "scaffolding on HTTPServer2");

		System.out.println("Request is executed successfully");
		System.out.println("Response = " + response.extract().asString());

	}

}


