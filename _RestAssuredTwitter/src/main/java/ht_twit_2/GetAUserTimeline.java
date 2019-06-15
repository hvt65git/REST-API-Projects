package ht_twit_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

import static ht_twit_2.OAUTH.accessToken;
import static ht_twit_2.OAUTH.accessTokenSecret;
import static ht_twit_2.OAUTH.consumerKey;
import static ht_twit_2.OAUTH.consumerSecret;

import static ht_twit_2.Resource.get_user_timeline_resource_URL;


class OAUTH {
	public static final String consumerKey 			= "Ez8HSiCaVZb0EW5JPctxVFz30";
	public static final String consumerSecret 		= "1jpzfBTO4cIIMFwYT6VsSFnetdBJkK4w032Qr813vwryZj5gwS";
	public static final String accessToken 			= "3556936994-DYhVytMQCt1quSyh20dxOKs3NNVHsbT6ebvCce7";
	public static final String accessTokenSecret	= "pRVltgFncO2Eb0t5uUkPtywCQRwunT7MrfxJpV1dgHGkJ";
}


class Resource {
	private static final String baseURI = "https://api.twitter.com";
	private static final String user_timeline_endpoint = "/1.1/statuses/user_timeline.json";
	
	public static String get_user_timeline_resource_URL() {
		return baseURI + user_timeline_endpoint;
	}
}

public class GetAUserTimeline {
	private static final int count = 20;
	private static final int statusCodeOK = 200;

	private static Response GETRequest(String resourceURL, int sCode) {
		
		ValidatableResponse vr = 
				given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param("count", count)
				.param("screen_name", "realDonaldTrump")
				.contentType(ContentType.JSON)
				.log().all()
				.when()
				.get(resourceURL)
				.then()
				.contentType(ContentType.JSON)
				.assertThat().statusCode(sCode); /*ValidatableResponse feature*/

		return vr.extract().response();
	}

	private static void printTweet(int ctr, HashMap<String, Object> map) {
		
		for(Entry<String, Object> entry : map.entrySet()) {
			
			switch(entry.getKey()) {
			case "created_at":
				System.out.println("\r\nTweet #" + ++ctr + ": " + entry.getValue());
				break;
			case "text":
				System.out.println(entry.getValue());
				break;
			}
		}
	}


	public static void main(String[] arg) {	
		try {
			//issue the twitter user time line Get request
			Response response = GETRequest(get_user_timeline_resource_URL(), statusCodeOK);

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
