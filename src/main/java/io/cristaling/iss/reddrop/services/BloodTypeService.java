package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class BloodTypeService {

    BloodTypeRepository bloodTypeRepository;

    @Autowired
    public BloodTypeService(BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;

        List<String> temp = new ArrayList<String>(Arrays.asList("O-", "O+","A-","A+","B-","B+","AB-","AB+"));
        for(String s :temp){
            BloodType bloodType=new BloodType();
            bloodType.setUuid(UUID.randomUUID());
            bloodType.setType(s);
            bloodTypeRepository.save(bloodType);
        }
    }

    public void addBloodType(BloodType bloodType){
        bloodTypeRepository.save(bloodType);
    }

    public void deleteBloodType(UUID uuid){
        bloodTypeRepository.deleteById(uuid);
    }

    public List<BloodType> getAll(){
        return bloodTypeRepository.findAll();
    }

}
