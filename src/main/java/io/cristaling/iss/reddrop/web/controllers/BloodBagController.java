package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.core.BloodBag;
import io.cristaling.iss.reddrop.core.BloodStock;
import io.cristaling.iss.reddrop.services.BloodBagService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.enums.Permission;
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
@RequestMapping("/api/bloodbag")
public class BloodBagController {

    BloodBagService bloodBagService;
    PermissionsService permissionsService;

    @Autowired
    public BloodBagController(BloodBagService bloodBagService, PermissionsService permissionsService) {
        this.bloodBagService = bloodBagService;
        this.permissionsService = permissionsService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addBloodBag(String token, @RequestBody BloodBag bloodBag){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bloodBagService.addBloodBag(bloodBag);
    }

    @RequestMapping("/stock")
    public List<BloodStock> getBloodBagStock(String token){
        if (!permissionsService.hasPermission(token, Permission.DOCTOR)) {
            return null;
        }
        return bloodBagService.getBloodStockAsList();
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBloodBag(String token, String uuid){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return;
        }
        bloodBagService.deleteBloodBagById(actualUuid);
    }

    @RequestMapping("/update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateBloodBag(String token, @RequestBody BloodBag bloodBag){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bloodBagService.updateBloodBag(bloodBag);
    }

}
