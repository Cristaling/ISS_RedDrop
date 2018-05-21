package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.BloodBagType;
import io.cristaling.iss.reddrop.utils.BloodType;

import java.util.HashMap;
import java.util.Map;

public class BloodStock {

	BloodBagType bloodBagType;
	Map<BloodType, Integer> stock = new HashMap<>();

	public BloodStock(BloodBagType bloodBagType) {
		this.bloodBagType = bloodBagType;
	}

	public void setBloodTypeNumber(BloodType bloodType, int value) {
		stock.put(bloodType, value);
	}

	public BloodBagType getBloodBagType() {
		return bloodBagType;
	}

	public Map<BloodType, Integer> getStock() {
		return stock;
	}
}
