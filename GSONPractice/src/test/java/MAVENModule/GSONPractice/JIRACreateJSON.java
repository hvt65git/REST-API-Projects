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
//Printing JIRAItem object:
//Printing Project:
//key = TEST
//Printing Fields object:
//Summary = REST ye merry gentlemen.
//Description = Creating of an issue using project keys and issue type names using the REST API
//Printing IssueType object:
//name = Bug

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
	private final Fields fields;

	public JIRAItem(Fields ff) {
		this.fields = ff;
	}

	public void print() {
		System.out.println("Printing JIRAItem object:");	
		fields.print();	
	}
}

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

	public void print() {
		project.print();	
		System.out.println("Printing Fields object:" +
				"\r\nSummary = " + summary +
				"\r\nDescription = " + description );	
		issuetype.print();	
	}
}

class Project {
	String key;

	public Project(String key) {
		this.key = key;
	}

	public void print() {
		System.out.println("Printing Project:"+"\r\n" +  "key = " + this.key);
	}
}


class IssueType {
	String name;

	public IssueType(String name) {
		this.name = name;
	}

	public void print() {
		System.out.println("Printing IssueType object:"+"\r\n" +  "name = " + this.name);
	}
}

public class JIRACreateJSON {

	@Test(enabled = true)
	public void test01() {

		Fields fields = new Fields(
				new Project("TEST"), 
				new String("REST ye merry gentlemen."),
				"Creating of an issue using project keys and issue type names using the REST API",
				new IssueType("Bug"));

		JIRAItem jitem = new JIRAItem(fields);
		System.out.println("\r\njiraItem dump before serialization => ");
		jitem.print();


		//instantiate a GsonBuilder object and pass java obj to it
		Gson gs = new GsonBuilder()
				.disableHtmlEscaping()
				.serializeNulls()
				.create();

		//convert to Json and print it out
		String stringJson = gs.toJson(jitem);
		System.out.println("stringJson = " + stringJson);

		JIRAItem jiraItem = gs.fromJson(stringJson, JIRAItem.class);
		System.out.println("\r\njiraItem dump after deserialization => ");
		jiraItem.print();
	}

}
