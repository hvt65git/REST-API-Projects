package toolsqa;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonPathUsage2 {
	

    @Test
    public void JsonPathUsage2() throws MalformedURLException
    {
    	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
    	RequestSpecification httpRequest = RestAssured.given();
    	Response response = httpRequest.get("");

    	// First get the JsonPath object instance from the Response interface
    	JsonPath jsonPathEvaluator = response.jsonPath();

    	// Read all the books as a List of String. Each item in the list
    	// represent a book node in the REST service Response
    	List<String> allBooks = jsonPathEvaluator.getList("books.title");

    	// Iterate over the list and print individual book item
    	for(String book : allBooks) {
    		System.out.println("Book: " + book);
    	}
    }

}
//success!
//[RemoteTestNG] detected TestNG version 6.14.2
//Book: Eloquent JavaScript, Second Edition
//Book: Learning JavaScript Design Patterns
//Book: Speaking JavaScript
//Book: Programming JavaScript Applications
//Book: Understanding ECMAScript 6
//Book: You Don't Know JS
//Book: Git Pocket Guide
//Book: Designing Evolvable Web APIs with ASP.NET
//PASSED: JsonPathUsage2
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


