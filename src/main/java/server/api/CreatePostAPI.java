package server.api;

import java.util.Date;

import org.json.simple.JSONObject;

import constant.RequestConstant;
import dao.PostDAO;
import entity.Post;
import server.request.Request;
import server.response.Response;
import server.response.error.BadRequestResponse;
import server.response.error.SuccessResponse;
import server.session.Session;
import server.session.SessionManager;

public class CreatePostAPI implements APIAdapter {

	public Response process(Request request) {
		Response response = null;
		JSONObject query = request.query;
		String token = request.token;

		String content = (String) query.get(RequestConstant.POST_CONTENT_KEY);
		if (content == null) {
			return new BadRequestResponse();
		}

		Post post = new Post();
		post.content = content;
		post.createTime = new Date();
		Session session = SessionManager.get(token);
		post.userId = session.userId;

		PostDAO.insert(post);
		response = new SuccessResponse();

		return response;
	}

}
