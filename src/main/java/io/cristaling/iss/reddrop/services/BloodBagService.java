package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BloodBagService {

    BloodBagRepository repository;
    DonatorRepository donatorRepository;

    @Autowired
    public BloodBagService(BloodBagRepository repository,DonatorRepository donatorRepository) {
        this.repository = repository;
        this.donatorRepository=donatorRepository;
    }

    public Donator getDonatorbyId(UUID donUUID){
        return donatorRepository.getOne(donUUID);
    }

}
