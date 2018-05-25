package APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class GetUserTimeline_bringin_da_boom {
	private int ctr = 0;
	private final Count count = new Count(200);
	private final String screenName = "bringin_da_boom";

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
	//http://www.baeldung.com/java-iterate-map
	//	In this quick article, we’ll have a look at the different ways of iterating through the entries of a Map in Java.
	//
	//	Simply put, we can extract the contents of a Map using keySet(), valueSet() or entrySet(). 
	//	Since those are all sets, similar iteration principles apply to all of them.
	//
	//	The Map.entrySet API returns a collection-view of the map, whose elements are from the Map class. The only way to obtain a reference to a single map entry is from the iterator of this collection view.
	//
	//	The entry.getKey() returns the key and entry.getValue() returns the corresponding value.
	//
	//	Let’s have a look at a few of these.
	

	private void iterateUsingEntrySet(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public void iterateUsingIteratorAndEntry(Map<String, Integer> map) {
		Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public void iterateUsingLambda(Map<String, Integer> map) {
		map.forEach((k, v) -> System.out.println((k + ":" + v)));
	}

	//Stream API is one of the main features of Java 8. We can use this feature to loop through a Map as well but as in previous examples, we need to obtain a set of entries first:
	public void iterateUsingStreamAPI(Map<String, Integer> map) {
		map.entrySet().stream()
		// ...
		.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
	}

	
	private void printOutDateOrTextStrings(Map<String, Object> map) {
		System.out.println();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getKey().equals("created_at")) {
				System.out.println("Date: " + entry.getValue());
			} else if(entry.getKey().equals("text")) {
				System.out.println("Tweet #" + ++ctr + ": " + entry.getValue());
			}
		}
	}
	
	@Test
	public void verifyTweetForUserTimeline() {
		int count = 0;
		Response response = doGetRequest(baseURI + endpoint, 200);

		//this puts the whole list into a string, not what we want to do
		//System.out.println("tweet " + ++count +  " = " +
		//response.jsonPath().get("text") + "\r\n");
		//no can do, not a map: List<Map<Object, Object>> tmap = response.jsonPath().getMap("text");

		//		[
		//		  {
		//		    "created_at": "Thu Nov 10 03:04:07 +0000 2016",
		//		    "id": 796548967001690100,
		//		    "id_str": "796548967001690112",
		//		    "text": "BREAKING NEWS https://t.co/TJUDCBMqxB",
		//		    "truncated": false,

		List<Map<String, Object>> root = response.jsonPath().getList("$");
		int rootSize = root.size();

		List<String> created_at = response.jsonPath().getList("created_at");
		int createdSize = root.size();

		List<String> tweets = response.jsonPath().getList("text");
		int tweetsSize = tweets.size();

		//print out date or text strings
		for(int i=0; i<root.size(); i++) {
			Map<String, Object> map = root.get(i);
			printOutDateOrTextStrings(map);
		}

	}
}
