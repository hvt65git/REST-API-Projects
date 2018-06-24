package twitter_timelines; 

import static twittercore.OAUTH.*;

import twittercore.Count;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

/*
//parse user data in get user timeline response
//use a list of maps data structure: List<Map<String,Object>> users
 */

//Map<String, String> map = ...
//for (Map.Entry<String, String> entry : map.entrySet())
//{
//    System.out.println(entry.getKey() + "/" + entry.getValue());
//}

//Interface Map.Entry<K,V>
//All Known Implementing Classes:
//AbstractMap.SimpleEntry, AbstractMap.SimpleImmutableEntry
//Enclosing interface:
//Map<K,V>
//
//public static interface Map.Entry<K,V>
//A map entry (key-value pair). The Map.entrySet method returns a collection-view of the map, whose elements are of this class

public class ParseTwitterUserEntitiesNode {
	private int ctr = 0;
	private final Count count = new Count(5);
	private final String screenName = "iamfonda"; //

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	public Response doGetRequest(String endpoint, int statusCode) {

		ValidatableResponse vr = 
				RestAssured.given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(count.getKeyname(), count.getValue())
				.param("screen_name", screenName)
				.contentType(ContentType.JSON)
				//.log().all()

				.get(endpoint)

				.then()
				.contentType(ContentType.JSON)
				.statusCode(statusCode);
		//.log().all();

		return vr.extract().response();
	}

	/*
	 * 
	 */
	private void printEntitiesUrls(Map<String, Map<String, List<Map<String,Object>>>> map) {
		String innerKey = null;
		//Json to parse and display:
		//            "url": "http://t.co/umCMAYmiAN",
		//             "expanded_url": "http://www.peterfonda.com",
		//             "display_url": "peterfonda.com",
		//from
		//            "entities": {
		//                "url": {
		//                    "urls": [
		//                        {
		//                            "url": "http://t.co/umCMAYmiAN",
		//                            "expanded_url": "http://www.peterfonda.com",
		//                            "display_url": "peterfonda.com",
		//                            "indices": [
		//                                0,
		//                                22
		//                            ]
		//                        }
		//                    ]
		//                },
		//need to use nested maps
		for (Map.Entry<String, Map<String, List<Map<String,Object>>>>
				outerEntry : map.entrySet()) {
			String outerKey = outerEntry.getKey();

			if(outerKey.equals("url")) {
				Map<String, List<Map<String,Object>>>
				innerMap = outerEntry.getValue();

				for (Map.Entry<String, List<Map<String,Object>>>
				innerEntry : innerMap.entrySet()) {
					innerKey = innerEntry.getKey();

					switch(innerKey) {
					case "urls":
						System.out.println("TODO: process entities->url->urls node");
						//write a method to get and print
						//            "url": "http://t.co/umCMAYmiAN",
						//             "expanded_url": "http://www.peterfonda.com",
						//             "display_url": "peterfonda.com",
						break;
					}
				}
			}
		}
	}
	
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void parseAndPrintUserData(
			List<Map<String, Map<String, Map<String, Map<String, List<Map<String,Object>>>>>>> root) {

		String innerKey = null;

		try {
			//Json to parse:
			//	        "user": {
			//            "id": 201923339,
			//            "id_str": "201923339",
			//            "name": "Peter Henry Fonda",
			//            "screen_name": "iamfonda",
			//            "location": "",
			//            "description": "Official Twitter Feed for Peter Henry Fonda - \r\nActor/Filmmaker/Author/Activist. \r\nVisit my website for latest blog and facebook page link.",
			//            "url": "http://t.co/umCMAYmiAN",
			//            "entities": {
			//                "url": {
			//                    "urls": [
			//                        {
			//                            "url": "http://t.co/umCMAYmiAN",
			//                            "expanded_url": "http://www.peterfonda.com",
			//                            "display_url": "peterfonda.com",
			//                            "indices": [
			//                                0,
			//                                22
			//                            ]
			//                        }
			//                    ]
			//                },
			//                "description": {
			//                    "urls": [
			//                        
			//                    ]
			//                }
			//            },
			Map<String, Map<String, Map<String, Map<String, List<Map<String,Object>>>>>> map;

			//print out date and text strings
			for(int i=0; i<root.size(); i++) {
				System.out.println();
				map = root.get(i);

				//need to use nested maps
				for (Map.Entry<String, Map<String, Map<String, Map<String, List<Map<String,Object>>>>>>
						outerEntry : map.entrySet()) {
					String outerKey = outerEntry.getKey();

					if(outerKey.equals("user")) {
						Map<String, Map<String, Map<String, List<Map<String,Object>>>>>
						innerMap = outerEntry.getValue();

						for (Map.Entry<String, Map<String, Map<String, List<Map<String,Object>>>>>
						innerEntry : innerMap.entrySet()) {
							innerKey = innerEntry.getKey();
							Object innerValue = innerEntry.getValue();

							switch(innerKey) {
							case "entities":
								System.out.println("TODO: process entities nodes");
								
//								//create a Map from innerEntry
//								Map<String, Map<String, Map<String, List<Map<String,Object>>>>> entMap = 
//										new HashMap<>();
//								
							    //fill in map
							    Set<Map.Entry<String, Map<String, List<Map<String,Object>>>>> set = 
							    		innerEntry.getValue().entrySet();

								Map<String, Map<String, List<Map<String,Object>>>> mapFromSet = 
										new HashMap<>();
							    
							    for(Map.Entry<String, Map<String, List<Map<String,Object>>>>  entry : set) {
							        mapFromSet.put(entry.getKey(), entry.getValue());
							    }
								
								printEntitiesUrls(mapFromSet);
								break;
							}
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

			List<Map<String, Map<String, Map<String, Map<String, List<Map<String,Object>>>>>>> root = 
					response.jsonPath().getList("$");

			parseAndPrintUserData(root);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}


