package com.jira;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.jira.api.framework.JIRACreateIssue.jiraCreateIssue;

public class JIRACreateIssueSmokeTest {

	private void callJIRACreateIssue(String testNumber) {
		try {			
			jiraCreateIssue(testNumber);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(dataProvider = "dataProvider")
	public void createIssue(String testNumber) {
		callJIRACreateIssue(testNumber);
	}

	@DataProvider(parallel = true)
	public String[][] dataProvider() {
		return new String[][] {
			{"...first test..."},
			{"...second test..."},
			{"...third test..."}
		};
	}

}
