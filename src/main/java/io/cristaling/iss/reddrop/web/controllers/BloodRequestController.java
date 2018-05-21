package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.services.BloodRequestService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/bloodrequest")
public class BloodRequestController {

    BloodRequestService bloodRequestService;
    PermissionsService permissionsService;

    @Autowired
    public BloodRequestController(BloodRequestService bloodRequestService, PermissionsService permissionsService) {
        this.bloodRequestService = bloodRequestService;
        this.permissionsService = permissionsService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addRequest(String token, @RequestBody BloodRequest bloodRequest) {
        if (!permissionsService.hasPermission(token, Permission.DOCTOR)) {
            return;
        }
        bloodRequestService.registerBloodRequest(bloodRequest);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRequest(String token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.DOCTOR)) {
            return;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return;
        }
        bloodRequestService.deleteBloodRequest(actualUuid);
    }

    /*@RequestMapping("/getall")
    public List<BloodRequest> getAllRequests(String token) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        return bloodRequestService.findAll();
    }*/

    @RequestMapping("/getfrom")
    public List<BloodRequest> getRequestsByDoctor(String token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.DOCTOR)) {
            return null;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return null;
        }
        return bloodRequestService.getBloodRequestByDoctor(actualUuid);
    }
}
