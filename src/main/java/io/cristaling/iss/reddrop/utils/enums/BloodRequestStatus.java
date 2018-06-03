package io.cristaling.iss.reddrop.utils.enums;

public enum BloodRequestStatus {

	UNRESOLVED("Unresolved"),
	AWAITING_CONFIRMATION("Awaiting Confirmation"),
	NEEDS_BREAKDOWN("Needs braekdown"),
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
