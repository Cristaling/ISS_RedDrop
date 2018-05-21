package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.AnalysisResultRepository;
import io.cristaling.iss.reddrop.repositories.DonationVisitRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AnalysisResultService {

    AnalysisResultRepository analysisResultRepository;
    DonationVisitRepository donationVisitRepository;

    @Autowired
    public AnalysisResultService(AnalysisResultRepository analysisResultRepository, DonationVisitRepository donationVisitRepository) {
        this.analysisResultRepository = analysisResultRepository;
        this.donationVisitRepository = donationVisitRepository;
    }

    public void addAnalysis(AnalysisResult analysisResult){
        if (analysisResult.getUuid() == null) {
            analysisResult.setUuid(UUID.randomUUID());
        }
        analysisResultRepository.save(analysisResult);
    }

	public List<AnalysisResult> getAllForDonator(UUID donatorUUID) {
        List<DonationVisit> visits = donationVisitRepository.getDonationVisitsByDonator(donatorUUID);
        List<UUID> visitsUuids=new ArrayList<>();
        for(DonationVisit donationVisit : visits){
            visitsUuids.add(donationVisit.getUuid());
        }
        return analysisResultRepository.getAnalysisResultsByDonationVisitIn(visitsUuids);

	}
}
