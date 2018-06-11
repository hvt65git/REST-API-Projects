package MAVENModule.RestAssuredYoutube;

// Sample Java code for user authorization

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.google.api.services.youtube.YouTube;

import static io.restassured.path.json.JsonPath.with;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyPlaylists {

	/** Application name. */
	private static final String APPLICATION_NAME = "API Sample";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = 
			new java.io.File( System.getProperty("user.home"), ".credentials/java-youtube-api-tests");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials
	 * at ~/.credentials/drive-java-quickstart
	 */

	//	private static final Collection<String> SCOPES = 
	//			Arrays.asList("googleapis.com/auth/youtube.force-ssl");// – sam Jul 27 '17 at 3:48
	//	

	//	private static final Collection<String> SCOPES =
	//			Arrays.asList("YouTubeScopes.https://www.googleapis.com/auth/youtube.force-ssl YouTubeScopes.https://www.googleapis.com/auth/youtubepartner");

	private static final Collection<String> SCOPES =
			Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl", 
					"https://www.googleapis.com/auth/youtubepartner");


	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		try {
			// Load client secrets.
			//InputStream in = ApiExample.class.getResourceAsStream("/client_secret.json");
			InputStream in  = new FileInputStream(DATA_STORE_DIR.getPath() + "\\client_secret.json");
			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader( in ));

			// Build flow and trigger user authorization request.
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
					HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
					.setDataStoreFactory(DATA_STORE_FACTORY)
					.setAccessType("offline")
					.build();
			Credential credential = new AuthorizationCodeInstalledApp(
					flow, new LocalServerReceiver()).authorize("user");
			System.out.println(
					"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
			return credential;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Build and return an authorized API client service, such as a YouTube
	 * Data API client service.
	 * @return an authorized API client service
	 * @throws IOException
	 */
	public static YouTube getYouTubeService() throws IOException {
		Credential credential = authorize();
		return new YouTube.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	public static void main(String[] args) throws IOException {

		try {
			YouTube youtube = getYouTubeService();

			HashMap<String, String> parameters = new HashMap<>();
			parameters.put("part", "snippet,contentDetails");
			parameters.put("mine", "true");
			parameters.put("maxResults", "25");
			parameters.put("onBehalfOfContentOwner", "");
			parameters.put("onBehalfOfContentOwnerChannel", "");

			YouTube.Playlists.List playlistsListMineRequest = youtube.playlists().list(parameters.get("part").toString());
			if (parameters.containsKey("mine") && parameters.get("mine") != "") {
				boolean mine = (parameters.get("mine") == "true") ? true : false;
				playlistsListMineRequest.setMine(mine);
			}

			if (parameters.containsKey("maxResults")) {
				playlistsListMineRequest.setMaxResults(Long.parseLong(parameters.get("maxResults").toString()));
			}

			if (parameters.containsKey("onBehalfOfContentOwner") && parameters.get("onBehalfOfContentOwner") != "") {
				playlistsListMineRequest.setOnBehalfOfContentOwner(parameters.get("onBehalfOfContentOwner").toString());
			}

			if (parameters.containsKey("onBehalfOfContentOwnerChannel") && parameters.get("onBehalfOfContentOwnerChannel") != "") {
				playlistsListMineRequest.setOnBehalfOfContentOwnerChannel(parameters.get("onBehalfOfContentOwnerChannel").toString());
			}

			PlaylistListResponse response = playlistsListMineRequest.execute();
			System.out.println(response);

			//parse the response and display the playlists info that would allow me to get captions
			//and video names, etc
			String jsonString = response.toPrettyString();
			List<Map<String, String>> items 		= JsonPath.with(jsonString).getList("items");
			List<Map<String, String>> snippet 	 	= JsonPath.with(jsonString).getList("items.snippet");
			List<Map<String, Object>> defthumbnails = JsonPath.with(jsonString).getList("items.snippet.thumbnails.high");

			//display details
			String  etag="", id="", kind="", publishedAt="", channelId="", title="",
					description="", channelTitle="", defThumbnailURL;
			System.out.println("\r\nMy Youtube Playlists Info:");
			for(int i=0; i<items.size(); i++) {
				
				etag	=  items.get(i).get("etag");
				id 		=  items.get(i).get("id");
				kind	=  items.get(i).get("kind");

				publishedAt		= snippet.get(i).get("publishedAt"); 
				channelId		= snippet.get(i).get("channelId");
				title 			= snippet.get(i).get("title");
				description 	= snippet.get(i).get("description");
				channelTitle	= snippet.get(i).get("channelTitle");
				defThumbnailURL = (String)defthumbnails.get(i).get("url"); 

				System.out.println();
				System.out.println("channel title = " + channelTitle);
				System.out.println("channel Id = " + channelId);

				System.out.println("playlist title = " + title);
				System.out.println("playlist Id = "    + id);
				System.out.println("playlist kind  = " + kind);
				System.out.println("playlist etag  = " + etag);

				System.out.println("playlist description = " + description);
				System.out.println("playlist publishedAt = " + publishedAt);
				System.out.println("playlist defThumbnailURL = " + defThumbnailURL);
			}

		} catch (GoogleJsonResponseException e) {
			e.printStackTrace();
			System.err.println("There was a service error: " + e.getDetails().getCode() + " : " +
					e.getDetails().getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
//success
//output:
//My Youtube Playlists Info:
//
//channel title = Hank65 65
//channel Id = UC0tZImEOXUutIyNqkq7lykA
//playlist title = ASMR
//playlist Id = PLz3RQ1o28UjYHaDmyckWXQoWa-Lxfurgj
//playlist kind  = youtube#playlist
//playlist etag  = "DuHzAJ-eQIiCIp7p4ldoVcVAOeY/ZOIyfs_lh3sfqhQ7moa6FCqmvJ4"
//playlist description = 
//playlist publishedAt = 2018-06-10T09:00:29.000Z
//playlist defThumbnailURL = https://i.ytimg.com/vi/6HyScCAgEI8/default.jpg
//
//channel title = Hank65 65
//channel Id = UC0tZImEOXUutIyNqkq7lykA
//playlist title = ORBS
//playlist Id = PLz3RQ1o28UjaS7pvmzdwL_46zS-vhZL0W
//playlist kind  = youtube#playlist
//playlist etag  = "DuHzAJ-eQIiCIp7p4ldoVcVAOeY/6e5U9Eo0r7himbdLdlILszTKUgs"
//playlist description = 
//playlist publishedAt = 2018-06-10T03:30:47.000Z
//playlist defThumbnailURL = https://i.ytimg.com/vi/OZwwR4mkKts/default.jpg
//
//channel title = Hank65 65
//channel Id = UC0tZImEOXUutIyNqkq7lykA
//playlist title = YTUBE_FAVS
//playlist Id = PLz3RQ1o28UjYspEt664JxPzrflMyovFLO
//playlist kind  = youtube#playlist
//playlist etag  = "DuHzAJ-eQIiCIp7p4ldoVcVAOeY/DLYRa5sVLfhUJk1stDOfCrbA2aw"
//playlist description = 
//playlist publishedAt = 2018-06-10T03:28:18.000Z
//playlist defThumbnailURL = https://i.ytimg.com/vi/6HyScCAgEI8/default.jpg



