package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.BloodRequest;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.BloodRequestRepository;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BloodRequestService {

    BloodRequestRepository requestRepository;
    DoctorRepository doctorRepository;
    @Autowired
    public BloodRequestService(BloodRequestRepository repository,DoctorRepository doctorRepository) {
        this.requestRepository = repository;
        this.doctorRepository=doctorRepository;
    }

    public void save(BloodRequest bloodRequest){
        requestRepository.save(bloodRequest);
    }

    public void deleteById(String uuid){
        requestRepository.deleteById(UUID.fromString(uuid));
    }

    public List<BloodRequest> findAll(){
        return requestRepository.findAll();
    }

    public List<BloodRequest> findSpecific(String uuid){
        Doctor doctor=doctorRepository.getOne(UUID.fromString(uuid));
        return requestRepository.getBloodRequestsByDoctorEquals(doctor);
    }
}
