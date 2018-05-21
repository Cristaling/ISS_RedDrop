package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.services.AnalysisResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/analysisresult")
public class AnalysisResultController {
    AnalysisResultService analysisResultService;

    @Autowired
    public AnalysisResultController(AnalysisResultService analysisResultService) {
        this.analysisResultService = analysisResultService;
    }

    @RequestMapping("/add")
    public void addAnalysis(@RequestBody AnalysisResult analysisResult){
        analysisResultService.addAnalysis(analysisResult);
    }
}
