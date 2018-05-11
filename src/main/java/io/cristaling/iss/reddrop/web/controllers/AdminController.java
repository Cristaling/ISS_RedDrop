package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.services.AdminService;
import io.cristaling.iss.reddrop.web.requests.LoginRequest;
import io.cristaling.iss.reddrop.web.responses.LoginResponse;
import io.cristaling.iss.reddrop.web.utils.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping("/login")
	public LoginResponse loginAdmin(@RequestBody LoginRequest loginRequest) {
		UUID token = adminService.tryToLogin(loginRequest.getCnp(), loginRequest.getPassword());
		return LoginUtils.generateLoginResponse(token);
	}

}
