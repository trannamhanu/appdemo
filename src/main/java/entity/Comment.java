package entity;

import java.util.Date;

import org.json.simple.JSONObject;

import constant.MongoConstant.COLLECTION_COMMENT;
import util.Util;

public class Comment implements JsonEntity {
	public String id;

	public String postId;

	public String userId;

	public String content;

	public Date createTime;

	public Comment() {

	}

	public Comment(String userId, String content) {
		this.userId = userId;
		this.content = content;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		if (this.id != null) {
			object.put(COLLECTION_COMMENT.KEY_ID, this.id);
		}
		if (this.postId != null) {
			object.put(COLLECTION_COMMENT.KEY_POST_ID, this.postId);
		}
		if (this.userId != null) {
			object.put(COLLECTION_COMMENT.KEY_USER_ID, this.userId);
		}
		if (this.content != null) {
			object.put(COLLECTION_COMMENT.KEY_CONTENT, this.content);
		}
		if (this.createTime != null) {
			object.put(COLLECTION_COMMENT.KEY_TIME, Util.formatStringDate(this.createTime));
		}
		return object;
	}

}
