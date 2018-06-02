package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.DonationVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DonationVisitRepository extends JpaRepository<DonationVisit,UUID>{

	List<DonationVisit> getDonationVisitsByDonatorAndDone(UUID uuid);
	List<DonationVisit> getDonationVisitsByDonatorAndDoneFalse(UUID uuid);

	List<DonationVisit> getDonationVisitsByDonatorOrderByDate(UUID uuid);
	List<DonationVisit> getDonationVisitsByDonatorOrderByDateAndDoneFalse(UUID uuid);

}
