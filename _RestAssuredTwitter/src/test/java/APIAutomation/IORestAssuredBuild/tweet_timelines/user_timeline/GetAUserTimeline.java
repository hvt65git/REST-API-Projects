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
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

import org.apache.log4j.LogManager;

/*
 * 
 */
public class GetAUserTimeline {
	private final Logger log = LogManager.getLogger(GetAUserTimeline.class.getName());

	private int ctr = 0;
	private final Count count = new Count(11);
	private final String screenName = "tatti_d"; //

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";


	public Response doGetRequest(String endpoint, int statusCode) {
		//log.info("Inside POSTRequest call");
		RestAssured.defaultParser = Parser.JSON;
		
		Response response = 
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
				.statusCode(statusCode).log().all()
				.extract().response();
		
		//log.debug(requestSpecification.log().all());
		return response;
	}

//	public RequestSpecification doGetRequest(String endpoint, int statusCode) {
//		RestAssured.defaultParser = Parser.JSON;
//
//		RequestSpecification requestSpecification = RestAssured.given()
//				.auth()
//				.oauth(consumerKey, 
//						consumerSecret, 
//						accessToken, 
//						accessTokenSecret)
//
//				.param(count.getKeyname(), count.getValue())
//				.param("screen_name", screenName)
//
//				.when().get(endpoint)
//
//				.then().contentType(ContentType.JSON)
//				.statusCode(statusCode)
//				.extract().response();;
//
//				return response;
//
//
//	}
	/*
	 * 
	 */
	private void printOutDateTextStrings(Map<String, Object> map) {

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
			printOutDateTextStrings(map);
		}

	}
}
