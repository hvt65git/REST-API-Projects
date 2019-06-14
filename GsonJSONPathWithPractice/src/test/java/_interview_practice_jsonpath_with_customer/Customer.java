package _interview_practice_jsonpath_with_customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.path.json.JsonPath;

import _interview_practice_jsonpath_with_customer.Customer.Customer1.Address;
import _interview_practice_jsonpath_with_customer.Customer.Customer1.Phone;


/*
 * MORE GOOD STUFF FOR INTERVIEWS;
 * 
 * 		(a) serialize java Object into JSON formatted string
 * 		Gson gs = new GsonBuilder().create().toJson(Object);
 * 
 * 		(b) get the original values from JSON string 
 * 		String street = JsonPath.with(stringJson).get("customer.address.street")
 */
/*
 * Create and parse the following JSON object
 * {
"customer" : {
    "first name" : "john",
    "last name" : "doe",
    "address" : {
        "street" : "4101 398th W.",
        "city" : "tacoma",
        "state" : "washington"
    },
    "phone" : {
        "cell" : "206 123 4567",
        "home" : "425 123 4567"
    }
}
}
 */

public class Customer {
	private Customer1 customer;

	public Customer(Customer1 c) {
		this.customer = c;
	}
	
	static class Customer1 {

		static class Address {
			String street;
			String city;
			String state;

			public Address(String street, String city, String state) {
				this.street = street;
				this.city = city;
				this.state = state;
			}
		}

		 static class Phone {
			String cell;
			String home;

			public Phone(String cell, String home) {
				this.cell = cell;
				this.home = home;
			}
		}

		String first_name;
		String last_name;
		Address address;
		Phone phone;

		public Customer1(String first_name, String last_name, Address address, Phone phone) {
			this.first_name = first_name;
			this.last_name = last_name;
			this.address = address;
			this.phone = phone;
		}
	}

	public static void main (String[] arg){
		//create the customer java objects
		Phone phone = new Phone("206 123 4567",  "425 123 4567");
		Address address = new Address("4101 398th W.", "tacoma", "washington");

		//public Customer(String first_name, String last_name, Address address, Phone phone)
		Customer1 cust = new Customer1("john", "doe", address, phone);

		//need to create a Customer object to match specification at top of file
		Customer customer = new Customer(cust);

		//https://stackoverflow.com/questions/16607444/how-to-serialize-object-to-json
		//*** use GsonBuilder().create() to SERIALIZE the customer object to a JSON string
		String stringJson = new GsonBuilder().create().toJson(customer);

		System.out.println( "SERIALIZED Java object JSON string => \r\n" + stringJson);
		System.out.println();

		//convert the Json string to JsonPath - good now		
		System.out.println("Printing out the customer values: ");

		System.out.println( "first_name = " + 
				JsonPath.with(stringJson).get("customer.first_name") +
				", last_name = " + 
				JsonPath.with(stringJson).get("customer.last_name"));

		//		Phone phone = new Phone("206 123 4567",  "425 123 4567");

		System.out.println( "cell phone = " + 
				JsonPath.with(stringJson).get("customer.phone.cell") +
				", home phone = " + 
				JsonPath.with(stringJson).get("customer.phone.home"));

		//        "address": {
		//        "street": "4101 398th W.",
		//        "city": "tacoma",
		//        "state": "washington"
		//      },

		System.out.println( "\r\ncustomer.address.street = " + 
				JsonPath.with(stringJson).get("customer.address.street") +
				"\r\ncustomer.address.city = " + 
				JsonPath.with(stringJson).get("customer.address.city")	+
				"\r\ncustomer.address.state = " + 
				JsonPath.with(stringJson).get("customer.address.state"));
	}
}

//from jsonpath.com - looks good now:
//	[
//	  [
//	    {
//	      "customer": {
//	        "first_name": "john",
//	        "last_name": "doe",
//	        "address": {
//	          "street": "4101 398th W.",
//	          "city": "tacoma",
//	          "state": "washington"
//	        },
//	        "phone": {
//	          "cell": "206 123 4567",
//	          "home": "425 123 4567"
//	        }
//	      }
//	    }
//	  ]
//	]
//OUTPUT
//SERIALIZED Java object JSON string => 
//{"customer":{"first_name":"john","last_name":"doe","address":{"street":"4101 398th W.","city":"tacoma","state":"washington"},"phone":{"cell":"206 123 4567","home":"425 123 4567"}}}
//
//Printing out the customer values: 
//first_name = john, last_name = doe
//cell phone = 206 123 4567, home phone = 425 123 4567
//
//customer.address.street = 4101 398th W.
//customer.address.city = tacoma
//customer.address.state = washington
