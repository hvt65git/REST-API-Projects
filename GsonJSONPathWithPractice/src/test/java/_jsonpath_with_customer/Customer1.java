package _jsonpath_with_customer;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.path.json.JsonPath;

public class Customer1 {
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
			super();
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
	

	public static void main (String[] arg){
		
		//create the customer java objects
		Phone phone = new Phone("206 123 4567",  "425 123 4567");
		Address address = new Address("4101 398th W.", "tacoma", "washington");
		
		//public Customer(String first_name, String last_name, Address address, Phone phone)
		Customer1 cust = new Customer1("john", "doe", address, phone);
		
		//convert the customer object to a JSON string
		Gson gs = new GsonBuilder().create();
		String stringJson = gs.toJson(cust);
		
		//convert the Json string to JsonPath
		String first_name = JsonPath.with(stringJson).get("first_name");
		String last_name = JsonPath.with(stringJson).get("last_name");
		
		System.out.println( "first_name = " + first_name + ", last_name = " + last_name);
		
	}

}
