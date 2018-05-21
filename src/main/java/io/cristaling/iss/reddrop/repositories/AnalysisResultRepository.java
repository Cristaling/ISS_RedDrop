package io.cristaling.iss.reddrop.repositories;

import io.cristaling.iss.reddrop.core.AnalysisResult;
import io.cristaling.iss.reddrop.core.DonationVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult,UUID> {

	AnalysisResult getAnalysisResultByDonationVisit(UUID uuid);
	List<AnalysisResult> getAnalysisResultsByDonationVisitIn(List<DonationVisit> donationVisitList);

}
