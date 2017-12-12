package dao;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import util.Constant;

@SuppressWarnings("deprecation")
public class MongoConnection {

	private static DB demoDB;
	private static MongoClient mongoClient;

	static {
		MongoCredential credential = MongoCredential.createCredential(Constant.DB_CONFIG.MONGO_USER, Constant.DB_CONFIG.MONGO_DB_AUTH,
				Constant.DB_CONFIG.MONGO_PASSWORD.toCharArray());

		mongoClient = new MongoClient(new ServerAddress(Constant.DB_CONFIG.MONGO_HOST, Constant.DB_CONFIG.MONGO_PORT),
				Arrays.asList(credential));
		demoDB = mongoClient.getDB(Constant.DB_CONFIG.DBNAME);

	}

	public static DB getDemoDB() {
		return demoDB;
	}
	
}
