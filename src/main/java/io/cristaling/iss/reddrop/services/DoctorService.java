package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    DoctorRepository repository;

    @Autowired
    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }
}
