package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.services.DonatorService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.enums.Permission;
import io.cristaling.iss.reddrop.web.requests.LoginRequest;
import io.cristaling.iss.reddrop.web.responses.LoginResponse;
import io.cristaling.iss.reddrop.web.utils.LoginUtils;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
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
	PermissionsService permissionsService;

	@Autowired
	public DonatorController(DonatorService donatorService, PermissionsService permissionsService) {
		this.donatorService = donatorService;
		this.permissionsService=permissionsService;
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
		if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
			return null;
		}
		UUID actualUuid = UUIDUtils.getUUIDFromString(token);
		if (actualUuid == null) {
			return null;
		}
		return donatorService.getNextAvailableDate(actualUuid);
	}

}
