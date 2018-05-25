package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.core.BloodType;
import io.cristaling.iss.reddrop.services.BloodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/bloodtype")
public class BloodTypeController {

    BloodTypeService bloodTypeService;

    @Autowired
    public BloodTypeController(BloodTypeService bloodTypeService) {
        this.bloodTypeService = bloodTypeService;
    }

    @RequestMapping("/add")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addBloodType(@RequestBody BloodType bloodType){
        bloodTypeService.addBloodType(bloodType);
    }

    @RequestMapping("/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBloodType(UUID uuid){
        bloodTypeService.deleteBloodType(uuid);
    }

    @RequestMapping("/getall")
    public List<BloodType> getAll(){
        return bloodTypeService.getAll();
    }

}
