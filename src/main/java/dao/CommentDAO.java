package dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import constant.DBConfig;
import constant.MongoConstant.COLLECTION_COMMENT;
import constant.MongoConstant.MONGO_COMMON;
import entity.Comment;
import util.Util;

public class CommentDAO {
	private static DBCollection collection;

	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(DBConfig.COLLECTION_COMMENT);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static List<Comment> findByPostId(String postId) {
		List<Comment> listComments = new ArrayList<Comment>();
		DBCursor cursor = collection.find(new BasicDBObject(COLLECTION_COMMENT.KEY_POST_ID, postId),
				new BasicDBObject(COLLECTION_COMMENT.KEY_ID, MONGO_COMMON.HIDE_FIELD)
						.append(COLLECTION_COMMENT.KEY_POST_ID, MONGO_COMMON.HIDE_FIELD));

		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Comment c = convertFromDBObject(obj);
			listComments.add(c);
		}
		return listComments;
	}

	public static Comment findById(String id) throws IllegalArgumentException {
		DBObject o = collection.findOne(new BasicDBObject(COLLECTION_COMMENT.KEY_ID, new ObjectId(id)));
		if (o == null) {
			return null;
		}
		Comment c = convertFromDBObject(o);
		return c;
	}

	public static boolean insert(Comment c) {
		BasicDBObject o = new BasicDBObject();
		o.put(COLLECTION_COMMENT.KEY_CONTENT, c.content);
		o.put(COLLECTION_COMMENT.KEY_TIME, Util.formatStringDate(c.createTime));
		o.put(COLLECTION_COMMENT.KEY_POST_ID, c.postId);
		o.put(COLLECTION_COMMENT.KEY_USER_ID, c.userId);
		collection.insert(o);
		return true;
	}
	
	public static Comment insertAndGet(Comment c) {
		if (!PostDAO.isExistPost(c.postId)) {
			return null;
		}
		BasicDBObject o = new BasicDBObject();
		o.put(COLLECTION_COMMENT.KEY_CONTENT, c.content);
		o.put(COLLECTION_COMMENT.KEY_TIME, Util.formatStringDate(c.createTime));
		o.put(COLLECTION_COMMENT.KEY_POST_ID, c.postId);
		o.put(COLLECTION_COMMENT.KEY_USER_ID, c.userId);
		collection.insert(o);
		Comment result = convertFromDBObject(o);
		return result;
	}
	
	public static int edit(String id, String content) {
		BasicDBObject query = new BasicDBObject(COLLECTION_COMMENT.KEY_ID, new ObjectId(id));
		BasicDBObject update = new BasicDBObject(MONGO_COMMON.SET, new BasicDBObject(COLLECTION_COMMENT.KEY_CONTENT, content));
		WriteResult result = collection.update(query, update, false, false);
		return result.getN();
	}
	
	public static int delete(String id) {
		WriteResult result = collection.remove(new BasicDBObject(COLLECTION_COMMENT.KEY_ID, new ObjectId(id)));
		return result.getN();
	}

	public static Comment convertFromDBObject(DBObject dbObject) {
		Comment c = new Comment();
		ObjectId objectId = (ObjectId) dbObject.get(COLLECTION_COMMENT.KEY_ID);
		if (objectId != null) {
			c.id = objectId.toString();
		}
		String postId = Util.getStringFromDBObject(dbObject, COLLECTION_COMMENT.KEY_POST_ID);
		if (postId != null) {
			c.postId = postId;
		}
		c.userId = Util.getStringFromDBObject(dbObject, COLLECTION_COMMENT.KEY_USER_ID);
		c.content = Util.getStringFromDBObject(dbObject, COLLECTION_COMMENT.KEY_CONTENT);
		String timeString = Util.getStringFromDBObject(dbObject, COLLECTION_COMMENT.KEY_TIME);
		try {
			Date date = Util.parseDate(timeString);
			c.createTime = date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

}
