package APIAutomation.SampleRestAssured;

public enum StatusCode {	
	OK(200),
	CREATED(201),
	ACCEPTED(202),
	BADREQUEST(400),
	NOTFOUND(404); 
	
	private int statusCode;
	public int getValue() {
		return statusCode;
	}
	private StatusCode(int code) {
		this.statusCode = code;
	}
	
}


