package io.cristaling.iss.reddrop.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

	private String adminCNP = "1971211055088";
	private String adminPassword = "1971211055088";
	private String adminUUID = "fdeeffa0-f714-4653-a9ed-0763d81249cb";

	public UUID tryToLogin(String cnp, String password) {

		if (cnp == null || password == null) {
			return null;
		}

		if (cnp.equalsIgnoreCase(adminCNP) && password.equalsIgnoreCase(adminPassword)) {
			return UUID.fromString(adminUUID);
		}

		return null;
	}

}
