package ht_twit_restapi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static ht_twit_restapi.OAUTH.*;
import static io.restassured.RestAssured.given;

/*
 * 
 */
public class GetAUserTimeline_DailyVersion {
	private static int ctr = 0;
	private static final int tweets = 1;
	private static final Count count = new Count(tweets);

	private static final String screenName = "realDonaldTrump"; 
	//private final String screenName = "Tatti_D"; 
	//private final String screenName = "Ishmail2015"; 
	///private final String screenName = "JustMaelle"; 

	private static final String baseURI = "https://api.twitter.com";
	private static final String endpoint = "/1.1/statuses/user_timeline.json";	

	private static final  String testdatafilename = System.getProperty("user.dir") +
			"\\src\\main\\java\\htrestmff1\\tweetdates.txt";

	private static final Logger log = 
			LogManager.getLogger(GetAUserTimeline_DailyVersion.class.getName());

	private static Response GETRequest(String endpoint, int statusCode) {

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

		/*
		 * 		Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");

		List<String> jsonResponse = response.jsonPath().getList("$");
		 */
		return vr.extract().response();
	}

	/*
	 * 
	 */
	private static void printOutJsonReponseTweets(
			Map<String, Object> map, String matchingdate, TestdataIO td, boolean printAllTweets) {

		String sdate = "";
		String stemp[] = {};
		Boolean matchingTweet = false;

		//		//practice
		//(1) 1st way to iterate the keys and their values
		//print out all the keys of the hash map
		//		Set<String> keys = map.keySet(); //no dupes no order
		//		keys.forEach(x->System.out.println("key => " + x + ", value => " + map.get(x)));

		//		From JavaDocs:
		//		entrySet
		//		Set<Map.Entry<K,V>> entrySet()
		//		Returns a Set view of the mappings contained in this map. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation, or through the setValue operation on a map entry returned by the iterator) the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.
		//		Returns:
		//		a set view of the mappings contained in this map
		//
		//		//(2) 2nd way to iterate the keys and their values using lamdas
		//		Set<Entry<String, Object>> entries =  map.entrySet();
		//		entries.forEach(x->System.out.println("key => " + x.getKey() +
		//				", value => " + x.getValue()));
		//		
		//		//(3) 3rd way is to use enhanced for loop on Map.Entry objects
		//		for(Entry<String, Object> entry :  map.entrySet()) {
		//			System.out.println("key=> " + entry.getKey() + ", value=> " + entry.getValue());
		//		}

		//use Map.Entry objects
		for(Entry<String, Object> entry : map.entrySet()) {

			if(entry.getKey().equals("created_at")) {
				sdate = (String) entry.getValue();
				stemp = sdate.split(" ");
				sdate = stemp[1] + " " + stemp[2];

				//test for matching date
				matchingTweet = matchingdate.equals(sdate);
			} 
			else if(entry.getKey().equals("text") ) {
				//write date to test file
				td.write(sdate + "\r\n");

				if(!printAllTweets) {
					//print the tweet only if it matches desired date
					if(matchingTweet) {
						System.out.println("\r\nTweet #" + ++ctr + ": " + entry.getValue() + 
								"\r\nTweet Date = " + sdate +
								"\r\nDesired Date to Match: " + matchingdate);				
					} 
				}
				else {
					//print the tweet
					System.out.println("\r\nTweet #" + ++ctr + ": " + entry.getValue() + 
							"\r\nTweet Date = " + sdate );
				}
			}
			else if(entry.getKey().equals("favorited") || entry.getKey().equals("possibly_sensitive")) {
				System.out.println(entry.getKey() + " = " + entry.getValue() );	
			}
		}
	}

	/*
	 * 
	 */
	private static String getDisplayDate() throws ParseException {
		String sdate = null;
		Date date2 = null;

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));		
		Date date = Calendar.getInstance().getTime();

		SimpleDateFormat format = new SimpleDateFormat("MM-dd"); 
		String dateString = format.format(date);

		date2 = format.parse (dateString); //Dec 18, 2018 eg
		sdate = date2.toString();//Fri Feb 27 00:00:00 PST 1970

		//put sdate into form: "MM-dd" -> Feb-27
		//from Fri Feb 27 00:00:00 PST 1970
		String stemp[] = null;
		stemp = sdate.split(" ");
		sdate = stemp[1] + " " + stemp[2];
		return sdate;
	}

	/*
	 * 
	 */
	public static void main(String[] arg) {	
		//init vars
		boolean printAllTweets = true;

		//create TestdataIO object for writing tweet dates to a file
		TestdataIO td = new TestdataIO();
		td.createFile(testdatafilename);

		try {
			//issue the REST get request
			Response response = GETRequest(baseURI + endpoint, 200);

			//get the response as json data into a list of hashmaps
			List<Map<String, Object>> root = response.jsonPath().getList("$");

			//print output header to std out
			if(printAllTweets) {
				System.out.println("\r\n*** PRINTING OUT ALL TWEETS ***");
			}
			else {
				System.out.println("\r\n*** PRINTING OUT ONLY TODAY'S TWEETS (EST)***");
			}

			//print out date and text strings to text file and to std out
			for(int i=0; i<root.size(); i++) {
				Map<String, Object> map = root.get(i);
				printOutJsonReponseTweets(map, getDisplayDate(), td, printAllTweets);
			}


		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			//close the tweetdates.txt file
			td.close();

			//tally the tweet results after closing the tweetdates.txt file
			TweetTally.tallyTweetResults();
		}
	}
}
