package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import io.cristaling.iss.reddrop.services.DoctorService;
import io.cristaling.iss.reddrop.web.requests.LoginRequest;
import io.cristaling.iss.reddrop.web.responses.LoginResponse;
import io.cristaling.iss.reddrop.web.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	DoctorRepository doctorRepository;
	@Autowired
	HospitalRepository hospitalRepository;

	DoctorService doctorService;

	@Autowired
	public DoctorController(DoctorRepository doctorRepository, DoctorService doctorService) {
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
	}

	@RequestMapping("/login")
	public LoginResponse loginAdmin(@RequestBody LoginRequest loginRequest) {
		UUID token = doctorService.tryToLogin(loginRequest.getCnp(), loginRequest.getPassword());
		return LoginUtils.generateLoginResponse(token);
	}

	@RequestMapping("/getall")
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}

	@RequestMapping("/add")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addDoctor(@RequestBody Doctor doctor){
		doctor.setUuid(UUID.randomUUID());
		doctor.setHospital(hospitalRepository.getOne(doctor.getHospital().getUuid()));
		doctorRepository.save(doctor);
	}

	@RequestMapping("/delete")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteDoctor(String uuid){
		doctorRepository.deleteById(UUID.fromString(uuid));
	}

}
