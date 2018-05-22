package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.BloodBagType;
import io.cristaling.iss.reddrop.utils.BloodType;

import java.util.HashMap;
import java.util.Map;

public class BloodStock {

	BloodType bloodType;
	Map<BloodBagType, Integer> stock = new HashMap<>();

	public BloodStock(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public void setBloodTypeNumber(BloodBagType bloodBagType, int value) {
		stock.put(bloodBagType, value);
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public Map<BloodBagType, Integer> getStock() {
		return stock;
	}
}
