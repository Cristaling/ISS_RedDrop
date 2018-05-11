package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	DoctorRepository doctorRepository;
	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	public DoctorController(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@RequestMapping("/getall")
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}

	@RequestMapping("/add")
	public void addDoctor(@RequestBody Doctor doctor){
		doctor.setUuid(UUID.randomUUID());
		doctor.setHospital(hospitalRepository.getOne(doctor.getHospital().getUuid()));
		doctorRepository.save(doctor);
	}

	@RequestMapping("/delete")
	public void deleteDoctor(String uuid){
		doctorRepository.deleteById(UUID.fromString(uuid));
	}

}
