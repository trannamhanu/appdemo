package entity;

import org.json.simple.JSONObject;

public class User implements JsonEntity {

	private static final String NAME_KEY = "name";
	private String name;

	private static final String EMAIL_KEY = "email";
	private String email;

	private static final String PASSWORD_KEY = "password";
	private String password;

	public User() {

	}

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJsonObject() {
		JSONObject object = new JSONObject();
		if (this.name != null) {
			object.put(NAME_KEY, this.name);
		}
		if (this.email != null) {
			object.put(EMAIL_KEY, this.email);
		}
		if (this.password != null) {
			object.put(PASSWORD_KEY, this.password);
		}
		return object;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
