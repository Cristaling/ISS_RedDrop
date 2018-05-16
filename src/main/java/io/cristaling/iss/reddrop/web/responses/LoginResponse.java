package io.cristaling.iss.reddrop.web.responses;

public class LoginResponse {

	boolean succesful;
	String token;

	public LoginResponse(boolean succesful, String token) {
		this.succesful = succesful;
		this.token = token;
	}

	public LoginResponse() {
	}

	public boolean isSuccesful() {
		return succesful;
	}

	public void setSuccesful(boolean succesful) {
		this.succesful = succesful;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
