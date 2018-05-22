package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.utils.BloodBagType;
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

    @RequestMapping("/getbloodtypes")
    public List<BloodType> getBloodTypes(){
        return Arrays.asList(BloodType.values());
    }

    @RequestMapping("/getbagtypes")
    public List<BloodBagType> getBloodBagTypes(){
        return Arrays.asList(BloodBagType.values());
    }
}
