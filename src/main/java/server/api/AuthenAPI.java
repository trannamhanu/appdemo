package server.api;

import org.json.simple.JSONObject;

import dao.UserDAO;
import server.Respond;
import server.TokenManager;
import util.Constant;

public class AuthenAPI implements APIAdapter {

	@SuppressWarnings("unchecked")
	public Respond process(JSONObject json) {
		
		JSONObject query = (JSONObject) json.get(Constant.REQUEST.QUERY_KEY);
		if (query == null) {
			return new Respond(Constant.RESPONSE.CODE_ERROR, null);
		}
		
		String email = (String) query.get(Constant.REQUEST.EMAIL_KEY);
		String pass = (String) query.get(Constant.REQUEST.PASSWORD_KEY);
		if (email == null || pass == null) {
			return new Respond(Constant.RESPONSE.CODE_ERROR, null);
		}
		
		//check user and generate token
		String token = UserDAO.userAuthenticate(email, pass);
		if (token == null) {
			return new Respond(Constant.RESPONSE.CODE_ERROR, null);
		}
		
		TokenManager.put(token);
		Respond respond = new Respond();
		respond.code = Constant.RESPONSE.CODE_SUCCESS;
		respond.data.put(Constant.REQUEST.TOKEN_KEY, token);
		
		return respond;
	}

}
