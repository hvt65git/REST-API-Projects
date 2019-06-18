package ht_twit_6;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static utils.OAUTH.accessToken;
import static utils.OAUTH.accessTokenSecret;
import static utils.OAUTH.consumerKey;
import static utils.OAUTH.consumerSecret;

public class GetTweets {
	/*
	 * getTweets
	 */
	private static Response getTweets(String URL, int count, String screenName, int sCode) {
		ValidatableResponse vr = RestAssured
				.given()
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret)
				.param("count", count)
				.param("screen_name", screenName)
				.contentType(ContentType.JSON)
				//.log().all()
				.when()
				.get(URL)
				.then()
				.assertThat().statusCode(sCode);
		return vr.extract().response();
	}
	
	/*
	 * printTweets
	 */
	private static void printTweetsAndEntities(int count, String screenName, List<HashMap<String, Object>> list) {
		System.out.println("\r\nPrinting out the " + count + 
				" most recent tweets from twitter user: " + screenName);
		
		for(int i=0; i<list.size(); i++) {
			System.out.println("\r\nTweet #" + (i+1) + "...");
			
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "created_at":
					System.out.println("Date Created:" + entry.getValue());
					break;
				case "text":
					System.out.println("Tweet text = " + entry.getValue());
					break;
				case "entities" :
					HashMap<String, Object> userMap = (HashMap<String, Object>)entry.getValue();
					for(Map.Entry<String, Object> userData : userMap.entrySet()) {
						System.out.println(userData.getKey() + " = " + userData.getValue());
					}
					break;
				}
			}
		}
	}
	
	
	/*
	 * printTweets
	 */
	private static void printUserMetadata(String screenName, List<HashMap<String, Object>> list) {
		System.out.println("\r\nPrinting out the twitter user profile data for: " + screenName);
		
		for(int i=0; i<1; i++) {
			for(Map.Entry<String, Object> entry : list.get(i).entrySet()) {
				switch(entry.getKey()) {
				case "user":
					HashMap<String, Object> userMap = (HashMap<String, Object>)entry.getValue();
					for(Map.Entry<String, Object> userData : userMap.entrySet()) {
						System.out.println(userData.getKey() + " = " + userData.getValue());
					}
					break;
				}
			}
		}
	}
	
	/*
	 * main
	 */
	public static void main(String[] args) {
		final int sCodeOK = 200;
		final int count = 33;
		final String screenName = "realDonaldTrump";
		final String resourceURL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
		
		//issue the twitter api get request to get the tweets
		Response response = getTweets(resourceURL, count, screenName, sCodeOK);
		
		//print out the twitter user data before printing out the user's tweets
		printUserMetadata(screenName, response.jsonPath().getList("$"));

		//print out the user's tweets
		printTweetsAndEntities(count, screenName, response.jsonPath().getList("$"));
	}
}

//OUTPUT:
//Printing out the twitter user profile data for: realDonaldTrump
//utc_offset = null
//friends_count = 47
//profile_image_url_https = https://pbs.twimg.com/profile_images/874276197357596672/kUuht00m_normal.jpg
//listed_count = 104199
//profile_background_image_url = http://abs.twimg.com/images/themes/theme1/bg.png
//default_profile_image = false
//favourites_count = 7
//description = 45th President of the United States of America🇺🇸
//created_at = Wed Mar 18 13:46:38 +0000 2009
//is_translator = false
//profile_background_image_url_https = https://abs.twimg.com/images/themes/theme1/bg.png
//protected = false
//screen_name = realDonaldTrump
//id_str = 25073877
//profile_link_color = 1B95E0
//is_translation_enabled = true
//translator_type = regular
//id = 25073877
//geo_enabled = true
//profile_background_color = 6D5C18
//lang = null
//has_extended_profile = false
//profile_sidebar_border_color = BDDCAD
//profile_text_color = 333333
//verified = true
//profile_image_url = http://pbs.twimg.com/profile_images/874276197357596672/kUuht00m_normal.jpg
//time_zone = null
//url = https://t.co/OMxB0x7xC5
//contributors_enabled = false
//profile_background_tile = true
//profile_banner_url = https://pbs.twimg.com/profile_banners/25073877/1559860593
//entities = {description={urls=[]}, url={urls=[{display_url=Instagram.com/realDonaldTrump, indices=[0, 23], expanded_url=http://www.Instagram.com/realDonaldTrump, url=https://t.co/OMxB0x7xC5}]}}
//statuses_count = 42435
//follow_request_sent = false
//followers_count = 61059037
//profile_use_background_image = true
//default_profile = false
//following = false
//name = Donald J. Trump
//location = Washington, DC
//profile_sidebar_fill_color = C5CEC0
//notifications = false
//
//Printing out the 5 most recent tweets from twitter user: realDonaldTrump
//
//Tweet #1 = Thank you Mr. Prime Minister, a great honor! https://t.co/ozLz84g3i0
//
//Tweet #2 = RT @i24NEWS_EN: Earlier today, @netanyahu and @USAmbIsrael held a ceremony for the inauguration of 'Trump Heights' — a town in #Israel's Go…
//
//Tweet #3 = RT @LindseyGrahamSC: Very glad to see Mexico working with President @realDonaldTrump to up their game and deal with immigration from Centra…
//
//Tweet #4 = Congratulations to Gary Woodland in winning the United States Open Golf Championship. Fantastic playing, great hear… https://t.co/aOIWxyO1fB
//
//Tweet #5 = Rep. Alexandria Ocasio-Cortez. “I think we have a very real risk of losing the Presidency to Donald Trump.” I agree… https://t.co/An2bEJbkdf
//
