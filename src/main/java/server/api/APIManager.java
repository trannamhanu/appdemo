package server.api;

import java.util.HashMap;
import java.util.Map;

import util.Constant;

public class APIManager {
	public static final Map<String, APIAdapter> apis = new HashMap<String, APIAdapter>();
	
	static {
		apis.put(Constant.API_NAME.AUTHEN_USER, new AuthenAPI());
		apis.put(Constant.API_NAME.FIND_POST, new FindPostAPI());
	}
	
	public static APIAdapter getAPI(String apiName) {
		return apis.get(apiName);
	}
}
