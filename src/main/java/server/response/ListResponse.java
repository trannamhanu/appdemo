package server.response;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import constant.ResponseConstant;
import entity.JsonEntity;

public class ListResponse<T> extends Response{

	public List<T> data;
	
	public ListResponse(int code) {
		super(code);
	}
	
	public ListResponse(int code, List<T> data) {
		super(code);
		this.data = data;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		object.put(ResponseConstant.KeyName.CODE, ResponseConstant.ResponseCode.SUCCESS);
		if (this.data != null) {
			JSONArray array = new JSONArray();
			for (T t : this.data) {
				array.add(((JsonEntity) t).toJsonObject());
			}
			object.put(ResponseConstant.KeyName.DATA, array);
		}

		return object;
	}
	
}
