package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Admin;
import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.AdminRepository;
import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import io.cristaling.iss.reddrop.utils.enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class PermissionsService {

	DonatorRepository donatorRepository;
	DoctorRepository doctorRepository;
	AdminRepository adminRepository;

	@Autowired
	public PermissionsService(DonatorRepository donatorRepository, DoctorRepository doctorRepository, AdminRepository adminRepository) {
		this.donatorRepository = donatorRepository;
		this.doctorRepository = doctorRepository;
		this.adminRepository = adminRepository;
	}

	public boolean hasPermission(String token, Permission permission) {
		if (token == null) {
			return false;
		}
		try {
			UUID uuid = UUID.fromString(token);
			return hasPermission(uuid, permission);
		} catch (IllegalArgumentException ex) {
			return false;
		}
	}

	public boolean hasPermission(UUID token, Permission permission) {

		try {
			Admin admin = adminRepository.getOne(token);
			return true;
		} catch (EntityNotFoundException ex) {
			if (permission == Permission.ADMIN) {
				return false;
			}
		}

		if (permission == Permission.DOCTOR) {
			try {
				Doctor doctor = doctorRepository.getOne(token);
				return true;
			} catch (EntityNotFoundException ex) {
				return false;
			}
		}

		if (permission == Permission.DONATOR) {
			try {
				Donator donator = donatorRepository.getOne(token);
				return true;
			} catch (EntityNotFoundException ex) {
				return false;
			}
		}

		return false;
	}

}
