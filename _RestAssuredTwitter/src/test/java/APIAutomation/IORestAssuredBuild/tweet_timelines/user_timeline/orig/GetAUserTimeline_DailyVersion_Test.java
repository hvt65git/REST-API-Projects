package APIAutomation.IORestAssuredBuild.tweet_timelines.user_timeline.orig;

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
import htrestmff1.GetAUserTimeline_DailyVersion;

import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

/*
 * 
 */
public class GetAUserTimeline_DailyVersion_Test {
	private int ctr = 0;
	private final int tweets = 200;
	private final Count count = new Count(tweets);

	private final String screenName = "realDonaldTrump"; 
	//private final String screenName = "Tatti_D"; 
	//private final String screenName = "Ishmail2015"; 
	///private final String screenName = "JustMaelle"; 

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";	
	private final static String testdatafilename = System.getProperty("user.dir") +
			"\\src\\test\\java\\APIAutomation\\IORestAssuredBuild\\tweet_timelines\\user_timeline"+
			"\\tweetdates.txt";

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
	private void printOutFormattedJsonReponseAllTweets(
			Map<String, Object> map, String matchingdate, TestdataIO_Test td) {

		String sdate = "";
		String stemp[] = {};

		//Boolean printTweet = true;
		//do not test for matching date, print all tweets

		for(Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getKey().equals("created_at")) {
				sdate = (String) entry.getValue();
				stemp = sdate.split(" ");
				sdate = stemp[1] + " " + stemp[2];
			} 
			else if(entry.getKey().equals("text") ) {
				//write date to test file
				td.write(sdate + "\r\n");

				System.out.println("Tweet #" + ++ctr + ": " + entry.getValue() + 
						"\r\nTweet Date = " + sdate +
						"\r\nDesired Date to Match: " + matchingdate);


			}
		}
	}


	/*
	 * 
	 */
	private void printOutFormattedJsonReponseDateMatchingTweets(
			Map<String, Object> map, String matchingdate, TestdataIO_Test td) {

		String sdate = "";
		String stemp[] = {};
		Boolean printTweet = false;

		for(Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getKey().equals("created_at")) {
				sdate = (String) entry.getValue();
				stemp = sdate.split(" ");
				sdate = stemp[1] + " " + stemp[2];

				//test for matching date
				printTweet = matchingdate.equals(sdate);
				printTweet = true; 			//hack
			} 
			else if(entry.getKey().equals("text") ) {
				//write date to test file
				td.write(sdate + "\r\n");

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

		Date date2 = null;
		String sdate = null;

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));		
		Date date = Calendar.getInstance().getTime();

		SimpleDateFormat format = new SimpleDateFormat("MM-dd"); 
		String dateString = format.format(date);


		//create testdataio object for writing tweet dates to a file
		//if file exists it will be truncated (contents removed) and appended
		TestdataIO_Test td = new TestdataIO_Test();
		td.createFile(testdatafilename);

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
				//printOutFormattedJsonReponseDateMatchingTweets(map, sdate, td);
				printOutFormattedJsonReponseAllTweets(map, sdate, td);
			}
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}
		finally {
			td.close();
		}

		//tally the tweet results
		TweetTally_Test.tallyTweetResults();
	}
}
