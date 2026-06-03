package com.shopdlow.notification_service.service.implementation;

import com.shopdlow.notification_service.exceptions.NotificationSendingFailException;
import com.shopdlow.notification_service.dtos.requests.SendMailRequest;
import com.shopdlow.notification_service.dtos.responses.SendMailResponse;
import com.shopdlow.notification_service.dtos.requests.NotificationRequest;
import com.shopdlow.notification_service.dtos.responses.NotificationResponse;
import com.shopdlow.notification_service.model.NotificationLog;
import com.shopdlow.notification_service.repository.NotificationRepositoryLog;
import com.shopdlow.notification_service.service.interfaces.NotificationService;
import com.shopdlow.notification_service.service.mail.MailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final MailService mailService;
    private final NotificationRepositoryLog notificationLogRepository;
    private  final ModelMapper modelMapper;

    public NotificationServiceImpl(MailService mailService, NotificationRepositoryLog notificationLogRepository, ModelMapper modelMapper) {
        this.mailService = mailService;
        this.notificationLogRepository = notificationLogRepository;
        this.modelMapper = modelMapper;
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
