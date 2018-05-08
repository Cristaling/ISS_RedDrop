package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.services.DonatorService;
import io.cristaling.iss.reddrop.web.controllers.requests.DonatorLoginRequest;
import io.cristaling.iss.reddrop.web.controllers.responses.DonatorLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/donator")
public class DonatorController {

	DonatorService donatorService;

	@Autowired
	public DonatorController(DonatorService donatorService) {
		this.donatorService = donatorService;
		Donator donator = new Donator();
		donator.setUuid(UUID.randomUUID());
		donator.setCnp("1971211055084");
		donator.setPassword("bh06fvb");
		donatorService.getRepository().save(donator);
	}

	@RequestMapping("/login")
	public DonatorLoginResponse loginDonator(@RequestBody DonatorLoginRequest loginRequest) {
		UUID token = donatorService.tryToLogin(loginRequest.getCnp(), loginRequest.getPassword());
		DonatorLoginResponse response = new DonatorLoginResponse();
		if (token == null) {
			response.setSuccesful(false);
			response.setToken(UUID.randomUUID().toString());
			return response;
		}
		response.setSuccesful(true);
		response.setToken(token.toString());
		return response;
	}

	/*@RequestMapping("/donorusers")
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
	public DonatorLoginResponse loginDonor(DonatorLoginRequest request) {
		throw new NotImplementedException();
	}

	@RequestMapping("/center")
	public CenterLoginResponse loginCenter(CenterLoginRequest request) {
		throw new NotImplementedException();
	}

	@RequestMapping("/hospital")
	public HospitalLoginResponse loginHospital(HospitalLoginRequest request) {
		throw new NotImplementedException();
	}*/

}
