package entity.response;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import constant.MongoConstant.COLLECTION_POST;
import entity.Comment;
import entity.JsonEntity;
import util.Util;

public class PostEntityResponse implements JsonEntity {
	public String id;

	public String userId;

	public String content;

	public Date createTime;

	public List<Comment> listComments;

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
		if (listComments != null) {
			JSONArray array = new JSONArray();
			for (Comment c : listComments) {
				JSONObject o = c.toJsonObject();
				array.add(o);
			}
			object.put(COLLECTION_POST.KEY_COMMENTS, array);
		}
		return object;
	}

}
