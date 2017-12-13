package server.api;

import org.json.simple.JSONObject;

import server.response.Response;

public interface APIAdapter {
	public Response process(JSONObject json);
}
