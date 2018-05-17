package  APIAutomation.IORestAssuredBuild.likes;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;
import static io.restassured.path.json.JsonPath.with;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

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
	private final Count count = new Count(111);
	private final String baseURI = "https://api.twitter.com";
	private final String resources = "/1.1/favorites/list.json";

	@Test
	public void getFavoritesList() 
	{
		RestAssured.baseURI = baseURI;
		Response response = RestAssured.given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(count.getKeyname(), count.getValue())
				//.log().all()
				.when()
				.get(resources)
				.then().assertThat().statusCode(200)
				//.log().all()
				.extract().response();

		//add data to lists
		List<String> allText = response.jsonPath().getList("text");
		List<String> allUser = response.jsonPath().getList("user.name");
		List<String> allScreenName = response.jsonPath().getList("user.screen_name");
		List<String> allLocation = response.jsonPath().getList("user.location");

		//print all 
		int size = allText.size();
		for(int i=0; i<size; i++) {
			System.out.println(
					"Favorite #" + (i+1) + " " +
					"screenName = " + allScreenName.get(i) + "\r\n" +
					"text = "		+ allText.get(i) +  "\r\n" );
		}
	}
}
