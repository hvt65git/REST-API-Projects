package ht_twit_9;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;
import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;


public class GetTweets {
	private static int paramCount = 10; 
	private static int httpStatusOK = 200;

	private static String paramScreenName = "realDonaldTrump"; 
	private static String resURL = "https://api.twitter.com/1.1/statuses/user_timeline.json";	

	/*
	 * done
	 */
	private static List<Map<String, Object>> getTweets(String resURL, long paramCount, String paramScreenName) {
		ValidatableResponse vr = RestAssured
				.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param("count", paramCount)
				.param("screen_name", paramScreenName)
				.when()
				.get(resURL)
				.then()
				.assertThat()
				.statusCode(httpStatusOK);
		return vr.extract().response().jsonPath().getList("$");
	}

	/*
	 * done
	 */
	private static void printListOfMaps(Object value) {
		ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)value;
		for(int i=0; i<list.size(); i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				System.out.println(entry.getKey() + " : " +  entry.getValue());
			}
		}	
	}


	/*
	 * done
	 */
	public static void printEntitiesUrl(Object value1) {
		Map<String, Object> value2 = (HashMap<String, Object>)value1;
		for(Map.Entry<String, Object> entry : value2.entrySet()) {
			switch(entry.getKey()) {
			case "urls":		
				printListOfMaps(entry.getValue());
				break;
			}
		}
	}

	/*
	 * done
	 */	
	public static void printMap(Object user) {
		Map<String, Object> hmap = (HashMap<String, Object>)user;
		for(Map.Entry<String, Object> entry : hmap.entrySet()) {
			System.out.println(entry.getKey() + " : " +  entry.getValue());
		}
	}

	/*
	 * done
	 */	
	public static void printEntities(Object user) {
		//parse and display
		//{description={urls=[]},
		//url : {urls=[{display_url=Instagram.com/realDonaldTrump, indices=[0, 23], expanded_url=http://www.Instagram.com/realDonaldTrump, url=https://t.co/OMxB0x7xC5}]}
		Map<String, Object> hmap = (HashMap<String, Object>)user;
		for(Map.Entry<String, Object> entry : hmap.entrySet()) {
			switch(entry.getKey()) {
			case "description":
				System.out.println("description => " +  entry.getValue());
				break;
			case "url":
				System.out.println("url =>");
				printEntitiesUrl(entry.getValue());
				break;
			}
		}
	}


	/*
	 *  done
	 */	
	public static void printUserEntitiesMetadata(Object user) {
		Map<String, Object> hmap = (HashMap<String, Object>)user;
		for(Map.Entry<String, Object> entry : hmap.entrySet()) {
			switch(entry.getKey()) {
			case "entities":
				printEntities(entry.getValue());
				break;
			}
		}
	}

	/*
	 * done
	 */	
	public static void printTwitterUserEntitiesMetaData(List<Map<String, Object>> list, String screenName) {
		System.out.println("\r\nPrinting Twitter User Entities Metadata for " + screenName + ":");
		for(int i=0; i<1; i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "user":
					printUserEntitiesMetadata(entry.getValue());
					break;
				}
			}
		}
	}

	/*
	 * done
	 */	
	public static void printTwitterUserMetaData(List<Map<String, Object>> list, String screenName) {
		for(int i=0; i<1; i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "user":
					System.out.println("\r\nPrinting Twitter User Metadata for " + screenName + ":");
					printMap(entry.getValue());
					break;
				}
			}
		}
	}

	/*
	 * done
	 */
	public static void printTwitterTweets(List<Map<String, Object>> list, String screenName) {
		System.out.println("\r\nPrinting Twitter Tweets for " + screenName + ":");
		for(int i=0; i<list.size(); i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "text":
					System.out.println("Tweet #" + i + " " +  entry.getValue());
					break;
				}
			}
		}
	}

	/*
	 * done
	 */
	public static void main(String[] arg) {
		List<Map<String, Object>> list = getTweets(resURL, paramCount, paramScreenName);
		printTwitterUserMetaData(list, paramScreenName);
		printTwitterUserEntitiesMetaData(list, paramScreenName);
		printTwitterTweets(list, paramScreenName);
	}
}