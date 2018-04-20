package io.cristaling.iss.reddrop.web.controllers.responses;

public class DonorLoginResponse {

	boolean succesful;
	String token;

	public DonorLoginResponse(boolean succesful, String token) {
		this.succesful = succesful;
		this.token = token;
	}

	public DonorLoginResponse() {
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
