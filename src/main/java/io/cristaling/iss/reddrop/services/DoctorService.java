package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DoctorService {

    DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
        Doctor doctor = new Doctor();
        doctor.setUuid(UUID.randomUUID());
        doctor.setCnp("1971211055085");
        doctor.setPassword("1971211055085");
        doctor.setFullName("Dr. Susan Dofenjmirtz");
        doctorRepository.save(doctor);
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
