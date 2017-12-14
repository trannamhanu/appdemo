package server.api;

import org.json.simple.JSONObject;

import constant.MongoConstant.COLLECTION_COMMENT;
import dao.CommentDAO;
import entity.Comment;
import server.request.Request;
import server.response.Response;
import server.response.error.BadRequestResponse;
import server.response.error.SuccessResponse;
import server.response.error.UnAuthorizedResponse;
import server.response.error.UnknownErrorResponse;
import server.session.Session;
import server.session.SessionManager;

public class EditCommentAPI implements APIAdapter {

	public Response process(Request request) {
		Response response = null;
		JSONObject query = request.query;
		String token = request.token;
		Session session = SessionManager.get(token);
		String userId = session.userId;

		String id = (String) query.get(COLLECTION_COMMENT.KEY_ID);
		String content = (String) query.get(COLLECTION_COMMENT.KEY_CONTENT);

		if (id == null || content == null) {
			return new BadRequestResponse();
		}

		Comment c = null;
		try {
			c = CommentDAO.findById(id);
		} catch (IllegalArgumentException e) { // invalid hexadecimal representation of an ObjectId
			e.printStackTrace();
			return new UnknownErrorResponse();
		}
		if (c == null) {
			return new UnknownErrorResponse();
		}

		if (!userId.equals(c.userId)) {
			return new UnAuthorizedResponse();
		}

		int updated = CommentDAO.edit(id, content);
		if (updated == 1) {
			response = new SuccessResponse();
		} else {
			response = new UnknownErrorResponse();
		}

		return response;
	}

}
