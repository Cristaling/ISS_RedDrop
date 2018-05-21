package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.AnalysisResultRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnalysisResultService {

    AnalysisResultRepository analysisResultRepository;
    DonatorRepository donatorRepository;

    @Autowired
    public AnalysisResultService(AnalysisResultRepository analysisResultRepository,DonatorRepository donatorRepository) {
        this.analysisResultRepository = analysisResultRepository;
        this.donatorRepository=donatorRepository;
    }

    public Donator getDonatorbyId(UUID donUUID){
        return donatorRepository.getOne(donUUID);
    }

    public void addAnalysis(AnalysisResult analysisResult){
        analysisResult.setUuid(UUID.randomUUID());
        analysisResultRepository.save(analysisResult);
    }

}
