package io.cristaling.iss.reddrop.web.responses;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.utils.enums.BloodBagStatus;

public class DonationVisitWithBloodBagResponse {

	@JsonUnwrapped
	BloodBag bloodBag;

	@JsonUnwrapped
	DonationVisit donationVisit;

	public DonationVisitWithBloodBagResponse() {
	}

	public DonationVisitWithBloodBagResponse(DonationVisit donationVisit, BloodBag bloodBag) {
		this.donationVisit = donationVisit;
		this.bloodBag = bloodBag;
	}

	public DonationVisit getDonationVisit() {
		return donationVisit;
	}

	public void setDonationVisit(DonationVisit donationVisit) {
		this.donationVisit = donationVisit;
	}

	public BloodBag getBloodBag() {
		return bloodBag;
	}

	public void setBloodBag(BloodBag bloodBag) {
		this.bloodBag = bloodBag;
	}
}
