package util;

public class Constant {
	public static final String DATE_FORMAT = "yyy-MM-dd HH:mm:ss";
	public static final int SESSION_TIMEOUT = 5;
	
	public class DB_CONFIG {
		public static final String MONGO_HOST = "localhost";
		public static final int MONGO_PORT = 27017;
		public static final String MONGO_USER = "admin";
		public static final String MONGO_PASSWORD = "admin";
		public static final String MONGO_DB_AUTH = "admin";
		public static final String DBNAME = "app_demo_db";
		
		public static final String COLLECTION_USER = "user";
		public static final String COLLECTION_POST = "post";
		
	}
	
	public class RESPONSE {
		public static final String CODE_KEY = "code";
		public static final String DATA_KEY = "data";
		
		public static final int CODE_SUCCESS = 0;
		public static final int CODE_ERROR = 1;
		public static final String POSTS_KEY = "posts";
	}
	
	public class API_NAME {
		public static final String AUTHEN_USER = "authen";
		public static final String FIND_POST = "find_post";
	}
	
	public class REQUEST {
		public static final String EMAIL_KEY = "email";
		public static final String PASSWORD_KEY = "password";
		public static final String QUERY_KEY = "query";
		
		public static final String API_KEY = "api";
		public static final String TOKEN_KEY = "token";
		
		public static final String POST_PAGE_KEY = "page";
		public static final String POST_SIZE_KEY = "size";
		public static final String POST_ORDERBY_KEY = "order_by";
		public static final String POST_ORDERKEY_KEY = "order_key";
		public static final String POST_ORDERRULE_KEY = "order_rule";
	}
	
}
