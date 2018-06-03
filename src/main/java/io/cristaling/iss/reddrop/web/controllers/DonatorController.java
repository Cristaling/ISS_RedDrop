package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.BloodTypeRepository;
import io.cristaling.iss.reddrop.services.BloodTypeService;
import io.cristaling.iss.reddrop.services.DonatorService;
import io.cristaling.iss.reddrop.services.EmailSenderService;
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
import java.util.List;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/api/donator")
public class DonatorController {

	DonatorService donatorService;
	PermissionsService permissionsService;
	EmailSenderService emailSenderService;
	BloodTypeService bloodTypeService;

	@Autowired
	public DonatorController(DonatorService donatorService, PermissionsService permissionsService,EmailSenderService emailSenderService,BloodTypeService bloodTypeService) {
		this.donatorService = donatorService;
		this.permissionsService=permissionsService;
		this.emailSenderService=emailSenderService;
		this.bloodTypeService=bloodTypeService;
	}

	@RequestMapping("/login")
	public LoginResponse loginDonator(@RequestBody LoginRequest loginRequest) {
		UUID token = donatorService.tryToLogin(loginRequest.getCnp(), loginRequest.getPassword());
		LoginResponse response= LoginUtils.generateLoginResponse(token);

		Donator donator=donatorService.getDonatorById(token);
		if(donator.getVerified()!=null){
			response.setSuccesful(false);
		}

		return response;
	}

	@RequestMapping("/register")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void registerDonator(@RequestBody Donator donator){
		if (donator.getUuid() == null) {
			donator.setUuid(UUID.randomUUID());
			donator.setVerified(UUID.randomUUID());
		}
		donatorService.registerDonator(donator);

		emailSenderService.sendEmailToDonator(donator.getUuid());
	}

	@RequestMapping("/getall")
	public List<Donator> getAllHospitals(String token) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return null;
		}
		return donatorService.getAllDonators();
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

	@RequestMapping("/delete")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteVisit(String token, String uuid) {
		if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
			return;
		}
		UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
		if (actualUuid == null) {
			return;
		}
		donatorService.deleteDonator(actualUuid);
	}

}
