package ht_twit_7;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;
import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;

/*
 * 
 */
public class GetTweets {
	/*
	 * 
	 */
	private static Response getTweets(int count,  String resURL, String screenName, int statusCodeOK) {
		ValidatableResponse vr = RestAssured.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param("count", count)
				.param("screen_name", screenName)
				.contentType(ContentType.JSON)
				//.log().all()
				.when()
				.get(resURL)
				.then()
				.assertThat().statusCode(statusCodeOK);
		return vr.extract().response();
	}

	/*
	 * 
	 */
	private static void printUserMetadata(String screenName, List<HashMap<String, Object>> list) {
		System.out.println("\r\nPrinting user metadata for: " + screenName);
		for(int i=0; i<1; i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "user":
					//type cast the value to a HashMap, ignoring the cast warning
					HashMap<String, Object> hmap = (HashMap<String, Object>)entry.getValue();
					for(Map.Entry<String, Object> e : hmap.entrySet()) {
						System.out.println(e.getKey() + "= " + e.getValue());
					}
					break;
				}		
			}
		}
	}

	/*
	 * 
	 */
	private static void printTweets(String screenName, List<HashMap<String, Object>> list) {
		System.out.println("\r\nPrinting tweets for: " + screenName);
		for(int i=0; i<list.size(); i++) {
			System.out.println("\r\nTweet #" + (i+1) + " :");
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "text":
					System.out.println(entry.getKey() + "= " + entry.getValue());
					break;
				case "entities" :
					//type cast the value to a HashMap, ignoring the cast warning
					HashMap<String, Object> hmap = (HashMap<String, Object>)entry.getValue();
					for(Map.Entry<String, Object> e : hmap.entrySet()) {
						System.out.println(e.getKey() + "= " + e.getValue());
					}
					break;
				}		
			}
		}
	}

	/*
	 * 
	 */
	public static void main(String[] args) {
		final int count = 10;
		final int statusCodeOK = 200;
		final String screenName = "realDonaldTrump";
		final String resourceURL = "https://api.twitter.com/1.1/statuses/user_timeline.json";

		//issue tweet api Get command and get the response
		Response response = getTweets(count, resourceURL, screenName, statusCodeOK);
		
		//print out the user metadata first
		printUserMetadata(screenName, response.jsonPath().getList("$"));
		
		//then print out the tweets, including the entities
		printTweets(screenName, response.jsonPath().getList("$"));

	}

}
