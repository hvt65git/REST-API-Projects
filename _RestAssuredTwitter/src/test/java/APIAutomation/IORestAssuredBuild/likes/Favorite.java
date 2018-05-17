package APIAutomation.IORestAssuredBuild.likes;

public class Favorite {
	String number;
	String text;
	String location;
	String screenName;

	public String getNumber() {
		return number;
	}

	public String getText() {
		return text;
	}

	public String getLocation() {
		return location;
	}

	public String getScreenName() {
		return screenName;
	}

	public Favorite(String number, String text, String location, String screenName) {
		super();
		this.number = number;
		this.text = text;
		this.location = location;
		this.screenName = screenName;
	}
}
