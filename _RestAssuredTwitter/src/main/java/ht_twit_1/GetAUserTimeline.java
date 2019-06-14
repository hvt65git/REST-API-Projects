package ht_twit_1;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import ht_twit_1.CountParam;
import ht_twit_1.ScreenNameParam;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

import static ht_twit_1.StatusCode.OK;
import static ht_twit_1.OAUTH.accessToken;
import static ht_twit_1.OAUTH.accessTokenSecret;
import static ht_twit_1.OAUTH.consumerKey;
import static ht_twit_1.OAUTH.consumerSecret;

import  ht_twit_1.CountParam;
import  ht_twit_1.ScreenNameParam;
import static ht_twit_1.Resource.get_user_timeline_resource_URL;

/*
 * 
 */
class StatusCode {
	public static final int OK = 200;
}

/*
 * 
 */
class OAUTH {
	public static final String consumerKey 			= "Ez8HSiCaVZb0EW5JPctxVFz30";
	public static final String consumerSecret 		= "1jpzfBTO4cIIMFwYT6VsSFnetdBJkK4w032Qr813vwryZj5gwS";
	public static final String accessToken 			= "3556936994-DYhVytMQCt1quSyh20dxOKs3NNVHsbT6ebvCce7";
	public static final String accessTokenSecret	= "pRVltgFncO2Eb0t5uUkPtywCQRwunT7MrfxJpV1dgHGkJ";
}

/*
 * 
 */
class Resource {
	private static final String baseURI = "https://api.twitter.com";
	private static final String user_timeline_endpoint = "/1.1/statuses/user_timeline.json";
	private static final String user_timeline_resource_URL = baseURI + user_timeline_endpoint;

	public static String get_user_timeline_resource_URL() {
		return user_timeline_resource_URL;
	}
}

/*
 * 
 */
class ScreenNameParam {
	private static final String key = "screen_name";
	private static final String value = "realDonaldTrump";

	public static String getValue() {
		return value;
	}

	public static  String getKey() {
		return key;
	}
}

/*
 * 
 */
class CountParam {
	private static final int value = 5;
	private static final String key = "count";

	public static String getKey() {
		return key;
	}
	public static int getValue() {
		return value;
	}
}

/*
 * 
 */
public class GetAUserTimeline {

	/*
	 * 
	 */
	private static Response GETRequest(String resourceURL, int statusCode) {
		ValidatableResponse vr = 
				given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(CountParam.getKey(), CountParam.getValue())
				.param(ScreenNameParam.getKey(), ScreenNameParam.getValue())
				.contentType(ContentType.JSON)
				.log().all()
				.when()
				.get(resourceURL)
				.then()
				.contentType(ContentType.JSON)
				.assertThat().statusCode(OK); /*ValidatableResponse feature*/

		return vr.extract().response();
	}
	
	/*
	 * 
	 */
	private static void printTweet(int ctr, HashMap<String, Object> map) {
		//iterate the map using Entry objects
		for(Entry<String, Object> entry : map.entrySet()) {

			//print the tweet date and the tweet text values
			if(entry.getKey().equals("created_at")) {
				System.out.println("\r\nTweet #" + ++ctr + ": " + entry.getValue());
			} 
			else if(entry.getKey().equals("text") ) {
				System.out.println(entry.getValue());
			}
		}
	}

	/*
	 * 
	 */
	public static void main(String[] arg) {	
		try {
			//issue the twitter user time line REST get request
			Response response = GETRequest(get_user_timeline_resource_URL(), OK);

			//store response in list of hashmaps
			List<HashMap<String, Object>> root = response.jsonPath().getList("$");

			//filter out and print out all the tweets
			for(int i=0; i<root.size(); i++) {			
				printTweet(i, root.get(i));	
				System.out.println();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
