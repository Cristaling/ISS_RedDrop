package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.services.DonationVisitService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.enums.Permission;
import io.cristaling.iss.reddrop.web.responses.DonationVisitMarkResponse;
import io.cristaling.iss.reddrop.web.responses.DonationVisitResponse;
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

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteVisit(String token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
            return;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return;
        }
        donationVisitService.deleteDonationVisitById(actualUuid);
    }

    @RequestMapping("/getall")
    public List<DonationVisit> getAllDonationVisits(String token) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        return donationVisitService.getVisitsSorted();
    }

    @RequestMapping("/getvisitedvisits")
    public List<DonationVisitResponse> getVisitedVisits(String token, String uuid) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return null;
        }
        return donationVisitService.getVisitsWithBagStatusByDonator(actualUuid);
    }

    @RequestMapping("/getunvisitedvisits")
    public List<DonationVisit> getUnvisitedVisits(String token) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        return donationVisitService.getVisitsNotDone();
    }

    @RequestMapping("/markdone")
    public DonationVisitMarkResponse markDonationVisit(String token, String donationVisitUUID, String bloodTypeUUID) {
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return null;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(donationVisitUUID);
        if (actualUuid == null) {
            return null;
        }
        DonationVisitMarkResponse response = new DonationVisitMarkResponse();
        response.setSuccessful(donationVisitService.markDonationVisitDone(actualUuid, bloodTypeUUID));
        return response;
    }
}
