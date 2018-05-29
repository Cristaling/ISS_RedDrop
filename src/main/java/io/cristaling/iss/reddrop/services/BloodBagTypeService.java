package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.repositories.BloodBagTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BloodBagTypeService {

    BloodBagTypeRepository bloodBagTypeRepository;

    @Autowired
    public BloodBagTypeService(BloodBagTypeRepository bloodBagTypeRepository) {
        this.bloodBagTypeRepository = bloodBagTypeRepository;
        List<String> temp = new ArrayList<String>(Arrays.asList("THROMBOCYTE", "PLASMA","HEMOGLOBINE"));
        for(String s :temp){
            BloodBagType bloodType=new BloodBagType();
            bloodType.setUuid(UUID.randomUUID());
            bloodType.setName(s);
            bloodBagTypeRepository.save(bloodType);
        }
    }

    public void addBloodBagType(BloodBagType bloodBagType){
        bloodBagType.setUuid(UUID.randomUUID());
        bloodBagTypeRepository.save(bloodBagType);
    }

    public void deleteBloodBagType(UUID uuid){
        bloodBagTypeRepository.deleteById(uuid);
    }

    public List<BloodBagType> getAll(){
        return bloodBagTypeRepository.findAll();
    }
}
