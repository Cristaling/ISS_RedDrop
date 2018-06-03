package io.cristaling.iss.reddrop.utils.enums;

public enum Importance {

	LOW("Low"),
	MEDIUM("Medium"),
	HIGH("High");

	String name;

	Importance(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
