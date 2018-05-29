package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.BloodBagTypeRepository;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class BloodBagService {

    BloodBagRepository bloodBagRepository;
    BloodTypeRepository bloodTypeRepository;
    BloodBagTypeRepository bloodBagTypeRepository;

    @Autowired
    public BloodBagService(BloodBagRepository bloodBagRepository, BloodTypeRepository bloodTypeRepository,BloodBagTypeRepository bloodBagTypeRepository) {
        this.bloodTypeRepository=bloodTypeRepository;
        this.bloodBagRepository = bloodBagRepository;
        this.bloodBagTypeRepository=bloodBagTypeRepository;
    }

    public List<BloodStock> getBloodStockAsList() {
        return new ArrayList<>(getBloodStockAsMap().values());
    }

    public HashMap<BloodType, BloodStock> getBloodStockAsMap() {
        HashMap<BloodType, BloodStock> result = new HashMap<>();
        List<BloodType> bloodTypes=bloodTypeRepository.findAll();
        List<BloodBagType> bagTypes=bloodBagTypeRepository.findAll();
        for (BloodType bloodType : bloodTypes) {
            BloodStock bloodStock = new BloodStock(bloodType);
            for (BloodBagType bloodBagType : bagTypes) {
                int stock = bloodBagRepository.getBloodBagsByBloodBagTypeAndBloodType(bloodBagType.getUuid(), bloodType.getUuid()).size();
                bloodStock.setBloodTypeNumber(bloodBagType, stock);
            }
            result.put(bloodType, bloodStock);
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
