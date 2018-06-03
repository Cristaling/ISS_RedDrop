package io.cristaling.iss.reddrop.utils.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.cristaling.iss.reddrop.utils.BloodBagStatusSerializer;

@JsonSerialize(using = BloodBagStatusSerializer.class)
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
