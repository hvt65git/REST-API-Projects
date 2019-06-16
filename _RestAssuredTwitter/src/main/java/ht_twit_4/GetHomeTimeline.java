
package ht_twit_4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;
import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;

/*
 * 
 */
public class GetHomeTimeline {
	private static final int count = 100;
	private static final int httpOK = 200;
	private static final String baseURI = "https://api.twitter.com/";
	private static final String endpoint = "1.1/statuses/home_timeline.json";

	/*
	 *  
	 */
	private static void displayUserObjects(Object userMap) {
		System.out.println("Printing out user information...");
		
		HashMap<String, Object> hmap = (HashMap<String, Object>)userMap;
		for(Entry<String, Object> entry : hmap.entrySet()) {
			switch(entry.getKey()) {
			//print these entries:			
			//***KEY*** = name
			//***VALUE*** = Russell Wilson
			//
			//***KEY*** = screen_name
			//***VALUE*** = DangeRussWilson
			//
			//***KEY*** = description
			//***VALUE*** = I want to Love like Jesus!
			//
			//***KEY*** = profile_image_url_https
			//***VALUE*** = https://pbs.twimg.com/profile_images/1091423317419012098/usm10f8I_normal.jpg
			//
			//***KEY*** = profile_background_image_url
			//***VALUE*** = http://abs.twimg.com/images/themes/theme1/bg.png
			case "name":
			case "screen_name":
			case "description":
			case "location":
			case "protected":
			case "verified":
			case "followers_count":
			case "friends_count":
			case "favourites_count":
			case "statuses_count":
			case "derived":
				System.out.println(entry.getKey() + " = " + entry.getValue());
				break;
			default:
				break;
			}

		}

	}


	/*
	 *  
	 */
	private static void printTweets(List<HashMap<String, Object>> list) {		
		for(int i=0; i<list.size(); i++) {
			for(Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "created_at" :
					System.out.println("\r\nTweet #" + (i+1) + ", Created at: " + entry.getValue());
					break;
				case "text" :
					System.out.println("Tweet text: " + entry.getValue());
					break;
				case "user" :
					displayUserObjects(entry.getValue());
					break;
				default:
					break;
				}
			}
		}
	}

	/*
	 * 
	 */
	private static Response getHomeTimeline(int count) {
		ValidatableResponse vr = 
				RestAssured.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param("count", count)
				.contentType(ContentType.JSON)
				//.log().all()
				.when()
				.get(baseURI+endpoint)
				.then().assertThat().statusCode(httpOK);
		return vr.extract().response();
	}

	/*
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Printing out my Twitter Home Timeline information...");
		printTweets(getHomeTimeline(count).jsonPath().getList("$"));
	}
}
