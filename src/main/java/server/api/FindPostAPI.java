package server.api;

import org.json.simple.JSONObject;

import constant.RequestConstant;
import constant.ResponseConstant;
import dao.PostDAO;
import entity.Post;
import server.response.ErrorResponse;
import server.response.ListResponse;
import server.response.Response;
import server.session.SessionManager;

public class FindPostAPI implements APIAdapter {

	public Response process(JSONObject json) {
		Response response = null;

		String token = (String) json.get(RequestConstant.TOKEN_KEY);
		if (token == null || !SessionManager.validToken(token)) {
			response = new ErrorResponse(ResponseConstant.ResponseCode.AUTHEN_ERROR,
					ResponseConstant.ResponseCode.AUTHEN_ERROR_DETAIL);
		} else {
			JSONObject query = (JSONObject) json.get(RequestConstant.QUERY_KEY);

			if (query == null) {
				response = new ErrorResponse(ResponseConstant.ResponseCode.BAD_REQUEST_ERROR,
						ResponseConstant.ResponseCode.BAD_REQUEST_ERROR_DETAIL);
				return response;
			}

			Long page = (Long) query.get(RequestConstant.POST_PAGE_KEY);
			if (page == null) {
				page = 0L;
			}

			Long size = (Long) query.get(RequestConstant.POST_SIZE_KEY);
			if (size == null) {
				size = 0L;
			}

			JSONObject order = (JSONObject) query.get(RequestConstant.POST_ORDERBY_KEY);
			String orderKey = null;
			Long orderRule = null;
			if (order != null) {
				orderKey = (String) order.get(RequestConstant.POST_ORDERKEY_KEY);
				orderRule = (Long) order.get(RequestConstant.POST_ORDERRULE_KEY);
			}

			response = new ListResponse<Post>(ResponseConstant.ResponseCode.SUCCESS,
					PostDAO.findAll(page.intValue(), size.intValue(), orderKey, orderRule));

		}

		return response;
	}

}
