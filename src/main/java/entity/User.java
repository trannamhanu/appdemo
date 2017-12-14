package entity;

import org.json.simple.JSONObject;

import constant.MongoConstant.COLLECTION_USER;

public class User implements JsonEntity {

	public String name;

	public String email;

	public String password;

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
			object.put(COLLECTION_USER.KEY_NAME, this.name);
		}
		if (this.email != null) {
			object.put(COLLECTION_USER.KEY_EMAIL, this.email);
		}
		if (this.password != null) {
			object.put(COLLECTION_USER.KEY_PASSWORD, this.password);
		}
		return object;
	}

}
