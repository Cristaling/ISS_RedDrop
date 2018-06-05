package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.Donator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonatorRepository extends JpaRepository<Donator,UUID> {

	Donator getDonatorByCnp(String cnp);
	Donator getDonatorByVerified(UUID uuid);

	List<Donator> getDonatorsByBloodType(UUID bloodType);

}
