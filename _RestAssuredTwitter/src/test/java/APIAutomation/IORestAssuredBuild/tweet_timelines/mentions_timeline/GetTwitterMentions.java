package  APIAutomation.IORestAssuredBuild.tweet_timelines.mentions_timeline;

import APIAutomation.IORestAssuredBuild._core.Count;

import static APIAutomation.IORestAssuredBuild._core.UtilsTwitter.doGetRequest;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


/*
 * 
 * Assignment Get and display the 20 most recent mentions of me ishmail2015
 * 
 * API Reference contents  
GET statuses/home_timeline 
GET statuses/mentions_timeline 
GET statuses/user_timeline 

Returns the 20 most recent mentions (Tweets containing a users’s @screen_name) for the authenticating user.
The timeline returned is the equivalent of the one seen when you view your mentions on twitter.com.
This method can only return up to 800 tweets.
See Working with Timelines for instructions on traversing timelines.

Resource URL
https://api.twitter.com/1.1/statuses/mentions_timeline.json
Resource Information
*/

public class GetTwitterMentions {
	private final int statusCode = 200;
	private final Count count = new Count(100);

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/mentions_timeline.json";

//	@Test
//	public void verifyTweetForUserTimeline() {
//		int ctr = 0;
//		
//		Response response = doGetRequest(baseURI + endpoint, statusCode, count);
//		List<String> tweets = response.jsonPath().getList("text");
//		
//		for(String tweet : tweets) {
//			System.out.println("tweet " + ++ctr +  " = " + tweet + "\r\n");
//		}
//
//	}
}


