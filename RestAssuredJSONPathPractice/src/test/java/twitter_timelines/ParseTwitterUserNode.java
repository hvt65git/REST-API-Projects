package twitter_timelines;


import static twittercore.OAUTH.*;

import twittercore.Count;

import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

/*
//parse user data in get user timeline response
//use a list of maps data structure: List<Map<String,Object>> users
 */

public class ParseTwitterUserNode {
	private int ctr = 0;
	private final Count count = new Count(5);
	private final String screenName = "iamfonda"; //

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	public Response doGetRequest(String endpoint, int statusCode) {

		ValidatableResponse vr = 
				RestAssured.given().auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(count.getKeyname(), count.getValue())
				.param("screen_name", screenName)
				.contentType(ContentType.JSON)
				.log().all()
				.get(endpoint)
				.then().contentType(ContentType.JSON)
				.statusCode(statusCode).log().all();

		return vr.extract().response();
	}

	/*
	 * 
	 */
	private void parseAndPrintUserData(Map<String, Map<String, Object>> map) {

		try {
			//Json to parse:
			//"user": {
			//		    "id": 25073877,
			//		    "id_str": "25073877",
			//		    "name": "Donald J. Trump",
			//		    "screen_name": "realDonaldTrump",
			//		    "location": "Washington, DC",
			//		    "description": "45th President of the United States of America??",
			//		    "url": "https://t.co/OMxB0xp8tD",
			//			...}
			//		need to use nested maps
			for (Map.Entry<String, Map<String, Object>> outerEntry : map.entrySet()) {
				String outerKeyName = outerEntry.getKey();

				if(outerKeyName.equals("user")) {
					Map<String, Object> innerMap = outerEntry.getValue();

					for (Map.Entry<String, Object> innerEntry : innerMap.entrySet()) {
						String childKey = innerEntry.getKey();
					
						switch(childKey) {
						case "id_str":
						case "name": 
						case "screen_name":
						case "location": 
						case "description":
						case "url":
							String childValue = (String)innerEntry.getValue();
							System.out.println(childKey + "= " + childValue);
							break;
						}
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());

		}
	}

	/*
	 * 
	 */
	@Test
	public void verifyTweetForUserTimeline() {
		try {
			int count = 0;
			Response response = doGetRequest(baseURI + endpoint, 200);

			List<Map<String, Map<String, Object>>> root = response.jsonPath().getList("$");
			int rootSize = root.size();

			//print out date and text strings
			for(int i=0; i<rootSize; i++) {
				System.out.println();
				parseAndPrintUserData(root.get(i));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());

		}

	}
}


