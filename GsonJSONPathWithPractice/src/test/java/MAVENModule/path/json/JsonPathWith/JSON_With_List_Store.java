package MAVENModule.path.json.JsonPathWith;

import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.restassured.RestAssured;
import static io.restassured.path.json.JsonPath.with;


/*
* create Java object, serialize it with Gson and then use JsonPath object 
* to parse it to get the data
 * 
 * { "store": {
	   "books": [
	    { "category": "reference",
	      "author": "Nigel Rees",
	      "title": "Sayings of the Century",
	      "isbn": "",
	      "price": 8.95
	    },
	    { "category": "fiction",
	      "author": "Evelyn Waugh",
	      "title": "Sword of Honour"
	      "isbn": "",
	      "price": 12.99
	    },
	    { "category": "fiction",
	      "author": "Herman Melville",
	      "title": "Moby Dick",
	      "isbn": "0-553-21311-3",
	      "price": 8.99
	    },
	    { 
	    "category": "fiction",
	      "author": "J. R. R. Tolkien",
	      "title": "The Lord of the Rings",
	      "isbn": "0-395-19395-8",
	      "price": 22.99
	    }
	  ],
	    "bicycle": {
	      "color": "red",
	      "price": 19.95
	    }
	  }
	 }
 * 
 * Create Java objects and use Gson toJson() method to serialize the Json 
 * data into a String
 * 
 */

class Store {
	Books store;
	Bicycle bicycle;

	public Store(Books store, Bicycle bicycle) {
		this.store = store;
		this.bicycle = bicycle;
	}
}

class Books {
	List<Book> books;

	public Books(List<Book> books) {
		this.books = books;
	}
}

class Book {
	String category;
	String author ;
	String title;
	String isbn;
	Double price;

	public Book(String category, String author, String title, String isbn, Double price) {
		this.category = category;
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.price = price;
	}
}

class Bicycle {
	String color;
	Double price;

	public Bicycle(String color, Double price) {
		super();
		this.color = color;
		this.price = price;
	}
}


public class JSON_With_List_Store {
	private final Store store;
	private final String JSONstring;

	private Object deserializeFromJson(String json, Class cls) {
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		return gs.fromJson(json, cls);
	}
	
	private String serializeToJson(Object o) {
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		return gs.toJson(store);
	}

	public JSON_With_List_Store() {

		//(A) create Bicycle object
		Bicycle bicycle = new Bicycle("red", 19.95);

		//(B) create Book and Books objects
		Books book = new Books (Arrays.asList(

				new Book(
						"level 1",
						"Nigel Rees",
						"Sayings of the Century",
						null,
						8.95
						),

				new Book(
						"level 3",
						"Evelyn Waugh",
						"Sword of Honour",
						null,
						12.99),

				new Book(
						"level 5",
						"Herman Melville",
						"Moby Dick",
						"0-553-21311-3",
						8.99),

				new Book(
						"level 7",
						"J. R. R. Tolkien",
						"The Lord of the Rings",
						"0-395-19395-8",
						22.99		
						)
				));

		//(C) create Store object and convert to  Json string
		store = new Store(book, bicycle);
		JSONstring = serializeToJson(store);

		//(E)
		Store store2 = (Store) deserializeFromJson(JSONstring, Store.class);
		
		Bicycle bicycle2;
		bicycle2 = store2.bicycle;
		System.out.println(	"Bicycle color = " + bicycle2.color + "\r\n" + 
							"Bicycle price = " + bicycle2.price );
	}

	@Test
	public void listAllBookCategories() {

		//Get the first book category:
		String categoryFirst = with(JSONstring).get("store.books[0].category");

		//Get the last book category:
		String categoryLast = with(JSONstring).get("store.books[-1].category");
		
		//To get a list of all book categories:
		List<String> categories = with(JSONstring).get("store.books.category");
		
		//display
		System.out.println("\r\nDisplaying all categories in the JSON string...");
		for(String current : categories) {
			System.out.println("book category = " + current);
		}
		
		//Another way to display all categories
		List<?> books = with(JSONstring).get("store.books.category");
		for(int i=0; i<books.size(); i++) {
			System.out.println(books.get(i));
		}
	}

}

