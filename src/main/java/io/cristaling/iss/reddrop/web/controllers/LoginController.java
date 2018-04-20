package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import io.cristaling.iss.reddrop.web.controllers.requests.CenterLoginRequest;
import io.cristaling.iss.reddrop.web.controllers.requests.DonorLoginRequest;
import io.cristaling.iss.reddrop.web.controllers.requests.HospitalLoginRequest;
import io.cristaling.iss.reddrop.web.controllers.responses.CenterLoginResponse;
import io.cristaling.iss.reddrop.web.controllers.responses.DonorLoginResponse;
import io.cristaling.iss.reddrop.web.controllers.responses.HospitalLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	DonatorRepository donatorRepository;

	@Autowired
	public LoginController(DonatorRepository donatorRepository) { throw new NotImplementedException(); }

	@RequestMapping("/donorusers")
	public List<Donator> getAllDonatorUsers() {
		throw new NotImplementedException();
	}

	@RequestMapping("/adddonor")
	public void addDonor(@RequestBody Donator donatorUser) { throw new NotImplementedException(); }

	@RequestMapping("/deletedonor")
	public void deleteDonor(@RequestBody String uuid) {
		throw new NotImplementedException();
	}

	@RequestMapping("/donor")
	public DonorLoginResponse loginDonor(DonorLoginRequest request) {
		throw new NotImplementedException();
	}

	@RequestMapping("/center")
	public CenterLoginResponse loginCenter(CenterLoginRequest request) {
		throw new NotImplementedException();
	}

	@RequestMapping("/hospital")
	public HospitalLoginResponse loginHospital(HospitalLoginRequest request) {
		throw new NotImplementedException();
	}

}
