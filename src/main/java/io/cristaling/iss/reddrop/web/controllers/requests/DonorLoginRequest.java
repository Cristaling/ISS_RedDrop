package io.cristaling.iss.reddrop.web.controllers.requests;

public class DonorLoginRequest {

	String email;
	String password;

	public DonorLoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public DonorLoginRequest() {
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
