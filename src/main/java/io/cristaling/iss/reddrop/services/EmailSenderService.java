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

    public void sendVerifyEmailToDonator(UUID uuid) {
	    Donator donator = donatorRepository.getOne(uuid);
	    if (donator.getVerified() == null) {
		    return;
	    }
		sendEmailToDonator(donator.getEmail(),
				"Confirm Registration Account on ISS_RedDrop",
				"Please follow this link to register your account and receive notifications when there's a desire for your blood type: http://cristaling.tk/api/emails/setconfirmed?cookie=" + donator.getVerified());
    }

	public void askDonatorToDonate(UUID uuid) {
		Donator donator = donatorRepository.getOne(uuid);
		if (donator.getVerified() == null) {
			return;
		}
		sendEmailToDonator(donator.getEmail(),
				"Someone could make use of your blood!",
				"We noticed you haven't donated in a while!\n" +
						"Someone could make great use of your blood right now!" +
						"Schedule a donation visit right now at: http://cristaling.tk/");
	}

    public void sendEmailToDonator(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        new Thread(() -> {
            emailSender.send(message);
        }).start();
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
