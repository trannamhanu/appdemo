package dao;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import util.Constant;

public class PostDAO {
private static DBCollection collection;
	
	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(Constant.COLLECTION_POST);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public static String findAll() {
		DBCursor cursor = collection.find();
		StringBuilder result = new StringBuilder();
		
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			return obj.toString();
		}
		
		return result.toString();
	}
}
