package htrestmff1;

import static APIAutomation.IORestAssuredBuild._core.OAUTH.accessToken;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.accessTokenSecret;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.consumerKey;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.consumerSecret;
import static io.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class UtilsTwitter {
	
	//http://www.baeldung.com/java-iterate-map
	//
	//In this quick article, we’ll have a look at the different ways of 
	//iterating through the entries of a Map in Java.
	//
	//Simply put, we can extract the contents of a Map using 
	//keySet(), valueSet() or entrySet(). 
	//Since those are all sets, similar iteration principles apply to all of them.
	//
	//The Map.entrySet API returns a collection-view of the map, whose elements are from the Map class. The only way to obtain a reference to a single map entry is from the iterator of this collection view.
	//
	//The entry.getKey() returns the key and entry.getValue() returns the corresponding value.
	//
	//Let’s have a look at a few of these.
	
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

	//Stream API is one of the main features of Java 8.
	//We can use this feature to
	//loop through a Map as well but as in previous examples,
	//we need to obtain a set of entries first:
	public void iterateUsingStreamAPI(Map<String, Integer> map) {
		map.entrySet().stream()
		// ...
		.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
	}
	
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
