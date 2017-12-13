package dao;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import constant.DBConfig;
import constant.MongoConstant;

public class UserDAO {

	private static DBCollection collection;
	
	static {
		try {
			collection = MongoConnection.getDemoDB().getCollection(DBConfig.COLLECTION_USER);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public static String userAuthenticate(String email, String password) {
		String userId = null;
		
		DBObject object = (DBObject) collection.findOne(new BasicDBObject("email", email).append("password", password));
		if (object != null) {
			ObjectId objectId = (ObjectId) object.get(MongoConstant.COLLECTION_USER.KEY_ID);
			userId = objectId.toString();
		}
		
		return userId;
	}
	
	public static void main(String[] args) {
		UserDAO.userAuthenticate("namtv@ntq-solution.com", "123456");
	}
	
}
