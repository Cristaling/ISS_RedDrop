package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    DonationRepository repository;

    @Autowired
    public DonationService(DonationRepository repository) {
        this.repository = repository;
    }
}
