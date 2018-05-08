package io.cristaling.iss.reddrop.web.controllers.requests;

public class DonatorLoginRequest {

	String cnp;
	String password;

	public DonatorLoginRequest(String cnp, String password) {
		this.cnp = cnp;
		this.password = password;
	}

	public DonatorLoginRequest() {
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
