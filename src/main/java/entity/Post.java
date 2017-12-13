package entity;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import constant.MongoConstant;
import util.Util;

public class Post implements JsonEntity {
	
	public String id;

	public String userId;

	public String content;

	public Date createTime;

	public List<Comment> listComment;

	public Post() {

	}

	public Post(String userId, String content, Date createTime, List<Comment> listComment) {
		super();
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
		this.listComment = listComment;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();

		if (this.id != null) {
			object.put(MongoConstant.COLLECTION_POST.KEY_ID, this.id);
		}
		
		if (this.userId != null) {
			object.put(MongoConstant.COLLECTION_POST.KEY_USER_ID, this.userId);
		}
		if (this.content != null) {
			object.put(MongoConstant.COLLECTION_POST.KEY_CONTENT, this.content);
		}
		if (this.createTime != null) {
			object.put(MongoConstant.COLLECTION_POST.KEY_TIME, Util.formatStringDate(this.createTime));
		}
		if (this.listComment != null && this.listComment.size() > 0) {
			JSONArray commentArray = new JSONArray();
			for (Comment c : listComment) {
				commentArray.add(c.toJsonObject());
			}
			object.put(MongoConstant.COLLECTION_POST.KEY_COMMENTS, commentArray);
		}
		return object;
	}

}
