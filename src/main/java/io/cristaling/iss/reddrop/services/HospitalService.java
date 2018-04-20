package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    HospitalRepository repository;

    @Autowired
    public HospitalService(HospitalRepository repository) {
        this.repository = repository;
    }
}
