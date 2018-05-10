package  APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;


class Record {
	private String created_at;
	private String text;
	private String quoted_text;

	public Record(String created_at, String text, String quoted_text, String hashtag) {
		super();
		this.created_at = created_at;
		this.text = text;
		this.quoted_text = quoted_text;
	}
}

public class GetUserTimeline {
	private final Count count = new Count(111);
	private final String screenName = "MaNithyaSudevi";

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	public Response doGetRequest(String endpoint, int statusCode) {
		RestAssured.defaultParser = Parser.JSON;
		
		return
				given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				
				.param(count.getKeyname(), count.getValue())
				.param("screen_name", screenName)
				
				.when().get(endpoint)
				
				.then().contentType(ContentType.JSON)
				.statusCode(statusCode)
				.extract().response();
	}

	@Test
	public void verifyTweetForUserTimeline() {
		int count = 0;
		
		Response response = doGetRequest(baseURI + endpoint, 200);
		
		List<String> tweets = response.jsonPath().getList("text");
		for(String tweet : tweets) {
			System.out.println("tweet " + ++count +  " = " + tweet + "\r\n");
		}

	}
}
