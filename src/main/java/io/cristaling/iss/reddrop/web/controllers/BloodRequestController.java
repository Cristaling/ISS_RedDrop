package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.services.BloodRequestService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
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
    public void addRequest(UUID token, @RequestBody BloodRequest bloodRequest) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bloodRequestService.save(bloodRequest);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRequest(UUID token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bloodRequestService.deleteById(uuid);
    }

    @RequestMapping("/getall")
    public List<BloodRequest> getAllRequests(UUID token) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        return bloodRequestService.findAll();
    }

    @RequestMapping("/getfrom")
    public List<BloodRequest> getSpecificRequests(UUID token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        return bloodRequestService.findSpecific(uuid);
    }
}
