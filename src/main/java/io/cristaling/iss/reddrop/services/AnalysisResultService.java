package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.AnalysisResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalysisResultService {

    AnalysisResultRepository repository;

    @Autowired
    public AnalysisResultService(AnalysisResultRepository repository) {
        this.repository = repository;
    }
}
