package ht_twit_3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static ht_twit_3.OAUTH.accessToken;
import static ht_twit_3.OAUTH.accessTokenSecret;
import static ht_twit_3.OAUTH.consumerKey;
import static ht_twit_3.OAUTH.consumerSecret;

import static ht_twit_3.TwitterResource.httpOK;
import static ht_twit_3.TwitterResource.baseURI;
import static ht_twit_3.TwitterResource.user_timeline_endpoint;
import static ht_twit_3.TwitterResource.countParam;
import static ht_twit_3.TwitterResource.screenNameParam;

/*
 * 
 */

/*
 * 
 */
class Param {
	String name;
	Object value;

	public Param(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
}

class TwitterResource {
	public static final int httpOK = 200;
	public static final String baseURI = "https://api.twitter.com";
	public static final String user_timeline_endpoint = "/1.1/statuses/user_timeline.json";
	
	public static final Param countParam = new Param("count", 3);
	public static final Param screenNameParam = new Param("screen_name", "realDonaldTrump");
}

/*
 * Calls the Twitter ReST API for getting tweets, parses the response, then displays the tweets.
 */
public class GetUserTimeline {
	
	/*
	 * 
	 */
	private static Response GetTwitterUserTimeline() {
		//issue the Get request for tweets
		ValidatableResponse vr = 
				RestAssured.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param(countParam.getName(), (int)countParam.getValue())
				.param(screenNameParam.getName(), screenNameParam.getValue())
				.contentType(ContentType.JSON)
				.log().all()
				.when()
				.get(baseURI+user_timeline_endpoint)
				.then()
				.assertThat().statusCode(httpOK);
		return vr.extract().response();
	}

	/*
	 * 
	 */
	private static void printTweets(List<HashMap<String,Object>> list) {
		//iterate the  map using an entry set
		for(int i=0; i<list.size(); i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "created_at":
					System.out.println("Tweet #" + (i+1) + " Date Created:" + entry.getValue());
					break;
				case "text":
					System.out.println(entry.getValue() + "\r\n");
					break;
				case "retweet_count":
				case "favorite_count":
					System.out.println(entry.getKey() + " = "+ entry.getValue());
					break;
				}
			}
		}
	}
	
	/*
	 * 
	 */
	public static void main(String[] arg) {	
		//issue the Rest Get request for getting a Twitter user timeline
		Response response = GetTwitterUserTimeline();

		//process the JSON response and print out the tweets
		printTweets(response.jsonPath().getList("$"));
	}
}

//OUTPUT
//Request method:	GET
//Request URI:	https://api.twitter.com/1.1/statuses/user_timeline.json?count=3&screen_name=realDonaldTrump
//Proxy:			<none>
//Request params:	count=3
//				screen_name=realDonaldTrump
//Query params:	<none>
//Form params:	<none>
//Path params:	<none>
//Headers:		Accept=*/*
//				Content-Type=application/json; charset=UTF-8
//Cookies:		<none>
//Multiparts:		<none>
//Body:			<none>
//Tweet #1 Date Created:Fri Jun 14 22:37:38 +0000 2019
//retweet_count = 10201
//favorite_count = 48480
//....While Pennsylvania is BOOMING, I don’t want there to be even a little glitch in Coatesville – every job counts.… https://t.co/DZ7LVDm0iu
//
//Tweet #2 Date Created:Fri Jun 14 22:37:37 +0000 2019
//retweet_count = 9875
//favorite_count = 47375
//Just spoke to Marillyn Hewson, CEO of @LockheedMartin, about continuing operations for the @Sikorsky in Coatesville… https://t.co/DqqlThg65Z
//
//Tweet #3 Date Created:Fri Jun 14 21:32:53 +0000 2019
//retweet_count = 11681
//favorite_count = 0
//RT @RepMarkMeadows: This week President Trump was able to do something no one else could: get the left to care about foreign intel in our e…


