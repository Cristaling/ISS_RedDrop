package io.cristaling.iss.reddrop.services;

import io.cristaling.iss.reddrop.core.Donator;
import io.cristaling.iss.reddrop.repositories.DonatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailSenderService {

    DonatorRepository donatorRepository;
    JavaMailSender emailSender;

    @Autowired
    public EmailSenderService(DonatorRepository donatorRepository, JavaMailSender emailSender) {
        this.donatorRepository = donatorRepository;
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendEmailToDonator(UUID uuid) {
        Donator donator = donatorRepository.getOne(uuid);
        if (donator.getVerified() == null) {
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(donator.getEmail());
        message.setSubject("Confirm Registration Account on ISS_RedDrop");
        message.setText("Please follow this link to register your account and receive notifications when there's a desire for your blood type: http://127.0.0.1/api/emails/setconfirmed?cookie=" + donator.getVerified());
        emailSender.send(message);
    }

    public boolean isDonatorVerified(UUID uuid) {
        Donator donator = donatorRepository.getOne(uuid);
        return donator.getVerified() == null;
    }

    public void setConfirmation(UUID verifyUUID) {

        Donator donator = donatorRepository.getDonatorByVerified(verifyUUID);
        if (donator.getVerified().equals(verifyUUID)) {
            donator.setVerified(null);
        }
        donatorRepository.save(donator);
    }

}
