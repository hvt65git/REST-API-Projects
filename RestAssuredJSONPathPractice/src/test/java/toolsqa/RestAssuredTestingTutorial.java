package toolsqa;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTestingTutorial {
	private static final String endPoint = "/Seattle";

	@Test
	public void GetWeatherDetailsResponse()
	{
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a GET request call directly by using RequestSpecification.get() method.
		// Make sure you specify the resource name.
		Response response = httpRequest.get(endPoint);

		// Response.asString method will directly return the content of the body
		// as String.
		System.out.println("Response Body is =>  " + response.asString());

		//		[RemoteTestNG] detected TestNG version 6.14.2
		//		Response Body is =>  {
		//		    "City": "Seattle",
		//		    "Temperature": "24.89 Degree celsius",
		//		    "Humidity":- "39 Percent",
		//		    "WeatherDescription": "scattered clouds",
		//		    "WindSpeed": "2.1 Km per hour",
		//		    "WindDirectionDegree": " Degree"
		//		}
		//parse out temperature
		String rs = response.jsonPath().get("Temperature");
		String sc[] = rs.split(" ");

		//convert to farenheight - T(°F) = T(°C) × 9/5 + 32
		Double celcius = new Double(sc[0]);
		Double farenheit = celcius * 9/5 + 32;
		System.out.println("\r\nCurrent temperature in Seattle:\r\ndeg Farenheit = " + 
				farenheit  + "\r\n" +
				"deg Celcius = " + celcius);


	}

	@Test(enabled=false)
	public void GetWeatherDetails()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(endPoint);

		// Get the status code from the Response. In case of 
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}

	@Test(enabled=false)
	public void GetWeatherDetailsInvalidCity()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/dfdfsdf");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	}

	@Test(enabled=false)
	public void GetWeatherStatusLine()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get the status line from the Response and store it in a variable called statusLine
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Correct status code returned");
	}


}

//output - SUCCESS
//[RemoteTestNG] detected TestNG version 6.14.2
//Response Body is =>  {
//    "City": "Seattle",
//    "Temperature": "14.19 Degree celsius",
//    "Humidity": "51 Percent",
//    "WeatherDescription": "light rain",
//    "WindSpeed": "5.7 Km per hour",
//    "WindDirectionDegree": "190 Degree"
//}
//PASSED: GetWeatherDetails
//PASSED: GetWeatherDetailsResponse
//PASSED: GetWeatherStatusLine
//FAILED: GetWeatherDetailsInvalidCity
//java.lang.AssertionError: Correct status code returned expected [200] but found [400]