package APIAutomation.SampleRestAssured;

import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

import  com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import static APIAutomation.SampleRestAssured.StatusCode.OK;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class ParsingGetJSON {

	private final String baseURI = "https://maps.googleapis.com"; //note, no slash on end
	private final String myGooglePlacesKey = "AIzaSyD-Ij7UTEjtRnSeUDhfP26ViC6eDfJ2vIQ";
	private final StatusCode statusCode = OK;

	@Test
	public void verifyResponse(){

		RestAssured.baseURI = "https://maps.googleapis.com";

		Response res = given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "5").
				param("type", "cruise").
				param("key", myGooglePlacesKey).
				//log().all(). //this writes all the Get headers to console

				when().
				get("/maps/api/place/nearbysearch/json").

				then().assertThat().statusCode(200).and().
				contentType(ContentType.JSON).
				log().all(). //this writes all the r headers to console

				extract().response();

		String response = res.asString();

		JsonPath jsonRes = new JsonPath(response);

		int arrSize = jsonRes.getInt("results.size()"); 
		//ht info:
		//results.size() = 2
		//instructor calls name a node
		//$.results[0].name = Sydney - dont need the $. notation like we need in the jsonpath editor
		//$.results[1].name = Pyrmont

		System.out.println("***** arrSize = " + arrSize);
		//this allows you to parse and extract the JSON data "nodes"
		for (int i = 0; i < arrSize; i++) {
			String name = jsonRes.getString("results["+i+"].name");
			System.out.println(name);
		}	 
	}
}

//output:
//[RemoteTestNG] detected TestNG version 6.14.2
//WARNING: An illegal reflective access operation has occurred
//WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass$3$1 (file:/C:/Users/nightbeats2/.m2/repository/org/codehaus/groovy/groovy/2.4.4/groovy-2.4.4.jar) to method java.lang.Object.finalize()
//WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass$3$1
//WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
//WARNING: All illegal access operations will be denied in a future release
//Request method:	GET
//Request path:	https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522%2C151.1957362&radius=5&type=cruise&key=AIzaSyD-Ij7UTEjtRnSeUDhfP26ViC6eDfJ2vIQ
//Proxy:			<none>
//Request params:	location=-33.8670522,151.1957362
//				radius=5
//				type=cruise
//				key=AIzaSyD-Ij7UTEjtRnSeUDhfP26ViC6eDfJ2vIQ
//Query params:	<none>
//Form params:	<none>
//Path params:	<none>
//Multiparts:		<none>
//Headers:		Accept=*/*
//Cookies:		<none>
//Body:			<none>
//HTTP/1.1 200 OK
//Content-Type: application/json; charset=UTF-8
//Date: Fri, 23 Mar 2018 09:24:05 GMT
//Expires: Fri, 23 Mar 2018 09:29:05 GMT
//Cache-Control: public, max-age=300
//Vary: Accept-Language
//Content-Encoding: gzip
//Server: scaffolding on HTTPServer2
//Content-Length: 1314
//X-XSS-Protection: 1; mode=block
//X-Frame-Options: SAMEORIGIN
//Alt-Svc: hq=":443"; ma=2592000; quic=51303432; quic=51303431; quic=51303339; quic=51303335,quic=":443"; ma=2592000; v="42,41,39,35"

//{
//   "html_attributions" : [],
//   "results" : [
//      {
//         "geometry" : {
//            "location" : {
//               "lat" : -33.8688197,
//               "lng" : 151.2092955
//            },
//            "viewport" : {
//               "northeast" : {
//                  "lat" : -33.5781409,
//                  "lng" : 151.3430209
//               },
//               "southwest" : {
//                  "lat" : -34.118347,
//                  "lng" : 150.5209286
//               }
//            }
//         },
//         "icon" : "https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png",
//         "id" : "044785c67d3ee62545861361f8173af6c02f4fae",
//         "name" : "Sydney",
//         "photos" : [
//            {
//               "height" : 1200,
//               "html_attributions" : [
//                  "\u003ca href=\"https://maps.google.com/maps/contrib/107074428360569736660/photos\"\u003eDANJIN THOMAS\u003c/a\u003e"
//               ],
//               "photo_reference" : "CmRaAAAAJv8OWCfnOyUBTZz68-QPzQinrJYJ2XBbmB50sPa1L87N3rdmEd8Ov9UPGXB3AAvFZ8l94c4xhQWSkdIdUquWkGIFlxk2jiI1DnzEJauifXzp2_fn3YINRLxiZ6rWNNNgEhB1w5kREQ8g3ck7OwV5g4LfGhQw1qCgrJ5VqVBZwj7TP5v9wdoijA",
//               "width" : 1920
//            }
//         ],
//         "place_id" : "ChIJP3Sa8ziYEmsRUKgyFmh9AQM",
//         "reference" : "CmRbAAAAeBXcsLE3PweVF4zINOx6-dVEc0sIGR7zPHXdRPBuXuAWxlnUnnPRZfxJODijkcv1ydLRhjLHRmmY213nQ-u_2tbR-EZyxcyPEewpvoFdjwOE7T19BnfkioFYFIP4FQHbEhATMMft-ZufLq81PGNrH0VBGhSh1KBZ3nQBRstB6-7N8mZSGhNwmg",
//         "scope" : "GOOGLE",
//         "types" : [ "colloquial_area", "locality", "political" ],
//         "vicinity" : "Sydney"
//      },
//      {
//         "geometry" : {
//            "location" : {
//               "lat" : -33.8737167,
//               "lng" : 151.1956918
//            },
//            "viewport" : {
//               "northeast" : {
//                  "lat" : -33.8625142,
//                  "lng" : 151.198773
//               },
//               "southwest" : {
//                  "lat" : -33.8760938,
//                  "lng" : 151.186625
//               }
//            }
//         },
//         "icon" : "https://maps.gstatic.com/mapfiles/place_api/icons/geocode-71.png",
//         "id" : "40840d476baa531e0227a353b1bef70262f66e7e",
//         "name" : "Pyrmont",
//         "photos" : [
//            {
//               "height" : 3024,
//               "html_attributions" : [
//                  "\u003ca href=\"https://maps.google.com/maps/contrib/104902586026265303304/photos\"\u003eJui Hong Teoh\u003c/a\u003e"
//               ],
//               "photo_reference" : "CmRaAAAAr1q12IG1nb6mYgksEUUwQxz6lseXo7kpvDFDudEyv7-SpZd1YzGVqnW_vxJOf-HTJEdODieWcRIr8jnUQYATjwqQfZb_1OnR47L5wLQajQ1DO8RrizzOHMpTk7GAAherEhBEUKdQcWPxBbKOvj8vuBmQGhRb7SPZLJp-Gb24mW0ZRJrfQXq9bA",
//               "width" : 4032
//            }
//         ],
//         "place_id" : "ChIJAWLZAzSuEmsRkMcyFmh9AQU",
//         "reference" : "CmRbAAAADFFMdtumvf6VyPxr_qVZh9jIKsQde2BbIwmtQ1EQe_OUJg35ZJ8-vWWFf4ebRcR5DJwISg29h0sOWB3wMGtqiqJ84VnSSyVvhWAkCEI6xGWDD_tAypnnGxgTz-wpVHR_EhBevs8_u0_bCiafdUnOxzoRGhTv7FDvFccgJ10vpRkiGi0_8Nm8jw",
//         "scope" : "GOOGLE",
//         "types" : [ "locality", "political" ],
//         "vicinity" : "Pyrmont"
//      }
//   ],
//   "status" : "OK"
//}
//
//***** arrSize = 2
//Sydney
//Pyrmont
//PASSED: verifyResponse
//
//===============================================
//    Default test
//    Tests run: 1, Failures: 0, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 1, Failures: 0, Skips: 0
//===============================================

