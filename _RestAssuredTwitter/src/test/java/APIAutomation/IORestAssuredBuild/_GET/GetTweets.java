package  APIAutomation.IORestAssuredBuild._GET;


import org.testng.annotations.Test;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;



/*
 * 
 * Assignment Get and display the 20 most recent tweets that I added 
 * 
 * Twitter API Documentation:
 * 
https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-user_timeline.html

Resource URL
https://api.twitter.com/1.1/statuses/user_timeline.json

Resource Information

Response formats
JSON
Requires authentication?
Yes
Rate limited?
Yes
Requests / 15-min window (user auth)
900
Requests / 15-min window (app auth)
1500
Parameters

Name
Required
Description
Default Value
Example
user_id
optional
The ID of the user for whom to return results.
 
12345
screen_name
optional
The screen name of the user for whom to return results.
 
noradio
since_id
optional
Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets that can be accessed through the API. If the limit of Tweets has occured since the since_id, the since_id will be forced to the oldest ID available.
 
12345
count
optional
Specifies the number of Tweets to try and retrieve, up to a maximum of 200 per distinct request. The value of count is best thought of as a limit to the number of Tweets to return because suspended or deleted content is removed after the count has been applied. We include retweets in the count, even if include_rts is not supplied. It is recommended you always send include_rts=1 when using this API method.
 
 
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
This parameter will prevent replies from appearing in the returned timeline. Using exclude_replies with the count parameter will mean you will receive up-to count tweets — this is because the count parameter retrieves that many Tweets before filtering out retweets and replies.
 
true
include_rts
optional
When set to false , the timeline will strip any native retweets (though they will still count toward both the maximal length of the timeline and the slice selected by the count parameter). Note: If you’re using the trim_user parameter in conjunction with include_rts, the retweets will still contain a full user object.
 
false
Example Request
GET https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=2

 */

//woohoo! first successful integration of RestAssured and Twitter-
//the keys were to use the io.rest-assured maven libs
//and the scribejava-api and -core version 2.5.2.jars

public class GetTweets {
	private final Count count = new Count(1);
	private final String screenName = "ishmail2015";
	
	private final String baseURI = "https://api.twitter.com";
	private final String resources = "/1.1/statuses/user_timeline.json";
	
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
				//.param("screen_name", screenName) //not necessary
				.log().all()

				.when()
				.get(resources)

				.then().assertThat().statusCode(200).log().all()
				.extract().response();

		System.out.println("*** Twitter Response Headers *** => " +
				response.getHeaders().toString());
		
		///now need to get some of the fields and compare them to expected values
	}
}
//success
//output:
//[RemoteTestNG] detected TestNG version 6.14.2
//WARNING: An illegal reflective access operation has occurred
//WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass (file:/C:/Users/nightbeats2/.m2/repository/org/codehaus/groovy/groovy/2.4.12/groovy-2.4.12.jar) to method java.lang.Object.finalize()
//WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass
//WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
//WARNING: All illegal access operations will be denied in a future release
//Request method:	GET
//Request URI:	https://api.twitter.com/1.1/statuses/user_timeline.json?count=1
//Proxy:			<none>
//Request params:	count=1
//Query params:	<none>
//Form params:	<none>
//Path params:	<none>
//Headers:		Accept=*/*
//Cookies:		<none>
//Multiparts:		<none>
//Body:			<none>
//HTTP/1.1 200 OK
//cache-control: no-cache, no-store, must-revalidate, pre-check=0, post-check=0
//content-disposition: attachment; filename=json.json
//content-encoding: gzip
//content-length: 887
//content-type: application/json;charset=utf-8
//date: Fri, 30 Mar 2018 06:01:41 GMT
//expires: Tue, 31 Mar 1981 05:00:00 GMT
//last-modified: Fri, 30 Mar 2018 06:01:41 GMT
//pragma: no-cache
//server: tsa_a
//set-cookie: personalization_id="v1_8NYeYMzKxAZEo4OIlDjzLw=="; Expires=Sun, 29 Mar 2020 06:01:41 UTC; Path=/; Domain=.twitter.com
//set-cookie: lang=en; Path=/
//set-cookie: guest_id=v1%3A152238970101923250; Expires=Sun, 29 Mar 2020 06:01:41 UTC; Path=/; Domain=.twitter.com
//status: 200 OK
//strict-transport-security: max-age=631138519
//x-access-level: read-write
//x-connection-hash: cd037ef9e2345568c8bf51be560163c3
//x-content-type-options: nosniff
//x-frame-options: SAMEORIGIN
//x-rate-limit-limit: 900
//x-rate-limit-remaining: 898
//x-rate-limit-reset: 1522390587
//x-response-time: 127
//x-transaction: 0097dc00002ac238
//x-twitter-response-tags: BouncerCompliant
//x-xss-protection: 1; mode=block; report=https://twitter.com/i/xss_report
//
//[
//    {
//        "created_at": "Thu Mar 29 08:16:30 +0000 2018",
//        "id": 979271063522693120,
//        "id_str": "979271063522693120",
//        "text": "My first automated tweet! woo hoo!",
//        "truncated": false,
//        "entities": {
//            "hashtags": [
//                
//            ],
//            "symbols": [
//                
//            ],
//            "user_mentions": [
//                
//            ],
//            "urls": [
//                
//            ]
//        },
//        "source": "<a href=\"http://easybix2.com\" rel=\"nofollow\">Henry's6535 Twitter App</a>",
//        "in_reply_to_status_id": null,
//        "in_reply_to_status_id_str": null,
//        "in_reply_to_user_id": null,
//        "in_reply_to_user_id_str": null,
//        "in_reply_to_screen_name": null,
//        "user": {
//            "id": 3556936994,
//            "id_str": "3556936994",
//            "name": "El Cosmic Lupo Solo",
//            "screen_name": "ishmail2015",
//            "location": "Seattle, WA",
//            "description": "",
//            "url": null,
//            "entities": {
//                "description": {
//                    "urls": [
//                        
//                    ]
//                }
//            },
//            "protected": true,
//            "followers_count": 7,
//            "friends_count": 17,
//            "listed_count": 0,
//            "created_at": "Mon Sep 14 05:28:02 +0000 2015",
//            "favourites_count": 508,
//            "utc_offset": -25200,
//            "time_zone": "Pacific Time (US & Canada)",
//            "geo_enabled": false,
//            "verified": false,
//            "statuses_count": 423,
//            "lang": "en",
//            "contributors_enabled": false,
//            "is_translator": false,
//            "is_translation_enabled": false,
//            "profile_background_color": "000000",
//            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
//            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
//            "profile_background_tile": false,
//            "profile_image_url": "http://pbs.twimg.com/profile_images/943500528067035136/xABMIIah_normal.jpg",
//            "profile_image_url_https": "https://pbs.twimg.com/profile_images/943500528067035136/xABMIIah_normal.jpg",
//            "profile_banner_url": "https://pbs.twimg.com/profile_banners/3556936994/1518368847",
//            "profile_link_color": "ABB8C2",
//            "profile_sidebar_border_color": "000000",
//            "profile_sidebar_fill_color": "000000",
//            "profile_text_color": "000000",
//            "profile_use_background_image": false,
//            "has_extended_profile": false,
//            "default_profile": false,
//            "default_profile_image": false,
//            "following": false,
//            "follow_request_sent": false,
//            "notifications": false,
//            "translator_type": "none"
//        },
//        "geo": null,
//        "coordinates": null,
//        "place": null,
//        "contributors": null,
//        "is_quote_status": false,
//        "retweet_count": 0,
//        "favorite_count": 1,
//        "favorited": true,
//        "retweeted": false,
//        "lang": "en"
//    }
//]
//PASSED: getFirstThreeTweets
//
//===============================================
//    Default test
//    Tests run: 1, Failures: 0, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 1, Failures: 0, Skips: 0
//===============================================
