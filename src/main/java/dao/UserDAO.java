package dao;

import java.util.UUID;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

import util.Constant;

public class UserDAO {

	private static DBCollection collection;
	
	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(Constant.DB_CONFIG.COLLECTION_USER);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public static String userAuthenticate(String email, String password) {
		Long count = collection.count(new BasicDBObject("email", email).append("password", password));
		String token = null;
		if (count == 1) {
			token = UUID.randomUUID().toString();
		}
		return token;
	}
	
}
