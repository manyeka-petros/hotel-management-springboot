package com.mapoto.HotelManagement.Emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControlEmailSend {

    @Autowired
    private ServiceEmailSender serviceEmailSender;
        @GetMapping
    private String sendEmail(){
        return serviceEmailSender.sendEmail();
    }
}
