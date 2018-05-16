package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Hospital;
import io.cristaling.iss.reddrop.repositories.HospitalRepository;
import io.cristaling.iss.reddrop.services.HospitalService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

	HospitalService hospitalService;
	PermissionsService permissionsService;

	@Autowired
	public HospitalController(HospitalService hospitalService, PermissionsService permissionsService) {
		this.hospitalService = hospitalService;
		this.permissionsService = permissionsService;
	}

	@RequestMapping("/getall")
	public List<Hospital> getAllHospitals(UUID token) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return null;
		}
		return hospitalService.getAllHospitals();
	}

	@RequestMapping("/add")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addHospital(UUID token, @RequestBody Hospital hospital) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return;
		}
		hospitalService.registerHospital(hospital);
	}

	@RequestMapping("/delete")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteHospital(UUID token, UUID uuid) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return;
		}
		hospitalService.deleteHospital(uuid);
	}

}
