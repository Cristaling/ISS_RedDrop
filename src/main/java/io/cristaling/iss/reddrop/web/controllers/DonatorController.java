package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.services.DonatorService;
import io.cristaling.iss.reddrop.web.requests.LoginRequest;
import io.cristaling.iss.reddrop.web.responses.LoginResponse;
import io.cristaling.iss.reddrop.web.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/donator")
public class DonatorController {

	DonatorService donatorService;

	@Autowired
	public DonatorController(DonatorService donatorService) {
		this.donatorService = donatorService;
	}

	@RequestMapping("/login")
	public LoginResponse loginDonator(@RequestBody LoginRequest loginRequest) {
		UUID token = donatorService.tryToLogin(loginRequest.getCnp(), loginRequest.getPassword());
		return LoginUtils.generateLoginResponse(token);
	}

	@RequestMapping("/register")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void registerDonator(@RequestBody Donator donator){
		if (donator.getUuid() == null) {
			donator.setUuid(UUID.randomUUID());
		}
		donatorService.registerDonator(donator);
	}

	@RequestMapping("/getnextvisit")
	public Date getLastVisit(String token){
		return donatorService.getNextAlvailableDate(UUID.fromString(token));
	}

}
