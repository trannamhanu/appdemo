package server.api;

import java.util.UUID;

import org.json.simple.JSONObject;

import constant.CommonConstant;
import constant.RequestConstant;
import constant.ResponseConstant;
import dao.UserDAO;
import server.response.ErrorResponse;
import server.response.Response;
import server.response.StringResponse;
import server.session.Session;
import server.session.SessionManager;

public class AuthenAPI implements APIAdapter {

	public Response process(JSONObject json) {
		
		JSONObject query = (JSONObject) json.get(RequestConstant.QUERY_KEY);
		if (query == null) {
			return new ErrorResponse(ResponseConstant.ResponseCode.BAD_REQUEST_ERROR,
					ResponseConstant.ResponseCode.BAD_REQUEST_ERROR_DETAIL);
		}
		
		String email = (String) query.get(RequestConstant.EMAIL_KEY);
		String pass = (String) query.get(RequestConstant.PASSWORD_KEY);
		if (email == null || pass == null) {
			return new ErrorResponse(ResponseConstant.ResponseCode.BAD_REQUEST_ERROR,
					ResponseConstant.ResponseCode.BAD_REQUEST_ERROR_DETAIL);
		}
		
		//check user and generate token
		String userId = UserDAO.userAuthenticate(email, pass);
		String token = null;
		if (userId == null) {
			return new ErrorResponse(ResponseConstant.ResponseCode.AUTHEN_ERROR,
					ResponseConstant.ResponseCode.AUTHEN_ERROR_DETAIL);
		} else {
			token = UUID.randomUUID().toString();
			Session session = new Session(token, userId, System.currentTimeMillis() + CommonConstant.SESSION_TIMEOUT);
			SessionManager.put(session);
		}
		
		StringResponse response = new StringResponse(ResponseConstant.ResponseCode.SUCCESS, token);
		
		return response;
	}

}
