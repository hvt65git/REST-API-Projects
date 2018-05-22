package APIAutomation.IORestAssuredBuild.tweet_timelines.home_timeline;

import static APIAutomation.IORestAssuredBuild._core.UtilsTwitter.doGetRequest;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
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
 */
/*
 * create a record with these two fields 
 *  "id_str": "998758309971701760",
    "text": "RT @Sshivatma: If you can face your fears, "
    		+ "you see they have no depth! https://t.co/7Qj17SZ5MO https://t.co/VhycYzXyh3",

 */

class Record {
	private String id_str;
	private String text;

	public String getId_str() {
		return id_str;
	}

	public String getText() {
		return text;
	}

	public Record(String id_str, String text) {
		this.id_str = id_str;
		this.text = text;
	}

}
public class GetHomeTimeline {
	private final int statusCode = 200;
	private final Count count = new Count(100);

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/home_timeline.json";

	@Test
	public void verifyTweetForUserTimeline() {
		int ctr = 0;
		try {

			//make get request and parse response into lists of tweet id_str and text
			Response response = doGetRequest(baseURI + endpoint, statusCode, count);
			List<String> tweets = response.jsonPath().getList("text");
			List<String> ids = response.jsonPath().getList("id_str");

			//sizes of both list must be the same
			Assert.assertEquals(tweets.size(), ids.size());
			
			//create a Record object and store the tweet text and id for each record
			List<Record> records = new ArrayList<>();
			for(int i=0; i<tweets.size(); i++) {
				records.add(i, new Record(ids.get(i), tweets.get(i)));
			}

			//display 
			for(int j=0; j<records.size(); j++) {
				System.out.println("\r\ntweet # " + j +
						", tweet id = " + records.get(j).getId_str() +
						",\r\ntext = " + records.get(j).getText() );
			}
			System.out.println();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}




