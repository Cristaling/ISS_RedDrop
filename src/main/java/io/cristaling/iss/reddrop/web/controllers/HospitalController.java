package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Hospital;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import io.cristaling.iss.reddrop.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

	HospitalRepository hospitalRepository;

	@Autowired
	public HospitalController(HospitalRepository hospitalRepository) {
		this.hospitalRepository=hospitalRepository;
	}

	@RequestMapping("/getall")
	public List<Hospital> getAllHospitals(){
		return hospitalRepository.findAll();
	}

	@RequestMapping("/add")
	public void addHospital(@RequestBody Hospital hospital){
		hospital.setUuid(UUID.randomUUID());
		hospitalRepository.save(hospital);
	}

	@RequestMapping("/delete")
	public void deleteHospital(String uuid){
		hospitalRepository.deleteById(UUID.fromString(uuid));
	}

}
