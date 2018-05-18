package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Donation;
import io.cristaling.iss.reddrop.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DonationService {

    DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getDonationsByDonatorId(UUID donID){
        return donationRepository.getDonationsByDonator(donID);
    }
}
