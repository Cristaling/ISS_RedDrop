package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.services.BloodTypeService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/bloodtype")
public class BloodTypeController {

    PermissionsService permissionsService;
    BloodTypeService bloodTypeService;

    @Autowired
    public BloodTypeController(BloodTypeService bloodTypeService,PermissionsService permissionsService) {
        this.bloodTypeService = bloodTypeService;
        this.permissionsService=permissionsService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addBloodType(String token,@RequestBody BloodType bloodType){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bloodType.setUuid(UUID.randomUUID());

        bloodTypeService.addBloodType(bloodType);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBloodType(String token,UUID uuid){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return;
        }
        bloodTypeService.deleteBloodType(uuid);
    }

    @RequestMapping("/getall")
    public List<BloodType> getAll(String token){
        if (!permissionsService.hasPermission(token, Permission.ADMIN)) {
            return new ArrayList<>();
        }
        return bloodTypeService.getAll();
    }

}
