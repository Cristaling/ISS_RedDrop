package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.repositories.DonatorUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonatorsService {

	DonatorUsersRepository repo;

	@Autowired
	public DonatorsService(DonatorUsersRepository repo){
		this.repo = repo;
	}

}
