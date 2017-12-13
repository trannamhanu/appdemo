package server.response;

import org.json.simple.JSONObject;

import constant.ResponseConstant;

public class ErrorResponse extends Response {
	public String error;
	
	public ErrorResponse(int code, String error) {
		super(code);
		this.error = error;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put(ResponseConstant.KeyName.CODE, this.code);
		if (this.error != null) {
			object.put(ResponseConstant.KeyName.DATA, this.error);
		}
		return object;
	}
}
