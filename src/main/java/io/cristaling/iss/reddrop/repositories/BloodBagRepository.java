package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.utils.BloodBagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BloodBagRepository extends JpaRepository<BloodBag,UUID>{

	List<BloodBag> getBloodBagsByBloodBagTypeAndBloodType(BloodBagType bloodBagType, UUID bloodType);

}
