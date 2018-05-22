package MAVENModule.path.json.JsonPathWith;

import java.util.List;

import org.testng.annotations.Test;

/*
# There are three Engagement API endpoints: /totals, /28hr, and /historical. 

# Here we will be hitting the /totals endpoint, which returns total engagement metrics for
 Tweets posted within 90 days. 

# The /totals endpoint provides 5 engagement types, while the other two endpoint provide 16. 
# API endpoint: https://data-api.twitter.com/insights/engagement/totals

# Request total Engagements for a list of Tweets (up to 250 per request) posted within 90-days.

twurl -H data-api.twitter.com -X POST "/insights/engagement/totals" -A "Content-Type: application/json"
 -d '{
  "tweet_ids": [
    "907022936564838401",
    "902731270274166784",
    "902301386880286721"
  ],...

/********************************************************************************************/
class Tweet_IDs {
	List<String> tweet_ids;

	public Tweet_IDs(List<String> tweet_ids) {
		this.tweet_ids = tweet_ids;
	}
}
/********************************************************************************************/
//"engagement_types": [
//"impressions",
//"engagements",
//"favorites"
//],

class Engagement_Types {

}



/********************************************************************************************/
//"groupings": {
//"Tweet metrics": {
//"group_by": [
//  "tweet.id",
//  "engagement.type"
//]
//{
class Groupings {

}




public class JSON_With_List_Twit_Engagements {

	@Test
	public void executeTest() {

		String json = "  \"tweet_ids\": [\r\n" + 
				"    \"998526317011357696\",\r\n" + 
				"    \"998497410199437312\",\r\n" + 
				"    \"998359240153579521\"\r\n" + 
				"  ],\r\n" + 
				"  \"engagement_types\": [\r\n" + 
				"    \"impressions\",\r\n" + 
				"    \"engagements\",\r\n" + 
				"    \"favorites\"\r\n" + 
				"  ],\r\n" + 
				"  \"groupings\": {\r\n" + 
				"    \"Tweet metrics\": {\r\n" + 
				"      \"group_by\": [\r\n" + 
				"        \"tweet.id\",\r\n" + 
				"        \"engagement.type\"\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

	}

}












