package server.response;

import org.json.simple.JSONObject;

import constant.ResponseConstant;
import entity.JsonEntity;

public class ObjectResponse<T> extends Response {

	public T data;

	public ObjectResponse(int code, T data) {
		super(code);
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put(ResponseConstant.KeyName.CODE, ResponseConstant.ResponseCode.SUCCESS);
		if (this.data != null) {
			object.put(ResponseConstant.KeyName.DATA, ((JsonEntity) data).toJsonObject());
		}
		return object;
	}
}
