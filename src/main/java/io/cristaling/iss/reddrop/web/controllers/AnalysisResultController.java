package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.services.AnalysisResultService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/analysisresult")
public class AnalysisResultController {
    AnalysisResultService analysisResultService;
    PermissionsService permissionsService;

    @Autowired
    public AnalysisResultController(AnalysisResultService analysisResultService,PermissionsService permissionsService) {
        this.analysisResultService = analysisResultService;
        this.permissionsService=permissionsService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addAnalysis(String token,@RequestBody AnalysisResult analysisResult){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        analysisResultService.addAnalysis(analysisResult);
    }
}
