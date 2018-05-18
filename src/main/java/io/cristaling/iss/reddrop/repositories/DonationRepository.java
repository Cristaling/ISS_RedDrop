package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DonationRepository extends JpaRepository<Donation,UUID> {

    List<Donation> getDonationsByDonator(UUID uuid);
}
