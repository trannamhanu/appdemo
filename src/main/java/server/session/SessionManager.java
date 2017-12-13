package server.session;

import java.util.HashMap;
import java.util.Map;

import constant.CommonConstant;

public class SessionManager {
	public static Map<String, Session> ACTIVE_SESSION = new HashMap<String, Session>();

	public static void put(Session session) {
		ACTIVE_SESSION.put(session.token, session);
	}

	public static boolean validToken(String token) {
		Session session = ACTIVE_SESSION.get(token);
		if (session == null) { return false; }
		if ((System.currentTimeMillis() - session.expiryTime) > CommonConstant.SESSION_TIMEOUT) {return false;}
		return true;
	}
}
