package entity;

import java.util.Date;

import org.json.simple.JSONObject;

import util.Util;

public class Comment implements JsonEntity {

	private static final String USERID_KEY = "user_id";
	public String userId;

	private static final String CONTENT_KEY = "content";
	public String content;

	private static final String TIME_KEY = "time";
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
		if (this.userId != null) {
			object.put(USERID_KEY, this.userId);
		}
		if (this.content != null) {
			object.put(CONTENT_KEY, this.content);
		}
		if (this.createTime != null) {
			object.put(TIME_KEY, Util.formatStringDate(this.createTime));
		}
		return object;
	}


}
