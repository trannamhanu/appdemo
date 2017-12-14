package server.api;

import org.json.simple.JSONObject;

import constant.MongoConstant.COLLECTION_POST;
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

public class DeleteCommentAPI implements APIAdapter{

	public Response process(Request request) {
		Response response = null;
		JSONObject query = request.query;
		String token = request.token;
		Session session = SessionManager.get(token);
		String userId = session.userId;

		String id = (String) query.get(COLLECTION_POST.KEY_ID);

		if (id == null) {
			return new BadRequestResponse();
		}
		Comment c = null;
		try {
			c = CommentDAO.findById(id);
		} catch (IllegalArgumentException e) { //invalid hexadecimal representation of an ObjectId
			e.printStackTrace();
			return new UnknownErrorResponse();
		}
		if (c == null) {
			return new UnknownErrorResponse();
		}

		if (!userId.equals(c.userId)) {
			return new UnAuthorizedResponse();
		}

		int deleted = CommentDAO.delete(id);
		if (deleted == 1) {
			response = new SuccessResponse();
		} else {
			response = new UnknownErrorResponse();
		}

		return response;
	}

}
