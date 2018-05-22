package io.cristaling.iss.reddrop.utils;

public enum BloodRequestStatus {

	UNRESOLVED("Unresolved"),
	AWAITING_CONFIRMATION("Awaiting Confirmation"),
	COMPLETED("Completed");

	String name;

	BloodRequestStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
