package dao;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import constant.DBConfig;

@SuppressWarnings("deprecation")
public class MongoConnection {

	private static DB demoDB;
	private static MongoClient mongoClient;

	static {
		MongoCredential credential = MongoCredential.createCredential(DBConfig.MONGO_USER, DBConfig.MONGO_DB_AUTH,
				DBConfig.MONGO_PASSWORD.toCharArray());

		mongoClient = new MongoClient(new ServerAddress(DBConfig.MONGO_HOST, DBConfig.MONGO_PORT),
				Arrays.asList(credential));
		demoDB = mongoClient.getDB(DBConfig.DBNAME);

	}

	public static DB getDemoDB() {
		return demoDB;
	}
	
}
