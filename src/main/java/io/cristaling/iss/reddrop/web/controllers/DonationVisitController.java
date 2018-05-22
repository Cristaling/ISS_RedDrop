package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.services.DonationVisitService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/donationvisit")
public class DonationVisitController {

	DonationVisitService donationVisitService;
	PermissionsService permissionsService;

	@Autowired
	public DonationVisitController(PermissionsService permissionsService, DonationVisitService donationVisitService) {
		this.permissionsService = permissionsService;
		this.donationVisitService = donationVisitService;
	}

	@RequestMapping("/add")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addVisit(String token, @RequestBody DonationVisit donationVisit) {
		if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
			return;
		}
		donationVisitService.addDonationVisit(donationVisit);
	}

	@RequestMapping("/getall")
	public List<DonationVisit> getAllDonationVisits(String token) {
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return null;
		}
		return donationVisitService.getVisitsSorted();
	}

	@RequestMapping("/getvisitedvisits")
	public List<DonationVisit> getVisitedVisits(String token,String uuid){
		if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
			return null;
		}
		UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
		if (actualUuid == null) {
			return null;
		}
		return donationVisitService.getVisitsVisited(actualUuid);
	}
}
