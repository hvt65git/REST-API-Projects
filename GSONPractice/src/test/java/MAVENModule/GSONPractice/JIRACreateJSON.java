package MAVENModule.GSONPractice;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

enum JIRAType {
	BUG,
	TASK;

	private String display;

	public void display() {
		System.out.println(this.display);
	}

	private JIRAType() {}

	private JIRAType(String display) {
		this.display = display;	
	}
}

/*
 * Jira Task Json format:
 * {
    "fields": {
       "project": { 
          "key": "TEST"
       },
       "summary": "REST ye merry gentlemen.",
       "description": "Creating of an issue using project keys and issue type names using the REST API",
       "issuetype": {
          "name": "Bug"
       }
   }
}
 */
class JIRAItem {
	Fields fields;

	public JIRAItem(Fields fields) {
		this.fields = fields;
	}
}


//"fields": {
//    "project": { 
//       "key": "TEST"
//    },
//    "summary": "REST ye merry gentlemen.",
//    "description": "Creating of an issue using project keys and issue type names using the REST API",
//    "issuetype": {
//       "name": "Bug"
//    }
//}

class Fields {
	Project project;
	String summary;
	String description;
	IssueType issuetype;

	public Fields(Project project, 
			String summary, 
			String description, 
			IssueType issuetype) {

		this.project = project;
		this.summary = summary;
		this.description = description;
		this.issuetype = issuetype;
	}
}

class Project {
	String key;

	public Project(String key) {
		this.key = key;
	}
}


class IssueType {
	String name;
	public IssueType(String name) {

	}
}

public class JIRACreateJSON {

	/*
	 * Jira Task Json format:
	 * {
	    "fields": {
	       "project": { 
	          "key": "TEST"
	       },
	       "summary": "REST ye merry gentlemen.",
	       "description": "Creating of an issue using project keys and issue type names using the REST API",
	       "issuetype": {
	          "name": "Bug"
	       }
	   }
	}
	 */

	@Test(enabled = true)
	public void test01() {
		//create the test data as a java object

		//(1) create obj:
		//		 "issuetype": {
		//			"name": "Bug"
		//		 }
		//			
		IssueType issuetype = new IssueType("Bug");

		//(2)
		//	       "project": { 
		//	          "key": "TEST"
		//	       },
		Project project = new Project("TEST");

		//(3)
		//	    "fields": {
		//		       "project": { 
		//		          "key": "TEST"
		//		       },
		//		       "summary": "REST ye merry gentlemen.",
		//		       "description": "Creating of an issue using project keys and issue type names using the REST API",
		//		       "issuetype": {
		//		          "name": "Bug"
		//		       }
		//		   }
		Fields fields = new Fields(project, 
				"REST ye merry gentlemen.",
				"Creating of an issue using project keys and issue type names using the REST API",
				issuetype);

		//(4) JIRAItem
		JIRAItem jitem = new JIRAItem(fields);

		//(5)
		//instantiate a GsonBuilder object and pass java obj to it
		//use GsonBuilder to create a Gson object 
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		String stringJson = gs.toJson(jitem);

		System.out.println("stringJson = " + stringJson);


	}

}
