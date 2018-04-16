package APIAutomation.OpenWeatherMap;
//https://openweathermap.org/api
//Key
//Name
//
//545c707006c049596a5b2e0e8c05f1ab
//Default
//  
//caa6782eca1c4085ae6c658deba512a1
//ht65key
//  
//https://openweathermap.org/current
//
//example - get seattle weather
//https://openweathermap.org/current


//Current weather data
//Home
//API
//Current weather
//Access current weather data for any location on Earth including over 200,000 cities! Current weather is frequently updated based on global models and data from more than 40,000 weather stations. Data is available in JSON, XML, or HTML format.
//
//Call current weather data for one location
//Please remember that all Examples of API calls that listed on this page are just samples and do not have any connection to the real API service!
//By city name
//Description:
//You can call by city name or city name and country code. API responds with a list of results that match a searching word.
//There is a possibility to receive a central district of the city/town with its own parameters (geographic coordinates/id/name) in API response. Example
//
//API call:
//api.openweathermap.org/data/2.5/weather?q={city name}
////http://samples.openweathermap.org/data/2.5/weather?q=Seattle&appid=545c707006c049596a5b2e0e8c05f1ab
//response
//{"coord":{"lon":-0.13,"lat":51.51},"weather":[{"id":300,"main":"Drizzle","description":"light intensity drizzle","icon":"09d"}],"base":"stations","main":{"temp":280.32,"pressure":1012,"humidity":81,"temp_min":279.15,"temp_max":281.15},"visibility":10000,"wind":{"speed":4.1,"deg":80},"clouds":{"all":90},"dt":1485789600,"sys":{"type":1,"id":5091,"message":0.0103,"country":"GB","sunrise":1485762037,"sunset":1485794875},"id":2643743,"name":"London","cod":200}
//http://api.openweathermap.org/data/2.5/weather?q=Seattle&appid=545c707006c049596a5b2e0e8c05f1ab
//{"coord":{"lon":-122.33,"lat":47.6},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"base":"stations","main":{"temp":276.73,"pressure":1022,"humidity":75,"temp_min":273.15,"temp_max":280.15},"visibility":16093,"wind":{"speed":1.01,"deg":29.5005},"clouds":{"all":1},"dt":1521530100,"sys":{"type":1,"id":2949,"message":0.0051,"country":"US","sunrise":1521555077,"sunset":1521598964},"id":5809844,"name":"Seattle","cod":200}

public class AppTest {

	public static void main( String[] args )
	{
		System.out.println( "OpenWeatherMap class AppTest - Hello World!" );
	}
}
