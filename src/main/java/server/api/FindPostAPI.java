package server.api;

import org.json.simple.JSONObject;

import dao.PostDAO;
import server.Respond;
import server.TokenManager;
import util.Constant;

public class FindPostAPI implements APIAdapter {

	@SuppressWarnings("unchecked")
	public Respond process(JSONObject json) {
		Respond respond = null;

		String token = (String) json.get(Constant.REQUEST.TOKEN_KEY);
		if (token == null || !TokenManager.validToken(token)) {
			respond = new Respond(Constant.RESPONSE.CODE_ERROR, null);
		} else {
			respond = new Respond(Constant.RESPONSE.CODE_SUCCESS);
			JSONObject query = (JSONObject) json.get(Constant.REQUEST.QUERY_KEY);

			if (query == null) {
				respond.data.put(Constant.RESPONSE.POSTS_KEY, PostDAO.findAll(0, 0, null, null));
				return respond;
			}

			Long page = (Long) query.get(Constant.REQUEST.POST_PAGE_KEY);
			if (page == null) {
				page = 0L;
			}
			
			Long size = (Long) query.get(Constant.REQUEST.POST_SIZE_KEY);
			if (size == null) {
				size = 0L;
			}
			
			JSONObject order = (JSONObject) query.get(Constant.REQUEST.POST_ORDERBY_KEY);
			String orderKey = null;
			Long orderRule = null;
			if (order != null) {
				orderKey = (String) order.get(Constant.REQUEST.POST_ORDERKEY_KEY);
				orderRule = (Long) order.get(Constant.REQUEST.POST_ORDERRULE_KEY);
			}
			
			respond.data.put(Constant.RESPONSE.POSTS_KEY, PostDAO.findAll(page.intValue(), size.intValue(), orderKey, orderRule));

			
		}

		return respond;
	}

}
