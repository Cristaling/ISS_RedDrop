package io.cristaling.iss.reddrop.web.controllers;

import io.cristaling.iss.reddrop.utils.enums.Importance;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api/utils")
@RestController
public class UtilsController {

    @RequestMapping("/getimportances")
    public Importance[] getImportances(){
        return Importance.values();
    }

}
