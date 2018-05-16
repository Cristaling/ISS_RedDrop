package io.cristaling.iss.reddrop.web.requests;

public class LoginRequest {

	String cnp;
	String password;

	public LoginRequest(String cnp, String password) {
		this.cnp = cnp;
		this.password = password;
	}

	public LoginRequest() {
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
