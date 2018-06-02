package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.BloodBagTypeRepository;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import io.cristaling.iss.reddrop.repositories.DonationVisitRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;
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
    DonationVisitRepository donationVisitRepository;
    DonatorRepository donatorRepository;

    @Autowired
    public BloodBagService(BloodBagRepository bloodBagRepository,
                           BloodTypeRepository bloodTypeRepository,
                           BloodBagTypeRepository bloodBagTypeRepository,
                           DonationVisitRepository donationVisitRepository,
                           DonatorRepository donatorRepository) {
        this.bloodBagRepository = bloodBagRepository;
        this.bloodTypeRepository = bloodTypeRepository;
        this.bloodBagTypeRepository = bloodBagTypeRepository;
        this.donationVisitRepository = donationVisitRepository;
        this.donatorRepository = donatorRepository;
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

                int stock = bloodBagRepository.getBloodBagsByBloodBagStatusAndBloodBagTypeAndBloodType(BloodBagStatus.DEPOSITED, bloodBagType.getUuid(), bloodType.getUuid()).size();
                bloodStock.setBloodTypeNumber(bloodBagType, stock);
            }
            result.put(bloodType, bloodStock);
        }
        return result;
    }

    public void addBloodBag(BloodBag bloodBag) {


        bloodBag.setUuid(UUID.randomUUID());
        bloodBag.setBloodBagStatus(BloodBagStatus.UNTESTED);
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
