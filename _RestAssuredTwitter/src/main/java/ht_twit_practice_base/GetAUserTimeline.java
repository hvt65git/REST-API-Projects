package ht_twit_practice_base;


/*
 * 
 * https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline
 * 
Get Tweet timelines

API Reference

API Reference contents 
GET statuses/home_timeline
GET statuses/mentions_timeline
GET statuses/user_timeline
GET statuses/user_timeline

Important notice: 
On June 19, 2019, we will begin limiting access to the /statuses/user_timeline endpoint to 100,000 requests 
per day as a default, in addition to existing user-level and app-level rate limits. 
This limit will be on a per-application basis, meaning that a single developer app can make up to 100,000 calls during any single 24-hour period.

Returns a collection of the most recent Tweets posted by the user indicated by the screen_name or user_id parameters.

User timelines belonging to protected users may only be requested when the authenticated user either "owns" the timeline or is an approved follower of the owner.

The timeline returned is the equivalent of the one seen as a user's profile on Twitter.

This method can only return up to 3,200 of a user's most recent Tweets. Native retweets of other statuses by the user is included in this total, regardless of whether include_rts is set to false when requesting this resource.

See Working with Timelines for instructions on traversing timelines.

See Embedded Timelines, Embedded Tweets, and GET statuses/oembed for tools to render Tweets according to Display Requirements.

Resource URL
https://api.twitter.com/1.1/statuses/user_timeline.json

Resource Information
Response formats	JSON
Requires authentication?	Yes
Rate limited?	Yes
Requests / 15-min window (user auth)	900
Requests / 15-min window (app auth)	1500
Parameters
Name	Required	Description	Default Value	Example
user_id	optional	The ID of the user for whom to return results.		12345
screen_name	optional	The screen name of the user for whom to return results.		noradio
since_id	optional	Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets that can be accessed through the API. If the limit of Tweets has occured since the since_id, the since_id will be forced to the oldest ID available.		12345
count	optional	Specifies the number of Tweets to try and retrieve, up to a maximum of 200 per distinct request. The value of count is best thought of as a limit to the number of Tweets to return because suspended or deleted content is removed after the count has been applied. We include retweets in the count, even if include_rts is not supplied. It is recommended you always send include_rts=1 when using this API method.


 */


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static ht_twit_practice_base.OAUTH.consumerKey;
import static ht_twit_practice_base.OAUTH.consumerSecret;
import static ht_twit_practice_base.OAUTH.accessToken;
import static ht_twit_practice_base.OAUTH.accessTokenSecret;

import static ht_twit_practice_base.StatusCode.OK;
import static ht_twit_practice_base.Resource.get_user_timeline_resource_URL;


class StatusCode {
	public static final int OK = 200;
}

class OAUTH {
	public static final String consumerKey 			= "Ez8HSiCaVZb0EW5JPctxVFz30";
	public static final String consumerSecret 		= "1jpzfBTO4cIIMFwYT6VsSFnetdBJkK4w032Qr813vwryZj5gwS";
	public static final String accessToken 			= "3556936994-DYhVytMQCt1quSyh20dxOKs3NNVHsbT6ebvCce7";
	public static final String accessTokenSecret	= "pRVltgFncO2Eb0t5uUkPtywCQRwunT7MrfxJpV1dgHGkJ";
}

class Resource {
	private static final String baseURI = "https://api.twitter.com";
	private static final String user_timeline_endpoint = "/1.1/statuses/user_timeline.json";
	private static final String user_timeline_resource_URL = baseURI + user_timeline_endpoint;
	
	public static String get_user_timeline_resource_URL() {
		return user_timeline_resource_URL;
	}
}

class ScreenNameParam {
	private final String key = "screen_name";
	private String value = "realDonaldTrump";

	public ScreenNameParam( ) {
		super();
	}

	public ScreenNameParam(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}
}

class CountParam {
	private final String key = "count";
	private int value = 5;

	public CountParam() {
		super();
	}
	public CountParam (int value) {
		this.value = value;
	}
	public String getKey() {
		return this.key;
	}
	public int getValue() {
		return value;
	}
}

public class GetAUserTimeline {
	private static int ctr = 0;
	private static final CountParam countParam = new CountParam();
	private static final ScreenNameParam screenNameParam = new ScreenNameParam();

	//terminology note(s):
	//
	//Resource URL
	//https://api.twitter.com/1.1/statuses/user_timeline.json
	//
	//Request URI:	
	//https://api.twitter.com/1.1/statuses/user_timeline.json?count=3&screen_name=realDonaldTrump
	//

	/*
	 * MEMORIZATION ORDER: #1
	 */
	private static Response GETRequest(String resourceURL, int statusCode) {

		ValidatableResponse vr = 
				given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param(countParam.getKey(), countParam.getValue())
				.param(screenNameParam.getKey(), screenNameParam.getValue())
				.contentType(ContentType.JSON)
				.log().all()
				.when()
				.get(resourceURL)
				.then()
				.contentType(ContentType.JSON)
				.assertThat().statusCode(OK); /*ValidatableResponse feature*/

		return vr.extract().response();
	}

	/*
	 * MEMORIZATION ORDER: #2
	 */
	private static void printTweet(HashMap<String, Object> map) {

		//iterate the map using Entry objects
		for(Entry<String, Object> entry : map.entrySet()) {

			//print the tweet date and the tweet text values
			if(entry.getKey().equals("created_at")) {
				System.out.println("\r\nTweet #" + ++ctr + ": " + entry.getValue());
			} 
			else if(entry.getKey().equals("text") ) {
				System.out.println(entry.getValue());
			}
		}
	}

	/*
	 * MEMORIZATION ORDER: #3
	 */
	public static void main(String[] arg) {	

		try {
			//issue the REST get request
			Response response = GETRequest(get_user_timeline_resource_URL(), OK);
			//System.out.println(response.body().asString());

			//store response in list of hashmaps
			List<HashMap<String, Object>> root = response.jsonPath().getList("$");

			//filter out and print out all the tweets
			for(int i=0; i<root.size(); i++) {			
				printTweet(root.get(i));	
				System.out.println();
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
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
//
//Tweet #1: Fri Jun 14 00:35:04 +0000 2019
//Thank you Jason Chaffetz! #MAGA https://t.co/sz6LiPXez6
//
//
//Tweet #2: Thu Jun 13 21:03:45 +0000 2019
//Today we announced vital new actions that we are taking to help former inmates find a job, live a crime-free life,… https://t.co/C6Iv95Vbss
//
//
//Tweet #3: Thu Jun 13 20:10:26 +0000 2019
//....She is a very special person with extraordinary talents, who has done an incredible job! I hope she decides to… https://t.co/A2HKT8oVGg

