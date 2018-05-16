package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DonatorService {

    DonatorRepository repository;

    @Autowired
    public DonatorService(DonatorRepository repository) {
        this.repository = repository;
    }

    public UUID tryToLogin(String cnp, String password) {
        Donator toCheck = repository.findDonatorByCnp(cnp);

        if (toCheck == null) {
            return null;
        }

        if (password.equals(toCheck.getPassword())) {
            return toCheck.getUuid();
        }
        return null;
    }

    public void registerDonator(Donator donator) {
        repository.save(donator);
    }

    public DonatorRepository getRepository() {
        return repository;
    }
}
