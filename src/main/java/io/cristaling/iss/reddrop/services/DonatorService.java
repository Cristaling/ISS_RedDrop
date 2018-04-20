package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonatorService {

    DonatorRepository repository;

    @Autowired
    public DonatorService(DonatorRepository repository) {
        this.repository = repository;
    }
}
