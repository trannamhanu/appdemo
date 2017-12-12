package server;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import util.Constant;

public class TokenManager {
	public static Map<String, Date> activeTokens = new HashMap<String, Date>();
	
	public static void put(String token) {
		activeTokens.put(token, new Date());
	}
	
	public static boolean validToken(String token) {
		if (token.length() == 0) return false;
		Date date = activeTokens.get(token);
		if (date == null) return false;
		
		Long registeredTime = date.getTime();
		Long currentTime = new Date().getTime();
		
		if ((currentTime - registeredTime) > (Constant.SESSION_TIMEOUT * 60 * 1000)) {
			activeTokens.remove(token);
			return false;
		}
		
		return true;
	}
}
