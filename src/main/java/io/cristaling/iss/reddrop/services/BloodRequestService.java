package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodRequestService {

    BloodRequestRepository repository;

    @Autowired
    public BloodRequestService(BloodRequestRepository repository) {
        this.repository = repository;
    }
}
