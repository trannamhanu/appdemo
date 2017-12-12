package server;

import org.json.simple.JSONObject;

public class Respond {

	public static final String CODE_KEY = "code";
	public int code;

	public static final String DATA_KEY = "data";
	public JSONObject data = new JSONObject();

	public Respond() {
	}

	public Respond(int code) {
		this.code = code;
	}

	public Respond(int code, JSONObject data) {
		this.code = code;
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJsonObject() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(CODE_KEY, code);
		if (data != null) {
			jsonObject.put(DATA_KEY, data);
		}
		return jsonObject;
	}

	@Override
	public String toString() {
		return toJsonObject().toJSONString();
	}
}
