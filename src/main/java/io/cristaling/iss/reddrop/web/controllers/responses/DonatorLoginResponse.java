package io.cristaling.iss.reddrop.web.controllers.responses;

public class DonatorLoginResponse {

	boolean succesful;
	String token;

	public DonatorLoginResponse(boolean succesful, String token) {
		this.succesful = succesful;
		this.token = token;
	}

	public DonatorLoginResponse() {
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
