package APIAutomation.IORestAssuredBuild._GET;

import static APIAutomation.IORestAssuredBuild._core.OAUTH.accessToken;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.accessTokenSecret;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.consumerKey;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.consumerSecret;

import org.testng.annotations.Test;

import APIAutomation.IORestAssuredBuild._core.Count;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/*
 * 
 * Assignment: Get and display the 20 most recent tweets and retweets  
 * 
 * Twitter API Documentation:
 * 
 * GET statuses/home_timeline

Returns a collection of the most recent Tweets and Retweets posted by the authenticating user and the users they follow. The home timeline is central to how most users interact with the Twitter service.
Up to 800 Tweets are obtainable on the home timeline. It is more volatile for users that follow many users or follow users who Tweet frequently.
See Working with Timelines for instructions on traversing timelines efficiently.
Resource URL
https://api.twitter.com/1.1/statuses/home_timeline.json
Resource Information


Response formats
JSON
Requires authentication?
Yes (user context only)
Rate limited?
Yes
Requests / 15-min window (user auth)
15
Parameters





Name
Required
Description
Default Value
Example
count
optional
Specifies the number of records to retrieve. Must be less than or equal to 200. Defaults to 20. The value of count is best thought of as a limit to the number of tweets to return because suspended or deleted content is removed after the count has been applied.
 
5
since_id
optional
Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occured since the since_id, the since_id will be forced to the oldest ID available.
 
12345
max_id
optional
Returns results with an ID less than (that is, older than) or equal to the specified ID.
 
54321
trim_user
optional
When set to either true , t or 1 , each Tweet returned in a timeline will include a user object including only the status authors numerical ID. Omit this parameter to receive the complete user object.
 
true
exclude_replies
optional
This parameter will prevent replies from appearing in the returned timeline. Using exclude_replies with the count parameter will mean you will receive up-to count Tweets — this is because the count parameter retrieves that many Tweets before filtering out retweets and replies.
 
true
include_entities
optional
The entities node will not be included when set to false.
 
false
Example Request
GET https://api.twitter.com/1.1/statuses/home_timeline.json
Example Response

 */

public class GetHomeTimeline {
	private final Count count = new Count(3);
	private final String baseURI = "https://api.twitter.com";
	private final String resources = "1.1/statuses/home_timeline.json";
	
	private void createTweet(String tweetJson) {
		
	}

	@Test
	public void verifyTweetForUserTimeline() {
		//create the tweet
		createTweet(null);
		
		//verify tweet appears in user timeline
		RestAssured.baseURI = baseURI;
		Response response = RestAssured.given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)

				.param(count.getKeyname(), count.getValue())

				.log().all()

				.when()
				.get(resources)

				.then().assertThat().statusCode(200).log().all()
				.extract().response();

		System.out.println("*** Twitter Response Headers *** => " +
				response.getHeaders().toString());
	}

}
