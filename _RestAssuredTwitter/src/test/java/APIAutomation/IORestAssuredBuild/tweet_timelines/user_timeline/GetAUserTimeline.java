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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

/*
 * 
 */
public class GetAUserTimeline {
	private int ctr = 0;
	//private final Logger log = LogManager.getLogger(GetAUserTimeline.class.getName());

	private final int tweets = 100;
	private final Count count = new Count(tweets);

	private final String screenName = "realDonaldTrump"; 

	private final String baseURI = "https://api.twitter.com";
	private final String endpoint = "/1.1/statuses/user_timeline.json";

	public Response doGetRequest(String endpoint, int statusCode) {
		//log.info("Inside doGetRequest call");

		ValidatableResponse vr = 
				RestAssured.given().auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(count.getKeyname(), count.getValue())
				.param("screen_name", screenName)
				.contentType(ContentType.JSON)
				//.log().all()
				.get(endpoint)
				.then().contentType(ContentType.JSON)
				.statusCode(statusCode);

		//log.debug(vr.log().all());
		return vr.extract().response();
	}

	/*
	 * 
	 */
	private void printOutFormattedJsonReponse(Map<String, Object> map, 
			String matchingdate) {//Fri Feb 27 00:00:00 PST 1970
		//log.info("Inside printOutDateTextStrings call");
		/*
		 * successfully formatted and printed this:
		 *  
			"created_at": "Sat Jun 09 23:03:16 +0000 2018",
	        "id": 1005586152076689408,
	        "id_str": "1005586152076689408",
	        "text": "Based on Justin’s false statements at his news conference, and the fact that Canada is charging massive Tariffs to… https://t.co/8GTCnRTTEG",

		 * next, format and print this data too:
		 * 
		 *  "user": {
            "id": 25073877,
            "id_str": "25073877",
            "name": "Donald J. Trump",
            "screen_name": "realDonaldTrump",
            "location": "Washington, DC",
            "description": "45th President of the United States of America??",
            "url": "https://t.co/OMxB0xp8tD",
            "entities": {
                "url": {
                    "urls": [
                        {
                            "url": "https://t.co/OMxB0xp8tD",
                            "expanded_url": "http://www.Instagram.com/realDonaldTrump",
                            "display_url": "Instagram.com/realDonaldTrump",
                            "indices": [
                                0,
                                23
                            ]
                        }
                    ]
                },
                "description": {
                    "urls": [

                    ]
                }
            },
            "protected": false,
            "followers_count": 52571077,
		 */
		String sdate = "*** blank date ***";
		String stemp[] = {};
		Boolean printTweet = false;

		for(Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getKey().equals("created_at")) {
				sdate = (String) entry.getValue();
				stemp = sdate.split(" ");
				sdate = stemp[1] + " " + stemp[2];
				//format
				//Date: Wed Feb 27 16:25:33 +0000 2019
				//("MM-dd"); = 
				printTweet = matchingdate.equals(sdate);

			} else if(entry.getKey().equals("text") && printTweet) {
				printTweet = false;
				System.out.println("Tweet #" + ++ctr + ": " + entry.getValue() + 
						"\r\nTweet Date = " + sdate +
						"\r\nDesired Date to Match: " + matchingdate);
			}
		}
	}

	/*
	 * 
	 */
	@Test
	public void verifyTweetForUserTimeline() {	
		Response response = doGetRequest(baseURI + endpoint, 200);
		//System.out.println("verifyTweetForUserTimeline Response = " + response.toString());

		List<Map<String, Object>> root = response.jsonPath().getList("$");
		int rootSize = root.size();

		System.out.println("\r\n***********************************************************************");
		System.out.println("\r\nPrinting out the first " + count.getValue() +" tweets for: " + screenName);

		//System.out.println( new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) );
		//System.out.println( Calendar.getInstance().getTime() );
		//Date date = new Date(System.currentTimeMillis());


		Date date2 = null;
		String sdate = null;

		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd"); 
		String dateString = format.format(date);

		System.out.println( "verifyTweetForUserTimeline date = " + dateString );
		//verifyTweetForUserTimeline date = 02-27

		
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
				System.out.println("\r\n***********************************************************************");
			}
		}
		catch(ParseException pe) {
			System.out.println(pe.getMessage());
		}






	}
}
