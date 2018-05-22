
package  APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;

import org.testng.annotations.Test;

import static APIAutomation.IORestAssuredBuild._core.UtilsTwitter.doGetRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;


import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;


public class GetUserTimeline_Swamiji {
	private final int statusCode = 200;
	private final Count count = new Count(100);
	private final String screenName = "tatti_d";

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	@Test
	public void verifyTweetForUserTimeline() {
		int ctr = 0;
		
		Response response = doGetRequest(baseURI + endpoint, statusCode, count, screenName);
		List<String> tweets = response.jsonPath().getList("text");
		
		for(String tweet : tweets) {
			System.out.println("tweet " + ++ctr +  " = " + tweet + "\r\n");
		}

	}
}
