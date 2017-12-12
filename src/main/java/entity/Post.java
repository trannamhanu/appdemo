package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import util.Constant;

public class Post implements JsonEntity {

	private static final String USERID_KEY = "user_id";
	private String userId;

	private static final String CONTENT_KEY = "content";
	private String content;

	private static final String TIME_KEY = "time";
	private Date createTime;

	private static final String COMMENTS_KEY = "comments";
	private List<Comment> listComment;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

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

		if (this.userId != null) {
			object.put(USERID_KEY, this.userId);
		}
		if (this.content != null) {
			object.put(CONTENT_KEY, this.content);
		}
		if (this.createTime != null) {
			object.put(TIME_KEY, dateFormat.format(createTime));
		}
		if (this.listComment != null && this.listComment.size() > 0) {
			JSONArray commentArray = new JSONArray();
			for (Comment c : listComment) {
				commentArray.add(c.toJsonObject());
			}
			object.put(COMMENTS_KEY, commentArray);
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}

}
