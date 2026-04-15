package com.shopdlow.notification_service.service.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    void testCanSendEmailViaGmail(){
        String subject = "Test Subject";
        String body = "Test Subject";
        String recipient = "fathiaoyinloye21@gmail.com";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(body);
        mailSender.send(simpleMailMessage);


    }

}
