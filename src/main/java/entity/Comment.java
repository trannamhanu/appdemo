package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

import util.Constant;

public class Comment implements JsonEntity {

	private static final String USERID_KEY = "user_id";
	private String userId;

	private static final String CONTENT_KEY = "content";
	private String content;

	private static final String TIME_KEY = "time";
	private Date createTime;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

	public Comment() {

	}

	public Comment(String userId, String content) {
		this.userId = userId;
		this.content = content;
	}

	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		if (this.userId != null) {
			object.put(USERID_KEY, this.userId);
		}
		if (this.content != null) {
			object.put(CONTENT_KEY, this.content);
		}
		if (this.createTime != null) {
			object.put(TIME_KEY, dateFormat.format(createTime));
		}
		return object;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
