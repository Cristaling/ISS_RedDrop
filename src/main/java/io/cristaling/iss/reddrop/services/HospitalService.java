package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Hospital;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HospitalService {

    HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public void deleteHospital(UUID uuid) {
        hospitalRepository.deleteById(uuid);
    }

    public void registerHospital(Hospital hospital) {
        //TODO Validate Doctor
        hospital.setUuid(UUID.randomUUID());
        hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

}
