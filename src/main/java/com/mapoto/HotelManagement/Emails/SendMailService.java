package com.mapoto.HotelManagement.Emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SendMailService  implements SendEmai{
    private final JavaMailSender javaMailSender;
    @Override
    @Async
    public void sendmail(String to, String email) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setText(email,true);
            messageHelper.setTo(to);
            messageHelper.setSubject("confirm your email please");
            messageHelper.setFrom("bsc-com-36-19@unima.ac.mw");
            javaMailSender.send(mimeMessage);

        }catch (MessagingException e){
            throw new IllegalStateException("fail to send message");
        }

    }
}
