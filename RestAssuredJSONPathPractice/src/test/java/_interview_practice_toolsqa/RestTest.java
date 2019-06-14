package _interview_practice_toolsqa;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.given;
/*
 * GOOD INTERVIEW MATERIAL HERE! Note: Does not use RequestSpecification objects (which allow baseURI tracking)
 * From:
 * https://www.testingexcellence.com/parse-json-response-rest-assured/
 */

/*
 * 
Home » API Testing » How to Parse JSON Response with REST-assured
How to Parse JSON Response with REST-assured
Updated: December 8, 2018 - Amir Ghahrai

In this API Testing tutorial, we take a look at how to parse JSON response and extract information using the REST-assured library.

When testing an API, you typically make a request to a resource, (e.g. via a GET or POST request). The server comes back with a response and then you do some assertions on the response.

Related:

How to send a POST request with REST-assured

How to Parse JSON Response
For this tutorial, I will be using JSONPlaceholder which is a fake online REST API for Testing and Prototyping. JSONPlaceholder is a free online REST service that you can use whenever you need some fake data.

More specifically, I will be using the users endpoint https://jsonplaceholder.typicode.com/users

Request and Response
When we make a GET request to the above resource, we get a JSON response which contains a list of users.

 This list is represented as a JSON Array. Each array has a structure like this:

{
  id: 1,
  name: "Leanne Graham",
  username: "Bret",
  email: "Sincere@april.biz",
  address: {
    street: "Kulas Light",
    suite: "Apt. 556",
    city: "Gwenborough",
    zipcode: "92998-3874",
    geo: {
      lat: "-37.3159",
      lng: "81.1496"
    }
  },
  phone: "1-770-736-8031 x56442",
  website: "hildegard.org",
  company: {
    name: "Romaguera-Crona",
    catchPhrase: "Multi-layered client-server neural-net",
    bs: "harness real-time e-markets"
  }
}

 */

//ValidatableResponse vr = 
//given()
//.auth()
//.oauth(consumerKey, 
//		consumerSecret, 
//		accessToken, 
//		accessTokenSecret)
//.param(count.getKeyname(), count.getValue())
//.param("screen_name", screenName)
//.contentType(ContentType.JSON)
////.log().all()
//.when()
//.get(endpoint)
//.then()
//.contentType(ContentType.JSON)
//.statusCode(statusCode);

public class RestTest {

	public static Response doGetRequest(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;

		return
				given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
				when().get(endpoint).
				then().contentType(ContentType.JSON).extract().response();
	}

	public static void main(String[] args) {
		
		Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");

		List<String> jsonResponse = response.jsonPath().getList("$");

		System.out.println(jsonResponse.size());

		//Parsing JSON Arrays and Lists
		//In the above example, if we wanted to get the username of all entries, we could use
		String usernames = response.jsonPath().getString("username");
		System.out.println(usernames);

		//This would print the array like:
		//[Bret, Antonette, Samantha, Karianne, Kamren, Leopoldo_Corkery, Elwyn.Skiles, Maxime_Nienow, Delphine, Moriah.Stanton]

		//If we then want to get the username of the first entry we could use
		usernames = response.jsonPath().getString("username[0]");

		//This would print the first username: Bret

		//Using a List we can use
		jsonResponse = response.jsonPath().getList("username");

		System.out.println(jsonResponse.get(0));

		//This would print the first username: Bret

		//Parsing JSON ArrayList and HashMap
		//Looking at the above JSON structure, the company is actually a map. If we only had one record, we could use

		response = doGetRequest("https://jsonplaceholder.typicode.com/users/1");

		Map<String, String> company = response.jsonPath().getMap("company");

		System.out.println(company.get("name"));

		//which would print Romaguera-Crona

		//But if the response returns an array and we want to extract the first company name, we could use:

		response = doGetRequest("https://jsonplaceholder.typicode.com/users/");

		company = response.jsonPath().getMap("company[0]");
		
		System.out.println(company.get("name"));

		//Alternatively, we could use:

		response = doGetRequest("https://jsonplaceholder.typicode.com/users/");

		List<Map<String, String>> companies = response.jsonPath().getList("company");
		
		System.out.println(companies.get(0).get("name"));
		
		//both of which will print Romaguera-Crona.
		//
		//Tags: API Testing, JSON, REST-assured
		//https://www.testingexcellence.com/parse-json-response-rest-assured/
	}
}