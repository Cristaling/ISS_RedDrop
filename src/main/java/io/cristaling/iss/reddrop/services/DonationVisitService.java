package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.repositories.DonationVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DonationVisitService {

    DonationVisitRepository donationVisitRepository;

    @Autowired
    public DonationVisitService(DonationVisitRepository donationVisitRepository) {
        this.donationVisitRepository = donationVisitRepository;
    }

    public void addDonationVisit(DonationVisit donationVisit){
        donationVisit.setUuid(UUID.randomUUID());
        donationVisitRepository.save(donationVisit);
    }

    public void deleteDonationVisitById(UUID donationVisit){
        donationVisitRepository.deleteById(donationVisit);
    }
}
