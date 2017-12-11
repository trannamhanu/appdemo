package dao;

import com.mongodb.DBCollection;

import util.Constant;

public class UserDAO {

	private static DBCollection collection;
	
	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(Constant.COLLECTION_USER);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
}
