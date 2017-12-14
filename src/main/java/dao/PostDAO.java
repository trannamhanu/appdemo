package dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import constant.DBConfig;
import constant.MongoConstant.COLLECTION_POST;
import constant.MongoConstant.MONGO_COMMON;
import entity.Post;
import util.Util;

public class PostDAO {
	private static DBCollection collection;

	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(DBConfig.COLLECTION_POST);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static List<Post> findAll(int page, int size, String orderKey, Long orderRule) {
		DBCursor cursor = null;
		if (orderKey == null || orderRule == null) {
			cursor = collection.find().skip((page - 1) * size).limit(size);
		} else {
			cursor = collection.find().skip((page - 1) * size).limit(size).sort(new BasicDBObject(orderKey, orderRule));
		}

		List<Post> listPosts = new ArrayList<Post>();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Post post = new Post();

			ObjectId objectId = (ObjectId) obj.get(COLLECTION_POST.KEY_ID);
			post.id = objectId.toString();
			post.userId = (String) obj.get(COLLECTION_POST.KEY_USER_ID);
			post.content = (String) obj.get(COLLECTION_POST.KEY_CONTENT);
			try {
				post.createTime = Util.parseDate((String) obj.get(COLLECTION_POST.KEY_TIME));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			listPosts.add(post);
		}
		return listPosts;
	}

	public static Post findById(String postId) throws IllegalArgumentException {
		DBObject o = collection.findOne(new BasicDBObject(COLLECTION_POST.KEY_ID, new ObjectId(postId)));
		if (o == null) {
			return null;
		}
		Post p = convertFromDBObject(o);
		return p;
	}
	
	public static boolean isExistPost(String postId) {
		Long count = collection.count(new BasicDBObject(COLLECTION_POST.KEY_ID, new ObjectId(postId)));
		if (count != 1) {return false;}
		return true;
	}

	public static int edit(String id, String content) {
		BasicDBObject query = new BasicDBObject(COLLECTION_POST.KEY_ID, new ObjectId(id));
		BasicDBObject update = new BasicDBObject(MONGO_COMMON.SET, new BasicDBObject(COLLECTION_POST.KEY_CONTENT, content));
		WriteResult result = collection.update(query, update, false, false);
		return result.getN();
	}

	public static boolean insert(Post post) {
		BasicDBObject o = new BasicDBObject();
		o.put(COLLECTION_POST.KEY_USER_ID, post.userId);
		o.put(COLLECTION_POST.KEY_CONTENT, post.content);
		o.put(COLLECTION_POST.KEY_TIME, Util.formatStringDate(post.createTime));
		collection.insert(o);
		return true;
	}
	
	public static Post insertAndGet(Post post) {
		BasicDBObject o = new BasicDBObject();
		o.put(COLLECTION_POST.KEY_USER_ID, post.userId);
		o.put(COLLECTION_POST.KEY_CONTENT, post.content);
		o.put(COLLECTION_POST.KEY_TIME, Util.formatStringDate(post.createTime));
		collection.insert(o);
		Post p = convertFromDBObject(o);
		return p;
	}
	
	public static int delete(String id) {
		WriteResult result = collection.remove(new BasicDBObject(COLLECTION_POST.KEY_ID, new ObjectId(id)));
		return result.getN();
	}

	public static Post convertFromDBObject(DBObject o) {
		Post p = new Post();
		ObjectId objectId = (ObjectId) o.get(COLLECTION_POST.KEY_ID);
		p.id = objectId.toString();
		String userId = Util.getStringFromDBObject(o, COLLECTION_POST.KEY_USER_ID);
		if (userId != null) {
			p.userId = userId;
		}
		String content = Util.getStringFromDBObject(o, COLLECTION_POST.KEY_CONTENT);
		if (content != null) {
			p.content = content;
		}
		String time = Util.getStringFromDBObject(o, COLLECTION_POST.KEY_TIME);
		if (time != null) {
			try {
				p.createTime = Util.parseDate(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
}
