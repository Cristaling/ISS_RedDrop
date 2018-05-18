package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BloodRequestService {

    BloodRequestRepository requestRepository;
    DoctorRepository doctorRepository;
    @Autowired
    public BloodRequestService(BloodRequestRepository repository,DoctorRepository doctorRepository) {
        this.requestRepository = repository;
        this.doctorRepository=doctorRepository;
    }

    public void deleteBloodRequest(UUID uuid) {
        requestRepository.deleteById(uuid);
    }

    public void registerBloodRequest(BloodRequest bloodRequest) {
        //TODO Validate Requst
        bloodRequest.setUuid(UUID.randomUUID());
        requestRepository.save(bloodRequest);
    }

    public List<BloodRequest> getBloodRequestByDoctor(UUID doctorID) {
        Doctor doctor = doctorRepository.getDoctorByUuid(doctorID);
        if (doctor == null) {
            return new ArrayList<>();
        }
        return requestRepository.getBloodRequestsByDoctor(doctorID);
    }

}
