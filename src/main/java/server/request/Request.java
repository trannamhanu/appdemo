package server.request;

import org.json.simple.JSONObject;

public class Request {
	public JSONObject query;
	public String api;
	public String token;

	public Request() {
	}

	public Request(String api, String token, JSONObject query) {
		super();
		this.query = query;
		this.api = api;
		this.token = token;
	}

}
