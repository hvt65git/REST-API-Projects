package MAVENModule.GSONPractice;
/*
 * 	ASSIGNMENT: 
 * (1) Define and create Java class(es) that correspond to the Etsy Json records
 * (2) Read the JSON data in from a .js json file into a String and deserialize from JSON via GsonBuilder 
 * (3) visually compare json data displayed to the expected display below
 *       
 *    JSON ETSY purchase records
[
  [
    {
      "title="Personalized Custom Made Jewelry Orgone Pendant -  custom picture or crystals on the front side holiday gift ideas for him and her",
      "purchase_date="07/27/2016",
      "shipped_date="08/03/2016",
      "shipping_address="1123 3rd East, , Seattle, WA, 98118, United States",
      "shipping_recipient="Jamie Starr",
      "shop_name="CosmicEnergyOrgonite",
      "order_number,
      "price="45.00",
      "subtotal="45.00",
      "taxes="0.00",
      "shipping_cost="5.20",
      "quantity=1,
      "currency_code="USD",
      "payment_method="PayPal",
      "payment_details="Via Paypal"
    },
    {
      "title="Reiki orgone decagon puck",
      "purchase_date="07/26/2016",
      "shipped_date="07/27/2016",
      "shipping_address="1123 3rd East, , Seattle, WA, 98118, United States",
      "shipping_recipient="Jamie Starr",
      "shop_name="orgonetabernacle",
      "order_number=,
      "price="15.00",
      "subtotal="15.00",
      "taxes="0.00",
      "shipping_cost="13.00",
      "quantity=1,
      "currency_code="USD",
      "payment_method="Credit Card",
      "payment_details="Credit Card"
    },
    {
      "title="Small Reiki orgone pyramid",
      "purchase_date="07/26/2016",
      "shipped_date="07/27/2016",
      "shipping_address="1123 3rd East, , Seattle, WA, 98118, United States",
      "shipping_recipient="Jamie Starr",
      "shop_name="orgonetabernacle",
      "order_number=,
      "price="15.00",
      "subtotal="15.00",
      "taxes="0.00",
      "shipping_cost="13.00",
      "quantity=1,
      "currency_code="USD",
      "payment_method="Credit Card",
      "payment_details="Credit Card"
    }
  ]
]
 */

import java.math.BigDecimal;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utils.PayloadGenerator;

class EtsyRecords {
	List<EtsyRecord3> recordsList;
}

final class Singleton {
	private static final Singleton INSTANCE = new Singleton();

	private Singleton() {}

	public static Singleton getInstance() {
		return INSTANCE;
	}
}

public class EtsyJSONDeserialize {
	private static final String jsonFile = "C:\\json test data\\purchases.json";


	@Test
	public void deserializeJson() {
		//  "purchase_date": "01/25/2018",
		//  "shipped_date": "01/30/2018",
		//  "shipping_address": "1111 39th Avenue South, , Seattle, WA, 98118, United States",
		//  "shipping_recipient": "Jamie Starr",
		//  "shop_name": "EternalBlissed",
		//  "order_number": 1282826135,
		//  "price": "27.50",
		//  "subtotal": "27.50",
		//  "taxes": "3.29",
		//  "shipping_cost": "5.00",
		//  "quantity": 1,
		//  "currency_code": "USD",
		//  "payment_method": "Credit Card",
		//  "payment_details": "Credit Card"


		try {
			//load the Json body from a file into a string
			//(A) - convert to Json and print it out and compare to expected
			String stringJson = PayloadGenerator.generatePayLoadString(jsonFile);
			System.out.println("\r\n(A) - Arrays.asList -> stringJson =\r\n " + stringJson);

			//deserialize the string into a java class
			Gson gs = new GsonBuilder()
					.disableHtmlEscaping()
					.serializeNulls()
					.create();

			EtsyRecord3[] records = new GsonBuilder().create().fromJson(stringJson, EtsyRecord3[].class);

			//print out list!
			double itemCost = 0.0;
			double totalCost = 0.0;
			double totalTaxes= 0.0;
			double totalShippingCost = 0.0;

			for(EtsyRecord3 current : records ) {
				totalShippingCost += Double.parseDouble(current.shipping_cost);

				totalTaxes += Double.parseDouble(current.taxes);

				itemCost = 
						Double.parseDouble(current.price) +
						Double.parseDouble(current.taxes) +
						Double.parseDouble(current.shipping_cost);

				totalCost += itemCost;

				System.out.println( 
						"title = " + current.title +"\r\n" +
								"shop name = " + current.shop_name +"\r\n" +
								"shipping recipient = " + current.shipping_recipient +"\r\n" +
								"purchase date = " 		+ current.purchase_date +"\r\n" +
								"order number = " 		+ current.order_number +"\r\n" +
								"quantity = " 			+ current.quantity +"\r\n" +

				"price = " + current.price +"\r\n" +
				"taxes = " + current.taxes +"\r\n" +
				"shipping_cost = " + current.shipping_cost +"\r\n" +

				"total cost for item = " + String.format("%.2f", new BigDecimal(itemCost)));
				System.out.println();
			}

			System.out.println("COST SUMMARY ");
			System.out.println("Total Taxes = "			+ String.format("%.2f", new BigDecimal(totalTaxes)));
			System.out.println("Total Shipping Cost = " + String.format("%.2f", new BigDecimal(totalShippingCost)));
			System.out.println("Total Cost = " 			+ String.format("%.2f", new BigDecimal(totalCost)));
			System.out.println();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}

		//now,tabulate all the cost fields and present the total cost to tthe console

	}

}