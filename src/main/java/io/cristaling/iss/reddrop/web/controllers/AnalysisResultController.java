package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.services.AnalysisResultService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.enums.Permission;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public boolean addAnalysis(String token,@RequestBody AnalysisResult analysisResult){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return false;
        }
        return analysisResultService.addAnalysis(analysisResult);
    }

    @RequestMapping("/getbydonator")
    public List<AnalysisResult> getAllAnalysisForDonator(String token, String uuid){
        if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
            return null;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return null;
        }
        return analysisResultService.getAllForDonator(actualUuid);
    }

    @RequestMapping("/getbyvisit")
    public AnalysisResult getAnalysisResultByVisit(String token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
            return null;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return null;
        }
        return analysisResultService.getByDonationVisit(actualUuid);
    }

}
