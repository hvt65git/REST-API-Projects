package APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

/*
 * 
 */
public class GetAUserTimeline {
	private final Logger log = LogManager.getLogger(GetAUserTimeline.class.getName());

	private int ctr = 0;
	private final Count count = new Count(2);
	private final String screenName = "realDonaldTrump"; //

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	public Response doGetRequest(String endpoint, int statusCode) {
		log.info("Inside doGetRequest call");
		
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
				.statusCode(statusCode);
		
		log.debug(vr.log().all());
		return vr.extract().response();
	}

	/*
	 * 
	 */
	private void printOutFormattedJsonReponse(Map<String, Object> map) {
		log.info("Inside printOutDateTextStrings call");
		/*
		 * successfully formatted and printed this:
		 *  
			"created_at": "Sat Jun 09 23:03:16 +0000 2018",
	        "id": 1005586152076689408,
	        "id_str": "1005586152076689408",
	        "text": "Based on Justin’s false statements at his news conference, and the fact that Canada is charging massive Tariffs to… https://t.co/8GTCnRTTEG",
	        
		 * next, format and print this data too:
		 * 
		 *  "user": {
            "id": 25073877,
            "id_str": "25073877",
            "name": "Donald J. Trump",
            "screen_name": "realDonaldTrump",
            "location": "Washington, DC",
            "description": "45th President of the United States of America??",
            "url": "https://t.co/OMxB0xp8tD",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/OMxB0xp8tD",
                            "expanded_url": "http://www.Instagram.com/realDonaldTrump",
                            "display_url": "Instagram.com/realDonaldTrump",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": [
                        
                    ]
                }
            },
            "protected": false,
            "followers_count": 52571077,
		 */
		
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getKey().equals("created_at")) {
				System.out.println("Date: " + entry.getValue());
			} else if(entry.getKey().equals("text")) {
				System.out.println("Tweet #" + ++ctr + ": " + entry.getValue());
			}
		}
	}

	/*
	 * 
	 */
	@Test
	public void verifyTweetForUserTimeline() {
		int count = 0;
		Response response = doGetRequest(baseURI + endpoint, 200);
		//System.out.println("verifyTweetForUserTimeline Response = " + response.toString());

		List<Map<String, Object>> root = response.jsonPath().getList("$");
		int rootSize = root.size();

		//print out date and text strings
		for(int i=0; i<rootSize; i++) {
			System.out.println();
			Map<String, Object> map = root.get(i);
			printOutFormattedJsonReponse(map);
		}

	}
}
