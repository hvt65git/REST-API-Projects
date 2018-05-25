package com.easybix.RestAssuredCore;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.easybix.utils.PayloadGenerator;
import com.easybix.utils.TestUtils;
import com.easybix.utils.URL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseTest {
	private static final Logger log = LogManager.getLogger(BaseTest.class.getName());
	
	public static String doLogin(){
		Response response;
		
		log.info("Starting Test Case : doLogin");
		String loginPayload = PayloadGenerator.generatePayLoadString("JiraLogin.json");
		
		String endPointURI = URL.getEndPoint("/rest/auth/1/session");
		response = RESTCalls.POSTRequest(endPointURI, loginPayload);
		
		String strResponse = TestUtils.getResposeString(response);		
		JsonPath jsonRes = TestUtils.jsonParser(strResponse);

		return jsonRes.getString("session.value");
	}

}
