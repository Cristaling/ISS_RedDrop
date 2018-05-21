package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.utils.BloodType;
import io.cristaling.iss.reddrop.utils.Diseases;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/utils")
public class UtilsController {


    @RequestMapping("/getdiseases")
    public Diseases[] getDiseases(){
        return Diseases.values();
    }

    @RequestMapping("/getbloodtypes")
    public List<BloodType> getBloodTypes(){
        return Arrays.asList(BloodType.values());
    }
}
