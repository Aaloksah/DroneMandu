
package model;

public class LoginModel {
	private String userName;
	private String password;
	
	public LoginModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getuserName() {
		return userName;
	}
	public void setEmail(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
