package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.utils.Diseases;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/utils")
public class UtilsController {


    @RequestMapping("/getdiseases")
    public Diseases[] getDiseases(){
        return Diseases.values();
    }
}
