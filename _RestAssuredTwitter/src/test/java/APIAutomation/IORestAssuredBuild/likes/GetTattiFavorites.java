package APIAutomation.IORestAssuredBuild.likes;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetTattiFavorites {
	private final Count count = new Count(200);
	private final String customFavName = "Tatti_D";

	private final String baseURI = "https://api.twitter.com";
	private final String resources = "/1.1/favorites/list.json";

	@Test
	public void getFavoritesList() 
	{
		RestAssured.baseURI = baseURI;
		Response response = RestAssured.given()
				.auth()
				.oauth(consumerKey, 
						consumerSecret, 
						accessToken, 
						accessTokenSecret)
				.param(count.getKeyname(), count.getValue())
				.when()
				.get(resources)
				.then().assertThat().statusCode(200)
				.extract().response();

		//create a list of user favs
		List<Favorite> userFavList = new ArrayList<>();

		//add data to lists
		List<String> allText = response.jsonPath().getList("text");
		List<String> allUser = response.jsonPath().getList("user.name");
		List<String> allScreenName = response.jsonPath().getList("user.screen_name");
		List<String> allLocation = response.jsonPath().getList("user.location");

		//create user list
		int size = allText.size();
		for(int i=0; i<size; i++) {
			
			//add to user fav list if it matches
			if(customFavName.equals(allScreenName.get(i))) {
				userFavList.add(
						new Favorite (
								"Favorite #" + (i+1),
								allText.get(i),
								allLocation.get(i),
								allScreenName.get(i))
						);
			}
		}

		//print out user favorites
		System.out.println("\r\nPrinting out the " + customFavName  + " Favorites:");
		System.out.println("Total Count:" + userFavList.size() + "\r\n");
		for(Favorite current : userFavList) {
			System.out.println(
					current.getNumber() + 	"\r\n" +
							current.getLocation() +	"\r\n" +
							current.getScreenName() + "\r\n" +
							current.getText() + "\r\n" );

		}
	}
}




