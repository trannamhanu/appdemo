package dao;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import util.Constant;

public class MongoConnection {

	private static DB demoDB;
	private static MongoClient mongoClient;

	static {
		MongoCredential credential = MongoCredential.createCredential(Constant.MONGO_USER, Constant.MONGO_DB_AUTH,
				Constant.MONGO_PASSWORD.toCharArray());

		mongoClient = new MongoClient(new ServerAddress(Constant.MONGO_HOST, Constant.MONGO_PORT),
				Arrays.asList(credential));
		demoDB = mongoClient.getDB(Constant.DBNAME);

	}

	public static DB getDemoDB() {
		return demoDB;
	}
	
}
