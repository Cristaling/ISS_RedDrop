package io.cristaling.iss.reddrop.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BloodStock {

	BloodType bloodType;
	Map<UUID, Integer> stock = new HashMap<>();

	public BloodStock(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public void setBloodTypeNumber(BloodBagType bloodBagType, int value) {
		stock.put(bloodBagType.getUuid(), value);
	}

	public Map<UUID, Integer> getStock() {
		return stock;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}
}
