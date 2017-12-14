package server.api;

import java.util.UUID;

import org.json.simple.JSONObject;

import constant.CommonConstant;
import constant.RequestConstant;
import constant.ResponseConstant.ResponseCode;
import dao.UserDAO;
import server.request.Request;
import server.response.Response;
import server.response.StringResponse;
import server.response.error.AuthenFailedResponse;
import server.response.error.BadRequestResponse;
import server.session.Session;
import server.session.SessionManager;

public class AuthenAPI implements APIAdapter {

	public Response process(Request request) {

		JSONObject query = request.query;

		String email = (String) query.get(RequestConstant.EMAIL_KEY);
		String pass = (String) query.get(RequestConstant.PASSWORD_KEY);
		if (email == null || pass == null) {
			return new BadRequestResponse();
		}

		// check user and generate token
		String userId = UserDAO.userAuthenticate(email, pass);
		String token = null;
		if (userId == null) {
			return new AuthenFailedResponse();
		} else {
			token = UUID.randomUUID().toString();
			Session session = new Session(token, userId, System.currentTimeMillis() + CommonConstant.SESSION_TIMEOUT);
			SessionManager.put(session);
		}

		StringResponse response = new StringResponse(ResponseCode.SUCCESS, token);

		return response;
	}

}
