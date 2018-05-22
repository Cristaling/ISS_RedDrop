package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Admin;
import io.cristaling.iss.reddrop.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

	AdminRepository adminRepository;

	@Autowired
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
		Admin admin = new Admin();
		admin.setUuid(UUID.fromString("b816fb68-7ca2-4d16-ab7a-8588827206d9"));
		admin.setCnp("1971211055088");
		admin.setPassword("1971211055088");
		adminRepository.save(admin);
	}

	public UUID tryToLogin(String cnp, String password) {

		if (cnp == null || password == null) {
			return null;
		}

		Admin toLogin = this.adminRepository.getAdminByCnp(cnp);

		if (toLogin == null) {
			return null;
		}

		if (password.equals(toLogin.getPassword())) {
			return toLogin.getUuid();
		}

		return null;
	}

}
