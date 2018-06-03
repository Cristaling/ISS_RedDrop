package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.*;
import io.cristaling.iss.reddrop.repositories.*;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;
import io.cristaling.iss.reddrop.web.responses.DonationVisitStatusResponse;
import io.cristaling.iss.reddrop.web.responses.DonationVisitWithBloodBagResponse;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DonationVisitService {

    DonationVisitRepository donationVisitRepository;
    DonatorRepository donatorRepository;
    AnalysisResultRepository analysisResultRepository;
    BloodTypeRepository bloodTypeRepository;
    BloodBagTypeRepository bloodBagTypeRepository;
    BloodBagRepository bloodBagRepository;

    @Autowired
    public DonationVisitService(BloodBagRepository bloodBagRepository, DonationVisitRepository donationVisitRepository, DonatorRepository donatorRepository, AnalysisResultRepository analysisResultRepository, BloodTypeRepository bloodTypeRepository, BloodBagTypeRepository bloodBagTypeRepository) {
        this.donatorRepository = donatorRepository;
        this.donationVisitRepository = donationVisitRepository;
        this.analysisResultRepository = analysisResultRepository;
        this.bloodTypeRepository = bloodTypeRepository;
        this.bloodBagTypeRepository = bloodBagTypeRepository;
        this.bloodBagRepository = bloodBagRepository;
    }

    public void addDonationVisit(DonationVisit donationVisit) {
        donationVisit.getDate().setTime(donationVisit.getDate().getTime() + 10800001);
        if (donationVisit.getUuid() == null) {
            donationVisit.setUuid(UUID.randomUUID());
        }
        Donator donator = donatorRepository.getOne(donationVisit.getDonator());
        donationVisit.setDonatorName(donator.getNume() + " " + donator.getPrenume());
        donationVisitRepository.save(donationVisit);
    }

    public void deleteDonationVisitById(UUID donationVisit) {
        donationVisitRepository.deleteById(donationVisit);
    }

    public List<DonationVisit> getVisitsSorted() {
        List<DonationVisit> result = donationVisitRepository.findAll();
        result.sort(new Comparator<DonationVisit>() {
            @Override
            public int compare(DonationVisit o1, DonationVisit o2) {
                return (int) (o1.getDate().getTime() - o2.getDate().getTime());
            }
        });
        return result;
    }

    public List<DonationVisit> getVisitsByDone(UUID donatorUUID, boolean done) {
        return donationVisitRepository.getDonationVisitsByDonatorAndDone(donatorUUID, done);
    }

    public List<DonationVisit> getVisitsNotDone() {
        return donationVisitRepository.getDonationVisitsByDone(false);
    }

    public boolean markDonationVisitDone(UUID uuid, String bloodTypeString) {
        DonationVisit donationVisit = donationVisitRepository.getOne(uuid);
        Donator donator = donatorRepository.getOne(donationVisit.getDonator());
        BloodType bloodType = null;
        if (bloodTypeString == null) {
            if (donator.getBloodType() == null) {
                return false;
            }

            bloodType = bloodTypeRepository.getOne(donator.getBloodType());
        }
        if (bloodType == null) {
            UUID bloodTypeUUID = UUIDUtils.getUUIDFromString(bloodTypeString);
            if (bloodTypeUUID == null) {
                return false;
            }
            bloodType = bloodTypeRepository.getOne(bloodTypeUUID);
        }

        donationVisit.setDone(true);
        donationVisitRepository.save(donationVisit);

        donator.setBloodType(bloodType.getUuid());
        donatorRepository.save(donator);

        BloodBagType bloodBagType = bloodBagTypeRepository.getBloodBagTypeByName("Whole");

        BloodBag bloodBag = new BloodBag();
        bloodBag.setUuid(UUID.randomUUID());
        bloodBag.setBloodBagType(bloodBagType.getUuid());
        bloodBag.setBloodType(bloodType.getUuid());
        bloodBag.setBloodBagStatus(BloodBagStatus.UNTESTED);

        bloodBag.setDonationVisit(donationVisit.getUuid());

        Date date = new Date();
        date.setTime(date.getTime() + bloodBagType.getDaysToExpire() * 86400000);
        bloodBag.setExpireDate(date);

        bloodBagRepository.save(bloodBag);

        return true;
    }

    public List<DonationVisitStatusResponse> getVisitsWithBagStatusByDonator(UUID donatorUUID) {
        List<DonationVisit> donationVisits = donationVisitRepository.getDonationVisitsByDonatorAndDone(donatorUUID, true);
        List<DonationVisitStatusResponse> result = new ArrayList<>();

        for (DonationVisit donationVisit : donationVisits) {
            BloodBag bloodBag = bloodBagRepository.getBloodBagByDonationVisit(donationVisit.getUuid());

            DonationVisitStatusResponse donationVisitStatusResponse = new DonationVisitStatusResponse();
            donationVisitStatusResponse.setDonationVisit(donationVisit);
            donationVisitStatusResponse.setBloodBagStatus(bloodBag.getBloodBagStatus());

            result.add(donationVisitStatusResponse);
        }

        return result;
    }

    public List<DonationVisitWithBloodBagResponse> getUnanalyzedVisitedVisits() {
        List<DonationVisit> donationVisits = donationVisitRepository.getDonationVisitsByDone(true);
        List<DonationVisitWithBloodBagResponse> pendings = new ArrayList<>();
        for (DonationVisit donationVisit : donationVisits) {
            BloodBag bloodBag = bloodBagRepository.getBloodBagByDonationVisit(donationVisit.getUuid());

            if (bloodBag.getBloodBagStatus() == BloodBagStatus.UNTESTED) {

                DonationVisitWithBloodBagResponse response = new DonationVisitWithBloodBagResponse();
                response.setDonationVisit(donationVisit);
                response.setBloodBag(bloodBag);
                pendings.add(response);
            }
        }
        return pendings;
    }
}
