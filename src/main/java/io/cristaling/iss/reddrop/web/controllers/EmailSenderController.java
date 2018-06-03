package io.cristaling.iss.reddrop.web.controllers;


import io.cristaling.iss.reddrop.services.EmailSenderService;
import io.cristaling.iss.reddrop.services.PermissionsService;
import io.cristaling.iss.reddrop.utils.enums.Permission;
import io.cristaling.iss.reddrop.web.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RequestMapping("/api/emails")
@RestController
public class EmailSenderController {

    EmailSenderService emailSenderService;
    PermissionsService permissionsService;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService,PermissionsService permissionsService) {
        this.emailSenderService = emailSenderService;
        this.permissionsService=permissionsService;
    }

    @RequestMapping("/send")
    public void sendMail(String token){
        if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
            return;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(token);
        if (actualUuid == null) {
            return;
        }
        emailSenderService.sendEmailToDonator(actualUuid);
    }

    @RequestMapping("/getconfirmed")
    public boolean verifyConfirmation(String token,String uuid){
        if (!permissionsService.hasPermission(token, Permission.DONATOR)) {
            return false;
        }
        UUID actualUuid = UUIDUtils.getUUIDFromString(uuid);
        if (actualUuid == null) {
            return false;
        }
        return emailSenderService.isDonatorVerified(actualUuid);
    }

    @RequestMapping("/setconfirmed")
    public void setConfirmation(String cookie){
        UUID actualUuidCookie = UUIDUtils.getUUIDFromString(cookie);
        if (actualUuidCookie == null) {
            return;
        }
        emailSenderService.setConfirmation(actualUuidCookie);
    }
}
