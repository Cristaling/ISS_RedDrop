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
        BloodBagType bloodType1 = new BloodBagType();
        bloodType1.setUuid(UUID.randomUUID());
        bloodType1.setName("Whole");
        bloodType1.setDaysToExpire(21);
        BloodBagType bloodType2 = new BloodBagType();
        bloodType2.setUuid(UUID.randomUUID());
        bloodType2.setName("Thrombocyte");
        bloodType2.setDaysToExpire(5);
        BloodBagType bloodType3 = new BloodBagType();
        bloodType3.setUuid(UUID.randomUUID());
        bloodType3.setName("Plasma");
        bloodType3.setDaysToExpire(90);
        BloodBagType bloodType4 = new BloodBagType();
        bloodType4.setUuid(UUID.randomUUID());
        bloodType4.setName("Hemoglobine");
        bloodType4.setDaysToExpire(42);
        bloodBagTypeRepository.save(bloodType1);
        bloodBagTypeRepository.save(bloodType2);
        bloodBagTypeRepository.save(bloodType3);
        bloodBagTypeRepository.save(bloodType4);
    }

    public void addBloodBagType(BloodBagType bloodBagType) {
        bloodBagType.setUuid(UUID.randomUUID());
        bloodBagTypeRepository.save(bloodBagType);
    }

    public void deleteBloodBagType(UUID uuid) {
        bloodBagTypeRepository.deleteById(uuid);
    }

    public List<BloodBagType> getAll() {
        return bloodBagTypeRepository.findAll();
    }
}
