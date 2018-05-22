package MAVENModule.GSONPractice;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * 	ASSIGNMENT: 
 * (1) Create Java class(es) and convert to JSON object string via GsonBuilder 
 * (2) Copy string from console output to jsoneditoronline
 * (3) visually compare json data displayed to the expected display below
 *       
 *    JSON ETSY purchase records
 */

public class EtsyJSONSerialize {

	@Test(enabled = true)
	public void test01() {

		//create the test data 
		List<EtsyRecord> purchases = Arrays.asList(
				new EtsyRecord(
						"Personalized Custom Made Jewelry Orgone Pendant -  custom picture or crystals on the front side holiday gift ideas for him and her",
						"07/27/2016",
						"CosmicEnergyOrgonite",
						45.00,
						5.20),
				new EtsyRecord(
						"Reiki orgone decagon puck",
						"07/26/2016",
						"orgonetabernacle",
						15.00,
						13.00),
				new EtsyRecord(
						"Small Reiki orgone pyramid",
						"07/27/2016",
						"orgonetabernacle",
						15.00,
						13.00)
				);

		//instantiate a GsonBuilder object 
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		//(A) - convert to Json and print it out and compare to expected
		String stringJson = gs.toJson(purchases);
		System.out.println("\r\n(A) - Arrays.asList -> stringJson =\r\n " + stringJson);
		
		//(B) - create EtsyPurchases object and convert to Json
		//calculate and set the costs
		EtsyPurchases ep = new EtsyPurchases(purchases);
		stringJson = gs.toJson(ep);
		System.out.println("\r\n(B) - EtsyPurchases object -> stringJson =\r\n " + stringJson + "\r\n");


	}

}
