package APIAutomation.IORestAssuredBuild._core;

import static APIAutomation.IORestAssuredBuild._core.OAUTH.accessToken;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.accessTokenSecret;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.consumerKey;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.consumerSecret;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class UtilsTwitter {
	
	public static Response doGetRequest(String URI, int statusCode, Count count, String screenName) {
		RestAssured.defaultParser = Parser.JSON;
		
		return
			given()
			.auth()
			.oauth(consumerKey, 
					consumerSecret, 
					accessToken, 
					accessTokenSecret)
			
			.param(count.getKeyname(), count.getValue())
			.param("screen_name", screenName)
			
			.when().get(URI)
			
			.then().contentType(ContentType.JSON)
			.statusCode(statusCode)
			
			.extract().response();
	}
	
	public static Response doGetRequest(String endpoint, int statusCode, Count count) {
		RestAssured.defaultParser = Parser.JSON;
		
		return
			given()
			.auth()
			.oauth(consumerKey, 
					consumerSecret, 
					accessToken, 
					accessTokenSecret)
			
			.param(count.getKeyname(), count.getValue())
			.when().get(endpoint)
			
			.then().contentType(ContentType.JSON)
			.statusCode(statusCode)
			
			.extract().response();
	}

}
