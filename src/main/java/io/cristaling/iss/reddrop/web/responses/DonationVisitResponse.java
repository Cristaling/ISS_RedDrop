package io.cristaling.iss.reddrop.web.responses;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;

public class DonationVisitResponse {

	@JsonUnwrapped
	DonationVisit donationVisit;

	BloodBagStatus bloodBagStatus;

	public DonationVisitResponse() {

	}

	public DonationVisitResponse(DonationVisit donationVisit, BloodBagStatus bloodBagStatus) {
		this.donationVisit = donationVisit;
		this.bloodBagStatus = bloodBagStatus;
	}

	public DonationVisit getDonationVisit() {
		return donationVisit;
	}

	public void setDonationVisit(DonationVisit donationVisit) {
		this.donationVisit = donationVisit;
	}

	public BloodBagStatus getBloodBagStatus() {
		return bloodBagStatus;
	}

	public void setBloodBagStatus(BloodBagStatus bloodBagStatus) {
		this.bloodBagStatus = bloodBagStatus;
	}
}
