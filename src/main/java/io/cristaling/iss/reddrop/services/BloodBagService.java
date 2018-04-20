package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodBagService {

    BloodBagRepository repository;

    @Autowired
    public BloodBagService(BloodBagRepository repository) {
        this.repository = repository;
    }
}
