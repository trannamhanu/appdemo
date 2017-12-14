package server.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONObject;

import constant.RequestConstant;
import constant.ResponseConstant.ResponseCode;
import dao.CommentDAO;
import dao.PostDAO;
import entity.Comment;
import entity.Post;
import entity.response.PostEntityResponse;
import server.request.Request;
import server.response.ListResponse;
import server.response.Response;

public class FindPostAPI implements APIAdapter {

	public Response process(Request request) {
		ListResponse<PostEntityResponse> response = new ListResponse<PostEntityResponse>(ResponseCode.SUCCESS);
		JSONObject query = request.query;

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
		

		List<Post> listRawPost;
		if (orderKey.equals(RequestConstant.POST_ORDERKEY_TIME)) { //sort by time
			listRawPost = PostDAO.findAll(page.intValue(), size.intValue(), orderKey, orderRule);
		} else {
			listRawPost = PostDAO.findAll(page.intValue(), size.intValue(), null, null);
		}
		
		List<PostEntityResponse> listResponse = new ArrayList<PostEntityResponse>();
		for (Post p : listRawPost) {
			PostEntityResponse po = new PostEntityResponse();
			po.id = p.id;
			po.userId = p.userId;
			po.content = p.content;
			po.createTime = p.createTime;
			List<Comment> listComments = CommentDAO.findByPostId(p.id);
			po.listComments = listComments;
			listResponse.add(po);
		}
		
		if (orderKey.equals(RequestConstant.POST_ORDERKEY_COMMENTS)) { //sort by number of comments
			Collections.sort(listResponse, new PostComparatorByNumberOfComment(orderRule.intValue()));
		}
		
		response.data = listResponse;
		return response;
	}
	
	public class PostComparatorByNumberOfComment implements Comparator<PostEntityResponse> {
		public int orderRule = 1;
		
		public PostComparatorByNumberOfComment(int orderRule) {
			this.orderRule = orderRule;
		}
		
		public int compare(PostEntityResponse p1, PostEntityResponse p2) {
			if (orderRule == RequestConstant.ORDER_RULE_ASC) {
				return p1.listComments.size() - p2.listComments.size();
			} else { //DESC
				return p2.listComments.size() - p1.listComments.size();
			}
			
		}
		
	}

}
