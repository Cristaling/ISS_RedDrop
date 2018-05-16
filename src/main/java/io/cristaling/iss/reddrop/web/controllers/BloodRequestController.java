package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/bloodrequest")
public class BloodRequestController {

    BloodRequestRepository bloodRequestRepository;

    @Autowired
    public BloodRequestController(BloodRequestRepository bloodRequestRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addRequest(@RequestBody BloodRequest bloodRequest){
        bloodRequestRepository.save(bloodRequest);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRequest(String uuid){
        bloodRequestRepository.deleteById(UUID.fromString(uuid));
    }


    @RequestMapping("/getall")
    public List<BloodRequest> getAllRequests(){
        return bloodRequestRepository.findAll();
    }

    @RequestMapping("/getfrom")
    public List<BloodRequest> getSpecificRequests(String uuid){
        Doctor doctor=new Doctor();
        doctor.setUuid(UUID.fromString(uuid));
        return bloodRequestRepository.getBloodRequestsByDoctorEquals(doctor);
    }
}
