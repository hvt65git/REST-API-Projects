package APIAutomation.IORestAssuredBuild.tweets.POST_statuses_update;

import org.testng.Assert;
import org.testng.annotations.Test;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

/* DONE! success!
 * 
 * POST statuses/update
 * https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update
 * 
Updates the authenticating user’s current status, also known as Tweeting.
For each update attempt, the update text is compared with the authenticating user’s recent Tweets.
Any attempt that would result in duplication will be blocked, resulting in a 403 error. 
A user cannot submit the same status twice in a row.
While not rate limited by the API, a user is limited in the number of Tweets they can create at a time. 
If the number of updates posted by the user reaches the current allowed limit this method will
 return an HTTP 403 error.

 * 
 */

public class CreateTweet {
	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/update.json";
	private final String theTweet = "hello hello automated tweet testing 123...";
	
	@Test(enabled = false, description = "expected 403 forbidden failure - sending no tweet param")
	public void verifyFailForSendingNoTweet() {
		int errorCode = 170;
		String errorMsg = "Missing required parameter: status.";
		
		RestAssured.baseURI = baseURI;
		try {
			Response response = RestAssured.given()
					.auth()
					.oauth(consumerKey, 
							consumerSecret, 
							accessToken, 
							accessTokenSecret)
					.when()
					.post(endpoint)
					.then()
					.log().all()
					.extract().response();

			//(A) verify status response header:
			//header: status: 403 Forbidden
			Assert.assertEquals(response.header("status") , "403 Forbidden" );

			//(B) verify response JSON fields
			//{ "errors": [ {"code": 170, "message": "Missing required parameter: status."} ]}
			JsonPath jsonRes = new JsonPath(response.asString());
			
			int actualCode = Integer.parseInt(jsonRes.getString("errors[0].code"));
			System.out.println("Actual error code = " + actualCode + " Expected error code = " + errorCode);
			Assert.assertEquals(actualCode, errorCode);
			
			String actualErrorMsg = jsonRes.getString("errors[0].message");
			System.out.println("Actual error msg = " + actualErrorMsg + " Expected error msg = " + errorMsg);
			Assert.assertEquals(actualErrorMsg, errorMsg);
		}
		catch(Exception e) {
			System.out.println("Test failed with error message: " + e.getMessage());
			Assert.fail();
		}
	}

	@Test(enabled = true, description = "expected success")
	public void verifyTweetForUserTimeline() {
		RestAssured.baseURI = baseURI;
		
		try {
			Response response = 
					RestAssured.given()
					.auth()
					.oauth(consumerKey, 
							consumerSecret, 
							accessToken, 
							accessTokenSecret)

					.param("status", theTweet) //just a normal string not a json string
					.log().all()

					.when()
					.post(endpoint)

					.then()
					.log().all()
					.statusCode(200)
					
					.extract().response();

			//print out all the response headers as a string
			System.out.println("Printing out the response headers now...");
			Headers allHeaders = response.headers();
			for(Header header : allHeaders) {
				System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
			}

			//verify these response headers:
			//content-type=application/json;charset=utf-8
			//status=200 OK	
			Assert.assertEquals(response.header("content-type") , "application/json;charset=utf-8");
			Assert.assertEquals(response.header("status") , "200 OK" );
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
}
