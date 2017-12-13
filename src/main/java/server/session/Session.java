package server.session;

public class Session {
	public String token;
	public String userId;
	public long expiryTime;

	public Session(String token, String userId, long expiryTime) {
		super();
		this.token = token;
		this.userId = userId;
		this.expiryTime = expiryTime;
	}

}
