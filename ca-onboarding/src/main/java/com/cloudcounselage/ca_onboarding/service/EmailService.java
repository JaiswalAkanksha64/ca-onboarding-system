package com.cloudcounselage.ca_onboarding.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String toEmail,
                                 String firstName,
                                 String utmLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to IAC Community Ambassador Program!");
        message.setText(
                "Dear " + firstName + ",\n\n" +
                        "Welcome to the IAC Community Ambassador Program!\n\n" +
                        "Your unique UTM link is:\n" + utmLink + "\n\n" +
                        "Please use this link to track your onboarding efforts.\n\n" +
                        "Best regards,\n" +
                        "IAC Team"
        );
        mailSender.send(message);
    }
}
