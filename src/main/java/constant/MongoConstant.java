package constant;

public class MongoConstant {
	
	public class MONGO_COMMON {
		public static final int HIDE_FIELD = 0;
		public static final int SHOW_FIELD = 0;
		
		public static final String SET = "$set";
	}
	
	public class COLLECTION_USER {
		public static final String KEY_ID = "_id";
		public static final String KEY_EMAIL = "email";
		public static final String KEY_PASSWORD = "password";
		public static final String KEY_NAME = "name";
	}
	
	public class COLLECTION_POST {
		public static final String KEY_ID = "_id";
		public static final String KEY_USER_ID = "user_id";
		public static final String KEY_CONTENT = "content";
		public static final String KEY_TIME = "time";
		public static final String KEY_COMMENTS = "comments";
		
	}
	
	public class COLLECTION_COMMENT {
		public static final String KEY_ID = "_id";
		public static final String KEY_USER_ID = "user_id";
		public static final String KEY_CONTENT = "content";
		public static final String KEY_TIME = "time";
		public static final String KEY_POST_ID = "post_id";
		
	}
	
}
