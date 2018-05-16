package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.services.BloodRequestService;
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

    @Autowired
    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addRequest(@RequestBody BloodRequest bloodRequest){
        bloodRequestService.save(bloodRequest);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRequest(String uuid){
        bloodRequestService.deleteById(uuid);
    }


    @RequestMapping("/getall")
    public List<BloodRequest> getAllRequests(){
        return bloodRequestService.findAll();
    }

    @RequestMapping("/getfrom")
    public List<BloodRequest> getSpecificRequests(String uuid){
        return bloodRequestService.findSpecific(uuid);
    }
}
