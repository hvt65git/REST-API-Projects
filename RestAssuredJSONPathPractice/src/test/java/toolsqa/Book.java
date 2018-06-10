package toolsqa;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class Book {
    String isbn;
    String title;
    String subtitle;
    String author;
    String published;
    String publisher;
    int pages;
    String description;
    String website;
    
}