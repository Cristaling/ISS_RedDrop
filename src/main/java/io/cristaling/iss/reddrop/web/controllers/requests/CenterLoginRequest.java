package io.cristaling.iss.reddrop.web.controllers.requests;

public class CenterLoginRequest {

	String centerName;
	String password;

	public CenterLoginRequest(String centerName, String password) {
		this.centerName = centerName;
		this.password = password;
	}

	public CenterLoginRequest() {
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
