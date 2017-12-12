package server.api;

import org.json.simple.JSONObject;

import server.Respond;

public interface APIAdapter {
	public Respond process(JSONObject json);
}
