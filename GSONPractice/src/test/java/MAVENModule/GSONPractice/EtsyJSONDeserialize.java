package MAVENModule.GSONPractice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utils.PayloadGenerator;


/*	DEVELOPER: Henry Todd
 * 
 * 	ASSIGNMENT: 
 * 
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
  ]
]
 */

/*
 * 
 */


/*
 * 
 */
public class EtsyJSONDeserialize {
	
	class EtsyRecords {
		List<EtsyRecordFull> recordsList;
	}
	
	double m_totalCost = 0.0;
	double m_totalTaxes= 0.0;
	double m_totalShippingCost = 0.0;
	
	private static final String jsonFile = "C:\\json test data\\purchases.json";
		
	/*
	 * 
	 */
	private Set<String> getShopNamesSet(EtsyRecordFull[] records) {
		Set<String> shops = new TreeSet<>();

		//add each shop to sorted tree set
		for(EtsyRecordFull current : records ) {
			shops.add(current.shop_name);
		}
		return shops;
	}
	
	/*
	 * 
	 */
	private void displayPurchasesByShop(EtsyRecordFull[] records, String shopName) {
		int ctr = 0;
		double itemCost = 0.0;
		double totalCost = 0.0;
		double totalTaxes= 0.0;
		double totalShippingCost = 0.0;

		for(EtsyRecordFull current : records ) {
			if(shopName.equals(current.shop_name)) {
				itemCost =	
						Double.parseDouble(current.price) +
						Double.parseDouble(current.taxes) +
						Double.parseDouble(current.shipping_cost);

				totalCost += itemCost;
				totalTaxes += Double.parseDouble(current.taxes);
				totalShippingCost += Double.parseDouble(current.shipping_cost);
			
				System.out.println(  
						"\r\nShop Name = " + current.shop_name +"\r\n" +
						"item # " + ++ctr + "\r\n" +
								"title = " + current.title + "\r\n" +
								"purchase date = " 		+ current.purchase_date +"\r\n" +
								"quantity = " 			+ current.quantity +"\r\n" +

			"price = " + current.price +"\r\n" +
			"taxes = " + current.taxes +"\r\n" +
			"shipping_cost = " + current.shipping_cost +"\r\n" +
			"total cost for item = " + String.format("%.2f", new BigDecimal(itemCost)));
			}
		}

		//now,tabulate all the cost fields and present the total cost to the console
		System.out.println("\r\nCOST SUMMARY - " + shopName);
		System.out.println("Total Taxes = "			+ String.format("%.2f", new BigDecimal(totalTaxes)));
		System.out.println("Total Shipping Cost = " + String.format("%.2f", new BigDecimal(totalShippingCost)));
		System.out.println("Total Cost = " 			+ String.format("%.2f", new BigDecimal(totalCost)));
		System.out.println("\r\n************************************************************************");

		//increment global costs
		 m_totalCost += totalCost;
		 m_totalTaxes += totalTaxes;
		 m_totalShippingCost += totalShippingCost;
	}

	/*
	 * 
	 */
	private void displayAllPurchases(EtsyRecordFull[] records) {
		int ctr = 0;
		double itemCost = 0.0;
		double totalCost = 0.0;
		double totalTaxes= 0.0;
		double totalShippingCost = 0.0;

		for(EtsyRecordFull current : records ) {
			itemCost =	Double.parseDouble(current.price) +
					Double.parseDouble(current.taxes) +
					Double.parseDouble(current.shipping_cost);

			totalCost += itemCost;
			totalTaxes += Double.parseDouble(current.taxes);
			totalShippingCost += Double.parseDouble(current.shipping_cost);

			System.out.println(  
					"item # " + ++ctr + "\r\n" +
							"title = " + current.title + "\r\n" +
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

		//now,tabulate all the cost fields and present the total cost to the console
		System.out.println("COST SUMMARY ");
		System.out.println("Total Taxes = "			+ String.format("%.2f", new BigDecimal(totalTaxes)));
		System.out.println("Total Shipping Cost = " + String.format("%.2f", new BigDecimal(totalShippingCost)));
		System.out.println("Total Cost = " 			+ String.format("%.2f", new BigDecimal(totalCost)));
		System.out.println();
	}
	
	/*
	 * 
	 */
	private void displayTotalCost() {
		System.out.println("\r\nTOTAL COST SUMMARY");
		System.out.println("Total Taxes = "			+ String.format("%.2f", new BigDecimal(m_totalTaxes)));
		System.out.println("Total Shipping Cost = " + String.format("%.2f", new BigDecimal(m_totalShippingCost)));
		System.out.println("Total Cost = " 			+ String.format("%.2f", new BigDecimal(m_totalCost)));
	}
	
	/*
	 * 
	 */
	private void displayAllPurchasesByStore(EtsyRecordFull[] records) {
		//get set of shops
		Set<String> shops = getShopNamesSet(records);

		//displays tabulated purchases by shop
		for(String current : shops ) {
			displayPurchasesByShop(records, current);
		}
		
		//display total cost
		displayTotalCost();
	}
	
	/*
	 * 
	 */
	@Test
	public void deserializeJsonAndGenerateReports() {
		try {
			//load the Json body from a file into a string
			String stringJson = PayloadGenerator.generatePayLoadStringFromFile(jsonFile);

			//deserialize the string into a java object
			EtsyRecordFull[] records = new GsonBuilder()
					.create()
					.fromJson(stringJson, EtsyRecordFull[].class);
			
			//display purchases by store
			displayAllPurchasesByStore(records);	
			
			//display all and compare total results with displayAllPurchasesByStore total results
			displayAllPurchases(records);

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}

	}

}
