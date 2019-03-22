package APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

/*
 * 
 */
public class GetAUserTimeline_DailyVersion {
	private int ctr = 0;
	private final int tweets = 33;
	private final Count count = new Count(tweets);

	private final String screenName = "realDonaldTrump"; 
	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	private final Logger log = LogManager.getLogger(GetAUserTimeline_DailyVersion.class.getName());

	public Response doGetRequest(String endpoint, int statusCode) {

		ValidatableResponse vr = 
				given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(count.getKeyname(), count.getValue())
				.param("screen_name", screenName)
				.contentType(ContentType.JSON)
				//.log().all()
				.when()
				.get(endpoint)
				.then()
				.contentType(ContentType.JSON)
				.statusCode(statusCode);

		//log.debug(vr.log().all());
		return vr.extract().response();
	}

	/*
	 * 
	 */
	private void printOutFormattedJsonReponse(
			Map<String, Object> map, String matchingdate) {

		String sdate = "";
		String stemp[] = {};
		Boolean printTweet = false;

		for(Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getKey().equals("created_at")) {
				sdate = (String) entry.getValue();
				stemp = sdate.split(" ");
				sdate = stemp[1] + " " + stemp[2];
				printTweet = matchingdate.equals(sdate);
			} 
			else if(entry.getKey().equals("text") ) {
				if(printTweet) {
					printTweet = false;
					System.out.println("Tweet #" + ++ctr + ": " + entry.getValue() + 
							"\r\nTweet Date = " + sdate +
							"\r\nDesired Date to Match: " + matchingdate);
				} 
				else {
					System.out.println("Tweet #" + ++ctr + 
							"\r\nTweet Date = " + sdate +
							"\r\nDesired Date to Match: " + matchingdate);
				}
			}
		}
	}

	/*
	 * 
	 */
	@Test
	public void verifyTweetForUserTimeline() {	
		Response response = doGetRequest(baseURI + endpoint, 200);
		System.out.println("verifyTweetForUserTimeline Response = " + response.toString());

		List<Map<String, Object>> root = response.jsonPath().getList("$");
		int rootSize = root.size();

		System.out.println("\r\n***********************************************************************");
		System.out.println("\r\nGREETINGS TOM!!!\r\nPrinting out the first " + count.getValue() +" tweets for: " + screenName);

		Date date2 = null;
		String sdate = null;

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));		
		Date date = Calendar.getInstance().getTime();

		SimpleDateFormat format = new SimpleDateFormat("MM-dd"); 
		String dateString = format.format(date);

		try {
			date2 = format.parse (dateString); //Dec 18, 2018 eg
			sdate = date2.toString();//Fri Feb 27 00:00:00 PST 1970

			//put sdate into form: "MM-dd" -> Feb-27
			//from Fri Feb 27 00:00:00 PST 1970
			String stemp[] = null;
			stemp = sdate.split(" ");
			sdate = stemp[1] + " " + stemp[2];

			//print out date and text strings
			for(int i=0; i<rootSize; i++) {
				System.out.println();
				Map<String, Object> map = root.get(i);
				printOutFormattedJsonReponse(map, sdate);
			}
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}

	}
}
