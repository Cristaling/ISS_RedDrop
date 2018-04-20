package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BloodRequestRepository extends JpaRepository<BloodRequest,UUID>{

}
