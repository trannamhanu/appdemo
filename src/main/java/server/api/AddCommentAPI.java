package server.api;

import java.util.Date;

import org.json.simple.JSONObject;

import constant.MongoConstant;
import dao.CommentDAO;
import entity.Comment;
import server.request.Request;
import server.response.Response;
import server.response.error.BadRequestResponse;
import server.response.error.SuccessResponse;
import server.session.Session;
import server.session.SessionManager;

public class AddCommentAPI implements APIAdapter {

	public Response process(Request request) {
		Response response = null;
		JSONObject query = request.query;
		String token = request.token;

		String postId = (String) query.get(MongoConstant.COLLECTION_COMMENT.KEY_POST_ID);
		String content = (String) query.get(MongoConstant.COLLECTION_COMMENT.KEY_CONTENT);

		if (postId == null || content == null) {
			return new BadRequestResponse();
		}

		Comment c = new Comment();
		c.postId = postId;
		c.content = content;
		c.createTime = new Date();
		Session session = SessionManager.get(token);
		c.userId = session.userId;

		CommentDAO.insert(c);
		response = new SuccessResponse();

		return response;
	}

}
