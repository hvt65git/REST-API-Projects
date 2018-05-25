package com.jira.api.framework;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.easybix.RestAssuredCore.BaseAssertion;
import com.easybix.RestAssuredCore.BaseTest;
import com.easybix.RestAssuredCore.RESTCalls;
import com.easybix.utils.PayloadGenerator;
import com.easybix.utils.URL;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class JIRACreateIssue {
	private static Response response;
	private static String sessionID; 
	private static final String URI = URL.getEndPoint("/rest/api/2/issue/");
	private static Logger log = LogManager.getLogger(JIRACreateIssue.class.getName());

	 static {
		 //init global constant session ID
		sessionID = BaseTest.doLogin();
	}

	public static void jiraCreateIssue(final String testNumber) throws Exception {
		String createIssuePayLoad = PayloadGenerator.generatePayLoadString("CreateBug.json");
		response = RESTCalls.POSTRequest(URI, createIssuePayLoad, sessionID);
		BaseAssertion.verifyStatusCode(response, 201);
		
		//get the response headers key value pairs into the hashmap
		HashMap<String, String> headerAll = new HashMap<String, String>();
		Headers responseHeaders = response.getHeaders();
		for (Header temp : responseHeaders) {
			headerAll.put(temp.getName(), temp.getValue());
		}
		//get the keyset
		Set<String> keys = headerAll.keySet();
		
		//print out the hashmap
		System.out.println("here are the response headers from the hashmap..." + headerAll + "\r\n****");
		for(String key : keys) {
			System.out.println( "***KEY***> " + key +  " ***VALUE***> "  + headerAll.get(key));
		}
	}

}
