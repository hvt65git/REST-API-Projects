package MAVENModule.RestAssuredJSONPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;


public class RestJsonListMapTest {

	public static Response doGetRequest(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;
		return
				given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
				when().get(endpoint).
				then().contentType(ContentType.JSON).extract().response();
	}

	@Test
	public static void testUsers() {
		Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");

		//Note the $ notation which means the root element.
		List<String> jsonResponse = response.jsonPath().getList("$"); //a list of hashmap nodes

		//The result of this call would print 10. 
		System.out.println(jsonResponse.size());

		//Parsing JSON Arrays and Lists:

		//In the above example, if we wanted to get the username of all entries, we could use
		String usernames = response.jsonPath().getString("username");
		System.out.println(usernames);

		//This would print the string like:
		//[Bret, Antonette, Samantha, Karianne, Kamren, Leopoldo_Corkery, Elwyn.Skiles, Maxime_Nienow, Delphine, Moriah.Stanton]

		//If we then want to get the username of the first entry we could use
		usernames = response.jsonPath().getString("username[0]");
		System.out.println(usernames);

		//This would print the string like:
		//Bret

		//Using a List we can use
		List<String> jsonResponse2 = response.jsonPath().getList("username");
		System.out.println(jsonResponse2.get(0));
		for(String current : jsonResponse2) {
			System.out.println("username entry = " + current);
		}

		//This would print the string like:
		//Bret

		//Parsing JSON ArrayList and HashMap
		//Looking at the above JSON structure, the company is actually a map. If we only had one record, we could use
		Response response2 = doGetRequest("https://jsonplaceholder.typicode.com/users/1");

		//print out the map
		System.out.println("\r\nprinting out the company map..");
		//Map<String, String> company = response2.jsonPath().getMap("company");
		
		Map<String, String> company = response2.jsonPath().getMap("company");
		Set<String> keys = company.keySet();
		for(Object current : keys) {
			System.out.println("company.get(" + current + ")" + " = " + company.get(current));
		}
		System.out.println();
		//which would print Romaguera-Crona
		

		//But if the response returns an array and we want to extract the first company name, we could use:
		Response response3 = doGetRequest("https://jsonplaceholder.typicode.com/users/");
		
//		company: {
//		name: "Romaguera-Crona",
//		catchPhrase: "Multi-layered client-server neural-net",
//		bs: "harness real-time e-markets"
//		}
//	...
//		company: {
//		name: "Deckow-Crist",
//		catchPhrase: "Proactive didactic contingency",
//		bs: "synergize scalable supply-chains"
//		}

		//print out first set of three keys
		List<Map<String, String>> company2 = response3.jsonPath().getList("company"); //a list of hashmap nodes
		System.out.println("*** name = " 		+ company2.get(0).get("name"));
		System.out.println("**  catchPhrase = " + company2.get(0).get("catchPhrase"));
		System.out.println("*   bs  = " 		+ company2.get(0).get("bs"));
		
		//print out all the keys
		for(int i=0; i<company2.size(); i++) {
			System.out.println();
			Map<String, String> companyMap = company2.get(i);
			Set<String> companyKeys = companyMap.keySet();
			for(String key : companyKeys) {
				System.out.println("key = " + key + ", get(" + key + ") = " + companyMap.get(key) );
			}
		}
	
		
		//Alternatively, we could use:
		Response response4 = doGetRequest("https://jsonplaceholder.typicode.com/users/");

		List<Map<Object, String>> companies = response.jsonPath().getList("company");
		System.out.println(companies.get(0).get("name"));

		//both of which will print Romaguera-Crona.

	}

}

//output:
//10
//[Bret, Antonette, Samantha, Karianne, Kamren, Leopoldo_Corkery, Elwyn.Skiles, Maxime_Nienow, Delphine, Moriah.Stanton]
//Bret
//Bret
//Romaguera-Crona
//Romaguera-Crona
//Romaguera-Crona

//all the data:
//https://jsonplaceholder.typicode.com/users/
//[
// {
//   "id": 1,
//   "name": "Leanne Graham",
//   "username": "Bret",
//   "email": "Sincere@april.biz",
//   "address": {
//     "street": "Kulas Light",
//     "suite": "Apt. 556",
//     "city": "Gwenborough",
//     "zipcode": "92998-3874",
//     "geo": {
//       "lat": "-37.3159",
//       "lng": "81.1496"
//     }
//   },
//   "phone": "1-770-736-8031 x56442",
//   "website": "hildegard.org",
//   "company": {
//     "name": "Romaguera-Crona",
//     "catchPhrase": "Multi-layered client-server neural-net",
//     "bs": "harness real-time e-markets"
//   }
// },
// {
//   "id": 2,
//   "name": "Ervin Howell",
//   "username": "Antonette",
//   "email": "Shanna@melissa.tv",
//   "address": {
//     "street": "Victor Plains",
//     "suite": "Suite 879",
//     "city": "Wisokyburgh",
//     "zipcode": "90566-7771",
//     "geo": {
//       "lat": "-43.9509",
//       "lng": "-34.4618"
//     }
//   },
//   "phone": "010-692-6593 x09125",
//   "website": "anastasia.net",
//   "company": {
//     "name": "Deckow-Crist",
//     "catchPhrase": "Proactive didactic contingency",
//     "bs": "synergize scalable supply-chains"
//   }
// },
// {
//   "id": 3,
//   "name": "Clementine Bauch",
//   "username": "Samantha",
//   "email": "Nathan@yesenia.net",
//   "address": {
//     "street": "Douglas Extension",
//     "suite": "Suite 847",
//     "city": "McKenziehaven",
//     "zipcode": "59590-4157",
//     "geo": {
//       "lat": "-68.6102",
//       "lng": "-47.0653"
//     }
//   },
//   "phone": "1-463-123-4447",
//   "website": "ramiro.info",
//   "company": {
//     "name": "Romaguera-Jacobson",
//     "catchPhrase": "Face to face bifurcated interface",
//     "bs": "e-enable strategic applications"
//   }
// },
// {
//   "id": 4,
//   "name": "Patricia Lebsack",
//   "username": "Karianne",
//   "email": "Julianne.OConner@kory.org",
//   "address": {
//     "street": "Hoeger Mall",
//     "suite": "Apt. 692",
//     "city": "South Elvis",
//     "zipcode": "53919-4257",
//     "geo": {
//       "lat": "29.4572",
//       "lng": "-164.2990"
//     }
//   },
//   "phone": "493-170-9623 x156",
//   "website": "kale.biz",
//   "company": {
//     "name": "Robel-Corkery",
//     "catchPhrase": "Multi-tiered zero tolerance productivity",
//     "bs": "transition cutting-edge web services"
//   }
// },
// {
//   "id": 5,
//   "name": "Chelsey Dietrich",
//   "username": "Kamren",
//   "email": "Lucio_Hettinger@annie.ca",
//   "address": {
//     "street": "Skiles Walks",
//     "suite": "Suite 351",
//     "city": "Roscoeview",
//     "zipcode": "33263",
//     "geo": {
//       "lat": "-31.8129",
//       "lng": "62.5342"
//     }
//   },
//   "phone": "(254)954-1289",
//   "website": "demarco.info",
//   "company": {
//     "name": "Keebler LLC",
//     "catchPhrase": "User-centric fault-tolerant solution",
//     "bs": "revolutionize end-to-end systems"
//   }
// },
// {
//   "id": 6,
//   "name": "Mrs. Dennis Schulist",
//   "username": "Leopoldo_Corkery",
//   "email": "Karley_Dach@jasper.info",
//   "address": {
//     "street": "Norberto Crossing",
//     "suite": "Apt. 950",
//     "city": "South Christy",
//     "zipcode": "23505-1337",
//     "geo": {
//       "lat": "-71.4197",
//       "lng": "71.7478"
//     }
//   },
//   "phone": "1-477-935-8478 x6430",
//   "website": "ola.org",
//   "company": {
//     "name": "Considine-Lockman",
//     "catchPhrase": "Synchronised bottom-line interface",
//     "bs": "e-enable innovative applications"
//   }
// },
// {
//   "id": 7,
//   "name": "Kurtis Weissnat",
//   "username": "Elwyn.Skiles",
//   "email": "Telly.Hoeger@billy.biz",
//   "address": {
//     "street": "Rex Trail",
//     "suite": "Suite 280",
//     "city": "Howemouth",
//     "zipcode": "58804-1099",
//     "geo": {
//       "lat": "24.8918",
//       "lng": "21.8984"
//     }
//   },
//   "phone": "210.067.6132",
//   "website": "elvis.io",
//   "company": {
//     "name": "Johns Group",
//     "catchPhrase": "Configurable multimedia task-force",
//     "bs": "generate enterprise e-tailers"
//   }
// },
// {
//   "id": 8,
//   "name": "Nicholas Runolfsdottir V",
//   "username": "Maxime_Nienow",
//   "email": "Sherwood@rosamond.me",
//   "address": {
//     "street": "Ellsworth Summit",
//     "suite": "Suite 729",
//     "city": "Aliyaview",
//     "zipcode": "45169",
//     "geo": {
//       "lat": "-14.3990",
//       "lng": "-120.7677"
//     }
//   },
//   "phone": "586.493.6943 x140",
//   "website": "jacynthe.com",
//   "company": {
//     "name": "Abernathy Group",
//     "catchPhrase": "Implemented secondary concept",
//     "bs": "e-enable extensible e-tailers"
//   }
// },
// {
//   "id": 9,
//   "name": "Glenna Reichert",
//   "username": "Delphine",
//   "email": "Chaim_McDermott@dana.io",
//   "address": {
//     "street": "Dayna Park",
//     "suite": "Suite 449",
//     "city": "Bartholomebury",
//     "zipcode": "76495-3109",
//     "geo": {
//       "lat": "24.6463",
//       "lng": "-168.8889"
//     }
//   },
//   "phone": "(775)976-6794 x41206",
//   "website": "conrad.com",
//   "company": {
//     "name": "Yost and Sons",
//     "catchPhrase": "Switchable contextually-based project",
//     "bs": "aggregate real-time technologies"
//   }
// },
// {
//   "id": 10,
//   "name": "Clementina DuBuque",
//   "username": "Moriah.Stanton",
//   "email": "Rey.Padberg@karina.biz",
//   "address": {
//     "street": "Kattie Turnpike",
//     "suite": "Suite 198",
//     "city": "Lebsackbury",
//     "zipcode": "31428-2261",
//     "geo": {
//       "lat": "-38.2386",
//       "lng": "57.2232"
//     }
//   },
//   "phone": "024-648-3804",
//   "website": "ambrose.net",
//   "company": {
//     "name": "Hoeger LLC",
//     "catchPhrase": "Centralized empowering task-force",
//     "bs": "target end-to-end models"
//   }
// }
//]