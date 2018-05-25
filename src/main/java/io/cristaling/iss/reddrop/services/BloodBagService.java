package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import io.cristaling.iss.reddrop.utils.BloodBagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BloodBagService {

    BloodBagRepository bloodBagRepository;
    BloodTypeRepository bloodTypeRepository;

    @Autowired
    public BloodBagService(BloodBagRepository bloodBagRepository, BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository=bloodTypeRepository;
        this.bloodBagRepository = bloodBagRepository;
    }

    public List<BloodStock> getBloodStocks() {
        List<BloodStock> result = new ArrayList<>();
        List<BloodType> types=new ArrayList<>();
        for (BloodType bloodType : types) {
            BloodStock bloodStock = new BloodStock(bloodType);
            for (BloodBagType bloodBagType : BloodBagType.values()) {
                int stock = bloodBagRepository.getBloodBagsByBloodBagTypeAndBloodType(bloodBagType, bloodType.getUuid()).size();
                bloodStock.setBloodTypeNumber(bloodBagType, stock);
            }
            result.add(bloodStock);
        }
        return result;
    }

    public void addBloodBag(BloodBag bloodBag){
        bloodBagRepository.save(bloodBag);
    }

    public void deleteBloodBagById(UUID uuid){
        bloodBagRepository.deleteById(uuid);
    }

    public void updateBloodBag(BloodBag bloodBag){
        bloodBagRepository.deleteById(bloodBag.getUuid());
        bloodBagRepository.save(bloodBag);
    }

}
