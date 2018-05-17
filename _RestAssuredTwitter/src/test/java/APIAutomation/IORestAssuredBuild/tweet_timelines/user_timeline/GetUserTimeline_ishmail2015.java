package  APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

/*
 *
 *	Resource URL
	https://api.twitter.com/1.1/statuses/user_timeline.json
	
	Resource Information
	Response formats	JSON
	Requires authentication?	Yes
	Rate limited?	Yes
	Requests / 15-min window (user auth)	900
	Requests / 15-min window (app auth)
 */
public class GetUserTimeline_ishmail2015 {
	private final Count count = new Count(100);
	private final String screenName = "SriNithyananda";

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	public Response doGetRequest(String endpoint, int statusCode) {
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
				
				.when().get(endpoint)
				
				.then().contentType(ContentType.JSON)
				.statusCode(statusCode)
				.extract().response();
	}

	@Test
	public void verifyTweetForUserTimeline() {
		int count = 0;
		Response response = doGetRequest(baseURI + endpoint, 200);
		//this puts the whole list into a string, not what we want to do
		//System.out.println("tweet " + ++count +  " = " +
		//response.jsonPath().get("text") + "\r\n");
		//no can do, not a map: List<Map<Object, Object>> tmap = response.jsonPath().getMap("text");
		
		List<String> tweets = response.jsonPath().getList("text");
		for(String tweet : tweets) {
			System.out.println("tweet " + ++count +  " = " + tweet + "\r\n");
		}

	}
}
