package io.cristaling.iss.reddrop.web.controllers.requests;

public class HospitalLoginRequest {

	String hospitalName;
	String password;

	public HospitalLoginRequest(String hospitalName, String password) {
		this.hospitalName = hospitalName;
		this.password = password;
	}

	public HospitalLoginRequest() {
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
