package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.AnalysisResultRepository;
import io.cristaling.iss.reddrop.repositories.BloodBagRepository;
import io.cristaling.iss.reddrop.repositories.DonationVisitRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AnalysisResultService {

    AnalysisResultRepository analysisResultRepository;
    DonationVisitRepository donationVisitRepository;
    BloodBagRepository bloodBagRepository;

    @Autowired
    public AnalysisResultService(AnalysisResultRepository analysisResultRepository, DonationVisitRepository donationVisitRepository, BloodBagRepository bloodBagRepository) {
        this.analysisResultRepository = analysisResultRepository;
        this.donationVisitRepository = donationVisitRepository;
        this.bloodBagRepository = bloodBagRepository;
    }

    public void addAnalysis(AnalysisResult analysisResult) {
        if (analysisResult.getUuid() == null) {
            analysisResult.setUuid(UUID.randomUUID());
        }

        analysisResultRepository.save(analysisResult);

        BloodBag bloodBag = bloodBagRepository.getBloodBagByDonationVisit(analysisResult.getDonationVisit());

        if (checkAnalysisValues(analysisResult)) {
            bloodBag.setBloodBagStatus(BloodBagStatus.DEPOSITED);
        } else {
            bloodBag.setBloodBagStatus(BloodBagStatus.REFUSED);
        }

        bloodBagRepository.save(bloodBag);


    }

    public List<AnalysisResult> getAllForDonator(UUID donatorUUID) {
        List<DonationVisit> visits = donationVisitRepository.getDonationVisitsByDonatorAndDone(donatorUUID, true);
        List<UUID> visitsUuids = new ArrayList<>();
        for (DonationVisit donationVisit : visits) {
            visitsUuids.add(donationVisit.getUuid());
        }
        return analysisResultRepository.getAnalysisResultsByDonationVisitIn(visitsUuids);

    }

    public AnalysisResult getByDonationVisit(UUID actualUuid) {
        return analysisResultRepository.getAnalysisResultByDonationVisit(actualUuid);
    }

    private boolean checkAnalysisValues(AnalysisResult analysisResult) {
        if (analysisResult.getSodium() < 135 || analysisResult.getSodium() > 145) {
            return false;
        }
        if (analysisResult.getCreatinine() < 53 || analysisResult.getCreatinine() > 114.9) {
            return false;
        }
        if (analysisResult.getGlucose() < 3.9 || analysisResult.getGlucose() > 5.6) {
            return false;
        }
        if (analysisResult.getPotassium() < 3.70 || analysisResult.getPotassium() > 5.20) {
            return false;
        }
        if (analysisResult.getUrea() < 2.14 || analysisResult.getUrea() > 7.14) {
            return false;
        }
        return true;
    }
}
