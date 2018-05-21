package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.utils.BloodBagType;
import io.cristaling.iss.reddrop.utils.BloodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BloodBagService {

    BloodBagRepository bloodBagRepository;

    @Autowired
    public BloodBagService(BloodBagRepository bloodBagRepository) {
        this.bloodBagRepository = bloodBagRepository;
    }

    public List<BloodStock> getBloodStocks() {
        List<BloodStock> result = new ArrayList<>();
        for (BloodType bloodType : BloodType.values()) {
            BloodStock bloodStock = new BloodStock(bloodType);
            for (BloodBagType bloodBagType : BloodBagType.values()) {
                int stock = bloodBagRepository.getBloodBagsByBloodBagTypeAndBloodType(bloodBagType, bloodType).size();
                bloodStock.setBloodTypeNumber(bloodBagType, stock);
            }
            result.add(bloodStock);
        }
        return result;
    }

}
