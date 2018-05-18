package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.DonationVisit;
import io.cristaling.iss.reddrop.services.DonationVisitService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/donationvisit")
public class DonationVisitController {

    DonationVisitService donationVisitService;
    PermissionsService permissionsService;

    @Autowired
    public DonationVisitController(PermissionsService permissionsService,DonationVisitService donationVisitService) {
        this.permissionsService=permissionsService;
        this.donationVisitService = donationVisitService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addVisit(String token,@RequestBody DonationVisit donationVisit){
        if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
            return;
        }
        donationVisitService.addDonationVisit(donationVisit);
    }
}
