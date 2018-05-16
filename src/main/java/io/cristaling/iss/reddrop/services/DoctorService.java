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
        Doctor doctor = new Doctor();
        doctor.setUuid(UUID.randomUUID());
        doctor.setCnp("1971211055085");
        doctor.setPassword("1971211055085");
        doctor.setFullName("Dr. Susan Dofenjmirtz");
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(UUID uuid) {
        doctorRepository.deleteById(uuid);
    }

    public void registerDoctor(Doctor doctor) {
        //TODO Validate Doctor
        doctor.setUuid(UUID.randomUUID());
        doctor.setHospital(hospitalRepository.getOne(doctor.getHospital().getUuid()));
        doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctorsByHospital(UUID hospitalID) {
        Hospital hospital = hospitalRepository.getOne(hospitalID);
        if (hospital == null) {
            return new ArrayList<>();
        }
        return doctorRepository.getDoctorsByHospital(hospital);
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
}
