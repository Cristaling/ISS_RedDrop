package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.DoctorRepository;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionsService {

	DonatorRepository donatorRepository;
	DoctorRepository doctorRepository;

}
