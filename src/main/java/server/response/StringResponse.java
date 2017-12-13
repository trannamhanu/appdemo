package server.response;

import org.json.simple.JSONObject;

import constant.ResponseConstant;

public class StringResponse extends Response {
	public String data;
	
	public StringResponse(int code, String data) {
		super(code);
		this.data = data;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put(ResponseConstant.KeyName.CODE, code);
		if (data != null) {
			object.put(ResponseConstant.KeyName.DATA, data);
		}
		return object;
	}

}
