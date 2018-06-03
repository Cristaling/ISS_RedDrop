package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.services.DoctorService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.enums.Permission;
import io.cristaling.iss.reddrop.web.requests.LoginRequest;
import io.cristaling.iss.reddrop.web.requests.PasswordChangeRequest;
import io.cristaling.iss.reddrop.web.responses.DonationVisitMarkResponse;
import io.cristaling.iss.reddrop.web.responses.LoginResponse;
import io.cristaling.iss.reddrop.web.utils.LoginUtils;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	DoctorService doctorService;
	PermissionsService permissionsService;

	@Autowired
	public DoctorController(PermissionsService permissionsService, DoctorService doctorService) {
		this.permissionsService = permissionsService;
		this.doctorService = doctorService;
	}

	@RequestMapping("/login")
	public LoginResponse loginDoctor(@RequestBody LoginRequest loginRequest) {
		UUID token = doctorService.tryToLogin(loginRequest.getCnp(), loginRequest.getPassword());
		return LoginUtils.generateLoginResponse(token);
	}

	@RequestMapping("/getbyhospital")
	public List<Doctor> getDoctorsByHospital(String token, String uuid) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return null;
		}
		UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
		if (actualUuid == null) {
			return null;
		}
		return doctorService.getDoctorsByHospital(actualUuid);
	}

	@RequestMapping("/add")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addDoctor(String token, @RequestBody Doctor doctor) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return;
		}
		doctorService.registerDoctor(doctor);
	}

	@RequestMapping("/update")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateDoctor(String token,@RequestBody Doctor doctor){
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return;
		}
		doctorService.updateDoctor(doctor);
	}

	@RequestMapping("/delete")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteDoctor(String token, String uuid) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return;
		}
		UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
		if (actualUuid == null) {
			return;
		}
		doctorService.deleteDoctor(actualUuid);
	}

	@RequestMapping("/checkfirst")
	public DonationVisitMarkResponse checkFirstLoginn(String token){
		DonationVisitMarkResponse response=new DonationVisitMarkResponse();
		response.setSuccessful(false);
		UUID actualUuid = UUIDUtils.getUUIDFromString(token);
		if (actualUuid == null) {
			response.setSuccessful(false);
		}
		response.setSuccessful(doctorService.checkFirstLogin(actualUuid));
		return response;
	}

	@RequestMapping("/changepassword")
	public DonationVisitMarkResponse changePasswordFist(String token, @RequestBody PasswordChangeRequest passwordChangeRequest){
		DonationVisitMarkResponse response=new DonationVisitMarkResponse();

		if (!permissionsService.hasPermission(token, Permission.DOCTOR)) {
			response.setSuccessful(false);
		}
		UUID actualUuid = UUIDUtils.getUUIDFromString(token);
		if (actualUuid == null) {
			response.setSuccessful(false);
		}
		if(passwordChangeRequest.getPassword1().equals(passwordChangeRequest.getPassword2())){
			Doctor doctor=doctorService.getByUUID(actualUuid);
			doctor.setPassword(passwordChangeRequest.getPassword1());
			doctor.setFirstLogin(false);
			doctorService.registerDoctor(doctor);
			response.setSuccessful(true);
		}
		return response;
	}

}
