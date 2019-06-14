package ht_twit_restapi;

public class Count {
	private final String keyname = "count";
	private int value;

	public Count (int value) {
		this.value = value;
	}
	public String getKeyname() {
		return this.keyname;
	}
	public int getValue() {
		return value;
	}
}