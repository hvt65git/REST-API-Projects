package _GET_;

import static _core.OAUTH.*;

import org.testng.annotations.Test;

import _core.Count;
import io.restassured.RestAssured;
import io.restassured.response.Response;


/*
 * 
 * Assignment Get and display the 20 most recent tweets that I added to my favorites list
 * 
 * Twitter API Documentation:
 * 
https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/get-favorites-list

GET favorites/list
Note: favorites are now known as likes.

Returns the 20 most recent Tweets liked by the authenticating or specified user.

Resource URL
https://api.twitter.com/1.1/favorites/list.json

Resource Information
Response formats	JSON
Requires authentication?	Yes
Rate limited?	Yes
Requests / 15-min window (user auth)	75
Requests / 15-min window (app auth)	75
Parameters
Name	Required	Description	Default Value	Example
user_id	optional	The ID of the user for whom to return results.	 	12345
screen_name	optional	The screen name of the user for whom to return results.	 	noradio
count	optional	Specifies the number of records to retrieve. Must be less than or equal to 200; defaults to 20. The value of count is best thought of as a limit to the number of Tweets to return because suspended or deleted content is removed after the count has been applied.	 	5
since_id	optional	Returns results with an ID greater than (that is, more recent than) the specified ID. There are limits to the number of Tweets which can be accessed through the API. If the limit of Tweets has occured since the since_id, the since_id will be forced to the oldest ID available.	 	12345
max_id	optional	Returns results with an ID less than (that is, older than) or equal to the specified ID.	 	54321
include_entities	optional	The entities node will be omitted when set to false .	 	false
Example Request
GET https://api.twitter.com/1.1/favorites/list.json?count=2&screen_name=episod
 */



public class GetFavorites {
	private final Count count = new Count(1);
	private final String baseURI = "https://api.twitter.com";
	private final String resources = "/1.1/favorites/list.json";

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
//
//[RemoteTestNG] detected TestNG version 6.14.2
//Request method:	GET
//Request URI:	https://api.twitter.com/1.1/favorites/list.json?count=1
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
//content-length: 1028
//content-type: application/json;charset=utf-8
//date: Sun, 06 May 2018 06:28:28 GMT
//expires: Tue, 31 Mar 1981 05:00:00 GMT
//last-modified: Sun, 06 May 2018 06:28:28 GMT
//pragma: no-cache
//server: tsa_a
//set-cookie: personalization_id="v1_PKzND6hKvSmQcQocED1V2w=="; Expires=Tue, 05 May 2020 06:28:28 GMT; Path=/; Domain=.twitter.com
//set-cookie: lang=en; Path=/
//set-cookie: guest_id=v1%3A152558810807810522; Expires=Tue, 05 May 2020 06:28:28 GMT; Path=/; Domain=.twitter.com
//status: 200 OK
//strict-transport-security: max-age=631138519
//x-access-level: read-write
//x-connection-hash: b6d62812895852e6ae38a3a8c33f2857
//x-content-type-options: nosniff
//x-frame-options: SAMEORIGIN
//x-rate-limit-limit: 75
//x-rate-limit-remaining: 71
//x-rate-limit-reset: 1525588134
//x-response-time: 94
//x-transaction: 0007814300d6a7ca
//x-twitter-response-tags: BouncerCompliant
//x-xss-protection: 1; mode=block; report=https://twitter.com/i/xss_report
//
//[
//    {
//        "created_at": "Thu May 03 11:30:00 +0000 2018",
//        "id": 992003338043469824,
//        "id_str": "992003338043469824",
//        "text": "In the Cross, Jesus showed us the greatness of His love and the power of His mercy.",
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
//        "source": "<a href=\"https://about.twitter.com/products/tweetdeck\" rel=\"nofollow\">TweetDeck</a>",
//        "in_reply_to_status_id": null,
//        "in_reply_to_status_id_str": null,
//        "in_reply_to_user_id": null,
//        "in_reply_to_user_id_str": null,
//        "in_reply_to_screen_name": null,
//        "user": {
//            "id": 500704345,
//            "id_str": "500704345",
//            "name": "Pope Francis",
//            "screen_name": "Pontifex",
//            "location": "Vatican City",
//            "description": "Welcome to the official Twitter page of His Holiness Pope Francis",
//            "url": "https://t.co/yRhGwps83P",
//            "entities": {
//                "url": {
//                    "urls": [
//                        {
//                            "url": "https://t.co/yRhGwps83P",
//                            "expanded_url": "http://www.vaticannews.va",
//                            "display_url": "vaticannews.va",
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
//            "followers_count": 17697812,
//            "friends_count": 8,
//            "listed_count": 26627,
//            "created_at": "Thu Feb 23 11:38:07 +0000 2012",
//            "favourites_count": 0,
//            "utc_offset": 7200,
//            "time_zone": "Rome",
//            "geo_enabled": false,
//            "verified": true,
//            "statuses_count": 1559,
//            "lang": "it",
//            "contributors_enabled": false,
//            "is_translator": false,
//            "is_translation_enabled": true,
//            "profile_background_color": "FFFFFF",
//            "profile_background_image_url": "http://pbs.twimg.com/profile_background_images/857999332/52f4b5a0278e5a44ed595ef3a031a709.jpeg",
//            "profile_background_image_url_https": "https://pbs.twimg.com/profile_background_images/857999332/52f4b5a0278e5a44ed595ef3a031a709.jpeg",
//            "profile_background_tile": false,
//            "profile_image_url": "http://pbs.twimg.com/profile_images/507818066814590976/KNG-IkT9_normal.jpeg",
//            "profile_image_url_https": "https://pbs.twimg.com/profile_images/507818066814590976/KNG-IkT9_normal.jpeg",
//            "profile_banner_url": "https://pbs.twimg.com/profile_banners/500704345/1409908226",
//            "profile_link_color": "0084B4",
//            "profile_sidebar_border_color": "FFFFFF",
//            "profile_sidebar_fill_color": "DDEEF6",
//            "profile_text_color": "333333",
//            "profile_use_background_image": true,
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
//        "retweet_count": 9429,
//        "favorite_count": 44923,
//        "favorited": true,
//        "retweeted": false,
//        "lang": "en"
//    }
//]
//*** Twitter Response Headers *** => cache-control=no-cache, no-store, must-revalidate, pre-check=0, post-check=0
//content-disposition=attachment; filename=json.json
//content-encoding=gzip
//content-length=1028
//content-type=application/json;charset=utf-8
//date=Sun, 06 May 2018 06:28:28 GMT
//expires=Tue, 31 Mar 1981 05:00:00 GMT
//last-modified=Sun, 06 May 2018 06:28:28 GMT
//pragma=no-cache
//server=tsa_a
//set-cookie=personalization_id="v1_PKzND6hKvSmQcQocED1V2w=="; Expires=Tue, 05 May 2020 06:28:28 GMT; Path=/; Domain=.twitter.com
//set-cookie=lang=en; Path=/
//set-cookie=guest_id=v1%3A152558810807810522; Expires=Tue, 05 May 2020 06:28:28 GMT; Path=/; Domain=.twitter.com
//status=200 OK
//strict-transport-security=max-age=631138519
//x-access-level=read-write
//x-connection-hash=b6d62812895852e6ae38a3a8c33f2857
//x-content-type-options=nosniff
//x-frame-options=SAMEORIGIN
//x-rate-limit-limit=75
//x-rate-limit-remaining=71
//x-rate-limit-reset=1525588134
//x-response-time=94
//x-transaction=0007814300d6a7ca
//x-twitter-response-tags=BouncerCompliant
//x-xss-protection=1; mode=block; report=https://twitter.com/i/xss_report
//PASSED: getFavoritesList
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



