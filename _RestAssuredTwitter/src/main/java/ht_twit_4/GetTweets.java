package ht_twit_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;
import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;

public class GetTweets {
	private static final int httpStatusCodeOK = 200;
	private static final String baseURI = "https://api.twitter.com";
	private static final String user_timeline_endpoint = "/1.1/statuses/user_timeline.json";
	
	/*
	 *  
	 */
	private static void printTweets(List<HashMap<String, Object>> list) {		
		for(int i=0; i<list.size(); i++) {
			for(Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "created_at" :
					System.out.println("Tweet #" + (i+1) + ", Created at: " + entry.getValue());
					break;
				case "text" :
					System.out.println("Tweet #" + (i+1) + ", Tweet: " + entry.getValue() + "\r\n");
					break;
				}
			}
		}
	}
	
	/*
	 * 
	 */
	private static Response getTweets() {
		
		ValidatableResponse vr = 
				RestAssured.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param("count", 10)
				.param("screen_name", "realDonaldTrump")
				.contentType(ContentType.JSON)
				.log().all()
				.when()
				.get(baseURI+user_timeline_endpoint)
				.then().assertThat().statusCode(httpStatusCodeOK);
		
		return vr.extract().response();
	}

	/*
	 * 
	 */
	public static void main(String[] args) {
		printTweets(getTweets().jsonPath().getList("$"));
	}
}
