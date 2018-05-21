package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import io.cristaling.iss.reddrop.utils.BloodBagType;
import io.cristaling.iss.reddrop.utils.BloodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BloodBagService {

    BloodBagRepository bloodBagRepository;

    @Autowired
    public BloodBagService(BloodBagRepository bloodBagRepository) {
        this.bloodBagRepository = bloodBagRepository;
    }

    public List<BloodStock> getBloodStocks() {
        List<BloodStock> result = new ArrayList<>();
        for (BloodBagType bloodBagType : BloodBagType.values()) {
            BloodStock bloodStock = new BloodStock(bloodBagType);
            for (BloodType bloodType : BloodType.values()) {
                int stock = bloodBagRepository.getBloodBagsByBloodBagTypeAndBloodType(bloodBagType, bloodType).size();
                bloodStock.setBloodTypeNumber(bloodType, stock);
            }
            result.add(bloodStock);
        }
        return result;
    }

}
