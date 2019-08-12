package ht_twit_7;

import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;
import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;

/*
 * 
 */



/*
 * 
 */
public class GetTweets {

	/*
	 * 
	 */
	private static Response getTweets(Map<String,Object> paramMap, String resURL, int statusCodeOK) {

		ValidatableResponse vr = RestAssured.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.params(paramMap)
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
	private static void printUserMetadata(String screenName,
			List<HashMap<String, Object>> list, String fileName) throws IOException {
		
		
		final String display = "\r\nPrinting user metadata for: " + screenName 
				+ "\r\nCurrent Date and Time: " + LocalDateTime.now().toString() + "\r\n";
		
		System.out.println(display);

		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));	
		bw.write(display);
		bw.newLine();

		for(int i=0; i<1; i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "user":
					//type cast the value to a HashMap, ignoring the cast warning
					HashMap<String, Object> hmap = (HashMap<String, Object>)entry.getValue();
					for(Map.Entry<String, Object> e : hmap.entrySet()) {
						System.out.println(e.getKey() + "= " + e.getValue());
						bw.write(e.getKey() + "= " + e.getValue());
						bw.newLine();
					}
					break;
				}		
			}
		}
		bw.close();
	}

	/*
	 * 
	 */
	private static void printTweets(String screenName, 
			List<HashMap<String, Object>> list, String fileName) throws IOException {
		
		final String display = "\r\nPrinting tweets for: " + screenName 
				 	+ "\r\nCurrent Date and Time: " + LocalDateTime.now().toString() +"\r\n";
		
		System.out.println(display);

		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));	
		bw.write(display);
		bw.newLine();

		for(int i=0; i<list.size(); i++) {
			System.out.println("\r\nTweet #" + (i+1) + " :");
			bw.write("\r\nTweet #" + (i+1) + " :");
			bw.newLine();

			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "created_at":
					System.out.println("Date Created:" + entry.getValue());
					bw.write("Date Created:" + entry.getValue());
					bw.newLine();
					break;
				case "text":
					System.out.println(entry.getKey() + "= " + entry.getValue());
					bw.write(entry.getKey() + "= " + entry.getValue());
					bw.newLine();
					break;
				case "entities" :
					//type cast the value to a HashMap, ignoring the cast warning
					HashMap<String, Object> hmap = (HashMap<String, Object>)entry.getValue();
					for(Map.Entry<String, Object> e : hmap.entrySet()) {
						System.out.println(e.getKey() + "= " + e.getValue());
						bw.write(e.getKey() + "= " + e.getValue());
						bw.newLine();
					}
					break;
				}		
			}
		}
		bw.close();
	}

	//use Optional container to prevent absent null pointer checks in client code
	//no! it had an unchecked exception warning that caused a runtime exception!
	private static Object getValueFromMap(Map<String, Object> map, String key) {
		for(Map.Entry<String, Object> e : map.entrySet()) {
			if(e.getKey().equals(key)) {
				return e.getValue();
			}
		}
		return null;
	}

	/*
	 * 
	 */
	public static void main(String[] args) {
		final int statusCodeOK = 200;
		final String resourceURL = "https://api.twitter.com/1.1/statuses/user_timeline.json";

		final String outputFileTweets = System.getProperty("user.dir") + "\\dir\\tweets.txt";
		final String outputFileUserMetadata = System.getProperty("user.dir") + "\\dir\\user_metadata.txt";

		
		Stream<String> stream = Stream.of("h","t");

		stream.forEach(System.out::println);
		

		try {

			//issue tweet api Get command and get the response
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("count", 200);
			paramMap.put("screenName", "ishMail2015");
			Response response = getTweets(paramMap,  resourceURL, statusCodeOK);

			//print out the user metadata first
			String screenName = (String) getValueFromMap(paramMap, "screenName");
			if(screenName != null) {
				printUserMetadata(screenName, response.jsonPath().getList("$"), 
						outputFileUserMetadata);

				//then print out the tweets, including the entities
				printTweets(screenName, response.jsonPath().getList("$"), 
						outputFileTweets);
				
				JsonPath jsp = response.jsonPath();
				jsp.prettyPrint();

			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
