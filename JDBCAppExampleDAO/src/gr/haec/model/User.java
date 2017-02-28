package gr.haec.model;

public class User {

	public static final int DEFAULT_ID = -1;

	private int id;
	private String userLogin;
	private String userPass;
	private String userEmail;
	private String userRegistered;

	public User() {
		this.id = DEFAULT_ID;
		this.userLogin = "";
		this.userPass = "";
		this.userEmail = "";
		this.userRegistered = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserRegistered() {
		return userRegistered;
	}

	public void setUserRegistered(String userRegistered) {
		this.userRegistered = userRegistered;
	}

	@Override
	public String toString() {
		return "User [ id = " + id + ", userLogin: " + userLogin + ", userPass: " + userPass + ", userEmail: " + userEmail + ", userRegistered: "
				+ userRegistered + " ]";
	}
}
