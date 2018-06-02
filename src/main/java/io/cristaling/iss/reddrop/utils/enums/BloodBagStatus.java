package io.cristaling.iss.reddrop.utils.enums;

public enum BloodBagStatus {

	UNTESTED("Untested"),
	DEPOSITED("Deposited"),
	REFUSED("Refused"),
	USED("Used");

	String name;

	BloodBagStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
