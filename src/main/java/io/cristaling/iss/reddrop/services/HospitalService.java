package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Hospital;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HospitalService {

    HospitalRepository hospitalRepository;
    DoctorRepository doctorRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository,DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository=doctorRepository;
    }

    public void deleteHospital(UUID uuid) {
        hospitalRepository.deleteById(uuid);
    }

    public void registerHospital(Hospital hospital) {
        //TODO Validate Doctor
        if (hospital.getUuid() == null) {
            hospital.setUuid(UUID.randomUUID());
        }
        hospitalRepository.save(hospital);
    }

    public Hospital getHospitalByDoctor(UUID docID){
        return hospitalRepository.getOne(doctorRepository.getDoctorByUuid(docID).getHospital());
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

}
