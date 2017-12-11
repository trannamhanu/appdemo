package dao;

import com.mongodb.DBCollection;

import util.Constant;

public class CommentDAO {
	private static DBCollection collection;

	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(Constant.COLLECTION_COMMENT);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
