package  APIAutomation.IORestAssuredBuild._GET;

import APIAutomation.IORestAssuredBuild._core.Count;
import static APIAutomation.IORestAssuredBuild._core.OAUTH.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//Programming Assignment
//for my @ishmail2015 account
//get all mentions and print out this JSON data
//"user": {
//  "id": 922643294223589376,
//  "id_str": "922643294223589376",
//  "name": "3rd Eye of Deborah",
//  "screen_name": "ConsecratedMind",
//  "location": "Realms",
//  "description": "Just when I though I had 'arrived' witnessing visions and miracles from God, I had an 'out-of-body experience' October 21st 2015",
//  "url": "https://t.co/VHiNeJepzs",
//...}
//
//
/*
 * 
 * 
https://developer.twitter.com/en/docs/tweets/timelines/api-reference/get-statuses-mentions_timeline.html

Overview 
Guides 
API Reference 
API Reference contents  
GET statuses/home_timeline 
GET statuses/mentions_timeline 
GET statuses/user_timeline 
GET statuses/mentions_timeline

Returns the 20 most recent mentions (Tweets containing a users’s @screen_name) for the authenticating user.
The timeline returned is the equivalent of the one seen when you view your mentions on twitter.com.
This method can only return up to 800 tweets.
See Working with Timelines for instructions on traversing timelines.
Resource URL - note, Postman calls this the request URL
https://api.twitter.com/1.1/statuses/mentions_timeline.json

 * 
 * 
 * 
 */

public class GetTwitterMentions {

}
