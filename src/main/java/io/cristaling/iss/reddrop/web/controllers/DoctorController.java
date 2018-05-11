package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
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
	public DoctorController(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@RequestMapping("/getall")
	public List<Doctor> getAllHospitals(){
		return doctorRepository.findAll();
	}

	@RequestMapping("/savehospital")
	public void addHospital(@RequestBody Doctor doctor){
		doctorRepository.save(doctor);
	}

	@RequestMapping("/delete")
	public void deleteHospital(String uuid){
		doctorRepository.deleteById(UUID.fromString(uuid));
	}

}
