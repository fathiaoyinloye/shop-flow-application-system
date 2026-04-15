package com.shopdlow.notification_service.service.implementation;

import com.shopdlow.notification_service.Exceptions.EmailNotificationServiceFailedException;
import com.shopdlow.notification_service.Exceptions.NotificationSendingFailException;
import com.shopdlow.notification_service.dtos.requests.SendMailRequest;
import com.shopdlow.notification_service.dtos.requests.brevo.EmailRequest;
import com.shopdlow.notification_service.dtos.requests.brevo.Recipient;
import com.shopdlow.notification_service.dtos.requests.brevo.Sender;
import com.shopdlow.notification_service.dtos.responses.SendMailResponse;
import com.shopdlow.notification_service.dtos.responses.brevo.EmailResponse;
import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import com.shopdlow.notification_service.dtos.responses.NotificationResponse;
import com.shopdlow.notification_service.model.NotificationLog;
import com.shopdlow.notification_service.repository.NotificationRepositoryLog;
import com.shopdlow.notification_service.service.interfaces.NotificationService;
import com.shopdlow.notification_service.service.mail.MailService;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.shopdlow.notification_service.model.Channel.EMAIL;

public class NotificationServiceImpl implements NotificationService {

    private final MailService mailService;
    private final NotificationRepositoryLog notificationLogRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    public NotificationServiceImpl(MailService mailService, NotificationRepositoryLog notificationLogRepository) {
        this.mailService = mailService;
        this.notificationLogRepository = notificationLogRepository;
    }

    @Override
    public NotificationResponse send(NotificationRequest notificationRequest) {
       switch(notificationRequest.getChannel()){
           case EMAIL -> {
               return sendNotification(mailService.send(
                       modelMapper.map(notificationRequest, SendMailRequest.class)),
                       notificationRequest);
           }
           default -> throw new NotificationSendingFailException(
                   "Notification sending via " + notificationRequest.getChannel() + " is currently unavailable"
           );
       }

    }

    private NotificationResponse sendNotification(SendMailResponse mailResponse, NotificationRequest notificationRequest){
        NotificationLog notification = modelMapper.map(notificationRequest, NotificationLog.class);
        notificationLogRepository.save(notification);
        return modelMapper.map(mailResponse, NotificationResponse.class);
    }

}
