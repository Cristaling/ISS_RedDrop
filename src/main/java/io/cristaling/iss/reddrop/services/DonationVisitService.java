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
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class DonationVisitService {

    DonationVisitRepository donationVisitRepository;
    DonatorRepository donatorRepository;
    AnalysisResultRepository analysisResultRepository;

    @Autowired
    public DonationVisitService(DonationVisitRepository donationVisitRepository,DonatorRepository donatorRepository,AnalysisResultRepository analysisResultRepository) {
        this.donatorRepository=donatorRepository;
        this.donationVisitRepository = donationVisitRepository;
        this.analysisResultRepository=analysisResultRepository;
    }

    public void addDonationVisit(DonationVisit donationVisit){
        donationVisit.getDate().setTime(donationVisit.getDate().getTime()+10800001);
        donationVisit.setUuid(UUID.randomUUID());
        Donator donator=donatorRepository.getOne(donationVisit.getDonator());
        donationVisit.setDonatorName(donator.getNume()+" "+donator.getPrenume());
        donationVisitRepository.save(donationVisit);
    }

    public void deleteDonationVisitById(UUID donationVisit){
        donationVisitRepository.deleteById(donationVisit);
    }

    public List<DonationVisit> getVisitsSorted(){
        List<DonationVisit> result = donationVisitRepository.findAll();
        result.sort(new Comparator<DonationVisit>() {
            @Override
            public int compare(DonationVisit o1, DonationVisit o2) {
                return (int) (o1.getDate().getTime() - o2.getDate().getTime());
            }
        });
        return result;
    }

    public List<DonationVisit> getVisitsVisited(UUID donatorUUID){
        List<DonationVisit> visits= donationVisitRepository.getDonationVisitsByDonator(donatorUUID);
        List<DonationVisit> visited=new ArrayList<>();
        for(DonationVisit donationVisit: visits){
            if(analysisResultRepository.getAnalysisResultByDonationVisit(donationVisit.getUuid())!=null){
                visited.add(donationVisit);
            }
        }
        return visited;
    }
}
