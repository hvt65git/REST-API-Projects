package  APIAutomation.IORestAssuredBuild._GET;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

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
GET statuses/mentions_timeline

Returns the 20 most recent mentions (Tweets containing a users’s @screen_name) for the authenticating user.
The timeline returned is the equivalent of the one seen when you view your mentions on twitter.com.
This method can only return up to 800 tweets.
See Working with Timelines for instructions on traversing timelines.

Resource URL
https://api.twitter.com/1.1/statuses/mentions_timeline.json
Resource Information


Response formats
JSON
Requires authentication?
Yes (user context only)
Rate limited?
Yes
Requests / 15-min window (user auth)
75
Parameters





Name
Required
Description
Default Value
Example
count
optional
Specifies the number of Tweets to try and retrieve, up to a maximum of 200. The value of count is best thought of as a limit to the number of tweets to return because suspended or deleted content is removed after the count has been applied. We include retweets in the count, even if include_rts is not supplied. It is recommended you always send include_rts=1 when using this API method.
 
 
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
When set to either true , t or 1 , each tweet returned in a timeline will include a user object including only the status authors numerical ID. Omit this parameter to receive the complete user object.
 
true
include_entities
optional
The entities node will not be included when set to false.
 
false
Example Request
GET https://api.twitter.com/1.1/statuses/mentions_timeline.json?count=2&since_id=14927799
 * 
 *
 */



public class GetTwitterMentions {
	private final Count count = new Count(1);
	private final String baseURI = "https://api.twitter.com";
	private final String resources = "/1.1/statuses/mentions_timeline.json";

	@Test
	public void getFavoritesList() {
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

				.then().assertThat().statusCode(200)
				.log().all()
				
				.extract().response();

		System.out.println("*** Twitter Response Headers *** => " +
		response.getHeaders().toString());
	}
}

//success!
//[RemoteTestNG] detected TestNG version 6.14.2
//Request method:	GET
//Request URI:	https://api.twitter.com/1.1/statuses/mentions_timeline.json?count=1
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
//content-length: 1188
//content-type: application/json;charset=utf-8
//date: Sun, 06 May 2018 08:25:58 GMT
//expires: Tue, 31 Mar 1981 05:00:00 GMT
//last-modified: Sun, 06 May 2018 08:25:58 GMT
//pragma: no-cache
//server: tsa_a
//set-cookie: personalization_id="v1_8G+9juAYJxEdaOnKF9RJAA=="; Expires=Tue, 05 May 2020 08:25:58 GMT; Path=/; Domain=.twitter.com
//set-cookie: lang=en; Path=/
//set-cookie: guest_id=v1%3A152559515861622256; Expires=Tue, 05 May 2020 08:25:58 GMT; Path=/; Domain=.twitter.com
//status: 200 OK
//strict-transport-security: max-age=631138519
//x-access-level: read-write
//x-connection-hash: 5f1110580335103f5c75347085050811
//x-content-type-options: nosniff
//x-frame-options: SAMEORIGIN
//x-rate-limit-limit: 75
//x-rate-limit-remaining: 74
//x-rate-limit-reset: 1525596058
//x-response-time: 44
//x-transaction: 0001274c00fef79a
//x-twitter-response-tags: BouncerCompliant
//x-xss-protection: 1; mode=block; report=https://twitter.com/i/xss_report
//
//[
//    {
//        "created_at": "Mon Feb 12 05:01:57 +0000 2018",
//        "id": 962914649862795264,
//        "id_str": "962914649862795264",
//        "text": "@ishmail2015 Subscribe :-)  I'm still setting up https://t.co/LSCBfjO8DU",
//        "truncated": false,
//        "entities": {
//            "hashtags": [
//                
//            ],
//            "symbols": [
//                
//            ],
//            "user_mentions": [
//                {
//                    "screen_name": "ishmail2015",
//                    "name": "Cosmico Lupo Solo",
//                    "id": 3556936994,
//                    "id_str": "3556936994",
//                    "indices": [
//                        0,
//                        12
//                    ]
//                }
//            ],
//            "urls": [
//                {
//                    "url": "https://t.co/LSCBfjO8DU",
//                    "expanded_url": "https://www.youtube.com/channel/UCmBRLoEbLBUod55EUrCtmCQ/videos?view_as=subscriber",
//                    "display_url": "youtube.com/channel/UCmBRL…",
//                    "indices": [
//                        49,
//                        72
//                    ]
//                }
//            ]
//        },
//        "source": "<a href=\"http://twitter.com\" rel=\"nofollow\">Twitter Web Client</a>",
//        "in_reply_to_status_id": 962801342694678528,
//        "in_reply_to_status_id_str": "962801342694678528",
//        "in_reply_to_user_id": 3556936994,
//        "in_reply_to_user_id_str": "3556936994",
//        "in_reply_to_screen_name": "ishmail2015",
//        "user": {
//            "id": 922643294223589376,
//            "id_str": "922643294223589376",
//            "name": "3rd Eye of Deborah",
//            "screen_name": "ConsecratedMind",
//            "location": "Realms https://www.facebook.com/3rdeyeofdeborah",
//            "description": "Just when I though I had 'arrived' witnessing visions and miracles from God, I had an 'out-of-body experience' October 21st 2015",
//            "url": "https://t.co/VHiNeJepzs",
//            "entities": {
//                "url": {
//                    "urls": [
//                        {
//                            "url": "https://t.co/VHiNeJepzs",
//                            "expanded_url": "https://www.youtube.com/channel/UCmBRLoEbLBUod55EUrCtmCQ",
//                            "display_url": "youtube.com/channel/UCmBRL…",
//                            "indices": [
//                                0,
//                                23
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
//            "protected": false,
//            "followers_count": 14,
//            "friends_count": 31,
//            "listed_count": 0,
//            "created_at": "Tue Oct 24 01:57:58 +0000 2017",
//            "favourites_count": 134,
//            "utc_offset": null,
//            "time_zone": null,
//            "geo_enabled": false,
//            "verified": false,
//            "statuses_count": 87,
//            "lang": "en",
//            "contributors_enabled": false,
//            "is_translator": false,
//            "is_translation_enabled": false,
//            "profile_background_color": "000000",
//            "profile_background_image_url": "http://abs.twimg.com/images/themes/theme1/bg.png",
//            "profile_background_image_url_https": "https://abs.twimg.com/images/themes/theme1/bg.png",
//            "profile_background_tile": false,
//            "profile_image_url": "http://pbs.twimg.com/profile_images/953040763893567488/ZJ0B9wUN_normal.jpg",
//            "profile_image_url_https": "https://pbs.twimg.com/profile_images/953040763893567488/ZJ0B9wUN_normal.jpg",
//            "profile_banner_url": "https://pbs.twimg.com/profile_banners/922643294223589376/1521696010",
//            "profile_link_color": "981CEB",
//            "profile_sidebar_border_color": "000000",
//            "profile_sidebar_fill_color": "000000",
//            "profile_text_color": "000000",
//            "profile_use_background_image": false,
//            "has_extended_profile": false,
//            "default_profile": false,
//            "default_profile_image": false,
//            "following": true,
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
//        "possibly_sensitive": false,
//        "lang": "en"
//    }
//]
//*** Twitter Response Headers *** => cache-control=no-cache, no-store, must-revalidate, pre-check=0, post-check=0
//content-disposition=attachment; filename=json.json
//content-encoding=gzip
//content-length=1188
//content-type=application/json;charset=utf-8
//date=Sun, 06 May 2018 08:25:58 GMT
//expires=Tue, 31 Mar 1981 05:00:00 GMT
//last-modified=Sun, 06 May 2018 08:25:58 GMT
//pragma=no-cache
//server=tsa_a
//set-cookie=personalization_id="v1_8G+9juAYJxEdaOnKF9RJAA=="; Expires=Tue, 05 May 2020 08:25:58 GMT; Path=/; Domain=.twitter.com
//set-cookie=lang=en; Path=/
//set-cookie=guest_id=v1%3A152559515861622256; Expires=Tue, 05 May 2020 08:25:58 GMT; Path=/; Domain=.twitter.com
//status=200 OK
//strict-transport-security=max-age=631138519
//x-access-level=read-write
//x-connection-hash=5f1110580335103f5c75347085050811
//x-content-type-options=nosniff
//x-frame-options=SAMEORIGIN
//x-rate-limit-limit=75
//x-rate-limit-remaining=74
//x-rate-limit-reset=1525596058
//x-response-time=44
//x-transaction=0001274c00fef79a
//x-twitter-response-tags=BouncerCompliant
//x-xss-protection=1; mode=block; report=https://twitter.com/i/xss_report
//PASSED: getFavoritesList
//
//===============================================
//    Default test
//    Tests run: 1, Failures: 0, Skips: 0
//===============================================


