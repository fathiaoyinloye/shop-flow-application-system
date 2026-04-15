package com.shopdlow.notification_service.service.mail;

import com.shopdlow.notification_service.Exceptions.EmailNotificationServiceFailedException;
import com.shopdlow.notification_service.dtos.requests.SendMailRequest;
import com.shopdlow.notification_service.dtos.requests.brevo.EmailRequest;
import com.shopdlow.notification_service.dtos.requests.brevo.Recipient;
import com.shopdlow.notification_service.dtos.requests.brevo.Sender;
import com.shopdlow.notification_service.dtos.responses.SendMailResponse;
import com.shopdlow.notification_service.dtos.responses.brevo.EmailResponse;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.UUID;

public class BrevoMailService implements MailService{
    @Override
    public SendMailResponse send(SendMailRequest sendMailRequest) {

        EmailRequest emailRequest = buildMailRequestFrom(sendMailRequest);
        EmailResponse emailResponse = dispatch(emailRequest);
        if (emailResponse.getMessageId() != null) {
            SendMailResponse sendMailResponse = new SendMailResponse();
            sendMailResponse.setChannel(sendMailRequest.getChannel().toString());
            sendMailResponse.setChannel(sendMailRequest.getChannel().toString());
            sendMailResponse.setEventId(UUID.randomUUID().toString());
            sendMailResponse.setStatus("success");
            return sendMailResponse;
        }
        throw new EmailNotificationServiceFailedException(
                "Failed to send email to " + sendMailRequest.getRecipient()
        );
    }

    private static EmailRequest buildMailRequestFrom(SendMailRequest sendMailRequest){
        Sender sender = new Sender();
        sender.setEmail("shopflow@gmail.com");
        sender.setName("ShopFlow");

        Recipient recipient = new Recipient();
        recipient.setName("order service");
        recipient.setEmail(sendMailRequest.getRecipient());

        EmailRequest request = new EmailRequest();
        request.setHtmlContent(sendMailRequest.getBody());
        request.setRecipients(List.of(recipient));
        request.setSubject(sendMailRequest.getSubject());
        request.setSender(sender);
        return request;
    }
    private EmailResponse dispatch(EmailRequest emailRequest){
        String apiKey = "";
        String url = "";

        URI uri = URI.create(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);
        RequestEntity<EmailRequest> httpRequest = new RequestEntity<>(emailRequest, headers, HttpMethod.POST, uri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmailResponse> emailResponseEntity = restTemplate.exchange(httpRequest,EmailResponse.class);
        restTemplate.postForEntity(uri, httpRequest, EmailRequest.class);
        return emailResponseEntity.getBody();
    }
}
