package constant;

public class ResponseConstant {
	
	public class KeyName {
		public static final String CODE = "code";
		public static final String DATA = "data";
	}
	
	public class ResponseCode {
		public static final int SUCCESS = 0;
		public static final int BAD_REQUEST_ERROR = 1;
		public static final String BAD_REQUEST_ERROR_DETAIL = "Bad request";
		
		public static final int UNKNOWN_API = 2;
		public static final String UNKNOWN_API_DETAIL = "Unknown api";
		
		public static final int UNKNOWN_ERROR = 3;
		public static final String UNKNOWN_ERROR_DETAIL = "Unknown error";
		
		public static final int AUTHEN_ERROR = 4;
		public static final String AUTHEN_ERROR_DETAIL = "Authentication failed";
	}
}
