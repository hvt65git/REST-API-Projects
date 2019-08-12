package ht_twit_8;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;
import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;

public class GetTweets {
	private static final int tweetCount = 100;
	private static final int statusCodeOK = 200;
	private static final String screenName = "realDonaldTrump";
	private static final String resourceURL = "https://api.twitter.com/1.1/statuses/user_timeline.json";

	/*
	 * getTweets
	 */
	private static List<Map<String, Object>> getTweets() {
		ValidatableResponse vr = RestAssured
				.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param("count", tweetCount)
				.param("screen_name", screenName)
				//.contentType(ContentType.JSON)
				//.log().all()
				.when()
				.get(resourceURL)
				.then()
				.assertThat().statusCode(statusCodeOK);
		return vr.extract().response().jsonPath().getList("$");		
	}
	
	/*
	 * printTweets
	 */
	private static void printTweets(List<Map<String, Object>> list) {
		for(int i=0; i<list.size(); i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "text":
					System.out.println("\r\nTweet #" + (i+1) + ": " + entry.getValue());
					break;
				}
			}
		}
	}

	
	/*
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Printing the " + tweetCount + " most recent tweets for: " + screenName);
		printTweets(getTweets());
	}
}
