package _interview_practice_jsonpath_with_customer;

import java.util.List;
import com.google.gson.GsonBuilder;
import io.restassured.path.json.JsonPath;
import java.util.Arrays;

//package _interview_practice_jsonpath_with_customer;
//{
//	"test": {
//	"test1": 564,
//	"test2": "der",
//	"list": [
//	         "tes4",
//	         "test5"
//	         ]
//},
//	"exList": [
//	           "tes4",
//	           "test5"
//	           ]
//}
//MY OUTPUT1:
//{
//	"test": {
//	"test1": 564,
//	"test2": "der",
//	"list": [
//	         "tes4",
//	         "test5"
//	         ]
//},
//	"exList": [
//	           "tes4",
//	           "test5"
//	           ]
//}
//
public class MorePractice {

	static class Test {
		private int test1;
		private String test2;
		private List<String> list;

		public Test(int test1, 
				String test2, 
				List<String> list) {
			this.test1 = test1;
			this.test2 = test2;
			this.list = list;
		}
	}
	private Test test;
	private List<String> exList;

	public MorePractice(Test test, 
			List<String> exList ) {
		this.test = test;
		this.exList = exList;
	}

	public static void main(String[] a) {
		//create the Test object
		Test test = 
				new Test(564, "der", Arrays.asList("tes4","test5"));

		MorePractice practice = 
				new MorePractice(test, Arrays.asList("tes4","test5"));

		//(a)serialize practice object into JsonPath string with Gson
		String jsonString = 
				new GsonBuilder().create().toJson(practice);

		System.out.println("sample jsonString = " 
				+ jsonString); //success!

		//(b) dserialize/recover the data
		System.out.println( 
				"\r\nDeserializing the Json:\r\n" + "\r\ntest1 = " + 
						JsonPath.with(jsonString).get("test.test1") +
						", test2 = " + 
						JsonPath.with(jsonString).get("test.test2"));

		List<String> list = 
				JsonPath.with(jsonString).get("test.list");
		for(int i=0; i<list.size(); i++) {
			System.out.println("\r\ntest.list->item #" + i + ": " + list.get(i));
		}

		List<String> exList = JsonPath.with(jsonString).get("exList");
		for(int i=0; i<exList.size(); i++) {
			System.out.println("\r\nexList->item #" + i + ": " + exList.get(i));
		}
	}
}
//OUTPUT2
//Deserializing the Json:
//
//test1 = 564, test2 = der
//
//test.list->item #0: tes4
//
//test.list->item #1: test5
//
//exList->item #0: tes4
//
//exList->item #1: test5

