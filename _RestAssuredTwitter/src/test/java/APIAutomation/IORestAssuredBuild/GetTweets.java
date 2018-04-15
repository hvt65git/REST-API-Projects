package APIAutomation.IORestAssuredBuild;

import static APIAutomation.IORestAssuredBuild.OAUTH.*;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;




//woohoo! first successful integration of RestAssured and Twitter-
//the keys were to use the io.rest-assured maven libs
//and the scribejava-api and -core version 2.5.2.jars


class OAUTH {
	public static final String consumerKey = "Ez8HSiCaVZb0EW5JPctxVFz30";
	public static final String consumerSecret = "1jpzfBTO4cIIMFwYT6VsSFnetdBJkK4w032Qr813vwryZj5gwS";
	public static final String accessToken = "3556936994-DYhVytMQCt1quSyh20dxOKs3NNVHsbT6ebvCce7";
	public static final String accessTokenSecret = "pRVltgFncO2Eb0t5uUkPtywCQRwunT7MrfxJpV1dgHGkJ";
}


class Count {
	private final String keyname = "count";
	private int value;

	public Count (int value) {
		this.value = value;
	}
	public String getKeyname() {
		return this.keyname;
	}
	public int getValue() {
		return value;
	}
}

public class GetTweets {
	private final Count count = new Count(1);
	private final String baseURI = "https://api.twitter.com";
	private final String resources = "/1.1/statuses/user_timeline.json";

	@Test
	public void getTweets() {
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
