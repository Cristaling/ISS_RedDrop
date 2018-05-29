package io.cristaling.iss.reddrop.utils;

import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.BloodType;

import java.util.HashMap;

public class StockUtils {

	public static int getInStock(HashMap<BloodType, BloodStock> stock, BloodType bloodType, BloodBagType bloodBagType) {
		BloodStock toCheck = stock.get(bloodType);
		return toCheck.getStock().get(bloodBagType.getUuid());
	}

	public static boolean hasInStock(HashMap<BloodType, BloodStock> stock, BloodType bloodType, BloodBagType bloodBagType) {
		return getInStock(stock, bloodType, bloodBagType) > 0;
	}

	public static void removeFromStock(HashMap<BloodType, BloodStock> stock, BloodType bloodType, BloodBagType bloodBagType, int value) {
		BloodStock toCheck = stock.get(bloodType);
		int number = toCheck.getStock().get(bloodBagType.getUuid());
		toCheck.setBloodTypeNumber(bloodBagType, number - value);
	}

}
