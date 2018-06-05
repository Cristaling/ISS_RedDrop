package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.core.Hospital;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;
    HospitalRepository hospitalRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        Hospital hospital = new Hospital();
        hospital.setUuid(UUID.fromString("6aa537ca-e706-47b3-8640-54ae85692dcf"));
        hospital.setName("County Main Hospital");
        hospitalRepository.save(hospital);
        Doctor doctor = new Doctor();
        doctor.setUuid(UUID.randomUUID());
        doctor.setHospital(hospital.getUuid());
        doctor.setCnp("1971211055085");
        doctor.setPassword("seventhdayslumber");
        doctor.setFullName("Dr. Susan Dofenjmirtz");
        doctor.setFirstLogin(false);
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(UUID uuid) {
        doctorRepository.deleteById(uuid);
    }

    public void registerDoctor(Doctor doctor) {
        //TODO Validate Doctor
        if (doctor.getUuid() == null) {
            doctor.setUuid(UUID.randomUUID());
        }
        doctor.setFirstLogin(true);
        doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctorsByHospital(UUID hospitalID) {
        if (hospitalID == null) {
            return new ArrayList<>();
        }
        return doctorRepository.getDoctorsByHospital(hospitalID);
    }

    public UUID tryToLogin(String cnp, String password) {
        if (cnp == null || password == null) {
            return null;
        }
        Doctor toLogin = this.doctorRepository.getDoctorByCnp(cnp);
        if (toLogin == null) {
            return null;
        }
        if (password.equals(toLogin.getPassword())) {
            return toLogin.getUuid();
        }
        return null;
    }

    public void updateDoctor(Doctor doctor){
        doctorRepository.deleteById(doctor.getUuid());
        doctorRepository.save(doctor);
    }
    public boolean checkFirstLogin(UUID uuid){
        return doctorRepository.getDoctorByUuid(uuid).isFirstLogin();
    }
    public Doctor getByUUID(UUID uuid){
        return doctorRepository.getOne(uuid);
    }

}
