package server.response;

import org.json.simple.JSONObject;

public abstract class Response {

	public int code;

	public Response() {
	}

	public Response(int code) {
		this.code = code;
	}

	public abstract JSONObject toJsonObject();

	public String toString() {
		return toJsonObject().toJSONString();
	}
}
