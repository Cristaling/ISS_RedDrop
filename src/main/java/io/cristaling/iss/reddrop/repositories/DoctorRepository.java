package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.Doctor;
import io.cristaling.iss.reddrop.core.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,UUID>{

	Doctor getDoctorByCnp(String cnp);
	List<Doctor> getDoctorsByHospital(Hospital hospital);

}
