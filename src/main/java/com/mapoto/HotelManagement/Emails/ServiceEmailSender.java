package com.mapoto.HotelManagement.Emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmailSender {
    @Autowired
    JavaMailSender javaMailSender;

    public String sendEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("");
        simpleMailMessage.setTo();
        simpleMailMessage.setSubject("");
        simpleMailMessage.setText("");
        javaMailSender.send(simpleMailMessage);
        return  "Email send succsesffuly";
    }
}
