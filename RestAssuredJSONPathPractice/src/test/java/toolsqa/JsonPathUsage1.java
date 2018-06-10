package toolsqa;

import java.net.MalformedURLException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonPathUsage1 {
	
    @Test
    public void jsonPathUsage1() throws MalformedURLException
    {
    	RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
    	RequestSpecification httpRequest = RestAssured.given();
    	Response response = httpRequest.get("");

    	// First get the JsonPath object instance from the Response interface
    	JsonPath jsonPathEvaluator = response.jsonPath();

    	// Read all the books as a List of String. Each item in the list
    	// represent a book node in the REST service Response
    	List<Book> allBooks = jsonPathEvaluator.getList("books", Book.class);

    	// Iterate over the list and print individual book item
    	// Note that every book entry in the list will be complete Json object of book
    	for(Book book : allBooks) {
    		System.out.println("Book: " + book.title);
    	}
    }

}

//FAIL
//[RemoteTestNG] detected TestNG version 6.14.2
//FAILED: jsonPathUsage1
//com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "website" (class toolsqa.Book), not marked as ignorable (0 known properties: ])
// at [Source: java.io.StringReader@1c4ee95c; line: 1, column: 13] (through reference chain: toolsqa.Book["website"])
//	at com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException.from(UnrecognizedPropertyException.java:79)
//	at com.fasterxml.jackson.databind.DeserializationContext.reportUnknownProperty(DeserializationContext.java:555)
//	at com.fasterxml.jackson.databind.deser.std.StdDeserializer.handleUnknownProperty(StdDeserializer.java:708)
//	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.handleUnknownProperty(BeanDeserializerBase.java:1160)
//	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:315)
//	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:121)
//	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:2888)
//	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:2048)
//	at com.fasterxml.jackson.databind.ObjectMapper$readValue$0.call(Unknown Source)
//	at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)
//	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)
//	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:133)
//	at io.restassured.internal.path.json.mapping.JsonPathJackson2ObjectDeserializer.deserialize(JsonPathJackson2ObjectDeserializer.groovy:47)
//	at io.restassured.path.json.mapping.JsonPathObjectDeserializer$deserialize.call(Unknown Source)
//	at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:48)
//	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:113)
//	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:125)
//	at io.restassured.internal.path.json.mapping.JsonObjectDeserializer.deserializeWithJackson2(JsonObjectDeserializer.groovy:98)
//	at io.restassured.internal.path.json.mapping.JsonObjectDeserializer$deserializeWithJackson2.callStatic(Unknown Source)
//	at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallStatic(CallSiteArray.java:56)
//	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callStatic(AbstractCallSite.java:194)
//	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callStatic(AbstractCallSite.java:214)
//	at io.restassured.internal.path.json.mapping.JsonObjectDeserializer.deserialize(JsonObjectDeserializer.groovy:67)
//	at io.restassured.path.json.JsonPath.jsonStringToObject(JsonPath.java:1016)
//	at io.restassured.path.json.JsonPath.getList(JsonPath.java:398)
//	at toolsqa.JsonPathUsage1.jsonPathUsage1(JsonPathUsage1.java:27)
//	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
//	at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
//	at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
//	at java.lang.reflect.Method.invoke(Unknown Source)
//	at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:124)
//	at org.testng.internal.Invoker.invokeMethod(Invoker.java:580)
//	at org.testng.internal.Invoker.invokeTestMethod(Invoker.java:716)
//	at org.testng.internal.Invoker.invokeTestMethods(Invoker.java:988)
//	at org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:125)
//	at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:109)
//	at org.testng.TestRunner.privateRun(TestRunner.java:648)
//	at org.testng.TestRunner.run(TestRunner.java:505)
//	at org.testng.SuiteRunner.runTest(SuiteRunner.java:455)
//	at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:450)
//	at org.testng.SuiteRunner.privateRun(SuiteRunner.java:415)
//	at org.testng.SuiteRunner.run(SuiteRunner.java:364)
//	at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
//	at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:84)
//	at org.testng.TestNG.runSuitesSequentially(TestNG.java:1208)
//	at org.testng.TestNG.runSuitesLocally(TestNG.java:1137)
//	at org.testng.TestNG.runSuites(TestNG.java:1049)
//	at org.testng.TestNG.run(TestNG.java:1017)
//	at org.testng.remote.AbstractRemoteTestNG.run(AbstractRemoteTestNG.java:114)
//	at org.testng.remote.RemoteTestNG.initAndRun(RemoteTestNG.java:251)
//	at org.testng.remote.RemoteTestNG.main(RemoteTestNG.java:77)
//
//
//===============================================
//    Default test
//    Tests run: 1, Failures: 1, Skips: 0
//===============================================
//
//
//===============================================
//Default suite
//Total tests run: 1, Failures: 1, Skips: 0
//===============================================
