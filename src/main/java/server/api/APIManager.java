package server.api;

import java.util.HashMap;
import java.util.Map;

import constant.Api;

public class APIManager {
	public static final Map<String, APIAdapter> APIS = new HashMap<String, APIAdapter>();
	
	static {
		APIS.put(Api.AUTHEN_USER, new AuthenAPI());
		APIS.put(Api.FIND_POST, new FindPostAPI());
		APIS.put(Api.CREATE_POST, new CreatePostAPI());
		APIS.put(Api.EDIT_POST, new EditPostAPI());
		APIS.put(Api.DELETE_POST, new DeletePostAPI());
		APIS.put(Api.ADD_COMMENT, new AddCommentAPI());
		APIS.put(Api.EDIT_COMMENT, new EditCommentAPI());
		APIS.put(Api.DELETE_COMMENT, new DeleteCommentAPI());
		
	}
	
	public static APIAdapter getAPI(String apiName) {
		return APIS.get(apiName);
	}
}
