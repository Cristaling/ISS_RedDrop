package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.services.BloodBagTypeService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.core.BloodBagType;
import io.cristaling.iss.reddrop.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/bloodbagtype")
public class BloodBagTypeController {

    BloodBagTypeService bagTypeService;
    PermissionsService permissionsService;

    @Autowired
    public BloodBagTypeController(BloodBagTypeService bagTypeService, PermissionsService permissionsService) {
        this.bagTypeService = bagTypeService;
        this.permissionsService = permissionsService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addBloodType(String token,@RequestBody BloodBagType bloodBagType){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bagTypeService.addBloodBagType(bloodBagType);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBloodType(String token,UUID uuid){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bagTypeService.deleteBloodBagType(uuid);
    }

    @RequestMapping("/getall")
    public List<BloodBagType> getAll(String token){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return new ArrayList<>();
        }
        return bagTypeService.getAll();
    }
}
