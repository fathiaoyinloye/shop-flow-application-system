package com.shopdlow.notification_service.service.mail;

import com.shopdlow.notification_service.dtos.requests.SendMailRequest;
import com.shopdlow.notification_service.dtos.responses.SendMailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class GMailService implements MailService{
    private final JavaMailSender javaMailSender;
    @Override
    public SendMailResponse send(SendMailRequest request) {
        String subject = request.getSubject();
        String body = request.getBody();
        String recipent = request.getRecipient();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipent);
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);

        SendMailResponse sendMailResponse = new SendMailResponse();
        sendMailResponse.setChannel(request.getChannel().toString());
        sendMailResponse.setRecipient(recipent);
        sendMailResponse.setStatus("SENT");
        return sendMailResponse;
    }
}
