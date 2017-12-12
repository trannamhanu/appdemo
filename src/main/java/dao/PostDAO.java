package dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import util.Constant;

public class PostDAO {
	private static DBCollection collection;

	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(Constant.DB_CONFIG.COLLECTION_POST);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static List<DBObject> findAll(int page, int size, String orderKey, Long orderRule) {
		DBCursor cursor = null;
		if (orderKey == null || orderRule == null) {
			cursor = collection.find(new BasicDBObject(), new BasicDBObject("_id", 0)).skip((page - 1) * size)
					.limit(size);
		} else {
			cursor = collection.find(new BasicDBObject(), new BasicDBObject("_id", 0)).skip((page - 1) * size)
					.limit(size).sort(new BasicDBObject(orderKey, orderRule));
		}

		if (cursor.hasNext())
			return cursor.toArray();
		return new ArrayList<DBObject>();
	}
}
