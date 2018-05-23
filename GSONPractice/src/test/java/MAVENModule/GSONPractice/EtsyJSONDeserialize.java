package MAVENModule.GSONPractice;
/*	DEVELOPER: Henry Todd
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
  ]
]
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import utils.PayloadGenerator;

class EtsyRecords {
	List<EtsyRecordFull> recordsList;
}

public class EtsyJSONDeserialize {
	private static final String jsonFile = "C:\\json test data\\purchases.json";


	private Set<String> getShopNamesSet(EtsyRecordFull[] records) {
		Set<String> shops = new TreeSet<>();

		//add each shop to sorted tree set
		for(EtsyRecordFull current : records ) {
			shops.add(current.shop_name);
		}
		return shops;
	}
	/*
Shop Name = EternalBlissed
item # 1
title = Women's Racerback Tank
purchase date = 01/25/2018
quantity = 1
price = 27.50
taxes = 3.29
shipping_cost = 5.00
total cost for item = 35.79
>>> need \r\n here <<<<
Shop Name = EternalBlissed
item # 2
title = Lakshmi Wooden Lotus Pendant For Abundance and Wealth, Pet Jewelry, Pet Collar Charm, Bohemian, Hindu, Gypsy, Gift for Him or Her, Yoga
purchase date = 01/25/2018
quantity = 1
price = 7.77
taxes = 1.06
shipping_cost = 2.66
total cost for item = 11.49
Shop Name = EternalBlissed
item # 3
title = Peridot and Amethyst bracelet for Crown and Heart Chakra Alignment, spirituality, Healing, Beaded Bracelet For Her
purchase date = 01/18/2018
quantity = 1
price = 25.00
taxes = 2.79
shipping_cost = 2.60
total cost for item = 30.39
Shop Name = EternalBlissed
item # 4
title = Raw Stone Amethyst, Turquoise, Aventurine, Lepidolite Tribal Bracelet. Unisex, Bracelet for Divine Communication, Boho, Bohemian, wanderlust
purchase date = 01/12/2018
quantity = 1
price = 35.00
taxes = 3.80
shipping_cost = 2.60
total cost for item = 41.40
Shop Name = EternalBlissed
item # 5
title = Fancy Rudraksha and Crystals bracelet for wellbeing, Connecting to Shiva, Genuine Rudrkasha beads from India, Gift for Yogi
purchase date = 01/06/2018
quantity = 1
price = 25.00
taxes = 1.79
shipping_cost = 2.61
total cost for item = 29.40
Shop Name = EternalBlissed
item # 6
title = Rudraksha and Red Felt Unisex Bracelet for Conecting to Shiva, Healing and Energy, Gift for Him, Mens Bracelet, Man, For Her, Yoga, Hindu
purchase date = 10/07/2017
quantity = 2
price = 15.00
taxes = 0.00
shipping_cost = 5.20
total cost for item = 20.20
Shop Name = EternalBlissed
item # 7
title = Tektite and Herkimer Diamond Bracelet For High Vibrational Energy & Consciousness/ April Birthstone/ Meteorite Jewelry/ Gift For Him or Her
purchase date = 04/16/2017
quantity = 1
price = 40.00
taxes = 0.00
shipping_cost = 2.61
total cost for item = 42.61
Shop Name = EternalBlissed
item # 8
title = Turquoise, Malachite, Obsidian and Lava Stone Unisex Meditation Mala Bracelet for Communication, Grounding and Abundance/ Fathers Day Gift/
purchase date = 04/13/2017
quantity = 1
price = 30.00
taxes = 0.00
shipping_cost = 2.61
total cost for item = 32.61
Shop Name = EternalBlissed
item # 9
title = Amber and Turquoise Bracelet with Black Obsidian & Pink Lepidolite, Talisman For Protection, Communication/ Unisex Handmade Bracelet/ GIft
purchase date = 03/24/2017
quantity = 1
price = 30.00
taxes = 0.00
shipping_cost = 6.73
total cost for item = 36.73
Shop Name = EternalBlissed
item # 10
title = Men's Turquoise and Lilac Lepidolite Bracelet for Grounding, Physical Energy, Connecting to Nature, Increasing Self Esteem and Confidence
purchase date = 03/03/2017
quantity = 1
price = 25.00
taxes = 0.00
shipping_cost = 2.61
total cost for item = 27.61
Shop Name = EternalBlissed
item # 11
title = Men's Garnet, Malachite, Labradorite and Gray Aventurine Bracelet, Talisman for Physical Energy, Opening the Heart, Magic, Mystery and More
purchase date = 01/28/2017
quantity = 1
price = 25.00
taxes = 0.00
shipping_cost = 2.61
total cost for item = 27.61
Shop Name = EternalBlissed
item # 12
title = Raw Pyrite Cube and Hematite Men's Bracelet, Talisman for Grounding, Bringing Higher Awareness to Physical Life, Manifestation, Masculinity
purchase date = 01/18/2017
quantity = 1
price = 40.00
taxes = 0.00
shipping_cost = 2.60
total cost for item = 42.60
Shop Name = EternalBlissed
item # 13
title = Men's Bracelet with Raw Iolite, Dumortierite, Hematite and Amethyst, Genuine Stone Talisman for Psychic Development, Spirituality, Grounding
purchase date = 01/18/2017
quantity = 1
price = 40.00
taxes = 0.00
shipping_cost = 2.60
total cost for item = 42.60

COST SUMMARY - EternalBlissed
Total Taxes = 12.73
Total Shipping Cost = 43.04
Total Cost = 421.04
	 */

	private void DisplayPurchasesByShop(EtsyRecordFull[] records, String shopName) {
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
	}

//	private void DisplayAllPurchases(EtsyRecordFull[] records) {
//		int ctr = 0;
//		double itemCost = 0.0;
//		double totalCost = 0.0;
//		double totalTaxes= 0.0;
//		double totalShippingCost = 0.0;
//
//		for(EtsyRecordFull current : records ) {
//			itemCost =	Double.parseDouble(current.price) +
//					Double.parseDouble(current.taxes) +
//					Double.parseDouble(current.shipping_cost);
//
//			totalCost += itemCost;
//			totalTaxes += Double.parseDouble(current.taxes);
//			totalShippingCost += Double.parseDouble(current.shipping_cost);
//
//			System.out.println(  
//					"item # " + ++ctr + "\r\n" +
//							"title = " + current.title + "\r\n" +
//							"shop name = " + current.shop_name +"\r\n" +
//							"shipping recipient = " + current.shipping_recipient +"\r\n" +
//							"purchase date = " 		+ current.purchase_date +"\r\n" +
//							"order number = " 		+ current.order_number +"\r\n" +
//							"quantity = " 			+ current.quantity +"\r\n" +
//
//			"price = " + current.price +"\r\n" +
//			"taxes = " + current.taxes +"\r\n" +
//			"shipping_cost = " + current.shipping_cost +"\r\n" +
//
//			"total cost for item = " + String.format("%.2f", new BigDecimal(itemCost)));
//			System.out.println();
//		}
//
//		//now,tabulate all the cost fields and present the total cost to the console
//		System.out.println("COST SUMMARY ");
//		System.out.println("Total Taxes = "			+ String.format("%.2f", new BigDecimal(totalTaxes)));
//		System.out.println("Total Shipping Cost = " + String.format("%.2f", new BigDecimal(totalShippingCost)));
//		System.out.println("Total Cost = " 			+ String.format("%.2f", new BigDecimal(totalCost)));
//		System.out.println();
//	}

	@Test
	public void deserializeJson() {

		try {
			//load the Json body from a file into a string
			String stringJson = PayloadGenerator.generatePayLoadString(jsonFile);

			//deserialize the string into a java class
			EtsyRecordFull[] records = new GsonBuilder()
					.create()
					.fromJson(stringJson, EtsyRecordFull[].class);

			//get set of shops
			Set<String> shops = getShopNamesSet(records);

			//displays tabulated purchases by shop
			for(String current : shops ) {
				DisplayPurchasesByShop(records, current);
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}

	}

}
