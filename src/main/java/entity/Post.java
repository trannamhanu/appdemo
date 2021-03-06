package entity;

import java.util.Date;

import org.json.simple.JSONObject;

import constant.MongoConstant.COLLECTION_POST;
import util.Util;

public class Post implements JsonEntity {

	public String id;

	public String userId;

	public String content;

	public Date createTime;

	public Post() {

	}

	public Post(String userId, String content, Date createTime) {
		super();
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();

		if (this.id != null) {
			object.put(COLLECTION_POST.KEY_ID, this.id);
		}

		if (this.userId != null) {
			object.put(COLLECTION_POST.KEY_USER_ID, this.userId);
		}
		if (this.content != null) {
			object.put(COLLECTION_POST.KEY_CONTENT, this.content);
		}
		if (this.createTime != null) {
			object.put(COLLECTION_POST.KEY_TIME, Util.formatStringDate(this.createTime));
		}
		return object;
	}

}
