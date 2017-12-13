package dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import constant.DBConfig;
import constant.MongoConstant;
import entity.Comment;
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

	@SuppressWarnings("unchecked")
	public static List<Post> findAll(int page, int size, String orderKey, Long orderRule) {
		DBCursor cursor = null;
		if (orderKey == null || orderRule == null) {
			cursor = collection.find().skip((page - 1) * size)
					.limit(size);
		} else {
			cursor = collection.find().skip((page - 1) * size)
					.limit(size).sort(new BasicDBObject(orderKey, orderRule));
		}

		List<Post> listPosts = new ArrayList<Post>();
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			Post post = new Post();
			
			ObjectId objectId = (ObjectId) obj.get(MongoConstant.COLLECTION_POST.KEY_ID);
			post.id = objectId.toString();
			post.userId = (String) obj.get(MongoConstant.COLLECTION_POST.KEY_USER_ID);
			post.content = (String) obj.get(MongoConstant.COLLECTION_POST.KEY_CONTENT);
			try {
				post.createTime = Util.parseDate((String) obj.get(MongoConstant.COLLECTION_POST.KEY_TIME));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			ArrayList<DBObject> comments = (ArrayList<DBObject>) obj.get(MongoConstant.COLLECTION_POST.KEY_COMMENTS);
			List<Comment> listComments = new ArrayList<Comment>();
			for (DBObject objComment : comments) {
				Comment c  = new Comment();
				c.userId = (String) objComment.get(MongoConstant.COLLECTION_POST.KEY_USER_ID);
				c.content = (String) objComment.get(MongoConstant.COLLECTION_POST.KEY_CONTENT);
				try {
					c.createTime = Util.parseDate((String) objComment.get(MongoConstant.COLLECTION_POST.KEY_TIME));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				listComments.add(c);
			}
			post.listComment = listComments;
			listPosts.add(post);
		}
		return listPosts;
	}
	
	public static void main(String[] args) {
		PostDAO.findAll(0, 0, null, null);
	}
}
