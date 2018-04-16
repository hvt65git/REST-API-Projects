package JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//SUCCESS!

/*
 * 
 * Design Java classes that can give this JSON object
 * when processed by gson.toJson(obj)
 * test this json field to be the following value:
 * $.[0].entities.user_mentions[0].screen_name = [
  "SriNithyananda"
]
 * ===================================================================
[
    {
        "created_at": "Sun Apr 08 11:40:38 +0000 2018",
        "id": 982946313321906182,
        "id_str": "982946313321906182",
        "text": "@SriNithyananda ? namaste",
        "truncated": false,
        "entities": {
            "hashtags": [

            ],
            "symbols": [

            ],
            "user_mentions": [
                {
                    "screen_name": "SriNithyananda",
                    "name": "Nithyananda",
                    "id": 535076489,
                    "id_str": "535076489",
                    "indices": [
                        0,
                        15
                    ]
                }
            ],
            "urls": [

            ]
        }
    }
    ] 
 */

//class Tweet {
//	List<TweetInfo> tweetInfo = new ArrayList<>();
//
//	public Tweet(TweetInfo tweetInfo) {
//		this.tweetInfo.add(tweetInfo);
//		System.out.println("* Tweet constuctor... *");
//	}
//}

class TweetInfo {
	String 	created_at = "Sun Apr 08 11:40:38 +0000 2018";
	String 	id = "982946313321906182";
	String  id_str =  "982946313321906182";
	String	text =  "@SriNithyananda ? namaste";
	boolean truncated = false;
	Entities entities;

	public TweetInfo(
			String 	created_at,
			String 	id ,
			String  id_str,
			String	text,
			Entities entities) {
		System.out.println("** TweetInfo constuctor... **");

		this.created_at = created_at;
		this.id = id;
		this.id_str = id_str;
		this.text = text;
		this.entities = entities;
	}
}

class Entities {
	List<String> hashtags;
	List<String> symbols;
	List<User_mentions> user_mentions = new ArrayList<>();
	List<String> urls;

	public Entities(User_mentions user_mentions) {
		this.user_mentions.add(user_mentions);

		System.out.println("*** Entities constuctor... ***");
	}
}

class User_mentions {

	String screen_name;
	String name;
	long id;
	String id_str;
	List<Integer> indices;

	public User_mentions (	
			String screen_name,
			String name,
			String id_str,
			long id,
			List<Integer> indices) {
		System.out.println("**** User_mentions constuctor... ****");

		this.screen_name = screen_name;
		this.name = name;
		this.id_str = id_str;
		this.id = id;
		this.indices = indices;
	}
}


public class JsonTest  {

	public static void main(String[] args) {

		//create the User_mentions object
		User_mentions user_mentions =
				new User_mentions(
						"SriNithyananda",
						"Nithyananda", 
						"535076489",
						535076489,
						Arrays.asList(0,15) );

		//create the next level Entities object
		Entities entities = new Entities(user_mentions);

		//create the next level TweetInfo object
		TweetInfo tweetInfo = new TweetInfo(
				"Sun Apr 08 11:40:38 +0000 2018",
				"982946313321906182",
				"982946313321906182",
				"@SriNithyananda ? namaste",
				entities);

		//create the next level Tweet object 
		//Tweet tweetObj = new Tweet(tweetInfo);

		//use GsonBuilder to create a Gson object 
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		String stringJson = gs.toJson(tweetInfo);

		System.out.println("stringJson = " + stringJson);
	}
}
//goal:
//[
// {
//     "created_at": "Sun Apr 08 11:40:38 +0000 2018",
//     "id": 982946313321906182,
//     "id_str": "982946313321906182",
//     "text": "@SriNithyananda ? namaste",
//     "truncated": false,
//     "entities": {
//         "hashtags": [
//
//         ],
//         "symbols": [
//
//         ],
//         "user_mentions": [
//             {
//                 "screen_name": "SriNithyananda",
//                 "name": "Nithyananda",
//                 "id": 535076489,
//                 "id_str": "535076489",
//                 "indices": [
//                     0,
//                     15
//                 ]
//             }
//         ],
//         "urls": [
//
//         ]
//     }
// }
// ] 
//output:
//**** User_mentions constuctor... ****
// when Tweet class not is used we get this which looks good!
//http://jsonpath.com/
//$.
//[
// {
//   "created_at": "Sun Apr 08 11:40:38 +0000 2018",
//   "id": "982946313321906182",
//   "id_str": "982946313321906182",
//   "text": "@SriNithyananda ? namaste",
//   "truncated": false,
//   "entities": {
//     "hashtags": null,
//     "symbols": null,
//     "user_mentions": [
//       {
//         "screen_name": "SriNithyananda",
//         "name": "Nithyananda",
//         "id": 535076489,
//         "id_str": "535076489",
//         "indices": [
//           0,
//           15
//         ]
//       }
//     ],
//     "urls": null
//   }
// }
//]