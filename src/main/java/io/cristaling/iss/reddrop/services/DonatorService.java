package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DonatorService {

    DonatorRepository donatorRepository;

    @Autowired
    public DonatorService(DonatorRepository repository) {
        this.donatorRepository = repository;
        Donator donator = new Donator();
        donator.setUuid(UUID.randomUUID());
        donator.setCnp("1971211055084");
        donator.setPassword("1971211055084");
        donator.setNume("Iova");
        donator.setPrenume("Rares");
        donator.setNrTel("0751080998");
        donatorRepository.save(donator);
    }

    public UUID tryToLogin(String cnp, String password) {

        if (cnp == null || password == null) {
            return null;
        }

        Donator toLogin = this.donatorRepository.getDonatorByCnp(cnp);

        if (toLogin == null) {
            return null;
        }

        if (password.equals(toLogin.getPassword())) {
            return toLogin.getUuid();
        }

        return null;
    }

    public void registerDonator(Donator donator) {
        donatorRepository.save(donator);
    }

}
